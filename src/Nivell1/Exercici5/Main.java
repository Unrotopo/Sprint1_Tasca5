package Nivell1.Exercici5;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person1 = new Person("Paquita", 59);

        Person.serializeArchive(person1);
        Person.deserializeArchive();
    }
}
