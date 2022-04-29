# Simple GWT (Google Web Toolkit) App

Build: `docker compose build`

Run: `docker compose up`

... and go to http://127.0.0.1:9091

If we want to get the source code:

```
docker exec -it gwt-service /bin/sh
cp -r /source /out
```

GWT specific requests:

```
POST /gwtwebapp/greet HTTP/1.1
Host: 127.0.0.1:9091
Content-Length: 169
X-GWT-Module-Base: http://127.0.0.1:9091/gwtwebapp/
X-GWT-Permutation: 2080CB5C1AB4B00C3AAA76D229217830
Content-Type: text/x-gwt-rpc; charset=UTF-8
User-Agent: Test
Accept: */*
Origin: http://127.0.0.1:9091
Referer: http://127.0.0.1:9091/
Accept-Encoding: gzip, deflate
Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7
Connection: close

7|0|6|http://127.0.0.1:9091/gwtwebapp/|BD9C9E335ABCED00FB2061A20F65E81D|ike.murami.client.GreetingService|greetServer|java.lang.String/2004016611|GWT User|1|2|3|4|1|5|6|


HTTP/1.1 200 OK
Connection: close
Content-Type: application/json;charset=utf-8
Content-Disposition: attachment
Server: Jetty(9.4.46.v20220331)

//OK[1,["Hello, GWT User!\u003Cbr\u003E\u003Cbr\u003EI am running jetty/9.4.46.v20220331.\u003Cbr\u003E\u003Cbr\u003EIt looks like you are using:\u003Cbr\u003ETest"],0,7]
```