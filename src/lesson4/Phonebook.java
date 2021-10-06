package lesson4;

import java.util.ArrayList;
import java.util.HashMap;

public class Phonebook {
    private HashMap<String, ArrayList<String>> phonebook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        ArrayList<String> phonesForLastName = phonebook.getOrDefault(lastName, new ArrayList<>());
        phonesForLastName.add(phoneNumber);
        phonebook.put(lastName, phonesForLastName);
    }

    public ArrayList<String> get(String lastName) {
        return phonebook.get(lastName);
    }

    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.add("Иванов", "1111111");
        phonebook.add("Петров", "2222222");
        phonebook.add("Сидоров", "3333333");
        phonebook.add("Сидоров2", "44444444");

        System.out.println(phonebook.get("Сидоров"));
    }
}
