package easy.http.rpc.okhttp;

import com.alibaba.fastjson.JSON;
import easy.http.rpc.HttpResponseData;
import easy.http.rpc.IHttpClient;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkHttp3Client implements IHttpClient {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public String request(String apiUrl, Object[] params) throws Exception {

        FormBody.Builder builder = new FormBody.Builder();

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                builder.addEncoded(String.valueOf(i), JSON.toJSONString(params[i]));
            }
        }
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
