package cn.fetech.sanyi.activity;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.wudoumi.batter.base.BatterAdapter;
import com.wudoumi.batter.ioc.annotation.ViewInject;

import cn.fetech.sanyi.BaseActivity;
import cn.fetech.sanyi.R;
import cn.fetech.sanyi.bean.Goods;
import cn.fetech.sanyi.fragment.GoodsFragment;

public class GoodsListActivity extends BaseActivity{

    @ViewInject(R.id.listview)
    ListView typeListView;



    private TypeAdapter typeAdapter;



    private int currentPosiont = 0;




    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected String getToolBarTitle() {
        return "货品列表";
    }

    @Override
    protected void initView() {
        typeAdapter = new TypeAdapter();
        typeListView.setAdapter(typeAdapter);


        typeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosiont = position;
                typeAdapter.notifyDataSetChanged();

                showFragment(typeAdapter.getItem(position).getTypeNo());

            }
        });


        showFragment(0);
    }

    private void showFragment(int type){
        getSupportFragmentManager().beginTransaction().replace(R.id.content,GoodsFragment.getInstance(type)).commit();
    }




//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_goods_list, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }


    class TypeAdapter extends BatterAdapter<Goods.GoodsType>{

        public TypeAdapter() {
            super(Goods.getTypes(), GoodsListActivity.this);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if(convertView==null){
                convertView = inflater.inflate(R.layout.item_goods_type,null);
                holder = new Holder();
                holder.tvName = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            }else{
                holder = (Holder) convertView.getTag();
            }
            holder.tvName.setSelected(currentPosiont==position);
            holder.tvName.setText(getItem(position).getTypeName());
            return convertView;
        }

        class Holder{
            TextView tvName;
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        moveTaskToBack(false);
    }
}
