# Sistema de reservas de casa/alojamientos - RestApi

## Requisitos de sistema
- Java 17 o superior <br>
- Spring boot 3.3.1 o superior<br>
- Maven<br>
- Postgrest v.15 o superior<br>
- Docker (opcional)<br>
- Cliente HTTP - Api Rest (Postman, Insomnia, etc)<br>
<br>
<br>

## Como testearlo?
-Asegurate de tener un entorno de GIT ya configurado en tu entorno local(es decir no tener problemas de identificacion con el servicio de autenticacion de Github)<br>
-Clonar el repositorio desde la URL : **git@github.com:3nr19u3/bideaTest.git** via SSH o HTTP <br> usando tu terminal favorita y en tu configuracion de controlador de versiones (GIT) o la herramienta GIT de tu agrado<br>
<br>
<br>

## Usas Docker?
- Si es asi puedes levantar el proyecto en tu maquina siguiendo los siguientes pasos
- Una vez clonado el repositorio dirigete a la carpeta raiz del proyecto y ejecuta el comando: **./mvnw clean package -DskipTests** para empaquetar el proyecto usando maven<br>
- Seguidamente puedes ejecutar el siguiente comando: **docker-compose build java_app** (creara la imagen de Docker siguiendo la configuracion especificada en el docker-compose)<br>
- Puedes verificarla corriendo el siguiente comando: **docker images**(alli deberias poder ver la imagen creada)<br>
- Finalmente y una vez creada la imagen puedes ejecutar esa imagen en un contenedor de docker usando el comando **docker-compose up**<br>
- (esto abrira la terminal de ejecucion del programa una vez levantado, recuerda que esta terminal es la del contenedor)<br>
- Ya tendrias listo el programa para ser testeado en tu equipo!<br>
<br>
<br>

## Si no usas Docker:
- Puedes Abrir el repositorio  clonado desde tu IDE favorito (Intellij, Eclipse, STS, Etc)<br>
- Una vez abierto puedes cambiar los valores de la configuracion en el file llamado application.yml <br>desde como se encuentra en el lado izquierdo a como esta en el lado derecho de la imagen adjunta<br>
![Captura de pantalla 2024-07-15 012824](https://github.com/user-attachments/assets/61394d54-5ff9-4ce2-954d-afbebaced419)
- Luego de cambiar estosa valores puedes seguir creando tu base de datos en postgres la cual llamaras por default "bookingdb"<br>
- Seguidamente ejecutaras el programa apoyandote de tu IDE (una buena opcion, es ejecutar la clase principal del proyecto directamente que seria esta: BookingServiceApplication.java)<br>
- Si prefieres levantarlo usando la terminal, puedes considerar desde la raiz del proyecto ejecutar el comando: **./mvnw spring-boot:run** (ejecuta una aplicacion de spring-boot apoyandose de maven)<br>
- Y Ya tendrias listo el programa para ser testeado en tu equipo!<br>
<br>
<br>

## Vamos a testearlo!
- Descargaras e importaras en tu cliente HTTP preferido la coleccion adjunta al proyecto (el file se llama : Insomnia_2024-07-13.json)<br>
- En dicha coleccion se encuentran especificados los endpoints sus nombre y los request requerido
- Asi que primero empezaremos creando un Usuario(considerar lo siguiente, usuarios mayores de edad > 18 y numeros telefonicos unicos)
- Seguidamente seguiremos creando una Casa o Hospedaje(considerar lo siguiente, las casas/hospedajes manejan estados "AVAILABLE" / "UNAVAILABLE", y un precio de alquiler o renta diario) <br> dichos valores se establecen para darle una logica a la creacion de reservas
- Finalmente intentaras crear una reserva asociada a un usuario y una casa ya existente (Se validan la existenca de los usuarios asi como tambien la existencia y estado las casa)<br>
- Como ultima validacion para el exito de la operacion se considera la respuesta del servicio externo (validacion del codigo de descuento)<br>
- Si la respuesta del servicio externo es positiva la reserva sera creada satisfactoriamente (caso contrario arrojara una respuesta con codigo HHTP 409)<br>
- Considerar que:<br>
- Se a desarrollado un pequeña logica para darle sentido a la operacion que consiste en calcular un un precio a la reserva este se calcula (valor del alquiler * numero de dias)<br>
- Se consideran validaciones logicas como la coherencia entre los dias de la reserva<br>
- Si el servicio externo tarda mas de 5 segundos en responder el programa arrojara una excepcion de tipo TIMEOUT<br>
<br>
<br>

## Algunas evidencias!

![Captura de pantalla 2024-07-15 025356](https://github.com/user-attachments/assets/e066fbd5-9c1e-4eb1-b6bd-fbaa0bbc89e9)

![Captura de pantalla 2024-07-15 025413](https://github.com/user-attachments/assets/62f6fef5-b42c-4c2c-a6b3-ffa0ee3b926a)

![Captura de pantalla 2024-07-15 025437](https://github.com/user-attachments/assets/d41dfcf1-39fc-41e6-a2d7-6a5cb2c13b63)

![Captura de pantalla 2024-07-15 025451](https://github.com/user-attachments/assets/71ced24e-289b-4459-850a-356024c2281f)

![Captura de pantalla 2024-07-15 025608](https://github.com/user-attachments/assets/da6b49fa-f8f9-4492-b048-5cbae531b91a)

![Captura de pantalla 2024-07-15 025702](https://github.com/user-attachments/assets/e8ccf993-fab6-46f2-817e-9a53a0de4bbd)

<br>
