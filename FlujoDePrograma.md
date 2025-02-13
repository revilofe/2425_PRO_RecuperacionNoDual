## Interacción

A continuación se presentan los requerimientos funcionales y no funcionales para el desarrollo de una aplicación de Trueque y Préstamos Geek. Se describen los casos de uso, las funcionalidades y las interacciones esperadas con el sistema.

### **1\. Menu principal**

**de menú en consola:**  

`=== Trueque & Préstamos Geek ===`  
`1. Registrar nuevo usuario`  
`2. Agregar producto`  
`3. Listar productos`  
`4. Crear solicitud`  
`5. Listar solicitudes`  
`6. Actualizar estado de solicitud`  
`7. Ficheros Carga productos (txt,json)`   
`8. Ficheros Descarga productos (txt,json)`   
`0. Salir`  
`Seleccione una opción:`

### **2\. Opción: Registrar Nuevo Usuario**

Esta funcionalidad permite a un usuario crear un perfil en la plataforma. A continuación, se describe detalladamente el comportamiento, las condiciones, posibles errores y las salidas esperadas:

---

#### **Flujo Normal de Ejecución**

1. **Inicio del Proceso:**

Al seleccionar la opción "1. Registrar nuevo usuario" desde el menú principal, el sistema muestra el encabezado:

`=== Registro de Usuario ===`


2. **Entrada de Datos:**
    * **Nombre:**  
      Se solicita al usuario que ingrese su nombre.  
      *Condición:* El nombre no debe estar vacío.
    * **Email:**  
      Se solicita el correo electrónico del usuario.  
      *Condiciones:*
        * No debe estar vacío.
        * Debe seguir un formato válido (por ejemplo, "usuario@dominio.com").
        * El sistema puede verificar que el email no esté ya registrado.
    * **Contraseña:**  
      Se solicita al usuario que ingrese una contraseña.  
      *Condición:*
        * No debe estar vacía.
        * Se pueden aplicar políticas de seguridad (mínimo de caracteres, complejidad, etc.).
3. **Procesamiento y Almacenamiento:**
    * Una vez ingresados los datos, el sistema valida cada uno de ellos.
    * Si todos los datos son correctos y cumplen las condiciones, se crea un nuevo objeto **Usuario** con un **id** auto-generado.
    * La información se almacena en la base de datos (o en la simulación de la base de datos, como un DAO en memoria).
4. **Salida en Caso de Éxito:**

El sistema muestra un mensaje de confirmación:

`Usuario "Ana Pérez" registrado exitosamente.`

---

#### **Posibles Errores y Condiciones a Validar**

1. **Nombre Vacío:**
    * **Error:** Si el usuario no ingresa ningún nombre o introduce solo espacios.

**Mensaje Esperado:**

`Error: El nombre no puede estar vacío. Por favor, ingrese un nombre válido.`


2. **Email Vacío o Inválido:**
    * **Error:**
        * Si el email está vacío.
        * Si el email no sigue el formato correcto (por ejemplo, falta el "@" o el dominio).

**Mensaje Esperado:**

`Error: Por favor, ingrese un email válido (ej. usuario@dominio.com).`


3. **Email ya Registrado:**
    * **Error:** Si el email ingresado ya existe en el sistema (verificación en la base de datos).

**Mensaje Esperado:**

`Error: El email "carlos@example.com" ya está registrado. Intente con otro.`

4. **Contraseña Vacía:**
    * **Error:** Si el usuario no ingresa una contraseña.

**Mensaje Esperado:**

`Error: La contraseña no puede estar vacía. Por favor, ingrese una contraseña segura.`


5. **Errores en la Conexión o Almacenamiento:**
    * **Error:** Si ocurre algún problema al guardar la información (por ejemplo, error en la base de datos).

**Mensaje Esperado:**

`Error: No se pudo registrar el usuario debido a un fallo interno. Inténtelo nuevamente más tarde.`


---

#### **Ejemplo de Ejecución Completa (Flujo Normal)**

`=== Registro de Usuario ===`  
`Ingrese nombre: Ana Pérez`  
`Ingrese email: ana.perez@example.com`  
`Ingrese contraseña: ********`  
`Usuario "Ana Pérez" registrado exitosamente.`

#### **Ejemplo de Ejecución con Error (Email Inválido)**

`=== Registro de Usuario ===`  
`Ingrese nombre: Carlos`  
`Ingrese email: carlosexample.com`  
`Error: Por favor, ingrese un email válido (ej. usuario@dominio.com).`  
`Ingrese email: carlos@example.com`  
`Ingrese contraseña: ********`  
`Usuario "Carlos" registrado exitosamente.`

### **3\. Opción: Agregar Producto**

Esta funcionalidad permite a un usuario (ya registrado) publicar un nuevo producto en la plataforma. El producto puede ser de dos tipos: **Videojuego** o **Libro**. A continuación se describe el flujo de la funcionalidad, las condiciones que deben cumplirse, los posibles errores y las salidas esperadas.

---

#### **Flujo Normal de Ejecución**

1. **Inicio del Proceso:**

Al seleccionar la opción "2. Agregar producto" desde el menú principal, el sistema muestra el encabezado:

`=== Agregar Producto ===`

