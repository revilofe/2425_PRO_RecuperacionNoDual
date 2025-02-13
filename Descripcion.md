# EJERCICIO

## Descripción 

TruequePrestamosGeek es una plataforma diseñada para facilitar el intercambio (trueque) y préstamo de videojuegos y libros entre usuarios. La aplicación permite a cualquier persona registrarse, publicar los productos que desea compartir, y a su vez, solicitar el intercambio o préstamo de productos ofrecidos por otros.

**Características Clave:**

1. **Registro de Usuarios:**  
   Los usuarios pueden crear su perfil ingresando datos básicos como nombre, correo electrónico y contraseña, lo que permite identificarlos y asociar sus publicaciones y solicitudes.  
2. **Gestión de Productos:**  
   Cada usuario puede agregar productos a la plataforma, especificando si se trata de un videojuego o un libro. Para los videojuegos se registra información como el título, descripción y plataforma, mientras que para los libros se indica el título, descripción y autor.  
3. **Solicitud de Trueque o Préstamo:**  
   Los usuarios pueden solicitar un trueque o préstamo para los productos registrados. Esto implica elegir un producto mediante su ID y especificar el tipo de solicitud (trueque o préstamo), gestionando posteriormente el estado de cada solicitud.  
4. **Listados y Actualización:**  
   La aplicación ofrece funciones para listar todos los productos disponibles y las solicitudes realizadas. Además, permite actualizar el estado de una solicitud (por ejemplo, de pendiente a aceptada), para reflejar el progreso y la gestión de cada transacción.  
5. **Interfaz de Usuario:**  
   Principalmente, la aplicación funciona a través de una interfaz de consola, con un menú interactivo que guía al usuario a través de las distintas funcionalidades. Se complementa con una pequeña interfaz gráfica para mostrar detalles o notificaciones de manera visual.  
6. **Persistencia y Robustez:**  
   Toda la información (usuarios, productos, solicitudes) se almacena utilizando un sistema de persistencia (a través de un patrón DAO, ya sea simulado o real), garantizando que los datos se mantienen íntegros y consistentes. Además, el sistema está preparado para gestionar errores y excepciones de forma robusta, asegurando una experiencia de usuario estable.

**Resumen para el Programador:**

* **Objetivo:** Facilitar el intercambio y préstamo de productos (videojuegos y libros) entre usuarios.  
* **Requisitos Funcionales:** Registro de usuarios, gestión de productos, creación y actualización de solicitudes, listados de productos y solicitudes.  
* **Interfaz:** Menú interactivo en consola con una pequeña parte de interfaz gráfica para mejorar la experiencia.  
* **Persistencia:** Uso de un sistema de almacenamiento (DAO) para mantener los datos y garantizar su integridad.  
* **Robustez:** Implementación de manejo de errores para asegurar el correcto funcionamiento en todas las operaciones.

Esta descripción recoge todos los aspectos esenciales del programa, permitiéndote entender los requerimientos necesarios para su desarrollo sin necesidad de entrar en detalles técnicos. ¡Es una base sólida para empezar a planificar la implementación\!

## Entidades

**A modo de ayuda, a continuación se listan brevemente las entidades y enumeraciones clave que podrían aparecer en el programa TruequePrestamosGeek. No tienen porque ser solo estas entidades, ni tampoco la lista de atributos es exahustiva:**

* **Usuario:**  
  **Representa a la persona que utiliza la plataforma. Cada usuario tiene atributos básicos como un identificador único (id), nombre, correo electrónico y contraseña. Es la entidad principal para asociar publicaciones y solicitudes.**  
* **Producto:**  
  **Es una entidad abstracta que engloba los ítems que pueden ser intercambiados o prestados. Se especializa en dos subtipos:**  
  * **Videojuego: Producto que, además de los atributos generales (como título y descripción), incluye atributos específicos como la plataforma en la que se juega (por ejemplo, PC, consola).**  
  * **Libro: Producto que contiene información adicional específica, como el autor, además del título y la descripción.**  
* **Solicitud:**  
  **Representa la petición realizada por un usuario para intercambiar o solicitar el préstamo de un producto. Incluye datos como un identificador único, el tipo de solicitud (trueque o préstamo), el usuario solicitante, el producto involucrado y el estado actual de la solicitud (por ejemplo, pendiente, aceptada, rechazada o finalizada).**  
