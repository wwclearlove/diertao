package cdictv.diertao.adatper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.bean.EtcBean;

public class EtcAdatper extends BaseAdapter {
    private Context mContext;
    private List<EtcBean >mEtcBeans;
    private TextView ectCarid;
    private TextView ectCarnum;
    private TextView ectCarmoney;
    private TextView ectCartime;
    private View mView;

    public EtcAdatper(Context context, List<EtcBean> etcBeans) {
        mContext = context;
        mEtcBeans = etcBeans;
    }

    @Override
    public int getCount() {
        return mEtcBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mEtcBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EtcBean etcBean=mEtcBeans.get(position);
        if(convertView==null){
           mView = LayoutInflater.from(mContext).inflate(R.layout.ectchongzhi_item,parent,false);
//                   View.inflate(mContext, R.layout.ectchongzhi_item,null);
//
        }else {
            mView =convertView;
        }
        initView();
        ectCarid.setText(etcBean.ectid);
        ectCarnum.setText(etcBean.ectnum);
        ectCarmoney.setText(etcBean.ectmoney);
        ectCartime.setText(etcBean.ecttime);
        return mView;
    }

    private void initView() {
        ectCarid = mView. findViewById(R.id.ect_carid);
        ectCarnum = mView. findViewById(R.id.ect_carnum);
        ectCarmoney = mView. findViewById(R.id.ect_carmoney);
        ectCartime = mView.findViewById(R.id.ect_cartime);

    }
}