2. **Entrada de Datos Comunes:**
    * **Título del Producto:**
        * Se solicita al usuario que ingrese el título del producto.
        * **Condición:** El título no debe estar vacío y, si se requiere, debe respetar un límite de longitud.
    * **Descripción del Producto:**
        * Se solicita una descripción breve que detalle las características del producto.
        * **Condición:** La descripción no debe estar vacía.
3. **Selección del Tipo de Producto:**

Se muestra el mensaje:

`Tipo de producto (1. Videojuego, 2. Libro):`

* El usuario debe ingresar "1" para Videojuego o "2" para Libro.
    * **Condición:** La opción debe ser numérica y estar entre 1 y 2\.
4. **Entrada de Datos Específicos Según el Tipo:**
    * **Si elige Videojuego:**

Se solicita el atributo específico:

`Ingrese plataforma:`

* **Condición:** La plataforma no debe estar vacía y se recomienda que sea uno de un conjunto predefinido (por ejemplo, "PC", "Xbox", "PlayStation", "Switch").
    * **Si elige Libro:**

Se solicita el atributo específico:

`Ingrese autor:`

* **Condición:** El nombre del autor no debe estar vacío; se recomienda validar que contenga solo letras y espacios.
5. **Procesamiento y Almacenamiento:**
    * Una vez ingresados y validados todos los datos, el sistema crea un nuevo objeto de tipo **Videojuego** o **Libro**, asignándole un **id** único (auto-generado) y asociándolo al usuario propietario.
    * La información se almacena mediante un DAO en la base de datos (o simulación en memoria).
6. **Salida en Caso de Éxito:**
    * El sistema muestra un mensaje de confirmación:

Para Videojuego:

`Producto "The Witcher 3" agregado correctamente.`

Para Libro:

`Producto "El Hobbit" agregado correctamente.`

---

#### **Posibles Errores y Condiciones a Validar**

1. **Título Vacío:**
    * **Error:** Si el usuario no ingresa ningún título o introduce solo espacios.

**Mensaje Esperado:**

`Error: El título del producto no puede estar vacío. Por favor, ingrese un título válido.`

2. **Descripción Vacía:**
    * **Error:** Si el usuario no ingresa una descripción o ingresa solo espacios.

**Mensaje Esperado:**

`Error: La descripción no puede estar vacía. Por favor, proporcione una descripción detallada.`

3. **Selección de Tipo Inválida:**
    * **Error:** Si el usuario ingresa un valor diferente a "1" o "2" al seleccionar el tipo de producto.

**Mensaje Esperado:**

`Error: Tipo de producto no reconocido. Por favor, ingrese "1" para Videojuego o "2" para Libro.`

4. **Dato Específico Vacío (Plataforma o Autor):**
    * **Error:** Si al solicitar la plataforma (en caso de videojuego) o el autor (en caso de libro) el usuario deja el campo vacío.

**Mensaje Esperado:**

Para Videojuego:

`Error: La plataforma no puede estar vacía. Por favor, ingrese una plataforma válida (ej. PC, Xbox, etc.).`

Para Libro:

`Error: El autor no puede estar vacío. Por favor, ingrese el nombre del autor.`

5. **Error en el Almacenamiento:**
    * **Error:** Si ocurre algún fallo al guardar el producto (por ejemplo, problemas con la base de datos o conflicto de identificadores).

**Mensaje Esperado:**

`Error: No se pudo agregar el producto debido a un fallo interno. Inténtelo nuevamente más tarde.`

---

#### **Ejemplos de Ejecución**

**Ejemplo de Flujo Normal (Videojuego):**

`=== Agregar Producto ===`  
`Ingrese título del producto: The Witcher 3`  
`Ingrese descripción: Juego de rol de acción.`  
`Tipo de producto (1. Videojuego, 2. Libro): 1`  
`Ingrese plataforma: PC`  
`Producto "The Witcher 3" agregado correctamente.`

**Ejemplo de Flujo Normal (Libro):**

`=== Agregar Producto ===`  
`Ingrese título del producto: El Hobbit`  
`Ingrese descripción: Novela de aventuras y fantasía.`  
`Tipo de producto (1. Videojuego, 2. Libro): 2`  
`Ingrese autor: J.R.R. Tolkien`  
`Producto "El Hobbit" agregado correctamente.`

**Ejemplo con Error (Título Vacío):**

`=== Agregar Producto ===`  
`Ingrese título del producto:`  
`Error: El título del producto no puede estar vacío. Por favor, ingrese un título válido.`  
`Ingrese título del producto: Super Mario Odyssey`  
`...`

**Ejemplo con Error (Tipo de Producto Inválido):**

`=== Agregar Producto ===`  
`Ingrese título del producto: Super Mario Odyssey`  
`Ingrese descripción: Videojuego de aventuras.`  
`Tipo de producto (1. Videojuego, 2. Libro): 3`  
`Error: Tipo de producto no reconocido. Por favor, ingrese "1" para Videojuego o "2" para Libro.`  
`Tipo de producto (1. Videojuego, 2. Libro): 1`  
`Ingrese plataforma: Nintendo Switch`  
`Producto "Super Mario Odyssey" agregado correctamente.`

### **4\. Opción: Listar Productos con Filtro**

