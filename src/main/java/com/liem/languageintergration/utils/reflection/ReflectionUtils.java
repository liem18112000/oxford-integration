package com.liem.languageintergration.utils.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Reflection utils.
 */
public class ReflectionUtils {

  /**
   * Gets all fields from class.
   *
   * @param type the type
   * @return the all fields from class
   */
  public static List<Field> getAllFieldsFromClass(Class<?> type) {
    return getAllFieldsFromClass(new ArrayList<>(), type);
  }

  /**
   * Gets field from class by field name.
   *
   * @param type      the type
   * @param fieldName the field name
   * @return the field from class by field name
   * @throws NoSuchFieldException the no such field exception
   */
  public static Field getFieldFromClassByFieldName(
      Class<?> type, String fieldName)
      throws NoSuchFieldException {
    return getAllFieldsFromClass(type)
        .stream().filter(f -> f.getName().equals(fieldName))
        .findFirst().orElseThrow(NoSuchFieldException::new);
  }

  /**
   * Gets all fields from class.
   *
   * @param fields the fields
   * @param type   the type
   * @return the all fields from class
   */
  private static List<Field> getAllFieldsFromClass(List<Field> fields, Class<?> type) {
    var addedFields = new ArrayList<>(List.of(type.getDeclaredFields()));
    addedFields.addAll(fields);
    if (type.getSuperclass() != null) {
      return getAllFieldsFromClass(addedFields, type.getSuperclass());
    }
    return fields;
  }

}
