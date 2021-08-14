### Set port
.env
```
PORT=8080
```

## Project setup

To compile and build:

```
mvn clean install


### To run locally

```
mvn spring-boot:run

### Application AWS URL

http://3.7.39.76:8080/

To test the backend from Postman/ any client UI

http://3.7.39.76:8080/api/user/path


Payload:

{
	"graphPath":"AB3, BC9, CD3, DE6, AD4, DA5, CE2, AE4, EB1",
	"weight_1":"A-B-C",
	"weight_2":"A-E-B-C-D",
	"weight_3":"A-E-D"
}

URL to access the application

http://3.7.39.76:8080/




