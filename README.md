# Marvel

This application shows a list with the Marvel characters and the details of each one.
Includes a search view to search characters by name. 

## Set up project

1. Clone project from github [here](https://github.com/leinaro/marvel)

2. Set up local.properties, this app uses [Marvel Comics API](https://developer.marvel.com/) to access information about Marvel's vast library of comics from what's coming up, to 70 years ago.
   - In order to run the project using the API, you need a developer account on [developer marvel](https://developer.marvel.com/account) to get the api keys.
   - Copy your private key and public key on your local.properties as follow:
   ```
   marvel.privateKey="private key"
   marvel.apiKey="public key"
   ```

## Code coverage

For code coverage the project use Jacoco and a gradle task, to check the coverage you can use the next commands:
```
./gradlew :{module}:checkCoverage
```
   
## Architecture

This project use a modular architecture based on Clean architecture,
 
- buildSrc module
The project have three groups of layers:
- presentation layers
  - app module
  - core module
  - view/feature modules
- domain layers
  - [domain module](domain/README.md)
- data layers
  - data layer

### Modules

## About the developer



Key words: view model, jetpack compose, flow, hilt, retrofit.