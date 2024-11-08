package co.edu.uco.talklate.util.validator;

import java.lang.reflect.Field;
import java.util.Objects;

public class NullValidator<T> {

    public void validateFields(T object) {

        Objects.requireNonNull(object, "The provided object is null");

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);

            try {
                if (field.get(object) == null) {
                    throw new IllegalArgumentException(field.getName() + " cannot be null");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error accessing field " + field.getName(), e);
            }

        }

    }

}

