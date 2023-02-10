package java_core_2_2_census;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Найти количество несовершеннолетних (т.е. людей младше 18 лет):
        Stream<Person> underage = persons.stream();
        long countUnderage = underage.filter(x -> x.getAge() < 18)
                .count();
        System.out.println(countUnderage);

        // Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        Stream<Person> conscripts = persons.stream();
        List<String> familiesConscripts = conscripts.filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(familiesConscripts);

        // Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        Stream<Person> workable = persons.stream();
        List<Person> listWorkable = workable.filter(x -> x.getAge() >= 18 && x.getEducation() == Education.HIGHER &&
                ((x.getAge() <= 60 && x.getSex() == Sex.WOMAN) || (x.getAge() <= 65 && x.getSex() == Sex.MAN)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(listWorkable);
    }
}
