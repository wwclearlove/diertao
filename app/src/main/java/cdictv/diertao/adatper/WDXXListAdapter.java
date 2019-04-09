package cdictv.diertao.adatper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.WDXXListBean;

public class WDXXListAdapter extends BaseAdapter {
    private Context context;
    private List<WDXXListBean> listBeanList;
    private TextView xh;
    private TextView bjlx;
    private TextView yz;
    private TextView dqz;
    public WDXXListAdapter(Context context, List<WDXXListBean> listBeanList) {
        this.context = context;
        this.listBeanList = listBeanList;
    }

    @Override
    public int getCount() {
        return listBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = View.inflate(context, R.layout.xxcx_list_item_layout, null);
        }
        init(convertView);


        xh.setText(listBeanList.get(position).getId()+"");
        bjlx.setText(listBeanList.get(position).getBjlx());
        yz.setText(listBeanList.get(position).getYz());
        dqz.setText(listBeanList.get(position).getDqz());








        return convertView;
    }

    private void init(View inflate) {
        xh = inflate.findViewById(R.id.xh);
        bjlx = inflate.findViewById(R.id.bjlx);
        yz = inflate.findViewById(R.id.yz);
        dqz =inflate. findViewById(R.id.dqz);
    }
}
