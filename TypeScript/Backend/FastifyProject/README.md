# Fastify

Fastify Getting Started: https://www.fastify.io/docs/latest/Guides/Getting-Started/

Так же, здесь есть статьи, как его спрятать за Nginx или HAProxy, как использовать fastify в Serverless решениях (Lambda) и тп


# Build and run

```
$ docker build -t ikemurami/fastifyapp -f Dockerfile .
$ docker run --rm -i -t -p 3000:3000 --name docker-fastify-app ikemurami/fastifyapp
```