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
    public HttpResponseData request(String apiUrl, Object[] params) {

        FormBody.Builder builder = new FormBody.Builder();

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                builder.addEncoded(String.valueOf(i), JSON.toJSONString(params[i]));
            }
        }
        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("Accept","application/json")
                .post(builder.build())
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            if (response.code() == 200) {
                return new HttpResponseData(responseData, response.code());
            } else {
                return new HttpResponseData("", response.code(), responseData);
            }

        } catch (IOException e) {
            return new HttpResponseData("", 500, e.getMessage());
        }
    }
}
