# Springboot Rest API based apllication :boom:

simple rest API based springboot application using Java(1.8.*)
[Example]() :To deploy to production rcdn, run the following commands:

+ features are
	- API (POST "/api/startCrawler"): is a api which will start crawler and parse input json and return back with string contains success, skiped and error url in given input json do indepth travesing in recursive way.


### prerequiest 
- Maven(3.0*) 
- Java(1.8.*)
- Springboot(2.3)
- JUint(JUint5)

### How to run application 
for runnig application will leverage maven CLI command before doing do that we have to follow fet steps

1. Clone this repository 
2. Go inside crawler dir
3. Then dial command in that dir ("mvn clean install") 
4. After building crawler application maven will create jar in target dir by name("crawler-0.0.1-SNAPSHOT.jar")
5. Command to run springboot java jar (java -Dserver.port="your_port_no" -jar "your_jar" example: java -Dserver.port=8091 -jar crawler-0.0.1-SNAPSHOT.jar
)

### Now is time to test our rest API 
you can use rest API client (like postman or ARC-advance rest client)
your API wloud be ("http://localhost:8091/api/startCrawler") because i am using 8091 exampel port but please use your port what you espesified in 5th command in  How to run application section.  
