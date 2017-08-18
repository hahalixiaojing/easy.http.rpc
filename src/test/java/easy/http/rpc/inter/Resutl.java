package easy.http.rpc.inter;

/**
 * Created by lixiaojing3 on 2017/8/17.
 */
public class Resutl<T> {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
