package Nivell1.Exercici5;

import Nivell1.Exercici5.entities.Person;
import Nivell1.Exercici5.entities.PersonSerializer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Person person1 = new Person("Paquita", 59);
        Person person2 = new Person("Antonio", 76);

        PersonSerializer.serializePerson(person1);
        PersonSerializer.deserializePerson(person1);

        PersonSerializer.serializePerson(person2);
        PersonSerializer.deserializePerson(person2);
    }
}
