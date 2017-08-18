package easy.http.rpc;

import com.alibaba.fastjson.JSON;
import easy.http.rpc.ex.HttpServerException;
import easy.http.rpc.okhttp.OkHttp3Client;

import javax.print.DocFlavor;
import javax.tools.JavaCompiler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class DefaultInvocationHandler implements InvocationHandler {

    private final String baseApiUrl;
    private final IHttpClient client;

    public DefaultInvocationHandler(String baseApiUrl, IHttpClient client) {
        this.baseApiUrl = baseApiUrl;
        if (client == null) {
            this.client = new OkHttp3Client();
        } else {
            this.client = client;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String interfaceClassName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();


        String apiUrl = this.getApiUrl(interfaceClassName, methodName);
        HttpResponseData request = this.client.request(apiUrl, args);
        if (request.httpStatus == 200) {
            if (returnType == void.class) {
                return null;
            }
            return this.returnDataParse(method, request.responseData);
        } else {
            throw new HttpServerException(request.httpStatus, apiUrl, request.errorMessage);
        }
    }

    private Object returnDataParse(Method method, String data) {

        Class<?> returnType = method.getReturnType();
        if (method.getGenericReturnType() instanceof ParameterizedType) {
            ParameterizedType genericReturnType = (ParameterizedType) method.getGenericReturnType();

            if (returnType == java.util.List.class || returnType == java.util.ArrayList.class) {
                Type type = genericReturnType.getActualTypeArguments()[0];
                return this.listReturnDataParse(data, type);
            }
            return JSON.parseObject(data, genericReturnType);
        }
        return JSON.parseObject(data, returnType);
    }

    private Object listReturnDataParse(String data, Type type) {
        if (type == java.lang.String.class) {
            return new ArrayList<String>(JSON.parseArray(data, String.class));
        }
        if (type == java.lang.Integer.class) {
            return new ArrayList<Integer>(JSON.parseArray(data, Integer.class));
        }
        if (type == java.lang.Float.class) {
            return new ArrayList<Float>(JSON.parseArray(data, Float.class));
        }
        return new ArrayList<Object>(JSON.parseArray(data, new Type[]{type}));
    }

    private String getApiUrl(String interfaceClassName, String methodName) {

        if (baseApiUrl.endsWith("/")) {
            return this.baseApiUrl + interfaceClassName + "/" + methodName;
        }
        return this.baseApiUrl + "/" + interfaceClassName + " / " + methodName;
    }
}
