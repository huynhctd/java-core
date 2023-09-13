package reflection.field_and_arrays;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
//        printDeclaredFieldsInfo(Person.class);
//        printDeclaredFieldsInfo(Address.class);
        Address address =  new Address("Hoang Hoa Tham", 596);
        String[] friends = new String[] {"Huynh", "Son"};
        Person person = new Person("Huynh", 29, address, friends);


        System.out.println(objectToJson(person, 1));
    }

    public static  <T> void printDeclaredFieldsInfo(Class<? extends T> clazz) {
        for (Field field: clazz.getDeclaredFields()) {
            System.out.println("Field info :" + " name " +field.getName() + ", type :" + field.getType());
            System.out.println("Field info :" + " synthetic " +field.isSynthetic());
            System.out.println();
        }
    }

    public static class Person {
        private String name;

        private int age;

        private Address address;
        

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, int age, Address address, String[] friends) {
            this.name = name;
            this.age = age;
            this.address = address;
            this.friends = friends;
        }

        private String[] friends;



        Person(String name) {
            this.name = name;
        }

        // inner class

        public static class Student {
            String schoolName;
            String className;

            public Student(String schoolName, String className) {
                this.schoolName = schoolName;
                this.className = className;
            }
        }

    }

    public static class Address {
        private String street;

        private int number;

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }

    }

    private static String objectToJson(Object obj, int tabNumber) throws IllegalAccessException {
        String space = tabNumber > 0 ? "    ".repeat(tabNumber) : "";
        StringBuilder stringBuilder =  new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        stringBuilder.append("{ \n");
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.isSynthetic()) {
                continue;
            }

            stringBuilder.append(space);
            stringBuilder.append(formatString(field.getName()));
            stringBuilder.append(" : ");

            if (field.getType().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(field.get(obj), field.getType()));
            } else if (field.getType().equals(String.class)) {
                stringBuilder.append(formatString(field.get(obj).toString()));
            } else if (field.getType().isArray()) {
                stringBuilder.append(arrayToJson(field.get(obj), tabNumber + 1));
            }else  {
                stringBuilder.append(objectToJson(field.get(obj), tabNumber + 1));
            }
            if(i < fields.length -1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(space + "}");
        return stringBuilder.toString();
    }

    private static String arrayToJson(Object obj, int tabNumber) throws IllegalAccessException {
        String space = tabNumber > 0 ? "    ".repeat(tabNumber) : "";
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append("[ ");
        stringBuilder.append("\n ");
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i ++) {
            Object element = Array.get(obj, i);

            if (element.getClass().isSynthetic()) {
                continue;
            }
            if (element.getClass().isPrimitive()) {
                stringBuilder.append(formatPrimitiveValue(element, element.getClass()));
            } else if (element.getClass().equals(String.class)) {
                stringBuilder.append(space);
                stringBuilder.append(formatString(element.toString()));
            } else {
                stringBuilder.append(objectToJson(element, tabNumber + 1));
            }
            if(i < length -1) {
                stringBuilder.append(",");
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append(space + "]");
        return stringBuilder.toString();
    }

    private static String formatPrimitiveValue(Object instance, Class<?> type) throws IllegalAccessException {
        if (type.equals(boolean.class)
                || type.equals(int.class)
                || type.equals(short.class)
                || type.equals(char.class)
                || type.equals(short.class)
                || type.equals(long.class)
        ) {
            return instance.toString();
        } else if (type.equals(double.class) || type.equals(float.class)) {
            return String.format("%2.0f", instance);
        }
        throw new RuntimeException("Not Support Type" + instance);
    }


    private static String formatString(String value) {
        return String.format("\"%s\"", value);
    }
}
