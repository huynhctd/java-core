package reflection.modifier;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        JsonWriter jsonWriter =  new JsonWriter();
        System.out.println(jsonWriter.objectToJson(new Address("Main Street", (short) 1, "12345")));
    }
}
