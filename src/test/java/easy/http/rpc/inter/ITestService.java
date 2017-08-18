package easy.http.rpc.inter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ITestService {

    String testNoParams();

    void add(int a, int b);

    String create(String a, int b, boolean c);

    int sub(int a, int b);

    RetObj getObj(String a);

    int[] intArray(String a);

    RetObj[] objArray(String a);

    List<String> stringList(String a);

    List<RetObj> objList(String a);

    ArrayList<String> arrayListList(String b);

    ArrayList<RetObj> arrayListObj(String b);

    Map<String, String> mapList(String b);

    Map<String, RetObj> mapObjList(String b);

    Resutl<RetObj> rr(String b);

    Resutl<int[]> intGe(String b);

    Resutl<ArrayList<RetObj>> objge(String b);

    RetObj retObj(RetObj retObj);
    List<RetObj> retObjList(RetObj retObj);
}
