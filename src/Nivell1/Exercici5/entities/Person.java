package Nivell1.Exercici5.entities;

import java.io.*;

public class Person implements Serializable {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    static String personsDirectory = "Nivell1" + File.separator +
            "Exercici5" + File.separator +
            "persons" + File.separator;

    public static void serializePerson(Person person) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(personsDirectory + person.getName() + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(person);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deserializePerson(Person serializedPerson) throws IOException, ClassNotFoundException {

        try (FileInputStream fis = new FileInputStream(personsDirectory + serializedPerson.getName() + ".ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Person person = (Person) ois.readObject();
            System.out.println(person);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }
}
