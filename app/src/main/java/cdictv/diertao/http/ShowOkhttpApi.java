package cdictv.diertao.http;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ShowOkhttpApi {
    public static void show(String uri,Mycall mycall){
        RequestBody body= new FormBody.Builder().build();
        Request request=new Request.Builder().url(uri).post(body)
                .build();
        NetworkApi.request(request,mycall);
    }

    public static void setCar(String uri, String str1,String str2 ,Mycall mycall){
        RequestBody body= new FormBody.Builder()
                .add("num", str1)
                .add("money", str2).build();
        Request request=new Request.Builder().url(uri).post(body)
                .build();
        NetworkApi.request(request,mycall);
    }
}
