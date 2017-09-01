package easy.http.rpc;

import easy.http.rpc.okhttp.OkHttp3Client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultInvocationHandler implements InvocationHandler {

    private final String baseApiUrl;
    private final IHttpClient client;
    private final ILoadBalanceApiUrl loadBalanceApiUrl;

    public DefaultInvocationHandler(String baseApiUrl, IHttpClient client) {
        this.baseApiUrl = baseApiUrl;
        if (client == null) {
            this.client = new OkHttp3Client();
        } else {
            this.client = client;
        }
        this.loadBalanceApiUrl = null;
    }

    public DefaultInvocationHandler(ILoadBalanceApiUrl loadBalanceApiUrl, IHttpClient client) {
        this.baseApiUrl = null;
        this.client = client;
        this.loadBalanceApiUrl = loadBalanceApiUrl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String interfaceClassName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();

        try {

            String apiUrl = this.getApiUrl(interfaceClassName, methodName);
            String request = this.client.request(apiUrl, args);

            if (returnType == void.class) {
                return null;
            }
            return JSONStringToObject.methodReturnDataToObject(method, request);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private String getApiUrl(String interfaceClassName, String methodName) {

        String apiUrl = this.baseApiUrl;
        if (this.baseApiUrl == null) {
            apiUrl = this.loadBalanceApiUrl.getApiUrl(interfaceClassName);
        }

        if (apiUrl.endsWith("/")) {
            return apiUrl + interfaceClassName + "/" + methodName;
        }
        return apiUrl + "/" + interfaceClassName + " / " + methodName;
    }
}
