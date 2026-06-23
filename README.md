🍔 Food Store - Sistema de Gestión de Pedidos de Comida

Trabajo Práctico Integrador para la materia Programación II (Tecnicatura Universitaria en Programación - UTN).

Se trata de un sistema de consola desarrollado en Java, orientado a la gestión de un negocio de comidas mediante Programación Orientada a Objetos (POO). El sistema permite gestionar categorías, productos, usuarios y pedidos mediante operaciones CRUD en memoria (usando Colecciones).

📌 Enlaces Obligatorios de Entrega

🎥 Video Demostrativo: https://drive.google.com/drive/folders/1xq-hMYGvY5TJHOxKrLxPqUB0Hpl_oAHB

📄 Documentación Técnica: https://drive.google.com/file/d/1mDtAqpvLzEaZJuZdPjAD2TlDyZsx7D8W/view?usp=drive_link


-----------------------
⚙️Requisitos Previos

Para poder ejecutar este proyecto, es necesario contar con:
[1] Java Development Kit (JDK): Versión 21 o superior.
[2] IDE (Opcional pero recomendado): Apache NetBeans, IntelliJ IDEA o Eclipse.



-----------------------------
🚀 Cómo ejecutar el proyecto


[-] Desde Apache NetBeans (Recomendado)
[1] Clonar este repositorio o extraer el archivo .zip.
[2] Abrir Apache NetBeans y seleccionar File > Open Project...
[3] Navegar hasta la carpeta raíz del proyecto (FoodStoreTPI) y presionar Open Project.
[4] Hacer clic derecho sobre el proyecto en la pestaña "Projects" y seleccionar Run (o presionar la tecla F6).



[-] Desde Consola / Terminal
[1] Navegar hasta la carpeta src del proyecto:
cd FoodStoreTPI/src
[2] Compilar los archivos Java:
javac main/Main.java
[3] Ejecutar el menú principal:
java main.Main


-------------------------
🏗️Arquitectura y Paquetes

El proyecto respeta la arquitectura recomendada por la cátedra, separando claramente responsabilidades:

[entities] : Clases de modelo de dominio y herencia (Base, Producto, Pedido, etc.).
[enums] : Enumeraciones para restringir datos (Estado, FormaPago, Rol).
[exception] : Excepciones propias del dominio y reglas de negocio (ValidacionNegocioException, etc.).
[interfaces] : Comportamientos estandarizados (Calculable).
[service] : Lógica de negocio, validaciones y colecciones en memoria que gestionan el CRUD.
[main] : Punto de entrada (AppMenu) con interfaz de usuario en consola y captura de excepciones mediante try-catch.


------------------------
👥 Equipo de Desarrollo

[-] Savo Merino - Comisión 8 | Código, Testing y Documentación.
[-] Franco Petrucci - Comisión 10 | Código y Testing.


Desarrollado para el ciclo lectivo 2026 - Universidad Tecnológica Nacional.