* **Enumeraciones:**  
  * **TipoSolicitud:**  
    **Define el tipo de operación que se está solicitando. Por ejemplo:**  
    * **`TRUEQUE`**  
    * **`PRESTAMO`**  
  * **EstadoSolicitud:**  
    **Define el estado en el que se encuentra una solicitud. Por ejemplo:**  
    * **`PENDIENTE`**  
    * **`ACEPTADA`**  
    * **`RECHAZADA`**  
    * **`FINALIZADA`**

**A continuación se presenta una descripción detallada y formateada de las entidades, con una lista de sus propiedades/atributos, especificando el tipo de dato, restricciones y cualquier peculiaridad relevante:**

### **Entidad: Producto**

| Atributo | Tipo de Dato | Restricciones / Formato | Notas / Valor por Defecto |
| ----- | ----- | ----- | ----- |
| **id** | **Integer** | **\- No nulo \- Único \- Auto-generado** | **Se asigna automáticamente al registrar un usuario; no es editable manualmente.** |
| **nombre** | **String** | **\- No nulo \- Puede tener una longitud máxima (según implementación)** | **Debe contener el nombre completo del usuario. No se permiten cadenas vacías.** |
| **email** | **String** | **\- No nulo \- Formato válido de email (ej.: usuario@dominio.com) \- Único** | **Se recomienda validar el formato mediante expresiones regulares para asegurar la integridad del dato.** |
| **password** | **String** | **\- No nulo \- Debe cumplir políticas de seguridad (mínimo de caracteres, complejidad, etc.)** | **Es recomendable almacenarlo cifrado (por ejemplo, usando hash).** |

---

**Resumen:**

* **id: Identificador único del usuario, asignado de forma automática al momento del registro.**  
* **nombre: Almacena el nombre completo del usuario y no puede ser nulo ni vacío.**  
* **email: Debe ser un correo válido (por ejemplo, "usuario@dominio.com") y único en la base de datos; se valida mediante expresiones regulares.**  
* **password: Contiene la contraseña del usuario, que debe cumplir ciertas políticas de seguridad y, preferiblemente, se almacena en formato cifrado para proteger la información.**

**Esta descripción permite comprender claramente los requisitos y restricciones asociados a la entidad Usuario en el proyecto, facilitando la implementación y validación de los datos en el sistema.**

### **Entidad: Producto**

| Atributo | Tipo de Dato | Restricciones / Formato | Notas / Valor por Defecto |
| ----- | ----- | ----- | ----- |
| **id** | **Integer** | **\- No nulo \- Único \- Auto-generado** | **Se asigna automáticamente al registrar un producto; no es editable manualmente.** |
| **titulo** | **String** | **\- No nulo \- No vacío \- Posible límite de longitud (según requerimientos de la aplicación)** | **Debe reflejar el nombre del producto (ej.: "The Witcher 3" o "El Hobbit").** |
| **descripcion** | **String** | **\- No nulo \- Se recomienda que contenga detalles claros sobre el producto** | **Proporciona información complementaria (ej.: "Juego de rol de acción" o "Novela de fantasía").** |
| **propietario** | **Usuario** | **\- No nulo \- Referencia a la entidad Usuario, que identifica al creador del producto** | **Se asocia con el usuario que registra el producto; debe ser una referencia válida.** |

---

**Resumen:**

* **id: Identificador único del producto, asignado de forma automática y no modificable.**  
* **titulo: Nombre descriptivo del producto. Es obligatorio y se sugiere establecer un límite de longitud para garantizar la consistencia.**  
* **descripcion: Texto explicativo que brinda detalles adicionales sobre el producto. Es obligatorio y debe ser lo suficientemente claro para informar al usuario.**  
* **propietario: Relación con la entidad Usuario que indica quién ha registrado el producto. Es indispensable para asociar cada producto con su creador.**

**Esta descripción proporciona las bases para entender cómo se modela la entidad Producto en el sistema, asegurando que cada producto registrado cumpla con los requisitos mínimos de integridad y consistencia. Además, al ser una entidad abstracta, sus subclases (por ejemplo, Videojuego y Libro) extenderán esta estructura, añadiendo atributos específicos según corresponda.**

### **Entidad: Videojuego**

