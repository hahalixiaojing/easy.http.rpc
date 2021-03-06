package easy.http.rpc;

import easy.http.rpc.okhttp.DefaultOkHttp3Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DefaultInvocationHandler implements InvocationHandler {

    //    private final String baseApiUrl;
    private final IHttpClient client;
    private final Logger logger = LoggerFactory.getLogger(DefaultInvocationHandler.class);
    private final IAddressSelect addressSelect;

    public DefaultInvocationHandler(IAddressSelect addressSelect, IHttpClient client) {
        this.addressSelect = addressSelect;
//        this.baseApiUrl = baseApiUrl;baseApiUrl
        if (client == null) {
            this.client = new DefaultOkHttp3Client();
        } else {
            this.client = client;
        }
    }

//    public DefaultInvocationHandler(IHttpClient client) {
//        this.addressSelect = null;
//        this.baseApiUrl = null;
//        this.client = client;
//    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String interfaceClassName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Class<?> returnType = method.getReturnType();

        String request = "";
        try {

            String apiUrl = this.getApiUrl(interfaceClassName, methodName);
            request = this.client.request(apiUrl, args);

            if (returnType == void.class) {
                return null;
            }
            return JSONStringToObject.methodReturnDataToObject(method, request);
        } catch (Exception ex) {
            this.logger.error(request, ex);
            throw ex;
        }
    }

    private String getApiUrl(String interfaceClassName, String methodName) {

        String baseApiUrl = this.addressSelect.select();

        if (baseApiUrl.endsWith("/")) {
            return baseApiUrl + interfaceClassName + "/" + methodName;
        }
        return baseApiUrl + "/" + interfaceClassName + "/" + methodName;
    }
}
