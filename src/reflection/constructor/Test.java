package reflection.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Address address = createInstanceWithArguments(Address.class, "Hoang Hoa Tham", 596);
        Person person = createInstanceWithArguments(Person.class, "huynh", 29, address);
        System.out.println(person);
    }

    public static <T> T createInstanceWithArguments(Class<T> clazz, Object... objects) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constructors = clazz.getConstructors();
        System.out.println("Number constructor of + " +clazz.getName()+ " +  : " +constructors.length );
        for (Constructor constructor : constructors) {
            if (constructor.getParameterTypes().length == objects.length) {
                return (T) constructor.newInstance(objects);
            }
        }

        System.out.println("===============Not exits constructor===============");
        return null;
    }

    public static class Person {
        private String name;

        private int age;

        private Address address;
        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", address=" + address +
                    '}';
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, int age, Address address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }


        Person(String name) {
            this.name = name;
        }
    }

    public static class Address {
        private String street;

        private int number;

        public Address(String street, int number) {
            this.street = street;
            this.number = number;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "street='" + street + '\'' +
                    ", number=" + number +
                    '}';
        }

    }
}