Esta funcionalidad permite al usuario visualizar un listado de todos los productos registrados en la plataforma, pero añadiendo la posibilidad de aplicar un filtro antes de mostrar el listado. El usuario puede elegir el campo por el cual filtrar y proporcionar un valor de filtrado que puede ser una subcadena del valor completo. Además, en el listado se mostrará el estado en el que se encuentra cada producto (por ejemplo, "Disponible", "Prestado", "En trueque", etc.).

---

#### **Flujo Normal de Ejecución**

1. **Inicio del Proceso:**

Al seleccionar la opción "3. Listar productos" desde el menú principal, el sistema muestra el encabezado:

`=== Listado de Productos ===`

2. **Solicitud de Filtro:**

El sistema pregunta al usuario si desea aplicar un filtro:

`¿Desea filtrar los productos? (S/N):`

* **Si el usuario responde "S" (Sí):**
    * Se le solicita elegir el campo de filtrado. Los campos disponibles pueden ser:
        * **1\. Título**
            * **2\. Descripción**
            * **3\. Propietario**
            * **4\. Estado**

Ejemplo:

`Seleccione el campo por el que desea filtrar (1. Título, 2. Descripción, 3. Propietario, 4. Estado):`


Luego, se pide al usuario ingresar el valor de filtrado (puede ser una subcadena del valor completo):

`Ingrese el valor a filtrar:`

* **Si el usuario responde "N" (No):**
    * El sistema procede a listar todos los productos sin aplicar filtro.
3. **Recuperación y Filtrado de Datos:**
    * El sistema consulta la base de datos o la estructura en memoria para obtener todos los productos.
    * Si se especificó un filtro, el sistema recorre la lista y selecciona aquellos productos cuyo valor en el campo escogido contenga la subcadena ingresada (la búsqueda es insensible a mayúsculas/minúsculas, según se implemente).
4. **Visualización del Listado:**
    * Para cada producto que cumple la condición (o todos, si no se aplicó filtro), se muestra la siguiente información:
        * **ID del Producto**
        * **Título**
        * **Propietario (nombre)**
        * **Estado del Producto**

**Formato de Salida (ejemplo):**

`ID: 1, Título: The Witcher 3, Propietario: Ana Pérez, Estado: Disponible`  
`ID: 3, Título: Super Mario Odyssey, Propietario: Carlos, Estado: Prestado`

---

#### **Posibles Errores y Condiciones a Validar**

1. **Entrada Inválida para la Selección del Filtro:**
    * **Error:** El usuario ingresa una respuesta diferente a "S" o "N".

**Mensaje Esperado:**

`Error: Por favor, responda "S" para Sí o "N" para No.`

2. **Selección de Campo de Filtrado Inválida:**
    * **Error:** El usuario elige un valor diferente a las opciones disponibles (1, 2, 3 o 4).

**Mensaje Esperado:**

`Error: Campo de filtrado no reconocido. Por favor, seleccione una opción válida (1, 2, 3 o 4).`

3. **Valor de Filtrado Vacío:**
    * **Error:** El usuario decide filtrar, pero no ingresa ningún valor.

**Mensaje Esperado:**

`Error: El valor de filtrado no puede estar vacío. Por favor, ingrese una subcadena válida.`

4. **Lista de Productos Vacía:**
    * **Condición:** Si no existen productos registrados en el sistema.

**Mensaje Esperado:**

`No hay productos registrados.`

5. **Ningún Producto Coincide con el Filtro:**
    * **Condición:** Si se aplica un filtro y ningún producto cumple la condición.

**Mensaje Esperado:**

`No se encontraron productos que coincidan con el filtro.`

6. **Error en la Recuperación de Datos:**
    * **Error:** Problemas internos al consultar la base de datos o la estructura de datos.

**Mensaje Esperado:**

`Error: No se pudieron cargar los productos. Por favor, inténtelo de nuevo más tarde.`

---

#### **Ejemplos de Ejecución**

**Ejemplo 1: Listado sin Filtrado**

`=== Listado de Productos ===`  
`¿Desea filtrar los productos? (S/N): N`  
`ID: 1, Título: The Witcher 3, Propietario: Ana Pérez, Estado: Disponible`  
`ID: 2, Título: El Hobbit, Propietario: Juan García, Estado: Disponible`  
`ID: 3, Título: Super Mario Odyssey, Propietario: Carlos, Estado: Prestado`

**Ejemplo 2: Listado con Filtrado por Título**

`=== Listado de Productos ===`  
`¿Desea filtrar los productos? (S/N): S`  
`Seleccione el campo por el que desea filtrar (1. Título, 2. Descripción, 3. Propietario, 4. Estado): 1`  
`Ingrese el valor a filtrar: Witcher`  
`ID: 1, Título: The Witcher 3, Propietario: Ana Pérez, Estado: Disponible`

**Ejemplo 3: Listado con Filtro sin Resultados**

`=== Listado de Productos ===`  
`¿Desea filtrar los productos? (S/N): S`  
`Seleccione el campo por el que desea filtrar (1. Título, 2. Descripción, 3. Propietario, 4. Estado): 4`  
`Ingrese el valor a filtrar: Inactivo`  
`No se encontraron productos que coincidan con el filtro.`

**Ejemplo 4: Error en la Selección de Filtro**

`=== Listado de Productos ===`  
`¿Desea filtrar los productos? (S/N): X`  
`Error: Por favor, responda "S" para Sí o "N" para No.`

