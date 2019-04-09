package cdictv.diertao.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.NesAdatper;
import cdictv.diertao.bean.NewsBean;
import cdictv.diertao.http.Mycall;
import cdictv.diertao.http.ShowOkhttpApi;


public class NewsActivity extends AppCompatActivity {
    private ImageView left_menu;
    private TextView title;
    private ListView list;
    List<NewsBean.NewslistBean> newslist;
    private NesAdatper mAdatper;
    private ProgressDialog mProgressDialog;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newslist=new ArrayList<>();
        setContentView(R.layout.activity_news);
        initView();
        okhttpGeda();
    }

    private void okhttpGeda() {
        mProgressDialog = ProgressDialog.show(NewsActivity.this,"提示","正在请求");
        ShowOkhttpApi.show("http://api.tianapi.com/keji/?key=6edb9118c6ce61c710140aeb03b10e2c&num=50&rand=1",
                new Mycall() {
                    @Override
                    public void success(String json) {
                        try {
                            Gson gson=new Gson();
                            NewsBean newsBean=gson.fromJson(json,NewsBean.class);
                            List<NewsBean.NewslistBean> list=newsBean.newslist;
                            newslist.clear();
                            newslist.addAll(list);
                            mAdatper.notifyDataSetChanged();
                        }catch (Exception e){

                        }finally {
                            mProgressDialog.dismiss();
                        }
                    }

                    @Override
                    public void filed(String msg) {
                        mProgressDialog.dismiss();
                    }

                });
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        list = (ListView) findViewById(R.id.list);
        mAdatper = new NesAdatper(newslist,NewsActivity.this);
        list.setAdapter(mAdatper);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(NewsActivity.this, WebViewActivity.class);
                intent.putExtra("title",newslist.get(position).title);
                intent.putExtra("uri",newslist.get(position).url);
                startActivity(intent);
            }
        });
    }


}
