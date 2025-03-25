El método writeMoreSortedFiles de la clase TreeDirectoryContentSorterToFile atraviesa todas las carpetas
y subcarpetas desde el directorio Tasca5/src y guarda en un archivo (resources/sorted.txt) el nombre de
la carpeta padre y los ficheros que contiene, especificando si el ficheroe s una carpeta o un archivo, así
como la última fecha de modificación.

La carpeta de trabajo del Proyecto es Tasca5.

En la terminal navegar a ./Tasca5/src

Para compilar los archivos ejecutar el siguiente comando:
javac -d bin Nivell1/Exercici3/*.java

Esto compilará todos los archivos necesarios, generando los archivos .class en una carpeta bin.

Para ejecutar el programa, se debe escribir en la terminal el comando:
java -cp bin Nivell1.Exercici3.Main