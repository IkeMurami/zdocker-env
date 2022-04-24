ARG NODE_VERSION=15.12.0-alpine3.10


# Create project
FROM node:${NODE_VERSION} AS creater

WORKDIR /source

RUN npm i express --save

COPY ./app/. .

# TODO: build app here

# Run project
FROM node:${NODE_VERSION} AS starter

WORKDIR /app

COPY --from=creater /source /app

ENTRYPOINT [ "node", "app.js"]