Welcome to your new Kafka Connect connector!

# Kafka_connector
Generate a connector (Source Kafka Connector)

## Setup:
1) JDK version 1.8
2) Generate new project with IntelliJ IDEA with Maven Archetype.
3) Configure:
  1. GroupID= io.confluent.maven
  2. ArtifactID: kafka-connect-quickstart
  3. Version: 0.10.2.1-cp1

## How to run

### Building packages:
1)
```
  mvn clean package
```
2)
```
  mvn assembly:single  # check pom.xml for assembly & jar package
```
### Configuration.
1) Export classpath:
```
   Export CLASSPATH="$(find target -type f -name '*.jar'| grep '\-package' | tr '\n'':')"
```
2) Build the Dockerfile:
```
   docker built . -t quangtn/kafka-connect-source-github:1.0
```
3) Run the docker:
```
docker run -e CLASSPATH=$CLASSPATH --net=host --rm -t -v $(pwd)/offsets:/kafka-connect-source-github/offsets quangtn/kafka-connect-source-github:1.0
```

## Note:
For sink connector (custom), I wil update it in another project. Besides, sink connector can be used with Elasticsearch or Database.


## Architecture

Kafka Connector architecture

![kafka_connector_architecture](https://github.com/quangtn266/Kafka_connector/assets/50879191/a2ab7d2c-2045-48df-b116-c8bdb459138d)

Kafka Connect Cluster Distributed Architecture

![distributed_architecture](https://github.com/quangtn266/Kafka_connector/assets/50879191/3d12f32f-ae89-4050-918e-05fc0e833c1f)

Data Pipeline

![data_pipeline_AWS](https://github.com/quangtn266/Kafka_connector/assets/50879191/d305c8ce-9055-4d3a-9652-8f811db2b1cc)



