import type { AppProps } from 'next/app';
import { addLocale } from 'primereact/api';
import { AuthContextProvider } from '@contexts/AuthContext';

// primereact
import 'primereact/resources/themes/lara-light-indigo/theme.css';
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';

// css globais
import '../styles/globals.css';
import '../styles/componentes.css';

import pt from '@config/pt.json';

addLocale('pt', pt);

export default function App({ Component, pageProps }: AppProps) {
  return (
    <AuthContextProvider>
      <Component {...pageProps} />
    </AuthContextProvider>
  );
}
