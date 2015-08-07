package cn.fetech.sanyi.data;

import android.support.v4.app.Fragment;

import cn.fetech.sanyi.fragment.MainUserFragment;
import cn.fetech.sanyi.fragment.NoAuthFragment;

/**
 * Created by qianjujun on 2015/7/30 0030 12:47.
 * qianjujun@163.com
 */
public class FragmentUtil {

    public static Fragment getMainUserFragment(int type){
        Fragment fragment = null;
        if(type == MainUserFragment.TYPE_RESOURCES){
            fragment = new NoAuthFragment();
        }else{
            fragment = MainUserFragment.getInstance(type);
        }
        return fragment;
    }
}
