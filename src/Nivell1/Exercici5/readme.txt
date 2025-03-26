El método serializePerson de la clase Person serializa el objeto person que se le pasa como parámetro en
un fichero .ser con el nombre del atributo name del objeto serializado en la carpeta ../Exercici5/persons
El método deserializePerson de la clase Person deserializa el archivo .ser correspondiente al objeto Person
que el usuario quiere deserializar y lo muestra en pantalla.

La carpeta de trabajo del Proyecto es Tasca5.

En la terminal navegar a ./Tasca5/src

Para compilar los archivos ejecutar el siguiente comando:
javac -d bin Nivell1/Exercici5/*.java

Esto compilará todos los archivos necesarios, generando los archivos .class en una carpeta bin.

Para ejecutar el programa, se debe escribir en la terminal el comando:
java -cp bin Nivell1.Exercici5.Main