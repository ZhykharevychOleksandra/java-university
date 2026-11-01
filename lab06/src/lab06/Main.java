package lab06;

import lab06.annotations.Component;
import lab06.model.Employee;
import lab06.validation.CachedValidationService;
import lab06.validation.ValidationResult;
import lab06.validation.Validator;
import lab06.validation.ValidatorService;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, "UTF-8"));

        task2();
        task3();
        task4();
    }

    // завдання 2
    private static void task2() {
        System.out.println("=== Завдання 2. ValidatorService ===");
        Employee valid = new Employee("Іваненко Олена Петрівна", "1234567890", 45000.0, 7, LocalDate.of(2019, 3, 1));
        Employee invalid = new Employee("Петренко Іван Сергійович", "12AB", 7000.0, 55, LocalDate.of(2015, 9, 15));

        Validator validator = new ValidatorService();
        printResult(valid, validator.validate(valid));
        printResult(invalid, validator.validate(invalid));
        System.out.println();
    }

    private static void printResult(Object entity, ValidationResult result) {
        System.out.println(entity);
        System.out.println("  IsValid = " + result.isValid());
        for (String error : result.getErrors()) {
            System.out.println("  - " + error);
        }
    }

    // завдання 3
    private static void task3() {
        System.out.println("=== Завдання 3. Бенчмарк: без кешу vs з кешем ===");
        final int iterations = 500_000;
        final int poolSize = 10_000;

        Employee[] pool = generateEmployees(poolSize, new Random(42));
        ValidatorService plain = new ValidatorService();
        CachedValidationService cached = new CachedValidationService();

        runBenchmark(plain, pool, 50_000);
        runBenchmark(cached, pool, 50_000);

        long[] plainRes = runBenchmark(plain, pool, iterations);
        long[] cachedRes = runBenchmark(cached, pool, iterations);

        if (plainRes[1] != cachedRes[1] || plainRes[2] != cachedRes[2]) {
            throw new IllegalStateException("Сервіси дали різні результати: "
                    + plainRes[1] + "/" + plainRes[2] + " vs " + cachedRes[1] + "/" + cachedRes[2]);
        }

        System.out.printf("Ітерацій: %,d (пул з %,d випадкових Employee)%n", iterations, poolSize);
        System.out.println("+--------------------------+------------+-------------+--------------+");
        System.out.println("| Сервіс                   | Час, мс    | нс/виклик   | Валідних     |");
        System.out.println("+--------------------------+------------+-------------+--------------+");
        printRow("ValidatorService", plainRes, iterations);
        printRow("CachedValidationService", cachedRes, iterations);
        System.out.println("+--------------------------+------------+-------------+--------------+");
        System.out.printf("Прискорення з кешем: у %.2f раза%n", (double) plainRes[0] / cachedRes[0]);
        System.out.println("Типів у кеші: " + cached.cacheSize());
        System.out.println();
    }

    private static long[] runBenchmark(Validator validator, Employee[] pool, int iterations) {
        long valid = 0;
        long errors = 0;
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            ValidationResult r = validator.validate(pool[i % pool.length]);
            if (r.isValid()) {
                valid++;
            }
            errors += r.getErrors().size();
        }
        long elapsed = System.nanoTime() - start;
        return new long[]{elapsed, valid, errors};
    }

    private static void printRow(String name, long[] res, int iterations) {
        System.out.printf("| %-24s | %10.2f | %11.1f | %,12d |%n",
                name, res[0] / 1_000_000.0, (double) res[0] / iterations, res[1]);
    }

    private static Employee[] generateEmployees(int count, Random random) {
        String[] names = {"Коваленко А.", "Шевченко Б.", "Бондаренко В.", "Мельник Г.", "Ткаченко Д."};
        Employee[] result = new Employee[count];
        for (int i = 0; i < count; i++) {
            String taxId;
            if (random.nextInt(10) < 7) {
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < 10; k++) {
                    sb.append(random.nextInt(10));
                }
                taxId = sb.toString();
            } else {
                taxId = random.nextBoolean() ? "ABC" + random.nextInt(1000) : "12345";
            }
            double salary = 5_000 + random.nextDouble() * 600_000;
            int years = random.nextInt(60) - 3;
            LocalDate hired = LocalDate.of(2000, 1, 1).plusDays(random.nextInt(9000));
            result[i] = new Employee(names[random.nextInt(names.length)], taxId, salary, years, hired);
        }
        return result;
    }

    // завдання 4
    private static void task4() throws Exception {
        System.out.println("Завдання 4. Сканування пакета та динамічне виконання");
        List<Class<?>> all = ComponentScanner.scanPackage("lab06.components");
        List<Class<?>> components = ComponentScanner.findComponents(all);
        System.out.println("Класів у пакеті: " + all.size() + ", з @Component: " + components.size());

        for (Class<?> type : components) {
            Component meta = type.getAnnotation(Component.class);
            System.out.println("-- " + type.getSimpleName() + " (name=" + meta.name() + ", version=" + meta.version() + ")");

            Object instance = type.getDeclaredConstructor().newInstance();

            Field field = type.getDeclaredFields()[0];
            field.setAccessible(true);
            field.set(instance, "ready");

            Method action = type.getDeclaredMethod("execute");
            action.invoke(instance);

            System.out.println("   " + instance);
        }
    }
}
