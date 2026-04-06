
crear un gateway y microservicios


# crear el gateway
```sh
npx -p generator-jhipster jhipster jdl tienda.jdl

cd gateway
./mvnw
npm start

```
# crear el microservicios
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