import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DeprecatedEx {
    // Создайте свою аннотацию @DeprecatedEx, аналогичную встроенной @Deprecated, но с поясняющим сообщением.
    // Требования:
    //    Аннотация может применяться к классам и методам.
    //    Аннотация содержит параметр String message(), в котором указывается альтернатива устаревшему элементу.
    String message();
}
