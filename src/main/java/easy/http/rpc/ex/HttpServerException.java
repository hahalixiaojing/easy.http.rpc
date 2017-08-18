package easy.http.rpc.ex;

/**
 * Created by lixiaojing3 on 2017/8/16.
 */
public class HttpServerException extends RuntimeException {
    private final int httpStatus;
    private final String url;

    public HttpServerException(int httpStatus, String url, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.url = url;
    }
    public int getHttpStatus() {
        return httpStatus;
    }

    public String getUrl() {
        return url;
    }
}
