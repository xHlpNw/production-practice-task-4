
//    Реализуйте класс-сериализатор, который:
//       Принимает любой объект.
//       Через рефлексию находит все поля, помеченные @JsonField.
//       Формирует строку JSON формата: {"jsonName1": значение, "jsonName2": значение, ...}

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class Serializer {
    public static String toJson(Object obj) throws IllegalAccessException {
        List<String> fieldLines = new ArrayList<>();
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonField.class)) {
                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                fieldLines.add(String.format("\"%s\": %s",
                        annotation.name(), getValue(field.get(obj), field.getType())));
            }
        }
        return "{" + String.join(", ", fieldLines) + "}";
    }
    private static String getValue(Object value, Class<?> type) {
        if (value == null) {
            return null;
        }
        if (type == String.class) {
            return "\"" + value + "\"";
        }
        if (Collection.class.isAssignableFrom(type)) {
            Collection<?> collection = (Collection<?>) value;
            return "[" + collection.stream().map(Object::toString)
                    .collect(Collectors.joining(", ")) + "]";
        }
        return value.toString();
    }
}
