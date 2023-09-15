package dynamic_config_loader.configloader;

import java.lang.reflect.Array;

public class ArrayFlattening {
    public <T> T concat(Class<?> type, Object... arguments) {

        if (arguments.length == 0) {
            return null;
        }

        /**
         * Complete code here
         */
        int length = 0;
        for (Object obj : arguments) {
            if (obj.getClass().isArray()) {
                length += Array.getLength(obj);
            } else {
                length ++;
            }
        }

        T result = (T) Array.newInstance(type, length);
        int index = 0;
        for (Object obj : arguments) {
            if (obj.getClass().isArray()) {
                for (int i = 0; i < Array.getLength(obj); i++) {
                    Array.set(result, index, Array.get(obj, i));
                    index++;
                }
            } else {
                Array.set(result, index, obj);
                index++;
            }
        }
        return result;
    }
}
