package cdictv.diertao.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.CarChongzhijiluBean;

public class ZhangdangguanliAdapter extends BaseAdapter {


    private TextView zhangbanCarid;
    private TextView zhangbanCarnum;
    private TextView zhangbanCarmoney;
    private TextView zhangbanCarper;
    private TextView zhangbanCartime;



    Context context;
    List<CarChongzhijiluBean> beans;

    public ZhangdangguanliAdapter(Context context, List<CarChongzhijiluBean> beans) {
        this.context = context;
        this.beans = beans;
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.zhangdan_item,parent,false);
        }
        zhangbanCarid = (TextView) convertView.findViewById(R.id.zhangban_carid);
        zhangbanCarnum = (TextView) convertView.findViewById(R.id.zhangban_carnum);
        zhangbanCarmoney = (TextView) convertView.findViewById(R.id.zhangban_carmoney);
        zhangbanCarper = (TextView) convertView.findViewById(R.id.zhangban_carper);
        zhangbanCartime = (TextView) convertView.findViewById(R.id.zhangban_cartime);

        zhangbanCarid.setText(beans.get(position).carid+"");
        zhangbanCarnum.setText(beans.get(position).carnum);
        zhangbanCarmoney.setText(beans.get(position).carmoney);
        zhangbanCarper.setText(beans.get(position).carper);
        zhangbanCartime.setText(beans.get(position).cartime);
        return convertView;
    }
}
