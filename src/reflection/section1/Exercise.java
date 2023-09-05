package reflection.section1;

import java.util.*;
public class Exercise {
    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the
     * input interface extends.
     */
    public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();

        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
            allImplementedInterfaces.add(currentInterface);
            /** Complete this code **/
            if (currentInterface.getInterfaces().length > 0) {
                allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
            }
        }

        return allImplementedInterfaces;
    }
}
