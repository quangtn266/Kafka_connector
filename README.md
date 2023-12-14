# Kafka_connector
Generate a connector (Source &amp; Sink- Kafka Connector)

## How to run

mvn clean package

mvn assembly:single  # check pom.xml for assembly & jar package

/**
* 1) Export classpath:
*    Export CLASSPATH="$(find target -type f -name '*.jar'| grep '\-package' | tr '\n'':')"
* 2) Build the Dockerfile:
* docker built . -t quangtn/kafka-connect-source-github:1.0
* 3) Run the docker
* docker run -e CLASSPATH=$CLASSPATH --net=host --rm -t -v $(pwd)/offsets:/kafka-connect-source-github/offsets quangtn/kafka-connect-source-github:1.0
**/

