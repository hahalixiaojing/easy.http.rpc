package easy.http.rpc;

import easy.http.rpc.okhttp.OkHttp3Client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ServiceBuilder<T> {

    private final Class<T> cls;
    private final InvocationHandler invocationHandler;

    public ServiceBuilder(String baseApiUrl, Class<T> cls, InvocationHandler invocationHandler, IHttpClient httpReqeust) {
        this.cls = cls;
        if (invocationHandler != null) {
            this.invocationHandler = invocationHandler;
        } else {
            this.invocationHandler = new DefaultInvocationHandler(baseApiUrl, httpReqeust);
        }
    }

    public ServiceBuilder(String baseApiUrl, Class<T> cls) {
        this(baseApiUrl, cls, null, new OkHttp3Client());
    }

    public ServiceBuilder(Class<T> cls, InvocationHandler invocationHandler, IHttpClient httpReqeust) {
        this.cls = cls;
        if (invocationHandler != null) {
            this.invocationHandler = invocationHandler;
        } else {
            this.invocationHandler = new DefaultInvocationHandler(httpReqeust);
        }
    }

    public T build() {
        return (T) Proxy.newProxyInstance(this.cls.getClassLoader(), new Class<?>[]{this.cls}, invocationHandler);
    }
}
