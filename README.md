# weather-app
Application to store log of weather after user saw it

To run please 
1. cd to the root
2. mvn clean install
3. cd to target
4. java -jar WeatherApp-0.0.1-SNAPSHOT.jar
5. access swagger ui via http://localhost:8080/swagger-ui.html#/ to see provided api
6. if you have docker install please do steps below for your convinience:
    - cd to the root
    - docker build -t weather-app . 
    - docker images (to list images you have, you find the weather-app)
    - copy the weather-app id in ID column
    - docker run -p 8080:8080 {id} (from the step 4)
    - access swagger ui via http://localhost:8080/swagger-ui.html#/ to see provided api