| Atributo | Tipo de Dato | Restricciones / Formato | Notas / Valor por Defecto |
| ----- | ----- | ----- | ----- |
| **(Hereda de Producto)** |  |  | **Incluye: id, titulo, descripcion y propietario (con las restricciones definidas en Producto).** |
| **plataforma** | **String** | **\- No nulo \- No vacío \- Puede estar restringido a un conjunto de valores predefinidos (por ejemplo, "PC", "Xbox", "PlayStation", "Switch")** | **Especifica la plataforma en la que el videojuego se puede ejecutar. Se recomienda validar el valor ingresado.** |

---

**Resumen:**

* **Herencia: La entidad Videojuego hereda los atributos básicos de Producto (id, titulo, descripcion y propietario).**  
* **plataforma: Es el atributo específico de un videojuego. Debe indicar la plataforma (por ejemplo, "PC" o "PlayStation") y no puede quedar vacío. Se sugiere limitar sus posibles valores a un conjunto definido para mantener la consistencia de la información.**

**Esta descripción garantiza que la entidad Videojuego se modela correctamente para almacenar toda la información necesaria y que se respetan las restricciones y formatos adecuados, facilitando su integración en el sistema.**

### **Entidad: Libro**

| Atributo | Tipo de Dato | Restricciones / Formato | Notas / Valor por Defecto |
| ----- | ----- | ----- | ----- |
| **(Hereda de Producto)** |  |  | **Incluye: id, titulo, descripcion y propietario (según las restricciones definidas en Producto).** |
| **autor** | **String** | **\- No nulo \- No vacío \- Puede tener un límite de longitud (según especificación)** | **Representa el nombre del autor del libro. Se recomienda validar que el campo contenga únicamente letras y espacios.** |

---

**Resumen:**

* **Herencia: La entidad Libro hereda los atributos básicos de Producto (id, titulo, descripcion y propietario).**  
* **autor: Es el atributo específico que identifica al autor del libro. Es obligatorio y se recomienda aplicar validaciones para asegurar que el contenido es adecuado (por ejemplo, que contenga solo letras y espacios) y que no quede vacío.**

**Esta descripción permite comprender cómo se modela la entidad Libro en el sistema, asegurando que se almacena toda la información necesaria y se respetan las restricciones para mantener la consistencia y calidad de los datos.**

### **Entidad: Solicitud**

| Atributo | Tipo de Dato | Restricciones / Formato | Notas / Valor por Defecto |
| ----- | ----- | ----- | ----- |
| **id** | **Integer** | **\- No nulo \- Único \- Auto-generado** | **Se asigna automáticamente al crear una solicitud; no es editable manualmente.** |
| **tipo** | **TipoSolicitud** | **\- No nulo \- Debe ser uno de los valores definidos en la enumeración (TRUEQUE, PRESTAMO)** | **Define el tipo de solicitud a realizar; se valida para que coincida con los valores permitidos.** |
| **solicitante** | **Usuario** | **\- No nulo \- Debe ser una referencia válida a la entidad Usuario** | **Indica el usuario que realiza la solicitud; se asocia mediante la relación con la entidad Usuario.** |
| **producto** | **Producto** | **\- No nulo \- Debe ser una referencia válida a la entidad Producto (o sus subclases: Videojuego o Libro)** | **Especifica el producto al que se aplica la solicitud.** |
| **estado** | **EstadoSolicitud** | **\- No nulo \- Debe ser uno de los valores definidos en la enumeración (PENDIENTE, ACEPTADA, RECHAZADA, FINALIZADA)** | **Valor por defecto: PENDIENTE. Representa el estado actual de la solicitud.** |

---

**Resumen:**

* **id: Identificador único de la solicitud, asignado automáticamente.**  
* **tipo: Define el tipo de solicitud y debe tomar uno de los valores de la enumeración TipoSolicitud (TRUEQUE o PRESTAMO).**  
* **solicitante: Representa el usuario que crea la solicitud, vinculado a la entidad Usuario.**  
* **producto: Indica el producto involucrado en la solicitud, derivado de la entidad Producto (o sus subclases Videojuego y Libro).**  
* **estado: Representa el estado actual de la solicitud, utilizando la enumeración EstadoSolicitud, con "PENDIENTE" como valor por defecto.**

**Esta descripción garantiza que la entidad Solicitud se modela de forma completa y coherente, permitiendo gestionar las peticiones de trueque o préstamo de manera precisa y con los controles necesarios para mantener la integridad y el seguimiento del estado de cada solicitud.**

