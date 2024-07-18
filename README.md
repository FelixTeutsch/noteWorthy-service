# noteWorthy-service

Ths is the backend service for the RIS Notes App.

In order for this backend to work, you need a database, and you need to configure that database in
the `application.properties` file.

## Database Configuration

The database used for this development was a `MySQL` database. I used `xampp` as a simple database server. In order to
create the database, you can use the following SQL script:

```mysql
CREATE
    DATABASE IF NOT EXISTS noteworthy;
USE
    noteworthy;
CREATE TABLE notes
(
    id       CHAR(36) PRIMARY KEY,
    title    VARCHAR(100)  NOT NULL DEFAULT 'untitled',
    content  VARCHAR(1000) NOT NULL,
    created  TIMESTAMP     NOT NULL,
    updated  TIMESTAMP     NOT NULL,
    archived BOOLEAN       NOT NULL DEFAULT FALSE
);
```

Once the database has been created and is up and running, you can configure the service to use it.
The database configuration is done in the `application.properties` file. The following properties need to be set:

```properties
spring.datasource.url=<db url>
spring.datasource.driverClassName=<db driver>
spring.jpa.database-platform=org.hibernate.dialect.<db hibernate dialect for entities>
spring.datasource.username=<db username>
spring.datasource.password=<db password>
```

## Running the Application

Once the database is set up and configured, you can run:

```shell
mvn clean install
```

This will build the service and run the (non-existing) tests. If the (non-existing) tests pass, you can run the
service with:

```shell
mvn spring-boot:run
```

If you don't want to execute two commands, you can run:

```shell
mvn clean install spring-boot:run
```

The service will be available at `http://localhost:8090`.

## API

The api swagger can be found at:
[openAPI.json](src%2Fmain%2Fresources%2FopenAPI.json)

It houses the following endpoints:

- `POST /save` - Save a note
- `GET /save{note}` - Save a note using get
- `GET /get{id}` - Get a note by id
- `PUT /update{note}` - Update a note
- `DELETE /delete{id}` - Delete a note by id
- `GET /listCurrent` - Get all notes that are not archived
- `GET /listArchived` - Get all notes that are archived
- `GET /archive{id}` - Archive a note by id
- `GET /unarchive{id}` - Unarchive a note by id
- `GET /search{query, archived}` - Search for a note by id and archived status

