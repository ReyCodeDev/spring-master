## Como empezar
- Java 26, indispensable, es la versión que he usado para este proyecto.
- WSL en windows.
- IntelliJ o el ide de preferencia.
## Estructura basica de un proyecto Spring para una API (basico)
- Controladores: Hacen la comunicación Cliente <-> Servidor.
- Modelos: Objetos en Java basados en las diferentes tablas.
- Repositorios: Conexión entre base de datos y Spring, usa JPA como base (parecido a Hibernate, si os acordais de clase de Marta).
#### Mas cosas relacionadas con la estructura
- Servicios: Es la parte que se encarga de aislar el funcionamiento entre base de datos y los controladores, aunque no necesaria es obligatorio para mantener el codigo limpio.
- DTOs (Data Transfer Object): Son objetos que se usan para aislar los datos de la base de datos con lo que se quiere mostrar, por ejemplo, de un usuario a lo mejor quieres mostrar solo nombre y email, y evitar el resto de campos.
- Mappers: Funcionamiento basico, sirven para convertir los objetos en otro objeto diferente, usado principalmente para convertir un Modelo a DTO o viceversa.
- Exception Handlers: Útiles para una API, ya que puedes controlar errores, por ejemplo un usuario no encontrado y que te devuelva código 404.

