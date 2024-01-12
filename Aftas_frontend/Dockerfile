FROM node:18.18.2 as build
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build
FROM nginx:stable
COPY --from=build /app/dist/aftas-frontend/ /usr/share/nginx/html 
EXPOSE 80

