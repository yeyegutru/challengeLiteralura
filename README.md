# Gestión de Biblioteca con Java y Spring Boot

Este proyecto utiliza **Java** y **Spring Boot** como base para implementar una solución que permite la gestión y consulta de libros y autores. El objetivo principal es ofrecer una herramienta sencilla y funcional que facilite la interacción con datos almacenados en una base de datos relacional.

---

## 🚀 Características Principales

### 📚 Gestión de Libros
- **Búsqueda por título:** Encuentra libros ingresando el título parcial o completo.
- **Listado por idioma:** Filtra los libros según el idioma de publicación.

### ✍️ Gestión de Autores
- **Consulta por nombre:** Obtén información detallada de un autor y los libros que ha escrito.
- **Autores vivos:** Identifica qué autores estaban activos en un año específico.

### 🛠️ Operaciones Personalizadas
- Ordena los resultados alfabéticamente o por popularidad.
- Soporte para múltiples idiomas.

---

## 🎯 Propósito

El proyecto está diseñado para proporcionar una solución ligera y adaptable para gestionar información literaria. Es ideal como base para ampliar funcionalidades y explorar el uso de **Spring Boot** en aplicaciones de consola.

---

## 🔧 Requisitos

Antes de ejecutar este proyecto, asegúrate de contar con:

- **Java 17** o superior
- **Maven 3.6** o superior
- **Git** para gestionar el repositorio

---

## ⚙️ Configuración Inicial

1. Clona este repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tuusuario/tu-repositorio.git
   ```

2. Navega al directorio del proyecto:
   ```bash
   cd tu-repositorio
   ```

3. Configura el archivo `application.properties` ubicado en `src/main/resources` con los datos de tu entorno.

### Ejemplo de Configuración

```properties
# Configuración básica
spring.application.name=gestion-biblioteca

# Configuración de la base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/gestion_biblioteca
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuración de JPA y Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
```

---

## 🏃‍♂️ Ejecución

1. Compila y ejecuta el proyecto con Maven:
   ```bash
   mvn spring-boot:run
   ```

2. Interactúa con la aplicación a través de la consola para realizar búsquedas, listar información y consultar estadísticas.

---

## 📌 Consideraciones

- La aplicación es únicamente de consola, por lo que no requiere un frontend.
- Los datos iniciales deben estar disponibles en la base de datos para obtener resultados precisos.
- Asegúrate de que las dependencias de Maven estén correctamente descargadas.

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
- **Hibernate**
- **MySQL**
- **Maven**

---

## 📝 Licencia

Este proyecto está bajo una licencia de uso personal. Si deseas contribuir o utilizarlo para otros fines, por favor contáctame.

---

¡Gracias por explorar este proyecto! 🚀
