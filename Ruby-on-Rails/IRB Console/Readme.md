# Ruby on Rails :: Interactive Ruby (IRB) Console

IRB + Postgres

Build: `docker-compose build`

UP: `docker-compose up`

Connect to Interactive Ruby: `docker exec -it rails-irb irb`

Example:

```
irb(main):001:0> 1+1
=> 2
irb(main):006:0> require 'active_record'
=> true
```

```ruby
require 'active_record'
require 'pg'

ActiveRecord::Base.establish_connection(
    adapter: 'postgresql',
    database: 'test_db',
    username: 'test_user',
    password: 'test_password',
    host: 'db'
)

# One line:  ActiveRecord::Base.establish_connection(adapter: 'postgresql', database: 'test_db', username: 'test_user', password: 'test_password', host: 'db')

class Order < ActiveRecord::Base
end

test = Order.new # надо как-то создать таблицу в базе, но я не нашел пока как через ruby это сделать. Но в целом, работает))

```