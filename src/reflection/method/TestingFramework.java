package reflection.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class TestingFramework {
    public void runTestSuite(Class<?> testClass) throws Throwable {
        /**
         * Complete your code here
         */
        Method[] methods = testClass.getDeclaredMethods();
        Constructor constructor = testClass.getDeclaredConstructor();
        Object obj = constructor.newInstance();

        // Step 1 run beforeClass
        Method step1 = findMethodByName(methods, "beforeClass");
        if (step1 == null) throw new Throwable("Not find class beforeClass()");
        step1.invoke(obj);

        // Step 2 called before every test.
        Method step2 = findMethodByName(methods, "setupTest");
        if (step2 == null) throw new Throwable("Not find class setupTest()");
        step2.invoke(obj);

        // Step 3 run unit test method
        List<Method> list = findMethodsByPrefix(methods, "test");
        for (Method method : list) {
            method.invoke(obj);
        }

        // Step 4 run at the end of the test suite, only once.
        Method step4 = findMethodByName(methods, "afterClass");
        if (step4 == null) throw new Throwable("Not find class afterClass()");
        step4.invoke(null);
    }

    /**
     * Helper method to find a method by name
     * Returns null if a method with the given name does not exist
     */
    private Method findMethodByName(Method[] methods, String name) {
        /**
         * Complete your code here
         */
        for (Method method : methods) {
            if (method.getName().equals(name) && method.getParameterCount() == 0
                    &&  method.getReturnType() == void.class) {
                return method;
            }
        }
        return null;
    }

    /**
     * Helper method to find all the methods that start with the given prefix
     */
    private List<Method> findMethodsByPrefix(Method[] methods, String prefix) {
        /**
         * Complete your code here
         */
        List<Method> result =  new LinkedList<>();
        for (Method method : methods) {
            if (method.getName().startsWith(prefix) && method.getParameterCount() == 0
                    &&  method.getReturnType() == void.class) {
                result.add(method);
            }
        }
        return result;
    }
}
