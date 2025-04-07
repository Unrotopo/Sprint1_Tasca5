package Nivell1.Exercici5.entities;

import java.io.*;

public class PersonSerializer {

    static String personsDirectory = "Nivell1" + File.separator +
            "Exercici5" + File.separator +
            "persons" + File.separator;

    public static void serializePerson(Person person) throws IOException {

        File directory = new File(personsDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(personsDirectory + person.getName() + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(person);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deserializePerson(Person serializedPerson) throws IOException, ClassNotFoundException {

        File directory = new File(personsDirectory);
        if (!directory.exists()) {
            throw new FileNotFoundException();
        }

        try (FileInputStream fis = new FileInputStream(personsDirectory + serializedPerson.getName() + ".ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Person person = (Person) ois.readObject();
            System.out.println(person);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
