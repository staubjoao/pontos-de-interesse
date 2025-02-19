# GPS Points of Interest

Solution for the [GPS Points of Interest](https://github.com/backend-br/desafios/tree/master/03-Hard/PontosDeInteressePorGPS) challenge by backend-br.

## Stack
- Java
- Spring Boot
- PostgreSQL

## Problem
XY Inc. is a company specialized in manufacturing excellent GPS (Global Positioning System) receivers.
The board is committed to launching an innovative device that promises to help people locate points of interest (POIs) and needs your help.

You have been hired to develop the platform that will provide all the intelligence to the device! This platform must be based on REST services to facilitate integration.

1. Develop a service to register points of interest with three attributes: POI name, X coordinate (non-negative integer), and Y coordinate (non-negative integer). The POIs must be stored in a database.

2. Develop a service to list all registered POIs.

3. Develop a service to list POIs by proximity. This service will receive an X and a Y coordinate specifying a reference point, as well as a maximum distance (d-max) in meters. The service should return all POIs from the database that are at a distance less than or equal to d-max from the reference point.

### Example Database:

- 'Diner' (x=27, y=12)
- 'Gas Station' (x=31, y=18)
- 'Jewelry Store' (x=15, y=12)
- 'Flower Shop' (x=19, y=21)
- 'Pub' (x=12, y=8)
- 'Supermarket' (x=23, y=6)
- 'Steakhouse' (x=28, y=2)

### Example Usage:
Given the reference point (x=20, y=10) indicated by the GPS receiver and a maximum distance of 10 meters, the service should return the following POIs:

- Diner
- Jewelry Store
- Pub
- Supermarket

## Endpoints

### Register POI
To register a point of interest
**[POST]** localhost:8080/ponto

Request body:

Field | Type | Description | Required
------|------|-----------|------------
name  |String|POI Name| Yes
x | int | POI X coordinate, non-negative | Yes
y | int | POI Y coordinate, non-negative | Yes

#### Example
#### Request
**[POST]** localhost:8080/ponto
```
{
    "descricao": "Diner",
    "x": 27,
    "y": 12
}
```
#### Response
201 Created

### List all POIs
#### Request
**[GET]** localhost:8080/ponto
#### Response
200 OK
```
[
    {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Diner",
        "x": 27,
        "y": 12
    },
    ...
]
```

### Get a POI by ID
#### Request
**[GET]** localhost:8080/ponto/{id}

#### Parameters

Parameter | Type | Description | Required
------|------|-----------|------------
id | String(UUID) | POI Identifier | Yes

#### Example
#### Request
**[GET]** localhost:8080/ponto/a9074aa9-cc2e-4dd3-992b-a11df154a34e

#### Response
200 OK
```
{
    "data": {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Diner",
        "x": 27,
        "y": 12
    },
    "errors": []
}
```

### Delete a POI by ID
#### Request
**[DELETE]** localhost:8080/ponto/{id}

#### Parameters

Parameter | Type | Description | Required
------|------|-----------|------------
id | String(UUID) | POI Identifier | Yes

#### Example
#### Request
**[DELETE]** localhost:8080/ponto/a9074aa9-cc2e-4dd3-992b-a11df154a34e

#### Response
200 OK
```
{
    "data": {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Diner",
        "x": 27,
        "y": 12
    },
    "errors": []
}
```

### List POIs by Proximity
**[GET]** localhost:8080/ponto/proximos

#### Parameters

Parameter | Type | Description | Required
------|------|-----------|------------
x | int | Reference point X coordinate | Yes
y | int | Reference point Y coordinate | Yes
distancia | int | Maximum distance to POI, non-negative | Yes

#### Example
#### Request
**[GET]** localhost:8080/ponto/proximos?x=20&y=10&dmax=10
#### Response
200 OK
```
[
    {
        "id": "a9074aa9-cc2e-4dd3-992b-a11df154a34e",
        "descricao": "Diner",
        "x": 27,
        "y": 12
    },
    {
        "id": "ff9cb7e8-06af-4530-bce8-51621583d9be",
        "descricao": "Jewelry Store",
        "x": 15,
        "y": 12
    },
    {
        "id": "895edefd-46c9-473b-a286-780d556a61f2",
        "descricao": "Pub",
        "x": 12,
        "y": 8
    },
    {
        "id": "e44969a9-ee84-4eab-9d0c-a78737f357b5",
        "descricao": "Supermarket",
        "x": 23,
        "y": 6
    }
]
```
