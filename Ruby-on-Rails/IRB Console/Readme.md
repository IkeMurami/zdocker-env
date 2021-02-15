# Ruby on Rails :: Interactive Ruby (IRB) Console

IRB + Postgres

Build: `docker-compose build`

UP: `docker-compose up`

Connect to Interactive Ruby: `docker run --rm -i -t --entrypoint=irb rails-irb`

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
ActiveRecord::Base.establish_connection(adapter: 'postgresql', database: 'test_db', username: 'test_user', password: 'test_password', host: 'localhost')

class Order < ActiveRecord::Base
end

test = Order.new

```