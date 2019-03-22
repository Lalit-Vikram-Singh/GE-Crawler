# Springboot Application :boom:

Simple rest API based springboot application using Java(1.8.*)

+ Current features are
    - API (POST "/api/startCrawler"): api which start crawler and parse input json after parsing it will return back with string contains success, skipped and error URL in given input json. It also does in-depth traversing in a recursive way.


#### prerequiest 
- Maven(3.0*) 
- Java(1.8.*)
- Springboot(2.1.3)
- JUint(JUint5)

### How to run an application 
for running application will leverage maven CLI command before doing so we have to follow few steps

1. Clone this repository 
2. Go inside crawler dir
3. Then dial command in that dir ("mvn clean install") 
4. After building crawler application maven will create jar in target dir by name("crawler-0.0.1-SNAPSHOT.jar")
5. Command to run springboot java jar (java -Dserver.port="your_port_no" -jar "your_jar" example: java -Dserver.port=8091 -jar crawler-0.0.1-SNAPSHOT.jar
)

### Now is time to test our rest API 
you can use rest API client (like postman or ARC-advance rest client)

your API would be ("http://localhost:8091/api/startCrawler") because i am using 8091 port in my example, but you can use your port what you specified in 5th command in  "How to run application section".  

[Signature](https://lalitv92.github.io/) : Checking
