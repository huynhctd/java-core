package reflection.section1;

import java.util.*;

public class ClassAnalyzer {
    private static final List<String> JDK_PACKAGE_PREFIXES =
            Arrays.asList("com.sun.", "java", "javax", "jdk", "org.w3c", "org.xml");

    public static PopupTypeInfo createPopupTypeInfoFromClass(Class<?> inputClass) {
        PopupTypeInfo popupTypeInfo = new PopupTypeInfo();

        /** Complete the Code **/


        popupTypeInfo.setPrimitive(inputClass.isPrimitive())
                .setInterface(inputClass.isInterface())
                .setEnum(inputClass.isEnum())
                .setName(inputClass.getSimpleName())
                .setJdk(isJdkClass(inputClass))
                .addAllInheritedClassNames(getAllInheritedClassNames(inputClass));

        return popupTypeInfo;
    }

    /*********** Helper Methods ***************/

    public static boolean isJdkClass(Class<?> inputClass) {
        /** Complete the code
         Hint: What does inputClass.getPackage() return when the class is a primitive type?
         **/
        Package namePackage = inputClass.getPackage();
        if (namePackage == null) return false;
        if (inputClass.isPrimitive()) return true;
        return JDK_PACKAGE_PREFIXES.contains(namePackage.getName());
    }

    public static String[] getAllInheritedClassNames(Class<?> inputClass) {
        /** Complete the code
         Hints: What does inputClass.getSuperclass() return when the inputClass doesn't inherit from any class?
         What does inputClass.getSuperclass() return when the inputClass is a primitve type?

         **/
        List<String> result =  new LinkedList<>();
        Class<?> aClass = inputClass.getSuperclass();
        while (aClass != null) {
            result.add(aClass.getName());
            aClass = aClass.getSuperclass();
        }
        return result.stream().toArray(String[]::new);
    }
}
