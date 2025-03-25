package Nivell1.Exercici5;

import Nivell1.Exercici5.entities.Person;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person1 = new Person("Paquita", 59);

        Person.serializePerson(person1);
        Person.deserializePerson(person1);
    }
}
