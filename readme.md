![](https://api.visitorbadge.io/api/VisitorHit?user=toby959&repo=toby959&countColor=%230e75b6)

 <h1 style="text-align: center;"> Urban Next  </h1>


# Descripción 📖
El desarrollo de una API rest para una Aplicación de alquiler de inmuebles para poder, registrar los datos de sus **inquilinos**; **anfitriones**: nombre, email, teléfono, documento, estado (activo/inactivo), especialidad y dirección, que contiene: calle, distrito, ciudad, número y complemento (categoría). Se realizó un borrado lógico para evitar perder datos en la base de datos, utilizando MySQL Workbench.
___ 

# Recursos 👾

| Plugin           | URL                      |
|------------------|--------------------------|
| spring-boot-starter-web | https://start.spring.io/ |
| spring-boot-devtools    | https://start.spring.io/ |
| lombok | https://start.spring.io/ |
| spring-boot-starter-data-jpa | https://start.spring.io/ |
| mysql-connector-j | https://start.spring.io/ |
| spring-boot-starter-validation | https://start.spring.io/ |
| spring-boot-starter-security | https://start.spring.io/ |
| java-jwt | https://start.spring.io/ |
| springdoc-openapi-starter-webmvc-ui | https://springdoc.org/#getting-started |
___
# Herramientas utilizadas 🛠️


<img src="https://www.vectorlogo.zone/logos/java/java-icon.svg" title="Java"  alt="Java" width="40" height="40"/>&nbsp;
<img src="https://www.vectorlogo.zone/logos/springio/springio-ar21.svg" title="Spring"  alt="Spring" width="40" height="40"/>&nbsp;
<img src="https://www.vectorlogo.zone/logos/mysql/mysql-ar21.svg" title="Mysql"  alt="Mysql" width="40" height="40"/>&nbsp;
<img src="https://www.vectorlogo.zone/logos/getpostman/getpostman-icon.svg" title="Postman"  alt="Postman" width="40" height="40"/>&nbsp;
<img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" title="Git"  alt="Git" width="40" height="40"/>&nbsp;
<img src="https://www.vectorlogo.zone/logos/github/github-icon.svg" title="GitHub"  alt="GitHub" width="40" height="40"/>&nbsp;

![Badge en Desarollo](https://img.shields.io/badge/STATUS-EN%20DESAROLLO-green)&nbsp;&nbsp;&nbsp;![Java 17](https://img.shields.io/badge/java-17-blue?logo=java)&nbsp;&nbsp;&nbsp;![Version](https://img.shields.io/badge/version-v1.0-COLOR.svg)&nbsp;&nbsp;&nbsp;[![MIT License](https://img.shields.io/badge/licencia-MIT-blue.svg)](LICENSE)&nbsp;&nbsp;&nbsp;![IntelliJ IDEA Community](https://img.shields.io/badge/IDE-IntelliJ%20IDEA%20Community-red?style=flat)&nbsp;&nbsp;&nbsp;![swagger 2.6.0](https://img.shields.io/badge/SWAGGER-%202.6.0-purple?style=flat)&nbsp;&nbsp;&nbsp;
___


# Ejecutar el Proyecto ⚙️
1 - Clona el repositorio en tu máquina local
``` bash
git clone
<https://github.com/toby959/toby959.git>
```
2 - Compila y ejecuta el archivo UrbanNextApplication.java.
___

# Funcionalidades 📦

Esta API REST permite cargar los datos de los médicos y pacientes, evitando la duplicación de información. Se pueden guardar los datos de los médicos y pacientes que se eliminan debido a un borrado lógico, que cambia el estado de 'activo' (1) a 'desactivo' (0). Además, se emplea Flyway, una herramienta de migración de bases de datos que facilita la gestión de cambios en la estructura de la base de datos a lo largo del tiempo.
### Beneficios
Simplicidad: Facilita el manejo de migraciones sin necesidad de realizar cambios manuales en la base de datos.   
Trazabilidad: Ofrece un registro claro y accesible sobre el estado actual y el historial de las migraciones aplicadas.   
Integración: Se integra fácilmente con herramientas como Maven y Gradle, así como con frameworks como Spring Boot.
___
## Uso de Postman
* Base de datos: `urbannext`
#### Flexibilidad: Al usar una variable como {{url}}, puedes cambiar la URL base en un solo lugar (en la configuración del entorno) y todas las solicitudes que utilicen esa variable se actualizarán automáticamente. Esto es especialmente útil cuando trabajas con diferentes entornos (desarrollo, pruebas, producción) donde la URL puede variar.

## Métodos:
### POST
- **Descripción**: Este método se utiliza para crear nuevos registros tanto de: **ADMINISTRADOR**; **ANFITRION**; **INQUILINO** en la base de datos.
- **Endpoint**: `{{url}}/auth/register`
- **Cuerpo de la solicitud**:

````bash
{
  "name": "Gabriel Pozzo",
  "email": "gaby@gmail.com",
  "password": "qazwsxed1",
  "tipoUsuario": "INQUILINO",
  "phone": "+5103544448900",
  "document": "24555000",
  "address": {
    "street": "Leon Guillet",
    "number": "123",
    "district": "Villa Mercedes",
    "city": "Villa MErcedes San Luis"
  } 
}
````
### POST
- **Descripción**: Este método permite logear a un usuario, con sus respectivo Tipo Usuario, generando un  token de accesos, que se almacena en la base de datos.     
- **Endpoint**: `{{url}}/auth/login`
- **Cuerpo de la solicitud**: 

````bash
{    
  "email": "rico@gmail.com",
  "password": "123456789",
  "tipo_usuario": "ANFITRION"
}
````
- **Respuesta**:
````bash
{
    "name": "Ricardo",
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cmJhbm5leHQtYXBpIiwic3ViIjoicmljb0BnbWFpbC5jb20iLCJleHAiOjE3NDI2MTA1MjN9.3XzlllUn3iH0PA-DzywBgYuk0WFlLg098_PuKhsYmOs",
    "tipoUsuario": "ANFITRION"
}    
```` 
### GET
- **Descripción**: Este método permite ingresar a un Usuario, con su respectivo Tipo Usuario(Rol) de acuerdo a ello le permitira: realizar un CRUD.
- **Endpoint**: `{{url}}/user`
- **Cuerpo de la solicitud**:
````bash
{
  "name": "Ricardo",
  "email": "ricog@gmail.com",
  "password": "123456789",
  "tipoUsuario": "AFITRION"
}
````
___
### DEL
- **Descripción**: Este método permite el borrado lógico del médico.
- **Endpoint**: `{{url}}/api/v1/doctors/3`
- **Cuerpo de la solicitud**: vacío.

### POST
- **Descripción**: Este método se utiliza para crear nuevos registros de pacientes en la base de datos.
- **Endpoint**: `{{url}}/api/v1/patients`
- **Cuerpo de la solicitud**:
````bash
{
    "name": "Maria G",
    "email": "mar@gmail.com",
    "phone": "4512",
    "document": "34345339",
    "address": {
        "addition": "quebradura expuesta",
       "street": "Avenida Libertad",
        "district": "Norte",
        "city": "Cartago",
        "number": "5321"        
    },
    "active":1
}
````
### GET
- **Descripción**: Este método trae una lista de pacientes, según el uso de 'Page' usado para la paginación de los datos de DB, en este caso `@PageableDefault(size = 10, sort = {"name"})`. Esto especifica que, por defecto, se devolverán hasta 10 elementos por página. Si no se proporciona un parámetro de paginación en la solicitud (por ejemplo, si el cliente no especifica cuántos elementos quiere por página), se utilizará este valor predeterminado `sort = {"name"}` este parámetro indica que los resultados se ordenarán por el campo name. En este caso, los pacientes serán listados en orden ascendente según su nombre. Si el cliente no proporciona un criterio de ordenamiento en la solicitud, se aplicará este orden predeterminado.
- **Endpoint**: `{{url}}/api/v1/patients`
- **Cuerpo de la solicitud**: vacío.

### PUT
- **Descripción**: Este método permite modificar, nombre; teléfono y domicilio del paciente.
- **Endpoint**: `{{url}}/api/v1/patients/1`
- **Cuerpo de la solicitud**:
````bash
{
    "id": 1,
    "name": "Carla Peterson",
    "phone": "332244",
    "address": {
        "addition": "presion alta",
        "street": "Avenida Colombia",
        "district": "Sur",
        "city": "San Fernando de Catamarca",
        "number": "101"
    }
}
````
### DEL
- **Descripción**: Este método permite el borrado lógico del médico.
- **Endpoint**: `{{url}}/api/v1/patients/3`
- **Cuerpo de la solicitud**: vacío.
___ 

# Colaboraciones 🎯
Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1 - Haz un fork del repositorio: Crea una copia del repositorio en tu cuenta de GitHub.  
2 - Crea una nueva rama: Utiliza el siguiente comando para crear y cambiar a una nueva rama:
```bash
git chechout -b feature-nueva
```
3 - Realiza tus cambios: Implementa las mejoras o funcionalidades que deseas agregar.  
4 - Haz un commit de tus cambios: Guarda tus cambios con un mensaje descriptivo:
```bash 
git commit -m 'Añadir nueva funcionalidad'
```
5 - Envía tus cambios: Sube tu rama al repositorio remoto:
````bash
git push origin feature-nueva
````
6 - Abre un pull request: Dirígete a la página del repositorio original y crea un pull request para que revisemos tus cambios.

💥Gracias por tu interés en contribuir a este proyecto. ¡Esperamos tus aportes!
___
# Imagenes:

![Imagen Workbench](./imagen1.png)


![Imagen UML](./uml_3.png)


![Imagen Diagrama JWT](./imagen3.png)

![Imagen Diagrama JWT](./imagen5.png)

![Imagen Diagrama JWT](./imagen4.png)

![Imagen Swagger](./imagen8.png)

![Imagen Swagger](./imagen9.png)

## Desglose del Payload


___   
## Swagger

````
localhost:8000/swagger-ui.html
````

### Instrucciones para Autenticación

1. **Genera la Clave**: Utiliza el script proporcionado a continuación para generar la clave.
````
{
  "login": "ricardo.iorio",
  "user_key": "123456"
}
```` 
![Imagen Diagrama JWT](./imagen6.png)

![Imagen Diagrama JWT](./imagen7.png)
2. **Copia el Token**: Una vez que hayas generado la clave, copia el token resultante.
3. **Autoriza el Acceso**:
    - Haz clic en el botón **'Authorize'** en la interfaz de Swagger.
    - Pega el token copiado en el campo correspondiente.
    - Presiona nuevamente el botón **'Authorize'** para confirmar.
    - Finalmente, haz clic en el botón **'Close'** para cerrar la ventana de autorización.

Con estos pasos, podrás acceder a todos los endpoints de la API.


___

## Ruta para Frontend 💡
````
http://localhost:4200
````

## ⚠️Se debe agregar la siguiente anotación en nuestra clase 'SecurityConfigurations', ya que por defecto esta desabilitado:

````bash
@EnableMethodSecurity(securedEnabled = true)
````

#### * Swagger: Leer la documentacion [Visita Swagger](https://springdoc.org/)
___

## Licencia 📜

#### Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](https://github.com/toby959/toby959-clinic/blob/main/LICENSE) para más detalles.

