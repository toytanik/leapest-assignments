# Fraud Reporting
<hr>

A fraud reporting platform is being developed. The project currently contains the following 2 microservices:
- [PDF Service](./pdf-service) - exposes a RESTful API for rendering PDF documents 
- [Kafka Publisher Service](./kafka-publisher-service) - publishes messages (fraud events) to an Apache Kafka server

The services work independently of each other. What's missing is a third service that integrates the published data from the Kafka Publisher Service with the PDF Service that allows that data to be rendered. 
