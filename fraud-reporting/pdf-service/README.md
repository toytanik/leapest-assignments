# PDF Service

<hr>
The service exposes a RESTful endpoint that takes fraud event data and generates a report in PDF format.

## Prerequisites

- Node v10.0+ until 14.7 (it is not working for node 16)

## Build

`$ npm install`

## Running

`$ npm run start:dev`

The app server listens on `PORT` and exposes the `POST /api/generate` API for PDF report generation.

### Sample payload

```
{
  "company": {
    "name": "East EuropePotatoes EMEA, Inc."
  },
  "records": [
    {
      "country": "Croatia",
      "percentage": 0.24,
      "total": 342
    },
    {
      "country": "Bulgaria",
      "percentage": 0.14,
      "total": 3452
    },
    {
      "country": "TheNetherlands",
      "percentage": 0.54,
      "total": 1342
    },
    {
      "country": "India",
      "percentage": 0.04,
      "total": 2342
    },
    {
      "country": "Japan",
      "percentage": 0.12,
      "total": 6342
    }
  ],
  "from": "01-11-2019",
  "to": "30-11-2019",
  "overall": 12429
}
```
### Response
Returns the generated PDF document as a base64-encoded string.
