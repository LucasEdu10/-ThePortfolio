# Etapa 1: build da aplicação
FROM node:18 AS builder

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Etapa 2: imagem leve com nginx
FROM nginx:stable-alpine

# Copia os arquivos de build para a pasta pública do Nginx
COPY --from=builder /app/dist /usr/share/nginx/html

# Copia um arquivo customizado de config do nginx (opcional)
# COPY nginx.conf /etc/nginx/nginx.conf

# Expondo a porta padrão do nginx
EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
