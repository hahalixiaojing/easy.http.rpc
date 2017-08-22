package easy.http.rpc;

public interface IHttpClient {
    String request(String apiUrl, Object[] params) throws Exception;
}
