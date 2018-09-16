# **open-apm**
Open source APM solutions HOW-TOs


## Steps to build the jars
* Open the publisher folder (I'm using VS Code)
* Build the target jar (./mvnw package)
* The jar should be created in the target folder
* Repeat for the FrontEndSvc folder


## Setting up MySQL publisherdb instance using Docker
>sudo docker run --name mysqldb -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=publisherdb -d -p 3306:3306 mysql 


## Set up HBase for Naver Pinpoint
* Download and extract HBase 1.4.6
* Set JAVA_HOME, make sure it is pointing to a Java 8 JRE!
* Configure hbase-site.xml:
   > \<configuration\>  
   \<property\>  
      \<name\>hbase.rootdir\</name\>  
      \<value\>file:///home/andre/hbase/root\</value\>  
  \</property\>  
  \<property\>  
    \<name\>hbase.zookeeper.property.dataDir\</name\>  
    \<value\>/home/andre/hbase/zookeeper\</value\>  
  \</property\>
* Start hbase (bin/start-hbase.sh)
* Generate the pinpoint tables 
  > bin/hbase shell /home/andre/pinpoint/pinpoint/hbase/scripts/hbase-create.hbase 
* Validate that the pinpoint tables are created


## Configure Pinpoint collector and web UI
* Download v1.7.3 of pinpoint collector and web UI war files
* Download Apache Tomcat 8.5.33
* Extract the Apache Tomcat files to ~/tomcat/pinpoint_web
* Remove everything under ~/tomcat/pinpoint_web/webapps
* Copy pinpoint web UI war file to webapps, rename it as root.war
* Start Tomcat (bin/startup.sh)
* Extract another copy of the Apache Tomcat files to ~/tomcat/pinpoint_collector
* Remove everything under ~/tomcat/pinpoint_collector/webapps
* Copy pinpoint collect war file to webapps, rename it as root.war
* Modify ~/tomcat/pinpoint_collector/conf/server.xml and change all the port number to avoid conflict with the other Tomcat instance
* Start Tomcat


## Configure the publisher service
* Download v1.7.3 of pinpoint agent
* Modify pinpoint.config file 
* Start the publisher service, make sure to use **Java 8 JRE**
  > $JAVA_HOME/bin/java -javaagent:./pinpoint-bootstrap-1.7.3.jar -Dpinpoint.agentId=publisher_01 -Dpinpoint.applicationName=publisher -jar ./publisher-0.0.1-SNAPSHOT.jar


## Configure the front service
* Extract another copy of pinpoint agent 1.7.3
* Modify pinpoint.config file
* Start the front service, use Java 8
  > $JAVA_HOME/bin/java -javaagent:./pinpoint-bootstrap-1.7.3.jar -Dpinpoint.agentId=frontsvc_01 -Dpinpoint.applicationName=frontsvc -jar FrontEndSvc-0.0.1-SNAPSHOT.jar 


## URLs for testing
To hit the publisher directly:  
> curl http://localhost:8090/publishers  
 > curl http://localhost:8090/publishers/Wrox  

To hit the publisher service and inject a 100ms delay  
> curl http://localhost:8090/publishers/Wrox?delay=100  

To hit the front service and invoke the publisher to obtain a publisher data:  
> curl http://localhost:8070/front/Wrox  

To create a loop that continuously hit the publisher service, inserting 100ms delay at the front service, and 500ms at the publisher service:  
  > while true; do curl "localhost:8070/front/Packt?delay=100&backendDelay=500"; echo; sleep 1; done

