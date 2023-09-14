package dynamic_config_loader.configloader;

import dynamic_config_loader.data.DataSource;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static final String DB_CONFIG_PATH = "resource/database-properties.cfg";
    public static void main(String[] args) {
        try {

            System.out.println(loadConfig(DataSource.class, Path.of(DB_CONFIG_PATH)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public  static <T> T loadConfig(Class<T> clazz, Path path) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(path);

        Constructor constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

        T config = (T) constructor.newInstance();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] nameValuePair = line.split(":");
            String name = nameValuePair[0].trim();
            String value = nameValuePair[1].trim();

            try {
                Field field = config.getClass().getDeclaredField(name);
                field.setAccessible(true);
                convertValueToFieldType(field, value, config);
            } catch (Exception e) {
                throw new RuntimeException("Cant find name property in config :" +name);
            }
        }
        return config;
    }

    public static <T> void convertValueToFieldType(Field field, String value, T obj) throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type.equals(int.class)) {
            field.setInt(obj, Integer.valueOf(value));
        } else if (type.equals(float.class)) {
            field.setFloat(obj, Float.valueOf(value));
        } else if (type.equals(double.class)) {
            field.setDouble(obj, Double.valueOf(value));
        } else if (type.equals(short.class)) {
            field.setShort(obj, Short.valueOf(value));
        } else if (type.equals(Long.class)) {
            field.set(obj, Long.valueOf(value));
        } else if (type.equals(String.class)) {
            field.set(obj, value);
        } else {
            throw new RuntimeException("Not support type field :" + value);
        }
    }
}
