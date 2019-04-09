package cdictv.diertao.http;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ShowOkhttpApi {
    public static void show(String uri,Mycall mycall){
        RequestBody body= new FormBody.Builder().build();
        Request request=new Request.Builder().post(body).url(uri)
                .build();
        NetworkApi.request(request,mycall);
    }
}
