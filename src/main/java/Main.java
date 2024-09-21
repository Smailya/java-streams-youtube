import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Filter females and print immediately (combined filtering and printing)
        people.stream()
                .filter(p -> p.getGender() == Gender.FEMALE) // Used '==' for enums (more idiomatic)
                .forEach(System.out::println); // Reduced lines by combining filtering and printing

        // Sort by age and gender in reversed order and print immediately (combined sorting and printing)
        people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getGender).reversed()) // Sorting done inline
                .forEach(System.out::println); // Combined sorting and printing, saving extra steps

        // All match (prints result directly instead of storing in a variable)
        System.out.println(people.stream().allMatch(p -> p.getAge() > 8)); // Removed intermediate variable

        // Any match (prints result directly instead of storing in a variable)
        System.out.println(people.stream().anyMatch(p -> p.getAge() > 121)); // Reduced verbosity

        // None match (prints result directly instead of storing in a variable)
        System.out.println(people.stream().noneMatch(p -> p.getName().equals("Antonio"))); // Same as above

        // Max (prints the max person by age immediately if present)
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println); // Combined max operation with print

        // Min (prints the min person by age immediately if present)
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println); // Combined min operation with print

        // Group by gender and print the groups inline (combined grouping and printing)
        people.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .forEach((gender, persons) -> { // Removed unnecessary variable assignment
                    System.out.println(gender);
                    persons.forEach(System.out::println); // Prints each person within the group
                });

        // Find the oldest female and print her name (combined filtering, max operation, and printing)
        people.stream()
                .filter(p -> p.getGender() == Gender.FEMALE) // Filtering for females
                .max(Comparator.comparing(Person::getAge)) // Find the oldest
                .map(Person::getName) // Get the name
                .ifPresent(System.out::println); // Print if present (all in one chain)
    }

    // Mock data for testing
    private static List<Person> getPeople() {
        return List.of(
            new Person("Antonio", 20, Gender.MALE),
            new Person("Alina Smith", 33, Gender.FEMALE),
            new Person("Helen White", 57, Gender.FEMALE),
            new Person("Alex Boz", 14, Gender.MALE),
            new Person("Jamie Goa", 99, Gender.MALE),
            new Person("Anna Cook", 7, Gender.FEMALE),
            new Person("Zelda Brown", 120, Gender.FEMALE)
        );
    }
}
