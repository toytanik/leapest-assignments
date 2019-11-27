const kafka = require('kafka-node');

const PRODUCER_TOPIC = process.env.PRODUCER_TOPIC || 'fraud-detected';
const KAFKA_HOST = process.env.KAFKA_HOST || 'localhost:9092';

const client = new kafka.KafkaClient({ kafkaHost: KAFKA_HOST });
const producer = new kafka.Producer(client);

const COUNTRIES = ['bg', 'gb', 'de', 'nl', 'in', 'jp', 'dk', 'se'];
const COMPANIES = ['facebook', 'mastercard', 'microsoft', 'sg-group', 'bank', 'softuni'];

const getRandomCountry = () => {
    return COUNTRIES[Math.floor(Math.random() * COUNTRIES.length)];
}

const getRandomCompany = () => {
    return COMPANIES[Math.floor(Math.random() * COMPANIES.length)];
}

const getRandomInt = (min, max) => {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

const getRandomCC = () => {
    const parts = new Array(4).fill(0)
        .map(() => new Array(4).fill(0)
            .map(() => getRandomInt(0, 9))
            .join('')
        );

    return parts.join(' ');
}

const getRandomDate = (start, end) => {
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
};

producer.on('ready', () => {
    const readline = require('readline');
    readline.emitKeypressEvents(process.stdin);
    process.stdin.setRawMode(true);
    process.stdin.on('keypress', (str, key) => {
        if (key.ctrl && key.name === 'c') {
            process.exit();
            return;
        } else if (key && key.name === 'return') {
            const payload = { 
                country: getRandomCountry(), 
                card: getRandomCC(), 
                company: getRandomCompany(), 
                date: getRandomDate(new Date(2019, 1, 1), new Date()).toISOString() 
            };

            console.log('Preparing to publish payload: ', payload);
            producer.send([{
                topic: PRODUCER_TOPIC,
                messages: [JSON.stringify(payload)],
                timestamp: Date.now()
            }], (err, data) => {
                if (err) {
                    console.log('Error publishing message:', err);
                } else {
                    console.log('Published', data);
                }
            });
        }
    });

    console.log('Kafka producer ready & waiting for you to press [Enter] to publish events. Press Ctrl + C to exit.');
});

producer.on('error', (err) => {
    console.error('Error connecting to kafka:', err);
});

client.createTopics([{ topic: PRODUCER_TOPIC, partitions: 1, replicationFactor: 1 }], (err, result) => {
    if (err) {
        console.warn('Attempted to create topic and failed: ', err);
    }
});
