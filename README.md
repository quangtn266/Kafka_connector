# Kafka_connector
Generate a connector (Source &amp; Sink- Kafka Connector)

## Setup:
1) JDK version 1.8
2) Generate new project with IntelliJ IDEA with Maven Archetype.
3) Configure:
  1. GroupID= io.confluent.maven
  2. ArtifactID: kafka-connect-quickstart
  3. Version: 0.10.2.1-cp1

## How to run

### Building packages:
1) mvn clean package
2) mvn assembly:single  # check pom.xml for assembly & jar package

### Configuration.
1) Export classpath: Export CLASSPATH="$(find target -type f -name '*.jar'| grep '\-package' | tr '\n'':')"
2) Build the Dockerfile: docker built . -t quangtn/kafka-connect-source-github:1.0
3) Run the docker: docker run -e CLASSPATH=$CLASSPATH --net=host --rm -t -v $(pwd)/offsets:/kafka-connect-source-github/offsets quangtn/kafka-connect-source-github:1.0

