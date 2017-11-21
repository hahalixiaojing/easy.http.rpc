package easy.http.rpc;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JSONStringToObject {

    private JSONStringToObject() {
    }

    public static Object methodReturnDataToObject(Method m, String data) throws ClassNotFoundException {
        Class<?> returnType = m.getReturnType();
        Type type = m.getGenericReturnType();

        return stringToObject(returnType, type, data);
    }

    public static Object methodParameterDataToObject(Method m, int paramIndex, String data) throws ClassNotFoundException {
        Class<?> paramType = m.getParameterTypes()[paramIndex];
        Type type = m.getGenericParameterTypes()[paramIndex];
        return stringToObject(paramType, type, data);
    }

    private static Object stringToObject(Class<?> cls, Type type, String data) throws ClassNotFoundException {


        if (type instanceof ParameterizedType) {
            ParameterizedType genericReturnType = (ParameterizedType) type;

            if (cls == java.util.List.class || cls == java.util.ArrayList.class) {
                Type actualType = genericReturnType.getActualTypeArguments()[0];
                return listReturnDataParse(data, actualType);
            }
            return JSON.parseObject(data, genericReturnType);
        }
        return JSON.parseObject(data, cls);
    }

    private static Object listReturnDataParse(String data, Type type) throws ClassNotFoundException {
        if (data == null || data.equals("") || data.equals("[]")) {
            return new ArrayList<Object>(JSON.parseArray("[]", Class.forName(type.toString().replace("class ", ""))));

        }

        if (type == String.class) {
            return new ArrayList<String>(JSON.parseArray(data, String.class));
        }
        if (type == Integer.class) {
            return new ArrayList<Integer>(JSON.parseArray(data, Integer.class));
        }
        if (type == Float.class) {
            return new ArrayList<Float>(JSON.parseArray(data, Float.class));
        }
        if (type == Boolean.class) {
            return new ArrayList<Boolean>(JSON.parseArray(data, Boolean.class));
        }
        if (type == Short.class) {
            return new ArrayList<Short>(JSON.parseArray(data, Short.class));
        }
        if (type == Double.class) {
            return new ArrayList<Double>(JSON.parseArray(data, Double.class));
        }
        if (type == Long.class) {
            return new ArrayList<Long>(JSON.parseArray(data, Long.class));
        }
        if (type == Byte.class) {
            return new ArrayList<Byte>(JSON.parseArray(data, Byte.class));
        }

        return new ArrayList<Object>(JSON.parseArray(data, Class.forName(type.toString().replace("class ", ""))));
    }
}
