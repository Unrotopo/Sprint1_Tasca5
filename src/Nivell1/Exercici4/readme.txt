El método runWriter de la clase WriteFile atraviesa todas las carpetas y subcarpetas desde
el directorio elegido por el usuario y guarda en un archivo creado por el usuario el nombre
de la carpeta padre y los ficheros que contiene (el programa crea el directorio y el archivo .txt
si estos no existen), especificando si el fichero es una carpeta o un archivo, así como la última
fecha de modificación.

El método runFileReader de la clase ReadFile muestra por pantalla los contenidos de un fichero .txt
elegido por el usuario.

En la terminal navegar a ./Tasca5/src

Para compilar los archivos ejecutar el siguiente comando:
javac -d bin Nivell1/Exercici4/*.java

Esto compilará todos los archivos necesarios, generando los archivos .class en una carpeta bin.

Para ejecutar el programa, se debe escribir en la terminal el comando:
java -cp bin Nivell1.Exercici4.Main