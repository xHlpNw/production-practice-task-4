import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // Работа с лямбда-выражениями
        // 1. Лямбда выражение для интерфейса Printable
        // Написать лямбда выражение для интерфейса Printable, который содержит один метод void print().
        System.out.println("Лямбда-выражения");
        System.out.println("Задание 1:");
        Printable printable = () -> System.out.println("Реализация интерфейса Printable");
        printable.print();

        // 2. Проверка пустой строки.
        //   1. Создать лямбда выражение, которое возвращает значение true, если строка не null,
        //      используя функциональный интерфейс Predicate.
        //   2. Создать лямбда выражение, которое проверяет, что строка не пуста,
        //      используя функциональный интерфейс Predicate.
        //   3. Написать программу, проверяющую, что строка не null и не пуста, используя
        //      метод and() функционального интерфейса Predicate.
        System.out.println("\nЗадание 2:");
        Predicate<String> isNotNull = (s) -> !Objects.isNull(s);
        Predicate<String> isNotEmpty = (s) -> !s.isEmpty();
        Predicate<String> hasValue = isNotNull.and(isNotEmpty);

        System.out.print("Результат для null: ");
        System.out.println(hasValue.test(null));
        System.out.print("Результат для пустой строки: ");
        System.out.println(hasValue.test(""));
        String testLine = "not empty line";
        System.out.printf("Результат для строки '%s': ", testLine);
        System.out.println(hasValue.test(testLine));

        // 3. Проверка строки.
        // Написать программу, которая проверяет, что строка начинается буквой “J” или “N” и заканчивается “A”.
        // Используем функциональный интерфейс Predicate.
        System.out.println("\nЗадание 3:");
        Predicate<String> correctFirstChar = (s) -> s.startsWith("J") || s.startsWith("N");
        Predicate<String> correctLastChar = (s) -> s.endsWith("A");

        Predicate<String> checkForString = hasValue.and(correctFirstChar.and(correctLastChar));

        System.out.print("Результат для null: ");
        System.out.println(checkForString.test(null));
        System.out.print("Результат для пустой строки: ");
        System.out.println(checkForString.test(""));
        testLine = "not empty line";
        System.out.printf("Результат для строки '%s': %s\n", testLine, checkForString.test(testLine));
        testLine = "not a";
        System.out.printf("Результат для строки '%s': %s\n", testLine, checkForString.test(testLine));
        testLine = "Not";
        System.out.printf("Результат для строки '%s': %s\n", testLine, checkForString.test(testLine));
        testLine = "is A";
        System.out.printf("Результат для строки '%s': %s\n", testLine, checkForString.test(testLine));
        testLine = "Not A";
        System.out.printf("Результат для строки '%s': %s\n", testLine, checkForString.test(testLine));
        testLine = "Just A";
        System.out.printf("Результат для строки '%s': %s\n", testLine, checkForString.test(testLine));

        // 4. Лямбда выражение для HeavyBox.
        // Написать лямбда выражение, которое принимает на вход объект типа HeavyBox и выводит
        // на консоль сообщение “Отгрузили ящик с весом n”. “Отправляем ящик с весом n”.
        // Используем функциональный интерфейс Consumer и метод по умолчанию andThen.
        System.out.println("\nЗадание 4:");
        Consumer<HeavyBox> shipTheBox = (b) ->
                System.out.printf("Отгрузили ящик с весом %d.\n", b.getWeight());
        Consumer<HeavyBox> sendTheBox = (b) ->
                System.out.printf("Отправляем ящик с весом %d.\n", b.getWeight());
        Consumer<HeavyBox> processTheBox = shipTheBox.andThen(sendTheBox);
        HeavyBox box = new HeavyBox(10);
        System.out.println("Объект - " + box);
        processTheBox.accept(box);

        // 5. Лямбда для Function.
        // Написать лямбда выражение, которое принимает на вход число и возвращает значение
        // “Положительное число”, “Отрицательное число” или “Ноль”.
        // Используем функциональный интерфейс Function.
        System.out.println("\nЗадание 5:");
        Function<Integer, String> classifyInteger = (n) -> {
            if (n == 0) return "Ноль.";
            else if (n > 0) return "Положительное число.";
            else return "Отрицательное число.";
        };
        int number = 0;
        System.out.printf("Результат классификации для числа %d: %s\n",
                number, classifyInteger.apply(number));
        number = 10;
        System.out.printf("Результат классификации для числа %d: %s\n",
                number, classifyInteger.apply(number));
        number = -100;
        System.out.printf("Результат классификации для числа %d: %s\n",
                number, classifyInteger.apply(number));

        // 6. Лямбда для Supplier.
        // Написать лямбда выражение, которое возвращает случайное число от 0 до 10.
        // Используем функциональный интерфейс Supplier.
        System.out.println("\nЗадание 6:");
        Supplier<Integer> getRandomInt = () ->
            new Random().nextInt(11);
        System.out.printf("Сгенерированное число от 0 до 10: %d\n",
                getRandomInt.get());

        // Работа с аннотациями и рефлексией
        // Задание 1: Кастомная аннотация @DeprecatedEx
        // Создайте свою аннотацию @DeprecatedEx, аналогичную встроенной @Deprecated, но с поясняющим сообщением.
        // Требования:
        //    Аннотация может применяться к классам и методам.
        //    Аннотация содержит параметр String message(), в котором указывается альтернатива устаревшему элементу.
        // Напишите обработчик, который:
        //    Принимает любой класс.
        //    Использует рефлексию, чтобы найти все помеченные элементы (методы и классы).
        //    Выводит в консоль предупреждение в формате:
        //       Внимание: метод 'methodName' устарел. Альтернатива: 'message'
        //       Внимание: класс 'ClassName' устарел. Альтернатива: 'message'
        System.out.println("\nАннотации и рефлексия");
        System.out.println("Задание 1:");
        Class<?> cls = DeprecatedClass.class;
        processClass(cls);
        System.out.println();
        processClass(Integer.class);

        // Задание 2: Кастомная сериализация в JSON с аннотацией @JsonField ***
        // Создайте аннотацию @JsonField, чтобы указывать имя поля в JSON-выводе.
        // Требования:
        //    Аннотация применяется к полям.
        //    Аннотация содержит параметр name(), задающий имя поля в JSON.
        //    Реализуйте класс-сериализатор, который:
        //       Принимает любой объект.
        //       Через рефлексию находит все поля, помеченные @JsonField.
        //       Формирует строку JSON формата: {"jsonName1": значение, "jsonName2": значение, ...}
        System.out.println("\nЗадание 2:");
        SerializableClass object = new SerializableClass();
        System.out.println(object);
        System.out.println(Serializer.toJson(object));

        object = new SerializableClass(21, "Pavel", 'S', 80.21212,
                new ArrayList<>(List.of(5, 4, 5, 3, 5)));
        System.out.println(object);
        System.out.println(Serializer.toJson(object));
        System.out.println(box);
        System.out.println(Serializer.toJson(box));
    }
    public static void processClass(Class<?> cls) {
        System.out.printf("Обработка класса '%s':\n", cls.getSimpleName());
        boolean hasMarks = false;
        if (cls.isAnnotationPresent(DeprecatedEx.class)) {
            hasMarks = true;
            DeprecatedEx annotation = cls.getAnnotation(DeprecatedEx.class);
            System.out.printf("Внимание: класс '%s' устарел. Альтернатива: '%s'\n",
                    cls.getSimpleName(), annotation.message());
        }

        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                hasMarks = true;
                System.out.printf("Внимание: метод '%s' устарел. Альтернатива: '%s'\n",
                        method.getName(), method.getAnnotation(DeprecatedEx.class).message());
            }
        }

        for (Class<?> inner : cls.getDeclaredClasses()) {
            if (inner.isAnnotationPresent(DeprecatedEx.class)) {
                hasMarks = true;
                System.out.printf("Внимание: внутренний класс '%s' устарел. Альтернатива: '%s'\n",
                        inner.getSimpleName(), inner.getAnnotation(DeprecatedEx.class).message());
            }
            for (Method method : inner.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DeprecatedEx.class)) {
                    hasMarks = true;
                    System.out.printf("Внимание: метод '%s' внутреннего класса '%s' устарел. Альтернатива: '%s'\n",
                            method.getName(), inner.getSimpleName(), method.getAnnotation(DeprecatedEx.class).message());
                }
            }
        }
        if (!hasMarks) System.out.println("Не было найдено устаревших методов и классов.");
    }
}