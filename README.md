# CPT Chat App

This repository contains a TCP chat application that implements the CPT Protocol.

## Protocol

You can find more information about the protocol being used you can check the [SPEC.md](./SPEC.md) file.

TL;DR:
The CPT protocol 

## Building the app

The project uses `mvnw` in order to build and package the app.

```
./mvnw dependency:resolve clean compile package
```

## Running the app

The cli is split in two subcommands, `server` and `client`, once you built the app, you can run the cli with the `--help` to get more informations :

```
java -jar target/dai-labo-2-1.0-SNAPSHOT.jar --help
```

### Running the server

```
java -jar target/dai-labo-2-1.0-SNAPSHOT.jar server
```

The server defaults to using the `39168` port, if you want to override this value you can provide the `--port` argument.

### Running the client

To connect the client to the server, you have to provide a username, this username must not be used in another connection to the server.

The hostname is also required, in this example the server is running on the same machine as the client.

You can also specify the port using the `--port` argument.

```
java -jar target/dai-labo-2-1.0-SNAPSHOT.jar client --username=alexandre --host=localhost
```
