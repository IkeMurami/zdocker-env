# Postgres in a container

Build:

```
~ docker compose build
~ docker compose up
```

Usage example:

```
~ pip install psycopg2-binary==2.9.9
```

```python
import psycopg2

conn = psycopg2.connect(**{
            'dbname': 'my_db',
            # 'sslmode': 'verify-full',
            'user': 'my_user',
            'password': 'my_password',
            'host': 'localhost',
            'port': 5432,
            'target_session_attrs': 'read-write'
        })
cur = conn.cursor()
cur.execute("CREATE TABLE test (id serial PRIMARY KEY, num integer, data varchar);")
cur.execute("INSERT INTO test (num, data) VALUES (%s, %s)", (101, "test"))

SELECT_QUERY = """
    SELECT id
    FROM test
    WHERE data=%(some)s;
"""


cur.execute(SELECT_QUERY, {"some": "test' or '1'='1"})
print(cur.fetchall())

cur.execute("SELECT * FROM test;")
print(cur.fetchone())

```