package cdictv.diertao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import cdictv.diertao.R;
import cdictv.diertao.util.Sputils;


public class ChongzhijiluFragment extends Fragment {
    View mView;
    Context mContext;
    private TextView zhichu;
    private TextView name;
    private TextView gone;
    private ListView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=container.getContext();
        mView = inflater.inflate(R.layout.frament_chognzhijilu,container,false);
        initview();
        xuanyan();
        return mView;
    }

    private void xuanyan() {
        name.setText(Sputils.getString("name"));
    }

    private void initview() {
        zhichu = mView. findViewById(R.id.zhichu);
        name= mView. findViewById(R.id.name);
        gone= mView. findViewById(R.id.gone);
        list =mView. findViewById(R.id.list);

    }
}
