# Simple Nginx Instance

Build:

- Set the Nginx version to the `docker-compose.yml` file
- Build container

```
$ docker compose build
```

Run:

```
$ docker compose up
```

and go to http://localhost:9015/index.html or:

```
$ curl -kI http://localhost:9015/index.html
HTTP/1.1 200 OK
Server: nginx/1.18.0
Date: Sat, 23 Apr 2022 11:11:38 GMT
Content-Type: text/html
Content-Length: 12
Connection: keep-alive

$ 
```