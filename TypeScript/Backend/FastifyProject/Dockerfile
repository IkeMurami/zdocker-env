FROM node:15.12.0-alpine3.10

WORKDIR /app

COPY ./app/* .

RUN npm i

CMD [ "npm", "run", "start" ]