package cdictv.diertao.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import cdictv.diertao.R;
import cdictv.diertao.adatper.WDXXListAdapter;
import cdictv.diertao.bean.WDXXListBean;

public class XXCXFragment extends Fragment {
    private Context context;
    private Spinner spinner;
    private ListView lsit;
    private List<String> list;
    private WDXXListAdapter wdxxListAdapter;
    private List<WDXXListBean> listBeanList=new ArrayList<>();
    private List<WDXXListBean> listBeanLists=new ArrayList<>();
    public XXCXFragment() {
    }

    @SuppressLint("ValidFragment")
    public XXCXFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.xxcx_layout, container, false);
        init(inflate);
        setAdapter();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                setData(position);
                Log.e("tag", "onItemSelected: "+position );
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return inflate;
    }

    private void setData(int position) {
        listBeanList.clear();
        for(WDXXListBean l:listBeanLists){
            if(position==0){
                listBeanList.clear();
                listBeanList.addAll(listBeanLists);
            }else
            if(l.getType()==(position)){
                listBeanList.add(l);
            }
        }
        wdxxListAdapter.notifyDataSetChanged();
    }

    private void setAdapter() {
        spinner.setAdapter(new ArrayAdapter<String>(context,R.layout.spinner_item_layout,R.id.tv,list));
        wdxxListAdapter = new WDXXListAdapter(context, listBeanList);
        lsit.setAdapter(wdxxListAdapter);
    }

    private void init(View inflate) {
        spinner = inflate.findViewById(R.id.spinner);
        lsit = inflate.findViewById(R.id.lsit);
        list=new ArrayList<>();
        list.add("全部");
        list.add("湿度");
        list.add("温度");
        list.add("CO2");
        list.add("光照");
        list.add("PM2.5");
        listBeanLists.add(new WDXXListBean(1,1,"【湿度】报警","80","90"));
        listBeanLists.add(new WDXXListBean(2,1,"【湿度】报警","50","70"));
        listBeanLists.add(new WDXXListBean(3,2,"【温度】报警","23","90"));
        listBeanLists.add(new WDXXListBean(4,2,"【温度】报警","34","50"));
        listBeanLists.add(new WDXXListBean(5,3,"【CO2】报警","98","45"));
        listBeanLists.add(new WDXXListBean(6,3,"【CO2】报警","76","50"));
        listBeanLists.add(new WDXXListBean(7,4,"【光照】报警","34","34"));
        listBeanLists.add(new WDXXListBean(8,4,"【光照】报警","56","89"));
        listBeanLists.add(new WDXXListBean(9,5,"【PM2.5】报警","23","45"));
        listBeanLists.add(new WDXXListBean(10,5,"【PM2.5】报警","56","89"));
        //listBeanList.addAll(listBeanLists);
    }
}
