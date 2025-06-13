(1) Prueba completa: Correr todas las aplicaciones.
(Imágenes de referencia en carpeta "img")


(1.1) Correr eureka-server:

(1.1.1) Desde carpeta raíz del proyecto, ejecutar:
javaw -Xms8m -Xmx128m -Dfile.encoding=UTF-8 -Dspring.profiles.active=albertodefault,albertopre,albertodiff -jar ./target/eurekaserver-0.0.3-SNAPSHOT.jar &>> ${HOME}/Desktop/log/eurekaserver-0.0.3-SNAPSHOT.jar.log &

(1.1.2) Verificar en navegador:
http://localhost:8761


 
(1.2) Correr myserver:

(1.2.1) Desde carpeta raíz del proyecto, ejecutar:
javaw -Xms8m -Xmx256m -Dfile.encoding=UTF-8 -Dspring.profiles.active=albertodefault,albertopre,albertodiff -jar ./target/myserver-1.0.0-SNAPSHOT.jar &>> ${HOME}/Desktop/log/myserver-1.0.0-SNAPSHOT.jar.log &

(1.2.2) Verificar en navegador:
http://localhost:4000/swagger-ui/index.html



(1.3) Con IDE / debugger, correr mymiddleware:

(1.3.1) En "CLR_alberto.java", cotejar que método "testAlberto01()" está deshabilitado en "main".

(1.3.2) Referencia de ejecución, para parámetros de IDE:
javaw -Xms8m -Xmx256m -Dfile.encoding=UTF-8 -Dspring.profiles.active=albertodefault,albertopre,albertodiff -jar ./target/mymiddleware-1.0.0-SNAPSHOT.jar &>> ${HOME}/Desktop/log/mymiddleware-1.0.0-SNAPSHOT.jar.log &

(1.3.3) Verificar en navegador:
http://localhost:4001/swagger-ui/index.html



(1.4) Correr myclient:

(1.4.1) Desde carpeta raíz del proyecto, ejecutar:
javaw -Xms8m -Xmx256m -Dfile.encoding=UTF-8 -Dspring.profiles.active=albertodefault,albertopre,albertodiff -jar ./target/myclient-1.0.0-SNAPSHOT.jar &>> ${HOME}/Desktop/log/myclient-1.0.0-SNAPSHOT.jar.log &

(1.4.2) Verificar en navegador: 
http://localhost:4002/swagger-ui/index.html
 

