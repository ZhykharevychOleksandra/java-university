package lab05;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class MainLab5 {

    public static void main(String[] args) {
        List<Employee> employees = createEmployees();

        System.out.println("ЗАВДАННЯ 1");
        task1(employees);

        System.out.println("\n ЗАВДАННЯ 2");
        task2();

        System.out.println("\n ЗАВДАННЯ 3");
        task3();

        System.out.println("\n ЗАВДАННЯ 4");
        task4(employees);

        System.out.println("\n ЗАВДАННЯ 5");
        task5(employees);
    }

    private static List<Employee> createEmployees() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("E001", "Олександр", "Developer",
                40000, 2020, "Розробка", 12, 1));
        employees.add(new Employee("E002", "Марія", "Tester",
                28000, 2022, "Тестування", 6, 2));
        employees.add(new Employee("E003", "Андрій", "Senior Developer",
                55000, 2018, "Розробка", 18, 1));
        employees.add(new Employee("E004", "Ірина", "Manager",
                45000, 2019, "Менеджмент", 8, 3));
        employees.add(new Employee("E005", "Дмитро", "Developer",
                35000, 2021, "Розробка", 7, 1));
        employees.add(new Employee("E006", "Олена", "Tester",
                32000, 2020, "Тестування", 11, 2));
        employees.add(new Employee("E007", "Максим", "Lead Architect",
                70000, 2017, "Розробка", 20, 1));
        employees.add(new Employee("E008", "Анна", "Analyst",
                37000, 2023, "Аналітика", 5, 4));
        employees.add(new Employee("E009", "Богдан", "Developer",
                31000, 2022, "Розробка", 4, 1));
        employees.add(new Employee("E010", "Софія", "Designer",
                29000, 2021, "Дизайн", 6, 5));
        employees.add(new Employee("E011", "Віктор", "Senior Tester",
                39000, 2019, "Тестування", 14, 2));
        employees.add(new Employee("E012", "Наталія", "Analyst",
                34000, 2020, "Аналітика", 9, 4));

        return employees;
    }

    // Завдання 1. Робота з узагальненим класом StorageManager.

    private static void task1(List<Employee> employees) {
        StorageManager<Employee> manager = new StorageManager<>();

        for (Employee employee : employees) {
            manager.add(employee);
        }

        System.out.println("Пошук працівника E005:");

        Employee found = manager.find(
                employee -> employee.getPersonnelNumber().equals("E005")
        );

        System.out.println(found);

        System.out.println("\nПрацівники із зарплатою від 35000:");

        List<Employee> highSalary = manager.filter(
                employee -> employee.getSalary() >= 35000
        );

        highSalary.forEach(System.out::println);

        System.out.println("\nПрийняті не раніше 2021 року або раніше?");
        System.out.println("Працівники, прийняті до 2022 року, з відділу Розробка:");

        List<Employee> developers = manager.filter(
                employee -> employee.getHireYear() <= 2021
                        && employee.getDepartment().equals("Розробка")
        );

        developers.forEach(System.out::println);

        System.out.println("\nПрацівники з понад 5 проєктами або Lead Architect:");

        List<Employee> experienced = manager.filter(
                employee -> employee.getCompletedProjectsCount() > 5
                        || employee.getPosition().equals("Lead Architect")
        );

        experienced.forEach(System.out::println);
    }

    // Завдання 2. Заміна підсписку та запис результату у файл.

    private static void task2() {
        try {
            List<String> lines = Files.readAllLines(Path.of("lists.txt"));

            if (lines.size() < 3) {
                System.out.println("У файлі lists.txt має бути три рядки.");
                return;
            }

            List<Integer> list1 = parseIntegerList(lines.get(0));
            List<Integer> list2 = parseIntegerList(lines.get(1));
            List<Integer> list3 = parseIntegerList(lines.get(2));

            System.out.println("Початковий список L1: " + list1);
            System.out.println("Підсписок L2: " + list2);
            System.out.println("Список L3: " + list3);

            int index = findSubList(list1, list2);

            if (index == -1) {
                System.out.println("Підсписок L2 не знайдено в L1.");
                return;
            }

            for (int i = 0; i < list2.size(); i++) {
                list1.remove(index);
            }

            list1.addAll(index, list3);

            System.out.println("Результат заміни: " + list1);

            writeNumbersByLine(list1, "result_list.txt", 7);

            System.out.println("Результат записано у result_list.txt.");

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        }
    }

    private static List<Integer> parseIntegerList(String line) {
        List<Integer> result = new ArrayList<>();

        String[] numbers = line.trim().split("[,\\s]+");

        for (String number : numbers) {
            if (!number.isBlank()) {
                result.add(Integer.parseInt(number));
            }
        }

        return result;
    }

    private static int findSubList(List<Integer> source,
                                   List<Integer> target) {
        if (target.isEmpty() || target.size() > source.size()) {
            return -1;
        }

        for (int i = 0; i <= source.size() - target.size(); i++) {
            boolean matches = true;

            for (int j = 0; j < target.size(); j++) {
                if (!source.get(i + j).equals(target.get(j))) {
                    matches = false;
                    break;
                }
            }

            if (matches) {
                return i;
            }
        }

        return -1;
    }

    private static void writeNumbersByLine(List<Integer> numbers,
                                           String fileName,
                                           int numbersPerLine)
            throws IOException {

        List<String> lines = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i += numbersPerLine) {
            int end = Math.min(i + numbersPerLine, numbers.size());

            String line = numbers.subList(i, end)
                    .stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" "));

            lines.add(line);
        }

        Files.write(Path.of(fileName), lines);
    }

    // Завдання 3. Об'єднання Map зі збереженням ключів першої Map.

    private static void task3() {
        try {
            List<String> lines = Files.readAllLines(Path.of("maps.txt"));

            if (lines.size() < 2) {
                System.out.println("У файлі maps.txt має бути два рядки.");
                return;
            }

            Map<String, Double> map1 = readMapLine(lines.get(0));
            Map<String, Double> map2 = readMapLine(lines.get(1));

            Map<String, Double> result = new TreeMap<>();

            for (String key : map1.keySet()) {
                if (map2.containsKey(key)) {
                    result.put(key, map1.get(key));
                } else {
                    result.put(key, map1.get(key));
                }
            }

            System.out.println("Перша Map: " + map1);
            System.out.println("Друга Map: " + map2);
            System.out.println("Результат: " + result);

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлом: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("У файлі maps.txt є некоректне число.");
        }
    }

    private static Map<String, Double> readMapLine(String line) {
        Map<String, Double> map = new LinkedHashMap<>();

        String[] entries = line.split(",");

        for (String entry : entries) {
            String[] pair = entry.trim().split("=");

            if (pair.length == 2) {
                map.put(pair[0].trim(),
                        Double.parseDouble(pair[1].trim()));
            }
        }

        return map;
    }

    // Завдання 4. Операції з використанням Stream API.

    private static void task4(List<Employee> employees) {

        System.out.println("Працівники із зарплатою понад 30000 " +
                "з відділу Розробка:");

        List<Employee> filtered = employees.stream()
                .filter(employee -> employee.getSalary() > 30000)
                .filter(employee ->
                        employee.getDepartment().equals("Розробка"))
                .collect(Collectors.toList());

        filtered.forEach(System.out::println);

        System.out.println("\nСортування за роком прийняття " +
                "та зарплатою:");

        List<Employee> sorted = employees.stream()
                .sorted(
                        Comparator.comparingInt(Employee::getHireYear)
                                .thenComparing(
                                        Comparator.comparingDouble(
                                                Employee::getSalary
                                        ).reversed()
                                )
                )
                .collect(Collectors.toList());

        sorted.forEach(System.out::println);

        System.out.println("\nНазви посад після пропуску перших 5 " +
                "працівників і вибору наступних 3:");

        List<String> positions = employees.stream()
                .skip(5)
                .limit(3)
                .map(Employee::getPosition)
                .collect(Collectors.toList());

        positions.forEach(System.out::println);

        System.out.println("\nПерший працівник, який завершив понад 10 проєктів:");

        employees.stream()
                .filter(employee ->
                        employee.getCompletedProjectsCount() > 10)
                .findFirst()
                .ifPresent(System.out::println);

        System.out.println("\nПрацівник із найбільшою зарплатою:");

        employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(System.out::println);
    }

    // Завдання 5. Групування, статистика та об'єднання даних.

    private static void task5(List<Employee> employees) {

        System.out.println("Кількість працівників у кожному відділі:");

        Map<String, Long> countByDepartment = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        TreeMap::new,
                        Collectors.counting()
                ));

        countByDepartment.forEach((department, count) ->
                System.out.println(department + ": " + count));

        System.out.println("\nМаксимальна зарплата за відділами:");

        Map<String, Optional<Employee>> maxSalaryByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                TreeMap::new,
                                Collectors.maxBy(
                                        Comparator.comparingDouble(
                                                Employee::getSalary
                                        )
                                )
                        ));

        maxSalaryByDepartment.forEach((department, employee) -> {
            System.out.println(department + ": "
                    + employee.map(Employee::getSalary).orElse(0.0));
        });

        List<Department> departments = Arrays.asList(
                new Department(1, "Розробка"),
                new Department(2, "Тестування"),
                new Department(3, "Менеджмент"),
                new Department(4, "Аналітика"),
                new Department(5, "Дизайн")
        );

        Map<Integer, Department> departmentMap = departments.stream()
                .collect(Collectors.toMap(
                        Department::getId,
                        department -> department
                ));

        System.out.println("\nПрацівники та їхні відділи:");

        List<EmployeeDepartmentInfo> joined = employees.stream()
                .map(employee -> {
                    Department department =
                            departmentMap.get(employee.getDepartmentId());

                    String departmentName = department != null
                            ? department.getName()
                            : "Невідомий відділ";

                    return new EmployeeDepartmentInfo(
                            employee.getName(),
                            employee.getPosition(),
                            departmentName
                    );
                })
                .collect(Collectors.toList());

        joined.forEach(System.out::println);

        System.out.println("\nПрацівники за відділами:");

        Map<String, List<String>> namesByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                TreeMap::new,
                                Collectors.mapping(
                                        Employee::getName,
                                        Collectors.toList()
                                )
                        ));

        namesByDepartment.forEach((department, names) ->
                System.out.println(department + ": " + names));

        System.out.println("\nТекстовий звіт:");

        String report = employees.stream()
                .map(employee -> employee.getName()
                        + " — " + employee.getPosition()
                        + " — " + employee.getDepartment()
                        + " — " + employee.getSalary())
                .collect(Collectors.joining("\n"));

        System.out.println(report);
    }
}