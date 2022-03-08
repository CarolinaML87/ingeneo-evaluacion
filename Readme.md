## API EVALUACION TECNICA INGENEO
#MAYRA CAROLINA MERCADO LAINEZ

## Desarrollo de API para transporte de productos por medio terrestre y maritimo

Diagrama ER de la solucion desarrollada.

![DIAGRAMA ER](https://user-images.githubusercontent.com/54516995/157090818-238bfbb6-59c8-494a-beb0-dddd7b694ad7.png)

## Tecnologias utilizadas para el desarrollo

* Proyecto Maven
* Java (version 8)
* Base de datos: PostgreSQL.
* Framework: spring boot version 2.6.3
* Documentacion de endpoint : swagger 3.0.0
* La utilizacion de proyectos Maven resulta de utilidad para poder optimizar los tiempos de desarrollo e invertir realmente en el desarrollo de logica de negocio y documentacion 
* de la aplicacion ya que se evita tiempo en configuraciones extras que puede necesitar cualquier otra tecnologia.

### Listado de EndPoint's
**Authenticacion con token tipo Bearer
* /authenticate : Para authenticacion de usuario y obtencion de token (username:javainuse, password:password).
* /signup: Para permitir el registro de usuarios de usuarios, para ello se han creado dos roles uno ADMIN y USER, los cuales (ADMIN, USER o ambos) se deben enviar como un list *de String para asociar * usuario con sus respectivos ROLES.
* /customers : CRUD para gestion de clientes, rol de acceso : ADMIN
* /destinations: CRUD para la gestion de bodegas de almacenamiento terrestre o maritimo (Tipo 1: Terrestre,Tipo2:Maritimo), rol de acceso : ADMIN.
* /product: CRUD para la gestion de productos, rol de acceso : ADMIN.
* /deliveries/: registro de entregas.
* /deliveries/all : todos los deliveries pendientes
* /deliveries/deliveriesByCustomer/{id}: obtener entregas registradas por ID de cliente.

Para cada uno de los ENDPOINTS se han creado las siguientes acciones:
* /all = Método HTTP: GET : returna un listado de la entity
* / = Método HTTP: POST : crea la entity
* /{id} = Método HTTP: GET : devuelve una entity en base a su id
* /{id} =Método HTTP: PUT: actualiza una entity
* / = Método HTTP: DELETE : elimina una entity.
