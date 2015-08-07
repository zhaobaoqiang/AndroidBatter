package com.wudoumi.battertest.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wudoumi.batter.view.animation.shimmer.Shimmer;
import com.wudoumi.batter.view.animation.shimmer.ShimmerTextView;
import com.wudoumi.battertest.R;

public class GifTextureFragment extends Fragment {
    ShimmerTextView stv;
    Shimmer shimmer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shimmer = new Shimmer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.texture, container, false);
        stv = (ShimmerTextView) view.findViewById(R.id.shimmer_text);
        shimmer.start(stv);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        shimmer.cancel();
    }
}
