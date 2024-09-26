import { useContext, useRef } from 'react';
import { useRouter } from 'next/router';
import Link from 'next/link';
import { useForm } from 'react-hook-form';
import { Image } from 'primereact/image';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { Toast } from 'primereact/toast';

import Head from '@/components/Head';
import { AuthContext } from '@/contexts/AuthContext';

type SignInForm = {
  username: string;
  password: string;
};

export default function Login() {
  const router = useRouter();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<SignInForm>();
  const toast = useRef<Toast>(null);

  const { signIn } = useContext(AuthContext);

  const onSubmit = async (data: SignInForm) => {
    try {
      const res = await signIn(data);

      if (res?.ok) {
        router.push('/');
      }
    } catch (error) {
      if (error instanceof Error) {
        showError(error.message);
      } else {
        console.error(error);
      }
    }
  };

  const showError = (message: string) => {
    toast.current?.show({
      severity: 'error',
      summary: 'Erro',
      detail: message,
      life: 3000,
    });
  };

  return (
    <>
      <Head nomePagina="Login" />
      <div className="container block w-full">
        <header className="header-login w-full flex justify-content-center align-items-center">
          <Image
            src="./images/brasao-ufrr.png"
            alt="logo ufrr"
            width="80px"
            height="80px"
            className="ml-4 absolute left-0 inline-block"
          />
          <div className="text-center text-4xl">Unidade Básica de Saúde</div>
          <div></div>
        </header>
        <main className="w-full">
          <div className="flex justify-content-center">
            <Toast ref={toast} />
            <form onSubmit={handleSubmit(onSubmit)}>
              <section className="flex flex-column align-items-center gap-3 mt-5 p-4 login-box">
                <Image
                  src="./images/brasao-ufrr.png"
                  alt="logo ufrr"
                  width="80px"
                  height="80px"
                  className="block"
                />
                <InputText
                  type="text"
                  placeholder="Nome de Usuário"
                  className="block w-full"
                  style={{ background: '#F7F7F7' }}
                  {...register('username')}
                />
                <InputText
                  type="password"
                  placeholder="Senha"
                  className="block w-full"
                  style={{ background: '#F7F7F7' }}
                  {...register('password')}
                />
                <Button
                  label="Entrar"
                  style={{ background: '#233876', borderRadius: '5%' }}
                />
                <hr className="w-full" />
                <section className="w-full flex flex-column justify-content-start">
                  <Link href={'/recoverpass'}>
                    <div>Esqueci minha senha?</div>
                  </Link>
                  <Link href={'/signin'}>
                    <div>cadastre-se</div>
                  </Link>
                </section>
              </section>
            </form>
          </div>
        </main>
        <footer className="footer-login bottom-0 flex align-items-center absolute w-full">
          <div className="text-white text-center w-full">
            Criado pelo Departamento de Ciência da Computação
          </div>
        </footer>
      </div>
    </>
  );
}