### **5\. Opción: Crear Solicitud**

Esta funcionalidad permite a un usuario solicitar la obtención de un producto registrado, diferenciando entre dos tipos de solicitud: **Trueque** y **Préstamo**. La diferencia se gestiona mediante el atributo **tipo** en la entidad **Solicitud**, el cual puede tomar el valor `TRUEQUE` o `PRESTAMO` (definidos en la enumeración **TipoSolicitud**). Según el tipo seleccionado, la aplicación puede aplicar reglas específicas (por ejemplo, para trueque, podría requerirse una propuesta de intercambio, mientras que para préstamo, se pueden establecer condiciones de uso y plazos de devolución).

---

#### **Flujo Normal de Ejecución**

1. **Inicio del Proceso:**

Al seleccionar la opción "4. Crear solicitud" desde el menú principal, el sistema muestra:

`=== Crear Solicitud ===`

2. **Ingreso del ID del Producto:**

El sistema solicita:

`Ingrese ID del producto:`

* **Condiciones:**
    * El valor debe ser numérico.
        * Debe corresponder a un producto existente.
3. **Selección del Tipo de Solicitud:**

Se muestra el mensaje:  
j  
`Tipo de solicitud (1. Trueque, 2. Préstamo):`

* **Condiciones:**
    * El usuario debe ingresar "1" para Trueque o "2" para Préstamo.
        * Según la opción (MEJORA QUE PUEDE AFECTAR AL RESTO DE OPCIONES):
            * **Trueque:** El usuario espera realizar un intercambio directo. En un flujo extendido, se podría solicitar información adicional para proponer el producto a intercambiar.
            * **Préstamo:** El usuario solicita el uso temporal del producto, por lo que se podrían definir condiciones de uso o plazos de devolución (aunque, en esta versión, se establece solo a nivel de datos mediante la enumeración).
4. **Creación y Almacenamiento de la Solicitud:**
    * El sistema valida que el producto exista.
    * Se genera un objeto **Solicitud**:
        * Se asigna un **id** auto-generado.
        * Se asocia el producto y el usuario solicitante.
        * Se define el atributo **tipo** según la opción seleccionada:
            * `TRUEQUE` si se eligió la opción 1\.
            * `PRESTAMO` si se eligió la opción 2\.
        * El estado inicial de la solicitud se fija como **PENDIENTE**.
    * La solicitud se almacena mediante el DAO correspondiente.
5. **Salida en Caso de Éxito:**
    * Se muestra un mensaje de confirmación. Ejemplo:

Para Trueque:

`Solicitud de TRUEQUE creada exitosamente con ID: 5 para el producto "The Witcher 3".`

Para Préstamo:

`Solicitud de PRÉSTAMO creada exitosamente con ID: 6 para el producto "The Witcher 3".`

---

#### **Posibles Errores y Validaciones**

1. **Error en la Entrada del ID del Producto:**
    * **Condición:** Valor no numérico o campo vacío.

**Mensaje Esperado:**

`Error: El ID ingresado no es válido. Por favor, ingrese un número de ID.`

2. **Producto No Encontrado:**
    * **Condición:** El ID ingresado no coincide con ningún producto registrado.

**Mensaje Esperado:**

`Error: Producto no encontrado. Por favor, verifique el ID e intente nuevamente.`

3. **Selección de Tipo de Solicitud Inválida:**
    * **Condición:** El usuario ingresa un valor distinto a "1" o "2".

**Mensaje Esperado:**

`Error: Tipo de solicitud no reconocido. Ingrese "1" para Trueque o "2" para Préstamo.`

4. **Error en el Almacenamiento de la Solicitud:**
    * **Condición:** Problemas internos (por ejemplo, error en el DAO o en la base de datos).

**Mensaje Esperado:**  

`Error: No se pudo crear la solicitud debido a un fallo interno. Inténtelo nuevamente más tarde.`

5. **Otros Errores de Validación:**
    * **Condición:** Datos incompletos o no válidos.

**Mensaje Esperado:**

`Error: Datos incompletos o inválidos. Por favor, revise la información ingresada.`

---

#### **Ejemplos de Ejecución**

**Ejemplo 1: Solicitud de Préstamo (Flujo Normal)**

`=== Crear Solicitud ===`  
`Ingrese ID del producto: 1`  
`Tipo de solicitud (1. Trueque, 2. Préstamo): 2`  
`Solicitud de PRÉSTAMO creada exitosamente con ID: 5 para el producto "The Witcher 3".`

**Ejemplo 2: Solicitud de Trueque (Flujo Normal)**

`=== Crear Solicitud ===`  
`Ingrese ID del producto: 1`  
`Tipo de solicitud (1. Trueque, 2. Préstamo): 1`  
`Solicitud de TRUEQUE creada exitosamente con ID: 6 para el producto "The Witcher 3".`

**Ejemplo 3: Error en la Entrada del ID (Valor No Numérico)**

`=== Crear Solicitud ===`  
`Ingrese ID del producto: abc`  
`Error: El ID ingresado no es válido. Por favor, ingrese un número de ID.`  
`Ingrese ID del producto: 1`  
`Tipo de solicitud (1. Trueque, 2. Préstamo): 2`  
`Solicitud de PRÉSTAMO creada exitosamente con ID: 7 para el producto "The Witcher 3".`

