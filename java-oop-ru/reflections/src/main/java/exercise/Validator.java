package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {

    public static List<String> validate(Object obj) {
        List<String> invalidFields = new ArrayList<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value == null) {
                        invalidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    System.err.println("Ошибка доступа к полю " + field.getName() + ": " + e.getMessage());
                }
            }
        }
        return invalidFields;
    }

    public static Map<String, List<String>> advancedValidate(Object obj) {
        Map<String, List<String>> invalidFields = new HashMap<>();
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);

            List<String> errors = new ArrayList<>();

            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotNull.class) && value == null) {
                    errors.add("can not be null");
                }
                if (field.isAnnotationPresent(MinLength.class) && value instanceof String) {
                    MinLength minLengthAnnotation = field.getAnnotation(MinLength.class);
                    int minLength = minLengthAnnotation.minLength();
                    String strValue = (String) value;
                    if (strValue != null && strValue.length() < minLength ) {
                        errors.add("length less than " + minLength);
                    }
                }

                if (!errors.isEmpty()) {
                    invalidFields.put(field.getName(), errors);
                }

            } catch (IllegalAccessException e) {
                System.err.println("Ошибка доступа к полю " + field.getName() + ": " + e.getMessage());
            }
        }
        return invalidFields;
    }
}
// END
