# Build stage
FROM node:18 as build
WORKDIR /app
COPY . .
RUN npm install && npm run build

# Serve statici con nginx
FROM nginx:alpine

# Rimuove la pagina di default
RUN rm -rf /usr/share/nginx/html/*

COPY --from=build /app/dist/LibrarySystem-Frontend /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
