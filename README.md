# kafka-demo
Spring Boot and Kafka Demo Project

## Run the App
### From the Command Line
```shell
docker-compose up -d
mvn spring-boot:run
```

## Kafka Command Line
How to interact with Kafka inside the Docker container:

#### Create a Topic
```shell
docker exec broker \
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic "customer.visit"
```

### Command Line Consumer
```shell
docker exec --interactive --tty broker \
kafka-console-consumer --bootstrap-server broker:9092 \
                       --topic "customer.visit" \
                       --from-beginning
```

### Command Line Producer
```shell
docker exec --interactive --tty broker \
   kafka-console-producer --bootstrap-server broker:9092 \
                          --topic "customer.visit"
```
You will see a prompt ">". Type some words and hit enter.

The Spring Boot terminal shows the logged message.
```
2024-10-15T11:21:45.838-06:00  INFO 11256 --- [kafka-demo] [ntainer#0-0-C-1] c.e.kafka_demo.KafkaDemoApplication      : listens: this is a test, this is only a test
2024-10-15T11:21:55.691-06:00  INFO 11256 --- [kafka-demo] [ntainer#0-0-C-1] c.e.kafka_demo.KafkaDemoApplication      : listens: bob
2024-10-15T11:22:05.463-06:00  INFO 11256 --- [kafka-demo] [ntainer#0-0-C-1] c.e.kafka_demo.KafkaDemoApplication      : listens: happy
2024-10-15T11:22:10.610-06:00  INFO 11256 --- [kafka-demo] [ntainer#0-0-C-1] c.e.kafka_demo.KafkaDemoApplication      : listens: this is cool

```