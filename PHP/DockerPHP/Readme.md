# Окружение для запуска PHP скриптов

Build: `docker-compose build`
Up: `docker-compose up`

# Подключаемся к контейнеру

```
$ docker exec -it php-empty-app /bin/sh
```

# Внутри контейнера

```
$ composer install
$ composer requere [our package]==[version]
```