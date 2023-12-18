Welcome to your new Kafka Connect connector!

# Kafka_connector
Generate a connector (Source and Sink Kafka Connector)

## Setup:
1) JDK version 1.8
2) Generate new project with IntelliJ IDEA with Maven Archetype.
3) Configure:

Steps:

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

### Running:

docker run -it --rm -p 2181:2181 -p 3030:3030 -p 8081:8081 -p 8082:8082 -p 8083:8083 -p 9092:9092 -e ADV_HOST=127.0.0.1 -e RUNTEST=0 -v ~/
<Absolute_dir>/target/kafka-connect-source-github-1.1-package/share/java/kafka-connect-source-github:/connectors <Kafka_docker>

Absolute_dir: Desktop/01_work/01_job/Source/kafka/kafka_connector/kafka_connector_prjcustom
Kafka_docker: lensesio/fast-data-dev (https://github.com/lensesio/fast-data-dev)

## Note:
I also added targert folder for easy deploy.

Enjoy it :D !
