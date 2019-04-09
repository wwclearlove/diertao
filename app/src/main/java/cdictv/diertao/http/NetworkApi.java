package cdictv.diertao.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cdictv.diertao.App;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkApi {
    private static OkHttpClient sHttpClient=new OkHttpClient.Builder()
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build();
    private static  Handler mHandler=new Handler(Looper.getMainLooper());
    public static void request(final Request request, final  Mycall mycall){
        sHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mycall.filed(e.getMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               final String str=response.body().string().trim();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(TextUtils.isEmpty(str)){
                             Toast.makeText(App.INSTANCE,"请求数据为空",Toast.LENGTH_SHORT).show();
                        }else {
                            mycall.success(str);
                        }
                    }
                });
            }
        });
    }

}
