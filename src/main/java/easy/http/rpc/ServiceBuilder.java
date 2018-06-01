package easy.http.rpc;

import easy.http.rpc.okhttp.DefaultOkHttp3Client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ServiceBuilder<T> {

    private final Class<T> cls;
    private final InvocationHandler invocationHandler;

//    @Deprecated
//    public ServiceBuilder(String baseApiUrl, Class<T> cls, InvocationHandler invocationHandler, IHttpClient httpReqeust) {
//        this.cls = cls;
//        if (invocationHandler != null) {
//            this.invocationHandler = invocationHandler;
//        } else {
//            this.invocationHandler = new DefaultInvocationHandler(new SingleStaticAddressSelect(baseApiUrl), httpReqeust);
//        }
//    }

    public ServiceBuilder(String baseApiUrl, Class<T> cls, IHttpClient httpClient) {
        this.cls = cls;
        this.invocationHandler = new DefaultInvocationHandler(new SingleStaticAddressSelect(baseApiUrl), httpClient);
    }

    public ServiceBuilder(IAddressSelect addressSelect, Class<T> cls, IHttpClient httpClient) {
        this.cls = cls;
        this.invocationHandler = new DefaultInvocationHandler(addressSelect, httpClient);
    }


    public ServiceBuilder(String baseApiUrl, Class<T> cls) {
        this(baseApiUrl, cls, new DefaultOkHttp3Client());
    }

//    @Deprecated
//    public ServiceBuilder(Class<T> cls, InvocationHandler invocationHandler, IHttpClient httpReqeust) {
//        this.cls = cls;
//        if (invocationHandler != null) {
//            this.invocationHandler = invocationHandler;
//        } else {
//            this.invocationHandler = new DefaultInvocationHandler(,httpReqeust);
//        }
//    }

    public T build() {
        return (T) Proxy.newProxyInstance(this.cls.getClassLoader(), new Class<?>[]{this.cls}, invocationHandler);
    }
}