**Ejemplo 4: Selección de Tipo de Solicitud Inválida**

`=== Crear Solicitud ===`  
`Ingrese ID del producto: 1`  
`Tipo de solicitud (1. Trueque, 2. Préstamo): 3`  
`Error: Tipo de solicitud no reconocido. Ingrese "1" para Trueque o "2" para Préstamo.`  
`Tipo de solicitud (1. Trueque, 2. Préstamo): 1`  
`Solicitud de TRUEQUE creada exitosamente con ID: 8 para el producto "The Witcher 3".`

### **6\. Opción: Listar Solicitudes**

Esta funcionalidad permite al usuario visualizar un listado de solicitudes, con la opción de aplicar filtros tanto por el tipo de solicitud (activas o histórico) como por el rol del usuario en la transacción. Es decir, el usuario podrá filtrar:

* **Solicitudes propias:** Aquellas solicitudes que el usuario ha realizado. En este listado se mostrará:
    * **Producto**
    * **Estado**
    * **Tipo de solicitud** (Trueque o Préstamo)
    * **Propietario** del producto
* **Solicitudes a mis productos:** Aquellas solicitudes que otros usuarios han realizado sobre los productos registrados por el usuario. En este listado se mostrará:
    * **Producto**
    * **Estado**
    * **Tipo de solicitud** (Trueque o Préstamo)
    * **Solicitante** (quien ha solicitado el producto)

**Nota:** No se puede aplicar ambos filtros (solicitudes propias y solicitudes a mis productos) al mismo tiempo; el usuario debe elegir una de las dos opciones.

---

#### **Flujo Normal de Ejecución**

1. **Inicio del Proceso:**
    * El usuario selecciona la opción "5. Listar solicitudes" desde el menú principal.

Se muestra el encabezado:

`=== Listado de Solicitudes ===`

2. **Selección del Filtro por Estado:**
    * Se le pregunta al usuario si desea ver:
        * **Solicitudes activas:** Aquellas solicitudes cuyo estado es “PENDIENTE” o en proceso.
        * **Histórico:** Solicitudes ya finalizadas, rechazadas o concluidas.

Ejemplo:

`¿Desea ver solicitudes activas o histórico? (A/H):`

* **Condición:** La entrada debe ser "A" para activas o "H" para histórico.
3. **Selección del Filtro por Rol:**
    * Posteriormente se le pregunta al usuario qué conjunto de solicitudes desea listar:
        * **Mis solicitudes:** Las solicitudes que el usuario ha realizado.
        * **Solicitudes a mis productos:** Las solicitudes realizadas por otros usuarios sobre los productos que el usuario posee.

Ejemplo:

`¿Desea listar sus solicitudes o las solicitudes realizadas a sus productos? (1: Mis solicitudes / 2: Solicitudes a mis productos):`

* **Condición:** El usuario debe ingresar "1" o "2".
4. **Procesamiento y Visualización:**
    * El sistema consulta la base de datos o la estructura en memoria y aplica los filtros seleccionados.
    * Se muestra el listado según la opción elegida:
        * **Si se eligen "Mis solicitudes":**  
          Se mostrarán registros con los campos:  
          `Producto | Estado | Tipo de Solicitud | Propietario`
        * **Si se eligen "Solicitudes a mis productos":**  
          Se mostrarán registros con los campos:  
          `Producto | Estado | Tipo de Solicitud | Solicitante`

---

#### **Posibles Errores y Condiciones a Validar**

1. **Entrada Inválida en el Filtro de Estado:**
    * **Error:** Si el usuario ingresa una respuesta distinta de "A" o "H".

**Mensaje Esperado:**

`Error: Por favor, responda "A" para solicitudes activas o "H" para histórico.`

2. **Entrada Inválida en el Filtro por Rol:**
    * **Error:** Si el usuario ingresa un valor distinto de "1" o "2".

**Mensaje Esperado:**

`Error: Opción no reconocida. Ingrese "1" para ver sus solicitudes o "2" para ver las solicitudes a sus productos.`

3. **Lista de Solicitudes Vacía:**
    * **Condición:** Si, tras aplicar el filtro, no se encuentra ningún registro.

**Mensaje Esperado:**

`No se encontraron solicitudes que coincidan con los filtros aplicados.`

4. **Error en la Recuperación de Datos:**
    * **Error:** Si ocurre algún problema técnico al consultar la base de datos.

**Mensaje Esperado:**

`Error: No se pudieron cargar las solicitudes. Por favor, inténtelo de nuevo más tarde.`

---

#### **Ejemplos de Ejecución**

**Ejemplo 1: Listado de Mis Solicitudes Activas**

`=== Listado de Solicitudes ===`  
`¿Desea ver solicitudes activas o histórico? (A/H): A`  
`¿Desea listar sus solicitudes o las solicitudes realizadas a sus productos? (1: Mis solicitudes / 2: Solicitudes a mis productos): 1`  
`ID: 5, Producto: The Witcher 3, Estado: PENDIENTE, Tipo: PRÉSTAMO, Propietario: Ana Pérez`  
`ID: 7, Producto: Super Mario Odyssey, Estado: PENDIENTE, Tipo: TRUEQUE, Propietario: Carlos`

**Ejemplo 2: Listado de Solicitudes a Mis Productos (Histórico)**

