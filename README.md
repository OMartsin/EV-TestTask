# Database Initialization

Upon the first launch, the application automatically seeds the database with a starter set of connector types for charging stations, such as Type 1, CCS, CHAdeMO, Type 2, and Tesla Supercharger.

## API Endpoints

    Create: POST /api/charging-stations
    Read: GET /api/charging-stations/{id}
    Read: GET /api/charging-stations

## Request & Response Examples
### Create Charging Station (POST)

Request:

```json
{
  "title": "Green Energy Station",
  "description": "24/7 public charging station with fast charging capabilities.",
  "address": "123 Eco Drive, Green City, GC 12345",
  "location": {
    "latitude": -80.0,
    "longitude": -90.005974
  },
  "isPublic": false,
  "connectors": [
    {
      "typeName": "Type 2",
      "maxKWPower": 10
    },
    {
      "typeName": "CCS",
      "maxKWPower": 50.0
    }
  ]
}
```

Response:

```json
{
  "id": 1,
  "title": "Green Energy Station",
  "description": "24/7 public charging station with fast charging capabilities.",
  "address": "123 Eco Drive, Green City, GC 12345",
  "location": {
    "latitude": -80.0,
    "longitude": -90.005974
  },
  "isPublic": false,
  "connectors": [
    {
      "id": 1,
      "type": {
        "name": "Type 2"
      },
      "maxKWPower": 10.0
    },
    {
      "id": 2,
      "type": {
        "name": "CCS"
      },
      "maxKWPower": 50.0
    }
  ]
}
```

# Error Handling

In case of errors, such as validation failures, the API responds with an error message.

## Example error response:
```json
{
    "errors": [
        "Geolocation coordinates must be valid. Latitude must be from -90 to 90, longitude must be from -180 to 180."
    ]
}

```