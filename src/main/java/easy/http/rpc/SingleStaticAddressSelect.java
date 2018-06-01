package easy.http.rpc;

public class SingleStaticAddressSelect implements IAddressSelect {
    private final String baseUrl;

    public SingleStaticAddressSelect(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String select() {
        return this.baseUrl;
    }
}
