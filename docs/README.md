# Semana 4 Formativa: GestionInventarioJava

## Descripción del Proyecto
Este proyecto es una aplicación de consola en Java que permite gestionar el inventario de una tienda.
Permite agregar, eliminar, actualizar y listar productos en el inventario.

## Requisitos
| Funcionales                                                                                                                                                                | No funcionales                                                                             |
|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------|
| Agregar/eliminar productos del inventario                                                                                                                                  | Menú claro, mensajes de error descriptivos                                                 |
| Actualizar productos existentes(nombre, precio, stock, descripción)                                                                                                        | Validación de entradas de usuario <br/>y manejar excepciones sin interrumpir la ejecución  |
| Buscar un producto por ID                                                                                                                                                  | Separación de responsabilidades (entre clases Producto, Inventario y MenuPrincipal)        |
| Buscar productos por nombre o parte del nombre                                                                                                                             | Preparar estructura para persistencia de datos                                             |
| Buscar productos por descripción                                                                                                                                           | Código limpio y bien documentado                                                           |
| Listar todos los productos                                                                                                                                                 |                                                                                            |
| Generar informes simples:<br/>- Productos con bajo stock<br/>- Total productos en inventario <br/>-Total unidades en stock<br/>- Valor total del inventario                |                                                                                            |
| Mostrar menú principal en cosnsola con opciones para:<br/>- Agregar/eliminar productos<br/>- Buscar y listar productos<br/>- Generar reportes<br/>- Salir de la aplicación ||

## Estructura del Proyecto

El proyecto está organizado en las siguientes clases:
- src:
  - main:
    - java:
      - model:
        - `Producto`: Representa un producto en el inventario.
        - `Inventario`: Gestiona la lista de productos y las operaciones sobre ellos.
        - `Categoria`: Enum para las categorías de productos.

      - view:
          - `IView`: 
          - `MenuPrincipalView` implementa `IView`: Vista para el menú principal.
          - `ProductoView` implementa `IView`: Vista específica para productos.
          - `InventarioView` implementa `IView`: Vista específica para el inventario.

      - controller:
          - `IController`: Interfaz para el controlador.

      - demo:
        - `GestionInventario`: Clase principal que inicia la aplicación.
  - test:
    - java:
      - integracion:
        - `ProductoInventarioTest`: Pruebas de integración para las clases `Producto` e `Inventario`.
      - unitarias:
        - `ProductoTest`: Pruebas unitarias para la clase `Producto`.
        - `InventarioTest`: Pruebas unitarias para la clase `Inventario`.

## Ejecución del Proyecto
Para ejecutar el proyecto, sigue estos pasos:
1. Clonar el repositorio:
2. Navegar al directorio del proyecto
3. Compilar el proyecto usando un IDE como IntelliJ IDEA o NetBeans, o desde la línea de comandos con `javac`.
4. Ejecutar la clase `GestionInventario` para iniciar la aplicación.
5. Seguir las instrucciones en la consola para gestionar el inventario.

## Pruebas de integración y unitarias
El proyecto incluye pruebas de integración y unitarias para asegurar la funcionalidad correcta de las clases principales
implementadas con JUnit.
- Las pruebas unitarias se encuentran en la carpeta `test/unitarias` y cubren métodos individuales de las clases `Producto` e `Inventario`.
- Las pruebas de integración se encuentran en la carpeta `test/integracion` y verifican la interacción entre las clases `Producto` e `Inventario`.