`=== Listado de Solicitudes ===`  
`¿Desea ver solicitudes activas o histórico? (A/H): H`  
`¿Desea listar sus solicitudes o las solicitudes realizadas a sus productos? (1: Mis solicitudes / 2: Solicitudes a mis productos): 2`  
`ID: 6, Producto: El Hobbit, Estado: FINALIZADA, Tipo: PRÉSTAMO, Solicitante: Juan García`  
`ID: 8, Producto: Super Mario Odyssey, Estado: RECHAZADA, Tipo: TRUEQUE, Solicitante: Marta López`

**Ejemplo 3: Entrada Inválida en Filtro de Estado**

`=== Listado de Solicitudes ===`  
`¿Desea ver solicitudes activas o histórico? (A/H): X`  
`Error: Por favor, responda "A" para solicitudes activas o "H" para histórico.`

**Ejemplo 4: Entrada Inválida en Filtro por Rol**

`=== Listado de Solicitudes ===`  
`¿Desea ver solicitudes activas o histórico? (A/H): A`  
`¿Desea listar sus solicitudes o las solicitudes realizadas a sus productos? (1: Mis solicitudes / 2: Solicitudes a mis productos): 3`  
`Error: Opción no reconocida. Ingrese "1" para ver sus solicitudes o "2" para ver las solicitudes a sus productos.`

---

### **7\. Opción: Actualizar Estado de Solicitud**

**Esta funcionalidad permite al usuario modificar el estado de una solicitud ya registrada. Dependiendo de la acción a realizar, se pueden rechazar o finalizar solicitudes, siguiendo reglas de transición de estado específicas:**

* **Rechazar la Solicitud:** Si el usuario decide no aceptar la solicitud (ya sea de préstamo o trueque), la solicitud puede ser rechazada. Esto solo es posible si la solicitud se encuentra en estado PENDIENTE.
* **Finalizar la Solicitud:** Una solicitud solo puede ser finalizada si ya ha sido aceptada. Esto refleja el caso en que se completa la transacción: ya sea que se haya devuelto el libro prestado o se haya realizado el trueque.

---

#### **Flujo Normal de Ejecución**

1. **Inicio del Proceso:**

**Al seleccionar la opción "6. Actualizar estado de solicitud" en el menú principal, el sistema muestra:**

**`=== Actualizar Estado de Solicitud ===`**

2. **Ingreso del ID de la Solicitud:**

**El sistema solicita:**

**`Ingrese ID de la solicitud:`**

* **Condición:**
    * **El ID debe ser un valor numérico.**
        * **El ID debe corresponder a una solicitud existente.**
3. **Selección del Nuevo Estado:**
    * **El sistema solicita que se ingrese el nuevo estado. Dependiendo del estado actual de la solicitud, se permiten las siguientes transiciones:**
        * **Desde PENDIENTE:**
            * **Se permite ACCEPTAR (cambiar a ACEPTADA) o RECHAZAR (cambiar a RECHAZADA).**
        * **Desde ACEPTADA:**
            * **Se permite FINALIZAR (cambiar a FINALIZADA).**

**El sistema muestra un mensaje ejemplo:**

**`Ingrese el nuevo estado:`**

**`1. Aceptada`**

**`2. Rechazada`**

**`3. Finalizada`**

* **Condición:**
    * **Si la solicitud está en estado PENDIENTE, solo se deben aceptar opciones 1 (ACEPTADA) o 2 (RECHAZADA).**
        * **Si la solicitud está en estado ACEPTADA, se debe aceptar únicamente la opción 3 (FINALIZADA).**
4. **Validación y Actualización:**
    * **El sistema valida la transición:**
        * **Si la solicitud está en PENDIENTE y se elige RECHAZADA o ACEPTADA, se actualiza el estado en consecuencia.**
        * **Si la solicitud está en ACEPTADA y se elige FINALIZADA, se actualiza el estado.**
    * **Si se intenta realizar una transición no permitida (por ejemplo, finalizar una solicitud que no esté en ACEPTADA), se muestra un mensaje de error.**
5. **Salida en Caso de Éxito:**

**Se muestra un mensaje de confirmación:**

**`Solicitud X actualizada a estado: ACEPTADA.`**

**o**

**`Solicitud X actualizada a estado: FINALIZADA.`**


---

#### **Tabla de Estados y Transiciones Permitidas**

| Estado Actual | Acción | Nuevo Estado | Descripción |
| ----- | ----- | ----- | ----- |
| **PENDIENTE** | **Aceptar** | **ACEPTADA** | **La solicitud es aceptada; se aprueba la transacción de trueque o préstamo.** |
| **PENDIENTE** | **Rechazar** | **RECHAZADA** | **La solicitud es rechazada; el usuario decide no aceptar la solicitud.** |
| **ACEPTADA** | **Finalizar** | **FINALIZADA** | **La solicitud se finaliza; se ha completado el trueque o se ha devuelto el producto prestado.** |

**Nota:**

* **No se permiten otras transiciones.**
* **Una solicitud en estado RECHAZADA o FINALIZADA no puede modificarse nuevamente a otro estado.**

---

#### **Posibles Errores y Mensajes de Error**

1. **Entrada Inválida del ID de Solicitud:**
    * **Error: El valor no es numérico o el ID no existe.**

