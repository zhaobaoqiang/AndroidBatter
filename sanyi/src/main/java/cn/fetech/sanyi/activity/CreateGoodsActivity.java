package cn.fetech.sanyi.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wq.photo.MediaChoseActivity;
import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.HorizontalListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Goods;
import cn.fetech.sanyi.widget.OnSelectComplete;
import cn.fetech.sanyi.widget.SingleWheelPop;

public class CreateGoodsActivity extends BaseActivity {

    @ViewInject(R.id.hListView)
    private HorizontalListView hlv;


    private DisplayImageOptions options;

    private ImageAdapter imageAdapter;


    private List<String> imagePathList = new ArrayList<>();



    @ViewInject(R.id.tv_select_type)
    private TextView tvType;
    @ViewInject(R.id.tv_select_qixing)
    private TextView tvQixin;
    @ViewInject(R.id.tv_select_ticai)
    private TextView tvTicai;
    @ViewInject(R.id.tv_select_zhongshui)
    private TextView tvZhongshui;
    @ViewInject(R.id.tv_select_color)
    private TextView tvColor;
    @ViewInject(R.id.tv_select_danwei)
    private TextView tvDanwei;
    @ViewInject(R.id.tv_select_baozhuang)
    private TextView tvBaozhuang;
    @ViewInject(R.id.tv_select_xiaci)
    private TextView tvXiaci;
    @ViewInject(R.id.tv_select_zhengshu)
    private TextView tvZhengtshu;


    @ViewInject(R.id.root)
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_goods;
    }

    @Override
    protected String getToolBarTitle() {
        return "添加货品";
    }

    @Override
    protected void initView() {
        imagePathList.add("mipmap://" + R.mipmap.icon_image_add);
        imageAdapter = new ImageAdapter(imagePathList);
        hlv.setAdapter(imageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_goods, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {
            ToastUtil.showToast(this,"正在保存");
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class ImageAdapter extends BatterAdapter<String> {

        public ImageAdapter(List<String> list) {
            super(list, CreateGoodsActivity.this);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            String url = "file:///" + getItem(position);
            ImageView imageView;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_goods_image, parent, false);
                imageView = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(imageView);
            } else {
                imageView = (ImageView) convertView.getTag();
            }
            if (position == list.size() - 1) {
                imageView.setImageResource(R.mipmap.icon_image_add);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MediaChoseActivity.class);
                        intent.putExtra("chose_mode", 1);
                        intent.putExtra("max_chose_count", 9);
                        startActivityForResult(intent, 100);
                    }
                });
            } else {
                ImageLoader.getInstance().displayImage(url, imageView, options);
            }

            return convertView;
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100 && data != null) {
            List<String> paths = data.getStringArrayListExtra("data");

            if (paths != null && paths.size() > 0) {
                imagePathList.addAll(imagePathList.size() - 1, paths);
                imageAdapter.notifyDataSetChanged();
            }
        }
    }


    public void performClick(View view) {
        getSingleWheelPop(view.getId()).show();
    }


    private Map<Integer, SingleWheelPop> map = new HashMap<>();
    private Map<Integer, String> resultMap = new HashMap<>();

    private Integer[] keys = new Integer[]{R.id.tv_select_type, R.id.tv_select_type, R.id.tv_select_type,
            R.id.tv_select_type, R.id.tv_select_type, R.id.tv_select_type, R.id.tv_select_type,
            R.id.tv_select_type, R.id.tv_select_type,};

    private SingleWheelPop getSingleWheelPop(int id) {
        if (!map.containsKey(id)) {
            List<String> list = null;
            switch (id) {
                case R.id.tv_select_type:
                    list = Goods.getGoodsType();
                    break;
                case R.id.tv_select_qixing:
                    list = Goods.getQixing();
                    break;
                case R.id.tv_select_ticai:
                    list = Goods.getTicai();
                    break;
                case R.id.tv_select_zhongshui:
                    list = Goods.getZhongshui();
                    break;
                case R.id.tv_select_color:
                    list = Goods.getColor();
                    break;
                case R.id.tv_select_danwei:
                    list = Goods.getDanwei();
                    break;
                case R.id.tv_select_xiaci:
                    list = Goods.getHave();
                    break;
                case R.id.tv_select_zhengshu:
                    list = Goods.getZhengshu();
                    break;
                case R.id.tv_select_baozhuang:
                    list = Goods.getHave();
                    break;
            }

            map.put(id, new SingleWheelPop(this, new MyOnSelect(id), list));
        }

        return map.get(id);
    }


    class MyOnSelect implements OnSelectComplete<String> {
        private int id;

        public MyOnSelect(int id) {
            this.id = id;
        }

        @Override
        public void onChoose(String result) {
            resultMap.put(id, result);

            switch (id) {
                case R.id.tv_select_type:
                    tvType.setText(result);
                    break;
                case R.id.tv_select_qixing:
                    tvQixin.setText(result);
                    break;
                case R.id.tv_select_ticai:
                    tvTicai.setText(result);
                    break;
                case R.id.tv_select_zhongshui:
                    tvZhongshui.setText(result);
                    break;
                case R.id.tv_select_color:
                    tvColor.setText(result);
                    break;
                case R.id.tv_select_danwei:
                    tvDanwei.setText(result);
                    break;
                case R.id.tv_select_xiaci:
                    tvXiaci.setText(result);
                    break;
                case R.id.tv_select_zhengshu:
                    tvZhengtshu.setText(result);
                    break;
                case R.id.tv_select_baozhuang:
                    tvBaozhuang.setText(result);
                    break;
            }
        }

        @Override
        public View getRootView() {
            return rootView;
        }
    }


}
