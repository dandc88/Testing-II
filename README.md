# Este proyecto es la solución de Testing II, basado en el archivo "Apoyo desafio testing II"



# Proyecto Android - Pruebas Unitarias e Integración

Este proyecto se centra en la implementación de pruebas unitarias e integración para un proyecto Android utilizando Kotlin, coroutines, y StateFlow para manejar el estado de la API. 

Los requerimientos del desafio se han satisfecho mediante la creación de varias pruebas dirigidas a diferentes aspectos del proyecto.

## Requerimientos de Pruebas

1. **Usando un MockWebServer, crea un UnitTest para probar la API de OpenWeatherMap.**
    - Se desarrolló una prueba de integración utilizando MockWebServer para simular las respuestas de la API de OpenWeatherMap. Esta prueba verifica que el servicio `OpenWeatherService` maneja correctamente las respuestas y extrae los datos necesarios.

2. **Crea un Unit Test para probar una función asíncrona.**
    - Se implementó un Unit Test para probar la función `getWeatherDataById` dentro de `WeatherRepositoryImp`. Esta prueba simula la obtención de datos de clima desde la base de datos de manera asíncrona, asegurando que los datos se procesen correctamente dentro de una coroutine.

3. **Crea un Unit Test para probar las funciones del Mapper.**
    - Se creó una prueba específica para validar la conversión de objetos `WeatherWrapper` a `WeatherDto`. Esta prueba asegura que todos los campos del objeto se convierten y se asignan correctamente, manteniendo la integridad de los datos.

4. **Genera un reporte de pruebas.**
    - Se generó un reporte de pruebas utilizando el comando `./gradlew test`. El reporte detalla el resultado de cada prueba, incluyendo las pruebas exitosas y las fallidas. El reporte generado se encuentra en el siguiente path del repositorio:
      ```
      app/src/test/java/com/desafiolatam/weatherlatam/reporteTest/index.html
      ```

## Si desea ejecutar todas las pruebas y generar el reporte en su computador debe descargar el codigo y proceder asi en la terminal de Android Studio: 

Para acceder al reporte de pruebas, sigue estos pasos:

1. Ejecuta el comando de pruebas:
   ```bash
   ./gradlew test

2. Navega al directorio donde se encuentra el reporte:

   app/src/test/java/com/desafiolatam/weatherlatam/reporteTest/index.html

3. Abre el archivo `index.html` en tu navegador para ver el reporte completo de las pruebas.
   Este archivo proporciona un resumen de todas las pruebas ejecutadas, ofreciendo información detallada sobre pruebas exitosas, fallidas, y la cobertura de código.


