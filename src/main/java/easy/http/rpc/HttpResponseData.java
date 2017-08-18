package easy.http.rpc;

public class HttpResponseData {
    public final String responseData;
    public final int httpStatus;
    public final String errorMessage;

    public HttpResponseData(String responseData, int httpStatus) {
        this(responseData, httpStatus, "");
    }

    public HttpResponseData(String responseData, int httpStatus, String errorMessage) {
        this.responseData = responseData;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
