package Nivell1.Exercici5;

import java.io.*;
import java.nio.file.Files;

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

    public static void serializeArchive(Person person) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(person.getName() + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(person);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deserializeArchive() throws IOException, ClassNotFoundException {

        try (FileInputStream fis = new FileInputStream("Paquita.ser");
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
