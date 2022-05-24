# OracleJDK Build

Собираем контейнер с OracleJDK. Здесь собирается Java 11, для сборки другой версии переставить параметр JDK_VERSION (например, для 1.8 — JDK_VERSION=18).

Примеры докер-контейнеров Oracle можно найти в их репозитории: https://github.com/oracle/docker-images.  
В частности, OracleJDK — https://github.com/oracle/docker-images/blob/main/OracleJava/.

Build & Run:

```
$ docker compose build
$ docker compose up
```