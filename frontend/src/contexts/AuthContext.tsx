import { ReactNode, createContext, useState } from 'react';
import { useRouter } from 'next/navigation';
import { destroyCookie, setCookie } from 'nookies';
import { AxiosError, AxiosResponse } from 'axios';
import api from '@/services/nextApi';
import { User } from '@/types/auth';

interface Session {
  user: User;
  expires: Date; // This is the expiry of the session, not any of the tokens within the session
}

interface SignInData {
  username: string;
  password: string;
}

interface SignInResponse {
  error: string | undefined;
  status: number;
  ok: boolean;
}

interface AuthorizeResponseDto {
  token: string;
}

interface IAuthContext {
  session: Session | undefined;
  signIn: ({ username, password }: SignInData) => Promise<SignInResponse>;
  signOut: () => Promise<undefined>;
}

interface IAuthContextProviderProps {
  children: ReactNode;
}

export const AuthContext = createContext({} as IAuthContext);

export function AuthContextProvider({ children }: IAuthContextProviderProps) {
  const router = useRouter();
  const [session, setSession] = useState<Session>();

  async function signIn({
    username,
    password,
  }: SignInData): Promise<SignInResponse> {
    try {
      const res = await api.post<
        SignInData,
        AxiosResponse<AuthorizeResponseDto>
      >('/login', {
        login: username,
        senha: password,
      });

      const { token } = res.data;

      api.defaults.headers.common.Authorization = 'Bearer ' + token;

      setCookie(undefined, 'ubs-access_token', token, {
        maxAge: 3600 * 24,
        path: '/',
        secure: true,
      });

      return { error: undefined, status: 200, ok: true };
    } catch (error) {
      if (error instanceof AxiosError) {
        if (error.response) {
          throw new Error(error.response.data.message);
        } else if (error.request) {
          console.log(error.request);
        } else {
          console.log('Error', error.message);
        }
      }
      throw new Error();
    }
  }

  async function signOut(): Promise<undefined> {
    destroyCookie(undefined, 'ubs-access_token', { path: '/' });
    router.refresh();
    return undefined;
  }

  return (
    <AuthContext.Provider value={{ session, signIn, signOut }}>
      {children}
    </AuthContext.Provider>
  );
}
