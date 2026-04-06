
crear un gateway y microservicios
```sh
npx -p generator-jhipster jhipster jdl tienda.jdl
``

# crear el gateway
```sh
cd gateway
./mvnw
npm start

```
# crear los microservicios
```sh
cd inventory
./mvnw

cd products
./mvnw
```

ver la version utilizada 
```sh
sdk current java
```