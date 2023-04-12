## Folder Structure

Here's a brief explanation of the main folders:

- **controller**: Contains the REST API controllers that handle the incoming HTTP requests and map them to the appropriate services.
- **model**: Contains the data models and entities, such as User, Message, etc.
- **service**: Contains the service classes that implement the business logic, such as saving messages, retrieving messages, and user management.
- **util**: Contains utility classes and helper functions, such as file handling, validation, and any other shared functionality.
- **resources**: Contains the project's resources like configuration files, properties files, and in your case, chat files.

You can store the chat files within the resources folder in a directory called `chat-files`. This folder can contain individual text files representing chat rooms or conversations. Each file can be named uniquely, possibly using a combination of user IDs and/or chat room IDs to ensure uniqueness.
