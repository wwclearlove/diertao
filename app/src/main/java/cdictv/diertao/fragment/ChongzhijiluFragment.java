package cdictv.diertao.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cdictv.diertao.R;


public class ChongzhijiluFragment extends Fragment {
    View mView;
    Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext=container.getContext();
        mView = inflater.inflate(R.layout.frament_chognzhijilu,container,false);
        return mView;
    }
}
