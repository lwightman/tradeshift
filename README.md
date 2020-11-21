
## Execution

Running:
```
docker-compose up --force-recreate
```

Stopping:

```
docker-compose down --rmi all --remove-orphans
```

#### Examples of the endpoints
http://localhost:8080/getDescendants?node=1

http://localhost:8080/reparent?nodeId=2&newParentId=3


#### Initial tree
        root
    /    |   \
    a    b    z
    |   / | \
    c   d e f