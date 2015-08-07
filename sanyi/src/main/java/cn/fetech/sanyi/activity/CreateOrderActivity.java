package cn.fetech.sanyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterActivity;
import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.utils.ToastUtil;
import com.wudoumi.batter.view.listview.stikyyheader.StikkyHeaderBuilder;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Goods;
import cn.fetech.sanyi.fragment.OrderInfoFragment;

public class CreateOrderActivity extends BaseActivity {
    @ViewInject(R.id.listview)
    private ListView mListView;

    @ViewInject(R.id.header)
    private FrameLayout head;


    private List<Goods> list = new ArrayList<>();

    private GoodsAdapter goodsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_create_order;
    }

    @Override
    protected String getToolBarTitle() {
        return "创建订单";
    }

    @Override
    protected void initView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content,new OrderInfoFragment()).commit();

        StikkyHeaderBuilder.stickTo(mListView)
                .setHeader(head)
                .minHeightHeaderDim(R.dimen.q100)
                        //.animator(animator)
                .build();

        goodsAdapter = new GoodsAdapter(list);

        mListView.setAdapter(goodsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_order, menu);
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
            ToastUtil.showToast(this, "正在保存");
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void performClick(View view){
        Intent intent = new Intent(this,GoodsListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Goods goods = (Goods) intent.getSerializableExtra("Goods");

        if(goods!=null){
            list.add(goods);
            goodsAdapter.notifyDataSetChanged();
        }

    }

    class GoodsAdapter extends BatterAdapter<Goods> {

        public GoodsAdapter(List<Goods> list) {
            super(list, CreateOrderActivity.this);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Goods goods = getItem(position);
            Holder holder = null;
            if(convertView==null){
                convertView = inflater.inflate(R.layout.item_order_goods,null);
                holder = new Holder(convertView);
            }else{
                holder = (Holder) convertView.getTag();
            }
            holder.tvNo.setText(goods.getGoodsNo()+"("+goods.getName()+")");
            holder.tvPrice.setText("￥"+goods.getSellingPrice()+"元");
            return convertView;
        }

        class Holder{
            ImageView iv;
            TextView tvNo,tvPrice;

            public Holder(View view) {
                iv = (ImageView) view.findViewById(R.id.icon);
                tvNo = (TextView) view.findViewById(R.id.goods_no);
                tvPrice = (TextView) view.findViewById(R.id.goods_price);
                view.setTag(this);
            }
        }
    }
}
