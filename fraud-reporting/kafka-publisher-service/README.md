# Kafka Publisher Service
<hr>
This service acts a source of fraud events which are published to a specific Apache Kafka topic.

## Prerequisites
- Node v10.0+
- Apache Kafka v2.1.1+

## Build
`$ npm install`

## Running
`$ npm run start:dev`

The service interfaces through the CLI with the following keys:
- [Enter] - generates a random payload in the format `{"country": <country-code>, "card": <cc-number>, "company": <company>, "data": <date>}` and publishes it to the `PRODUCER_TOPIC` kafka topic
- Ctrl + C - exits
