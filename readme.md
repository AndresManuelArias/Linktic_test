
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
nvm ls
```

# Requerimientos
tener 
java version 21.0.2-tem
node v24.13.0