import axios from 'axios';
import { parseCookies } from 'nookies';

const cookies = parseCookies();
const { 'ubs-access_token': accessToken } = cookies;

const nextApi = axios.create({
  baseURL: process.env.NEXT_PUBLIC_API_URL,
  timeout: 1000,
});

if (accessToken){
  nextApi.defaults.headers.common.Authorization = 'Bearer ' + accessToken;
}

if (process.env.NODE_ENV === 'development') {
  // Adiciona um interceptador na requisição
  nextApi.interceptors.request.use(
    function (config) {
      // Faz alguma coisa antes da requisição ser enviada
      return config;
    },
    function (error) {
      // Faz alguma coisa com o erro da requisição
      return Promise.reject(error);
    }
  );

  // Adiciona um interceptador na resposta
  nextApi.interceptors.response.use(
    function (response) {
      // Qualquer código de status que dentro do limite de 2xx faz com que está função seja acionada
      // Faz alguma coisa com os dados de resposta
      return response;
    },
    function (error) {
      // Qualquer código de status que não esteja no limite do código 2xx faz com que está função seja acionada
      // Faz alguma coisa com o erro da resposta
      return Promise.reject(error);
    }
  );
}

export default nextApi;
