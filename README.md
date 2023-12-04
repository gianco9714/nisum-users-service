# nisum-users-service

This service provides endpoints for user registration

## Tech Stack

- Springboot

- Java 11 

- H2

## Usage/Examples

Url

```java
http://localhost:8080/api/v1/users
```


Request Example

```java
{
    "name": "Juan Rodriguez",
    "email": "juan244n@rodriguez.org",
    "password": "Eenados2345!",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        },
        {
            "number": "1234567",
            "citycode": "1",
            "countrycode": "57"
        }
    ]
}
```

Response Example

```java
{
    "id": "aa52e740-dfc1-4d1b-ab8f-01cd00f4a2bd",
    "createdAt": "2023-12-04T00:58:17.2742339-05:00",
    "modifiedAt": "2023-12-04T00:58:17.2742339-05:00",
    "lastLogin": "2023-12-04T00:58:17.2742339-05:00",
    "token": "80e4f11a-a843-4eef-80f0-1c77b523cecd",
    "active": false
}
```
