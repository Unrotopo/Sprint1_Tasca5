El método runWriter de la clase WriteFile atraviesa todas las carpetas y subcarpetas desde
el directorio elegido por el usuario y guarda en un archivo creado por el usuario el nombre
de la carpeta padre y los ficheros que contiene (el programa crea el directorio y el archivo .txt
si estos no existen), especificando si el fichero es una carpeta o un archivo, así como la última
fecha de modificación.

El método runFileReader de la clase ReadFile muestra por pantalla los contenidos de un fichero .txt
elegido por el usuario.

El método serializePerson de la clase Person serializa el objeto person que se le pasa como parámetro en
un fichero .ser con el nombre del atributo name del objeto serializado.
El método deserializePerson de la clase Person deserializa el archivo .ser e imprime el objeto en pantalla.

La carpeta de trabajo del Proyecto es Tasca5.

En la terminal navegar a Tasca5/src. Tanto la compilación como la ejecución se realizan desde este directorio

Para compilar los archivos ejecutar el siguiente comando:
javac Nivell1/Exercici5/*.java

Esto compilará todos los archivos necesarios, generando los archivos .class.

Para ejecutar el programa, se debe escribir en la terminal el comando:
java Nivell1.Exercici5.Main