**Mensaje:**

**`Error: El ID ingresado no es válido. Por favor, ingrese un número de ID existente.`**

2. **Transición No Permitida:**
    * **Error: Intentar finalizar una solicitud que no se encuentra en estado ACEPTADA, o rechazar una solicitud que ya no está en estado PENDIENTE.**
    * **Mensaje:**

**Si se intenta finalizar una solicitud que no es ACEPTADA:**

**`Error: Solo se puede finalizar una solicitud que esté en estado ACEPTADA.`**

**Si se intenta rechazar una solicitud que ya ha sido aceptada:**

**`Error: Solo se puede rechazar una solicitud que esté en estado PENDIENTE.`**

3. **Selección Inválida del Nuevo Estado:**
    * **Error: El usuario ingresa una opción fuera de las permitidas (por ejemplo, opción "3" cuando la solicitud está en PENDIENTE).**

**Mensaje:**

**`Error: Opción de estado no válida para la transición actual.`**

4. **Error Interno en el Almacenamiento:**
    * **Error: Problemas técnicos durante la actualización del estado en la base de datos o en el DAO.**

**Mensaje:**

**`Error: No se pudo actualizar la solicitud debido a un fallo interno. Inténtelo nuevamente más tarde.`**

---

#### **Ejemplos de Ejecución**

**Ejemplo 1: Transición Correcta desde PENDIENTE a ACEPTADA**

**`=== Actualizar Estado de Solicitud ===`**

**`Ingrese ID de la solicitud: 5`**

**`Tipo de actualización:`**

**`1. Aceptada`**

**`2. Rechazada`**

**`Ingrese opción: 1`**

**`Solicitud 5 actualizada a estado: ACEPTADA.`**

**Ejemplo 2: Transición Correcta desde PENDIENTE a RECHAZADA**

**`=== Actualizar Estado de Solicitud ===`**

**`Ingrese ID de la solicitud: 6`**

**`Tipo de actualización:`**

**`1. Aceptada`**

**`2. Rechazada`**

**`Ingrese opción: 2`**

**`Solicitud 6 actualizada a estado: RECHAZADA.`**

**Ejemplo 3: Transición Correcta desde ACEPTADA a FINALIZADA**

**`=== Actualizar Estado de Solicitud ===`**

**`Ingrese ID de la solicitud: 7`**

**`Tipo de actualización:`**

**`3. Finalizada`**

**`Ingrese opción: 3`**

**`Solicitud 7 actualizada a estado: FINALIZADA.`**

**Ejemplo 4: Error en la Transición (Finalizar Solicitud no ACEPTADA)**

**`=== Actualizar Estado de Solicitud ===`**

**`Ingrese ID de la solicitud: 8`**

**`Tipo de actualización:`**

**`3. Finalizada`**

**`Ingrese opción: 3`**

**`Error: Solo se puede finalizar una solicitud que esté en estado ACEPTADA.`**

**Ejemplo 5: Selección Inválida del Nuevo Estado**

**`=== Actualizar Estado de Solicitud ===`**

**`Ingrese ID de la solicitud: 9`**

**`Tipo de actualización:`**

**`1. Aceptada`**

**`2. Rechazada`**

**`Ingrese opción: 3`**

**`Error: Opción de estado no válida para la transición actual.`**

### **8\. Opción: Ficheros \- Carga de Productos (TXT, JSON)**

**Descripción:**  
**Esta funcionalidad permite al usuario cargar datos de productos desde un archivo externo en formato TXT o JSON. Los productos leídos se asocian automáticamente al usuario actualmente logueado. Es fundamental que el archivo tenga el formato esperado para que la carga se realice correctamente.**

---

#### **Flujo Normal de Ejecución**

1. **Selección de la Opción:**
    * **El usuario selecciona la opción "7. Ficheros Carga productos (txt,json)" en el menú principal.**
2. **Elección del Formato del Archivo:**

**El sistema solicita al usuario indicar el formato del archivo a cargar:**

**`Indique el formato del archivo (1. TXT, 2. JSON):`**

* **Condición: La entrada debe ser "1" o "2".**
3. **Ingreso del Nombre o Ruta del Archivo:**

**Se solicita al usuario proporcionar el nombre o ruta del archivo que contiene los datos.**

**`Ingrese la ruta/nombre del archivo:`**

* **Condición:**
    * **El archivo debe existir y estar accesible.**
        * **El archivo debe seguir el formato predefinido.**
4. **Carga y Validación de Datos:**
    * **El sistema lee el archivo y valida el contenido:**
        * **Para TXT:**  
          **Se espera un formato donde cada línea contiene los campos del producto (por ejemplo, `titulo;descripcion;tipo;atributo_especifico`) separados por un delimitador, como el punto y coma (`;`).**
        * **Para JSON:**  
          **Se espera un array de objetos JSON, donde cada objeto representa un producto con sus atributos (ejemplo: `{"titulo": "The Witcher 3", "descripcion": "Juego de rol", "tipo": "Videojuego", "plataforma": "PC"}` o `{"titulo": "El Hobbit", "descripcion": "Novela", "tipo": "Libro", "autor": "J.R.R. Tolkien"}`).**
    * **Los productos leídos se asocian al usuario logueado y se almacenan en la base de datos o estructura en memoria.**
