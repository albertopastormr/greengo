# GreenGo


## About
GreenGo is an manage car rentals desktop java organizer made for the subject Software Modeling of Software Engineer Bachelor's Degree at Universidad Complutense Madrid.
The documentation has been written in spanish because of a language constraint in the assignment for the subject as some classes at the code.
## How to use it
GreenGo uses [mariadb 10.3](https://downloads.mariadb.org/mariadb/10.3.10/), [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [JUnit 5](https://junit.org/junit5/); these tools are necessary to run the application.
### Running mariadb
Once you've installed [mariadb 10.3](https://downloads.mariadb.org/mariadb/10.3.10/), launch MySQL client, create a user with _manager_ as username and _manager-if_ as password.
After that, run the following command:

`CREATE DATABASE greengo`

GreenGo is the name of the database.

Create a new user:

`CREATE USER 'manager'@'localhost' IDENTIFIED BY 'manager_if';`

Set privileges for the user created:

`GRANT ALL PRIVILEGES ON * . * TO 'manager'@'localhost';`

`FLUSH PRIVILEGES;`

Go to /bin directory:

`{INSTALLATION PATH}/bin`

If you are in MariaDb terminal: (keys) Ctrl + C

`mysql -u manager -p greengo < {PATH}/greengo-create-db.sql`

Insert the password `manager_if`.

this imports an existing database with a valid structure for greengo.

## Authors
The development team consists in a class-group of six students:
  - [Alberto Pastor Moreno](https://github.com/albertopastormr/) @albertopastormr
  - [Iván Fernandez Mena](https://github.com/ivanfermena) @ivanf3rmena
  - [Manuel Monforte](https://github.com/manumonforte) @manumonforte
  - [Pablo Agudo](https://github.com/pibloo94) @pibloo94
  - [Alejandro Cabezas](https://github.com/AlexCabezas2018) @AlexCabezas2018
  - [Gerardo Parra](https://github.com/gprossignoli) @gprossignoli
  
 
