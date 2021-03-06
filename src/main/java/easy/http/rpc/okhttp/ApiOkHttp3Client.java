package easy.http.rpc.okhttp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import easy.http.rpc.IHttpClient;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiOkHttp3Client implements IHttpClient {
    private final OkHttpClient client;

    public ApiOkHttp3Client(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            this.client = okHttpClient;
        } else {
            this.client = new OkHttpClient();
        }
    }

    public ApiOkHttp3Client() {
        this(null);
    }

    @Override
    public String request(String apiUrl, Object[] params) throws Exception {


        String[] argsArray = new String[params.length];
        for (int i = 0; i < argsArray.length; i++) {
            argsArray[i] = JSON.toJSONString(params[i], SerializerFeature.WriteMapNullValue);
        }

        String args = JSON.toJSONString(argsArray, SerializerFeature.WriteMapNullValue);


        FormBody.Builder builder = new FormBody.Builder();
        builder.addEncoded("args", args);

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Accept", "application/json")
                .post(builder.build())
                .build();
        Response response = client.newCall(request).execute();


        String explicitEx = response.header("explicitEx");
        if (explicitEx != null && !explicitEx.equals("")) {
            Class<?> aClass = Class.forName(explicitEx);
            if (aClass != null) {
                throw (Exception) JSON.parseObject(response.body().string(), aClass);
            }
        }

        if (response.code() != 200) {
            throw new RuntimeException(response.body().string());
        }

        return response.body().string();
    }
}