5. **Salida en Caso de Éxito:**

**Se muestra un mensaje confirmando la carga exitosa:**

**`Productos cargados exitosamente desde el archivo "productos.json".`**

---

#### **Posibles Errores y Mensajes**

1. **Formato Inválido en la Selección de Formato:**
    * **Error: Entrada distinta de "1" o "2".**

**Mensaje:**

**`Error: Formato no reconocido. Ingrese "1" para TXT o "2" para JSON.`**

2. **Archivo No Encontrado o Inaccesible:**
    * **Error: La ruta o nombre de archivo ingresado no existe o no se puede acceder.**

**Mensaje:**

**`Error: No se encontró el archivo. Verifique la ruta y vuelva a intentarlo.`**

3. **Formato de Archivo Incorrecto:**
    * **Error: El contenido del archivo no sigue el formato predefinido.**

**Mensaje:**

**`Error: El formato del archivo es incorrecto o está incompleto. Verifique la estructura de los datos.`**

4. **Error Durante la Carga de Datos:**
    * **Error: Problemas internos al leer o procesar el archivo (por ejemplo, error de parseo).**

**Mensaje:**

**`Error: Ocurrió un fallo al cargar los productos. Inténtelo nuevamente.`**

---

#### **Ejemplo de Ejecución**

**Ejemplo TXT:**

**`Indique el formato del archivo (1. TXT, 2. JSON): 1`**

**`Ingrese la ruta/nombre del archivo: productos.txt`**

**`Producto "The Witcher 3" cargado correctamente.`**

**`Producto "El Hobbit" cargado correctamente.`**

**`Productos cargados exitosamente desde el archivo "productos.txt".`**

**Ejemplo JSON:**

**`Indique el formato del archivo (1. TXT, 2. JSON): 2`**

**`Ingrese la ruta/nombre del archivo: productos.json`**

**`Producto "The Witcher 3" cargado correctamente.`**

**`Producto "El Hobbit" cargado correctamente.`**

**`Productos cargados exitosamente desde el archivo "productos.json".`**

---

### **9\. Opción: Ficheros \- Descarga de Productos (TXT, JSON)**

**Descripción:**  
**Esta funcionalidad permite al usuario logueado exportar sus productos registrados a un archivo en formato TXT o JSON. El archivo resultante contendrá todos los datos de los productos asociados al usuario, formateados de manera estructurada.**

---

#### **Flujo Normal de Ejecución**

1. **Selección de la Opción:**
    * **El usuario selecciona la opción "8. Ficheros Descarga productos (txt, json)" desde el menú principal.**
2. **Elección del Formato de Descarga:**

**El sistema solicita al usuario indicar el formato de salida:**

**`Indique el formato de descarga (1. TXT, 2. JSON):`**

* **Condición: La entrada debe ser "1" o "2".**
3. **Procesamiento y Generación del Archivo:**
    * **El sistema consulta la lista de productos asociados al usuario logueado.**
    * **Se formatea la información según el formato seleccionado:**
        * **Para TXT:**  
          **Se genera un archivo de texto donde cada línea representa un producto. Los campos se separan por un delimitador (por ejemplo, ";").**  
          **Ejemplo de línea:**  
          **`1;The Witcher 3;Juego de rol;Videojuego;PC;Disponible`**

**Para JSON:**  
**Se genera un archivo JSON con un array de objetos, donde cada objeto contiene los atributos del producto.**  
**Ejemplo de objeto:**

**`{`**

**`"id": 1,`**

**`"titulo": "The Witcher 3",`**

**`"descripcion": "Juego de rol",`**

**`"tipo": "Videojuego",`**

**`"atributo_especifico": "PC",`**

**`"estado": "Disponible"`**

**`}`**

4. **Descarga y Confirmación:**
    * **El archivo se guarda en una ubicación predefinida o se ofrece al usuario para su descarga.**

**Se muestra un mensaje confirmando la descarga exitosa:**

**`Productos exportados exitosamente en formato JSON al archivo "mis_productos.json".`**

---

#### **Posibles Errores y Mensajes**

1. **Entrada Inválida para Selección de Formato:**
    * **Error: El usuario ingresa un valor distinto de "1" o "2".**

**Mensaje:**

**`Error: Formato no reconocido. Ingrese "1" para TXT o "2" para JSON.`**

2. **No Hay Productos Registrados:**
    * **Condición: Si el usuario no tiene productos registrados.**

**Mensaje:**

**`No tiene productos registrados para exportar.`**

3. **Error en la Generación del Archivo:**
    * **Error: Problemas al escribir el archivo (por ejemplo, falta de permisos o error interno).**

**Mensaje:**

**`Error: No se pudo generar el archivo de exportación. Inténtelo nuevamente.`**

---

#### **Ejemplo de Ejecución**

**Ejemplo TXT:**

**`Indique el formato de descarga (1. TXT, 2. JSON): 1`**

**`Se han exportado 3 productos a "mis_productos.txt".`**

**Ejemplo JSON:**

**`Indique el formato de descarga (1. TXT, 2. JSON): 2`**

**`Se han exportado 3 productos a "mis_productos.json".`**

**Ejemplo con Error (Sin Productos):**

**`Indique el formato de descarga (1. TXT, 2. JSON): 2`**

**`No tiene productos registrados para exportar.`**
