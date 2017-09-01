package easy.http.rpc.inter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import easy.http.rpc.IHttpClient;
import easy.http.rpc.ServiceBuilder;
import easy.http.rpc.okhttp.OkHttp3Client;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyTest {

    @Test
    public void stringResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {
                return "1";
            }
        });
        String a = build.create("a", 1, false);
    }

    @Test
    public void intResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {
                return JSON.toJSONString(1);
            }
        });
        int a = build.sub(1, 1);
    }

    @Test
    public void arrayResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {
                return JSON.toJSONString(new int[]{1, 2, 3});
            }
        });

        int[] as = build.intArray("a");
    }

    @Test
    public void objArrayResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c"});

                List<String> list = new ArrayList<>();
                list.add("aa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "a");

                retObj.setMap(hashMap);

                return JSON.toJSONString(new RetObj[]{retObj});
            }
        });

        RetObj[] as = build.objArray("a");
    }

    @Test
    public void stringListArrayResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                List<String> strings = new ArrayList<>();
                strings.add("aaaa");

                return JSON.toJSONString(strings);
            }
        });

        List<String> strings = build.stringList("a");
    }

    @Test
    public void arrayListResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                ArrayList<String> strings = new ArrayList<>();
                strings.add("aaaa");

                return JSON.toJSONString(strings);
            }
        });

        ArrayList<String> a = build.arrayListList("a");
    }

    @Test
    public void arrayListObjTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c", "b"});

                List<String> list = new ArrayList<>();
                list.add("aaa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "aa");

                retObj.setMap(hashMap);

                ArrayList<RetObj> list1 = new ArrayList<>();
                list1.add(retObj);

                return JSON.toJSONString(list1);
            }
        });

        ArrayList<RetObj> x = build.arrayListObj("x");
    }

    @Test
    public void objResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c"});

                List<String> list = new ArrayList<>();
                list.add("aa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "a");

                retObj.setMap(hashMap);

                return JSON.toJSONString(retObj);
            }
        });
        RetObj a = build.getObj("a");

    }

    @Test
    public void objListResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c"});

                List<String> list = new ArrayList<>();
                list.add("aa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "a");

                retObj.setMap(hashMap);

                ArrayList<RetObj> list1 = new ArrayList<>();
                list1.add(retObj);

                return JSON.toJSONString(list1);
            }
        });

        List<RetObj> a = build.objList("a");
    }

    @Test
    public void MapListResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                Map<String, String> map = new HashMap<>();
                map.put("a", "a");

                return JSON.toJSONString(map);
            }
        });

        Map<String, String> map = build.mapList("b");
    }

    @Test
    public void MapObjListResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {
                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c"});

                List<String> list = new ArrayList<>();
                list.add("aa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "a");

                retObj.setMap(hashMap);


                Map<String, RetObj> map = new HashMap<>();
                map.put("a", retObj);

                return JSON.toJSONString(map);
            }
        });

        Map<String, RetObj> b = build.mapObjList("b");
    }

    @Test
    public void intGeResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                Resutl<int[]> ext = new Resutl<>();
                ext.setData(new int[]{1, 2, 3});

                return JSON.toJSONString(ext);
            }
        });

        Resutl<int[]> c = build.intGe("c");

    }

    @Test
    public void argsTest() {


        RetObj retObj2 = new RetObj();
        retObj2.setA(1);
        retObj2.setAlist(new int[]{1, 2, 3});
        retObj2.setB(true);
        retObj2.setSlist(new String[]{"a", "b", "c"});

        List<String> list2 = new ArrayList<>();
        list2.add("aa");
        retObj2.setLlist(list2);

        Map<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("a", "a");

        retObj2.setMap(hashMap2);


        RetObj retObj = new RetObj();
        retObj.setA(1);
        retObj.setAlist(new int[]{1, 2, 3});
        retObj.setB(true);
        retObj.setSlist(new String[]{"a", "b", "c"});

        List<String> list = new ArrayList<>();
        list.add("aa");
        retObj.setLlist(list);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "a");

        retObj.setMap(hashMap);


        Map<String, RetObj> map = new HashMap<>();
        map.put("a", retObj);


        Resutl<int[]> ext = new Resutl<>();
        ext.setData(new int[]{1, 2, 3});

        Map<String, String> mm = new HashMap<>();
        mm.put("sdf", "xxxx");

        Object[] objects = new Object[7];
        objects[0] = 1;
        objects[1] = true;
        objects[2] = "123";
        objects[3] = map;
        objects[4] = retObj2;
        objects[5] = ext;
        objects[6] = mm;

        String s = JSON.toJSONString(objects);

        List<String> strings = JSON.parseArray(s, String.class);

        Resutl<int[]> resutl = JSON.parseObject(strings.get(5), new TypeReference<Resutl<int[]>>() {
        });
    }

    @Test
    public void objgeResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {

                Resutl<ArrayList<RetObj>> ext = new Resutl<>();
                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c", "b"});

                List<String> list = new ArrayList<>();
                list.add("aaa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "aa");

                retObj.setMap(hashMap);

                ArrayList<RetObj> item = new ArrayList<>();
                item.add(retObj);

                ext.setData(item);

                return JSON.toJSONString(ext);
            }
        });

        Resutl<ArrayList<RetObj>> c = build.objge("c");
    }

    @Test
    public void GenericResultTest() {
        ITestService build = this.create(new IHttpClient() {
            @Override
            public String request(String apiUrl, Object[] params) {
                Resutl<RetObj> f2 = new Resutl<>();

                RetObj retObj = new RetObj();
                retObj.setA(1);
                retObj.setAlist(new int[]{1, 2, 3});
                retObj.setB(true);
                retObj.setSlist(new String[]{"a", "b", "c"});

                List<String> list = new ArrayList<>();
                list.add("aa");
                retObj.setLlist(list);

                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("a", "a");

                retObj.setMap(hashMap);

                f2.setData(retObj);


                return JSON.toJSONString(f2);
            }
        });

        Resutl<RetObj> ss = build.rr("ss");
    }

    @Test
    public void jsonTest() {

        Resutl<String> f = new Resutl<>();
        f.setData("sddsf");

        String s = JSON.toJSONString(f);

        Resutl resutl = JSON.parseObject(s, f.getClass());

        Resutl<RetObj> f2 = new Resutl<>();

        RetObj retObj = new RetObj();
        retObj.setA(1);
        retObj.setAlist(new int[]{1, 2, 3});
        retObj.setB(true);
        retObj.setSlist(new String[]{"a", "b", "c"});

        List<String> list = new ArrayList<>();
        list.add("aa");
        retObj.setLlist(list);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "a");

        retObj.setMap(hashMap);

        f2.setData(retObj);

        String s1 = JSON.toJSONString(f2);


        Resutl resutl1 = JSON.parseObject(s1, new TypeReference<Resutl<RetObj>>() {
        });
    }

    @Test
    public void retObjResultTest() {
        ITestService build = this.create(new OkHttp3Client());

        Resutl<RetObj> f2 = new Resutl<>();

        RetObj retObj = new RetObj();
        retObj.setA(1);
        retObj.setAlist(new int[]{1, 2, 3});
        retObj.setB(true);
        retObj.setSlist(new String[]{"a", "b", "c"});

        List<String> list = new ArrayList<>();
        list.add("aa");
        retObj.setLlist(list);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "a");

        retObj.setMap(hashMap);

        RetObj obj = build.retObj(retObj);
        RetObj nullobj = build.retObj(null);
    }

    @Test
    public void retObjResultListTest() {
        ITestService build = this.create(new OkHttp3Client());
        Resutl<RetObj> f2 = new Resutl<>();

        RetObj retObj = new RetObj();
        retObj.setA(1);
        retObj.setAlist(new int[]{1, 2, 3, 4});
        retObj.setB(true);
        retObj.setSlist(new String[]{"a", "b", "c"});
        retObj.setBigDecimal(new BigDecimal("23.56"));

        List<String> list = new ArrayList<>();
        list.add("aa");
        retObj.setLlist(list);

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("a", "a");

        retObj.setMap(hashMap);

        List<RetObj> retObjs = build.retObjList(retObj);
    }

    @Test
    public void noParamTest() {
        ITestService build = this.create(new OkHttp3Client());

        String s = build.testNoParams();
        System.out.println(s);
    }


    private ITestService create(IHttpClient client) {
        ServiceBuilder<ITestService> serviceServiceBuilder = new ServiceBuilder<>("http://localhost:8091/test/", ITestService.class, null, client);
        return serviceServiceBuilder.build();
    }

}
