package cn.fetech.sanyi.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.base.BatterLoadingFragment;
import com.wudoumi.batter.batterthread.BatterTask;
import com.wudoumi.batter.batterthread.BatterTaskItem;
import com.wudoumi.batter.batterthread.DoWork;
import com.wudoumi.batter.ioc.annotation.ViewInject;
import com.wudoumi.batter.view.loadview.BatterLoadResponseListner;
import com.wudoumi.batter.view.pullableview.PullToRefreshLayout;
import com.wudoumi.batter.view.pullableview.State;

import java.util.ArrayList;
import java.util.List;

import cn.fetech.sanyi.R;
import cn.fetech.sanyi.activity.CreateOrderActivity;
import cn.fetech.sanyi.bean.Goods;
import cn.fetech.sanyi.data.TestData;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsFragment extends BatterLoadingFragment  implements PullToRefreshLayout.OnRefreshListener{
    @ViewInject(R.id.content)
    ListView goodsListView;

    @ViewInject(R.id.refresh)
    PullToRefreshLayout refreshLayout;

    private List<Goods> list = new ArrayList<>();

    private GoodsAdapter goodsAdapter;


    int state = 0;

    int type = 0;

    public GoodsFragment() {
        // Required empty public constructor
    }


    public static GoodsFragment getInstance(int type){
        GoodsFragment goodsFragment = new GoodsFragment();
        Bundle b = new Bundle();
        b.putInt("type",type);
        goodsFragment.setArguments(b);
        return goodsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getInt("type");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initData() {
        super.initData();
        goodsAdapter = new GoodsAdapter(list);

        goodsListView.setAdapter(goodsAdapter);

        refreshLayout.setOnRefreshListener(this);


        goodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), CreateOrderActivity.class);
                intent.putExtra("Goods",list.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void requestData(BatterLoadResponseListner responseListner) {
        state = 0;
        doRequest(responseListner);
    }

    private void doRequest(BatterLoadResponseListner responseListner){
        BatterTaskItem batterTaskItem = new BatterTaskItem(responseListner, new DoWork() {
            @Override
            public boolean doWorkInThread() {
                if(state!=2){
                    list.clear();
                }
                list.addAll(TestData.createGoods(type));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return true;
            }
        });

        BatterTask batterTask = new BatterTask(batterTaskItem);
        batterTask.execute();
    }


    @Override
    protected void requestEnd(boolean success) {
        super.requestEnd(success);
        if(state==1){
            refreshLayout.refreshFinish(success? State.SUCCEED:State.FAIL);
        }else if(state==2){
            refreshLayout.loadmoreFinish(success ? State.SUCCEED : State.FAIL);
        }
    }

    @Override
    protected boolean requestSuccess(String result) {
        boolean success = Boolean.parseBoolean(result);
        if(success){
            goodsAdapter.notifyDataSetChanged();
            if(state==2){
               // goodsListView.setSelection(list.size()-10);
            }
        }
        return success;
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        state = 1;
        doRequest(getResponseListner());
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        state = 2;
        doRequest(getResponseListner());
    }


    class GoodsAdapter extends BatterAdapter<Goods> {

        public GoodsAdapter(List<Goods> list) {
            super(list, getActivity());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Goods goods = getItem(position);
            Holder holder = null;
            if(convertView==null){
                convertView = inflater.inflate(R.layout.item_goods,null);
                holder = new Holder(convertView);
            }else{
                holder = (Holder) convertView.getTag();
            }
            holder.tvNo.setText(goods.getGoodsNo());
            holder.tvName.setText(goods.getName());
            holder.tvPrice.setText("￥"+goods.getConsignmentPrice()+"元");
            return convertView;
        }

        class Holder{
            ImageView iv;
            TextView tvNo,tvName,tvPrice;

            public Holder(View view) {
                iv = (ImageView) view.findViewById(R.id.icon);
                tvNo = (TextView) view.findViewById(R.id.goods_no);
                tvName = (TextView) view.findViewById(R.id.goods_name);
                tvPrice = (TextView) view.findViewById(R.id.goods_price);
                view.setTag(this);
            }
        }
    }
}
