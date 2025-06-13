(2) Simulación: Middleware, emulación interna de myclient y myserver.
(Imágenes de referencia en carpeta "img")


 
(2.1) Correr eureka-server:

(2.1.1) Desde carpeta raíz del proyecto, ejecutar:
javaw -Xms8m -Xmx128m -Dfile.encoding=UTF-8 -Dspring.profiles.active=albertodefault,albertopre,albertodiff -jar ./target/eurekaserver-0.0.3-SNAPSHOT.jar &>> ${HOME}/Desktop/log/eurekaserver-0.0.3-SNAPSHOT.jar.log &

(2.1.2) Verificar en navegador:
http://localhost:8761



(2.2) Con IDE / debugger, correr mymiddleware:

(2.2.1) En "CLR_alberto.java", cotejar que método "testAlberto01()" está habilitado en "main".

(2.2.2) Referencia de ejecución, para parámetros de IDE:
javaw -Xms8m -Xmx256m -Dfile.encoding=UTF-8 -Dspring.profiles.active=albertodefault,albertopre,albertodiff -jar ./target/mymiddleware-1.0.0-SNAPSHOT.jar &>> ${HOME}/Desktop/log/mymiddleware-1.0.0-SNAPSHOT.jar.log &

(2.2.3) Verificar en navegador:
http://localhost:4001/swagger-ui/index.html

(2.2.4) Debugger: punto de parada en CLR_alberto

