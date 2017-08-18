package easy.http.rpc.inter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class RetObj {
    private int a;
    private boolean b;
    private int[] alist;
    private String[] slist;
    private Map<String, String> map;
    private BigDecimal bigDecimal;

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public int[] getAlist() {
        return alist;
    }

    public void setAlist(int[] alist) {
        this.alist = alist;
    }

    public String[] getSlist() {
        return slist;
    }

    public void setSlist(String[] slist) {
        this.slist = slist;
    }

    public List<String> getLlist() {
        return llist;
    }

    public void setLlist(List<String> llist) {
        this.llist = llist;
    }

    private List<String> llist;
}
