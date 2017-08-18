package easy.http.rpc;

public interface IHttpClient {
    HttpResponseData request(String apiUrl, Object[] params);
}
