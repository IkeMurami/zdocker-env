# Laravel
[Instruction](https://www.digitalocean.com/community/tutorials/how-to-containerize-a-laravel-application-for-development-with-docker-compose-on-ubuntu-18-04-ru) from Digital Ocean.

```
app PHP7.4-FPM
    composer https://getcomposer.org/ - это для подтягивания зависимостей PHP-проекта
    artisan https://laravel.com/docs/6.x/artisan — это laravel штука
```

Build: `docker-compose build`

Up: `docker-compose up`

Connect to container: `docker exec -it laravel-empty-app /bin/sh`

# Clone Laravel Framework: Laravel <= v8.4.2 debug mode: Remote code execution (CVE-2021-3129)

On docker:
```
$ git clone https://github.com/laravel/laravel.git && cd laravel
$ git checkout e849812
$ composer install
$ composer require facade/ignition==2.5.1
$ cp .env.example .env
$ php artisan key:generate
$ php artisan serve --host 0.0.0.0 --port 8000

```
On host: http://127.0.0.1:8000/

TODO: позже чекнуть пейлоад: 
- https://www.ambionics.io/blog/laravel-debug-rce
- https://github.com/ambionics/laravel-exploits