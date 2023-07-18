# JavaRestTomcatJerseyExample

This project is to create a REST API backend for a homegrown chat app.

## Environmental variable
com.sidequest.parley.util.Config has an environmental variable that points to the config.properties file.

Maven most likely does this correct but I didn't want to figure it out.

| Variable           | Value                                |
|--------------------|--------------------------------------|
| PARLEY_CONFIG_FILE | < ABSOLUTE PATH >\\config.properties |

## Folder Structure

```
- project-root/
    - src/
        - main/
            - java/
                - com/
                    - sidequest/
                        - parley/
                            - controller/
                            - model/
                            - service/
                            - util/
            - resources/
                - chat-files/
                - users/
                    - users.txt
                - config.properties
    - pom.xml
    - readme.md
```

Here's a brief explanation of the main folders:

- **controller**: Contains the REST API controllers that handle the incoming HTTP requests and map them to the appropriate services.
- **model**: Contains the data models and entities, such as User, Message, etc.
- **service**: Contains the service classes that implement the business logic, such as saving messages, retrieving messages, and user management.
- **util**: Contains utility classes and helper functions, such as file handling, validation, and any other shared functionality.
- **resources**: Contains the project's resources like configuration files, properties files, and in your case, chat files.

You can store the chat files within the resources folder in a directory called `chat-files`. This folder can contain individual text files representing chat rooms or conversations. Each file can be named uniquely, possibly using a combination of user IDs and/or chat room IDs to ensure uniqueness.

## API Endpoints

# UserController API Documentation

### Retrieve all users

Endpoint: `GET /users`

Retrieves a list of all users in JSON format.

#### Success Response

**Status Code:** 200 OK

**Response Body:**
```json
{
  "users": [
    {
      "id": 1,
      "name": "John Doe"
    },
    {
      "id": 2,
      "name": "Sally Doe"
    }
  ]
}
```

#### Error Responses

**Status Code:** 404 Not Found

If the file is not found or an I/O error occurs.

### Retrieve a user by ID

Endpoint: `GET /users/{id}`

Retrieves a user by their ID.

#### Request Parameters

| Parameter | Type | Description                     |
|-----------|------|---------------------------------|
| id        | int  | The ID of the user to retrieve. |

#### Success Response

**Status Code:** 200 OK

**Response Body:**
```json
{
  "id": 1,
  "name": "John Doe"
}
```

#### Error Responses

**Status Code:** 404 Not Found

If the user with the specified ID is not found.

### Create a new user

Endpoint: `POST /users`

Creates a new user using the provided input data.

#### Request Body

The request body must be a JSON object containing the following fields:

| Field | Type   | Required | Description               |
|-------|--------|----------|---------------------------|
| name  | string | Yes      | The name of the new user. |

#### Success Response

**Status Code:** 200 OK

**Response Body:**
```json
{
  "id": 3,
  "name": "Bob Smith"
}
```

#### Error Responses

**Status Code:** 500 Internal Server Error

If an I/O error occurs.




# ChatMessageController API Documentation

#### Create Chat Message

Endpoint: `POST /chat`

Creates a new chat message for the specified chat room.

#### Request Body

The request body must be a JSON object containing the following fields:

| Field      | Type | Required | Description                                       |
|------------|------|----------|---------------------------------------------------|
| `chatId`   | int  | Yes      | The ID of the chat room to create the message in. |
| `senderId` | int  | Yes      | The ID of the user who sent the message.          |
| `message`  | int  | Yes      | The content of the chat message.                  |

#### Response

Returns a JSON object containing the details of the newly created chat message.

| Field       | Type   | Description                                                |
|-------------|--------|------------------------------------------------------------|
| `sender`    | Object | Contains the ID and name of the user who sent the message. |
| `timestamp` | Object | Contains the full date and time the message was sent.      |
| `id`        | int    | The unique ID of the chat message.                         |
| `content`   | String | The content of the chat message.                           |

#### Example Request

```
POST /chat
Content-Type: application/json

{
  "chatId": 1,
  "senderId": 1,
  "message": "Hello world!"
}
```

#### Example Response

```json
{
  "content": "This is another message",
  "id": 3,
  "sender": {
    "id": 1,
    "name": "John Doe"
  },
  "timestamp": {
    "year": 2023,
    "monthValue": 4,
    "dayOfMonth": 20,
    "hour": 8,
    "minute": 52,
    "second": 49,
    "nano": 538765400,
    "month": "APRIL",
    "dayOfWeek": "THURSDAY",
    "dayOfYear": 110,
    "chronology": {
      "id": "ISO",
      "calendarType": "iso8601"
    }
  }
}
```


