package cn.fetech.sanyi.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wudoumi.batter.base.BatterFragment;

import cn.fetech.sanyi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoAuthFragment extends BatterFragment {


    public NoAuthFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_no_auth;
    }
}
