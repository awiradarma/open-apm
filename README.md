# open-apm
Open source APM solutions HOW-TOs

Open each project (I'm using VS Code)
Build the target jar (./mvnw package)
Repeat for the other project

Setup MySQL publisherdb instance using Docker:
sudo docker run --name mysqldb -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=publisherdb -d -p 3306:3306 mysql 

Set JAVA_HOME, make sure it is pointing to a Java 8 JRE!

Start the publisher service
$JAVA_HOME/bin/java -javaagent:./pinpoint-bootstrap-1.7.3.jar -Dpinpoint.agentId=publisher_01 -Dpinpoint.applicationName=publisher -jar ./publisher-0.0.1-SNAPSHOT.jar

Start the front service
$JAVA_HOME/bin/java -javaagent:./pinpoint-bootstrap-1.7.3.jar -Dpinpoint.agentId=frontsvc_01 -Dpinpoint.applicationName=frontsvc -jar FrontEndSvc-0.0.1-SNAPSHOT.jar 

To hit the publisher directly:
curl http://localhost:8090/publishers
curl http://localhost:8090/publishers/Wrox

To hit the publisher service and inject a 100ms delay
curl http://localhost:8090/publishers/Wrox?delay=100

To hit the front service and invoke the publisher to obtain a publisher data:
curl http://localhost:8070/front/Wrox

To create a loop that continuously hit the publisher service, inserting 100ms delay at the front service, and 500ms at the publisher service:
while true; do curl "localhost:8070/front/Packt?delay=100&backendDelay=500"; echo; sleep 1; done

