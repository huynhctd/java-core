package reflection.field_and_arrays;

import java.lang.reflect.Field;
import java.util.List;

public class ObjectSizeCalculator {
    private static final long HEADER_SIZE = 12;
    private static final long REFERENCE_SIZE = 4;

    public static void main(String[] args) throws IllegalAccessException {
        Address address =  new Address("" , 496);
        Person person = new Person("", 30, address);
        System.out.println(sizeOfObject(address));
        System.out.println(sizeOfObject(person));
    }

    public static long sizeOfObject(Object input) throws IllegalAccessException {
        /**
         * Complete your code here
         */
        long result = 0;
        Field[] fields = input.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length ; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            if (field.getType().isPrimitive()) {
                result = result + sizeOfPrimitiveType(field.getType());
            } else if(field.getType().equals(String.class)) {
                result = result + sizeOfString(field.get(input).toString());
            } else {
                result = result + sizeOfObject(field.get(input));
            }
        }
        return result;
    }


    /*************** Helper methods ********************************/
    private static long sizeOfPrimitiveType(Class<?> primitiveType) {
        if (primitiveType.equals(int.class)) {
            return 4;
        } else if (primitiveType.equals(long.class)) {
            return 8;
        } else if (primitiveType.equals(float.class)) {
            return 4;
        } else if (primitiveType.equals(double.class)) {
            return 8;
        } else if (primitiveType.equals(byte.class)) {
            return 1;
        } else if (primitiveType.equals(short.class)) {
            return 2;
        }
        throw new IllegalArgumentException(String.format("Type: %s is not supported", primitiveType));
    }

    private static long sizeOfString(String inputString) {
        int stringBytesSize = inputString.getBytes().length;
        return HEADER_SIZE + REFERENCE_SIZE + stringBytesSize;
    }

    public static class Person {
        private String name;

        private int age;

        private Address address;


        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
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
}
