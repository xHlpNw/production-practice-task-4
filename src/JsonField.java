import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Создайте аннотацию @JsonField, чтобы указывать имя поля в JSON-выводе.
// Требования:
//    Аннотация применяется к полям.
//    Аннотация содержит параметр name(), задающий имя поля в JSON.

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonField {
    String name();
}
