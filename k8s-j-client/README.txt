K8sJClient -- updated: 2025-02-22

(While developing, kube-config file is required in root folder).

Create jar:
mvn clean compile assembly:single

Execution, no arguments:
java -jar ./target/{filename}.jar

Execution, with arguments:
java -jar ./target/{filename}.jar arg1 arg2 arg3

Execution, using JVM params:
java -Xms8m -Xmx32m -jar ./target/{filename}.jar
