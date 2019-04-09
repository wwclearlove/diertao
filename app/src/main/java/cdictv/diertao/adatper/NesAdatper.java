package cdictv.diertao.adatper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.NewsBean;


public class NesAdatper extends BaseAdapter {
    List<NewsBean.NewslistBean> newslist;
    Context mContext;
    public NesAdatper(List<NewsBean.NewslistBean> list, Context context) {
        newslist=list;
        mContext=context;
    }

    @Override
    public int getCount() {
        return newslist.size();
    }

    @Override
    public Object getItem(int position) {
        return newslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsBean.NewslistBean  bean=newslist.get(position);
        if(convertView==null){
            convertView=View.inflate(mContext, R.layout.news_item,null);
        }
        TextView title = convertView.findViewById(R.id.title);
        title.setText(bean.title+"");
        return convertView;
    }
}
