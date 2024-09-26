FROM node:20-alpine3.17

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando os arquivos do projeto para o diretório de trabalho
COPY . .

# Definindo argumentos e variáveis de ambiente
ARG PORT=3000
ARG BACKEND_API_URL
ARG NODE_ENV=production

# Escrevendo as variáveis de ambiente no arquivo .env.local
RUN echo NEXT_PUBLIC_API_URL=http://localhost:$PORT/api >> .env.local \
    && echo BACKEND_API_URL=$BACKEND_API_URL >> .env.local \
    && echo NODE_ENV=$NODE_ENV >> .env.local

# Configurando a timezone do sistema
RUN apk add --no-cache tzdata \
    && cp /usr/share/zoneinfo/America/Boa_Vista /etc/localtime \
    && echo "America/Boa_Vista" > /etc/timezone

# Instalando as dependências do projeto
RUN npm install

# Compilando o projeto
RUN npm run build

# Iniciando o servidor
CMD ["npm", "run", "start"]