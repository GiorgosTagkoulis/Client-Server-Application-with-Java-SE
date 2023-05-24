# Client Server Application with Java SE

This is a Maven project with two submodules, a server and a client, where the server reads a text file and when requested by the client sends it over the network. The client saves the file and create a top 10 list over the most common words and the number their occurences in the text file. 

In order to run the project, you need to have installed Java SE 8, and Maven.

## Download and build the project
First clone the project: `git@github.com:GiorgosTagkoulis/Client-Server-Application-with-Java-SE.git`

Then change directory into the project: `cd Client-Server-Application-with-Java-SE/ `

and at last: `mvn clean package`

## Run the project
First run the server: `java -jar server/target/server-1.0.0.jar`

Then the client: `java -jar client/target/client-1.0.0.jar`