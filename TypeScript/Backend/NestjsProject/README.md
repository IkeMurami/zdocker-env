# NestJS Example App

Create new app (if you don't have a nest.js app)

```bash
$ mkdir app
$ docker build -t ikemurami/nestjsapp -f Dockerfile-creater .
$ docker run --rm -i -t -v ${PWD}/app:/out --name docker-nestjs-app-creater ikemurami/nestjsapp
```

Start our app (3000 for NestJS App, 9229 for debug)

```bash
$ docker build -t ikemurami/nestjsapp -f Dockerfile-starter .
$ docker run --rm -i -t -p 3000:3000 -p 9229:9229 -v ${PWD}/app:/app --name docker-nestjs-app ikemurami/nestjsapp
```

