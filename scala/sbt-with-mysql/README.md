copy config.properties.example to config.properties
sbt liquibase-update
sbt genScalikeProps
sbt "scalikejdbc-gen column_family"
