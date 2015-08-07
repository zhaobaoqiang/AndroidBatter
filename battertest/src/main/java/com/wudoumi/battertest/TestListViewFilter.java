package com.wudoumi.battertest;// @author Bhavya Mehta

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wudoumi.batter.view.listview.listviewfilter.ConvertData;
import com.wudoumi.batter.view.listview.listviewfilter.IndexBarView;
import com.wudoumi.batter.view.listview.listviewfilter.ListHashMap;
import com.wudoumi.batter.view.listview.listviewfilter.PinnedHeaderListView;
import com.wudoumi.battertest.bean.TestData;
import com.wudoumi.battertest.testemptyview.SortAdapter;


// Activity that display customized list view and search box
public class TestListViewFilter extends Activity {


    // custom list view with pinned header
    PinnedHeaderListView mListView;



    // search box
    EditText mSearchView;

    // loading view
    ProgressBar mLoadingView;

    // empty view
    TextView mEmptyView;




    List<TestData> items;

    List<TestData> adapterItems = new ArrayList<>();

    ListHashMap<String,Integer> map = new ListHashMap<>();


    SortAdapter sortAdapter = null;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // UI elements
        setupViews();

        // Array to ArrayList
        items = TestData.getTestData();

        sortAdapter = new SortAdapter(adapterItems,this,map);


        mListView.setAdapter(sortAdapter);

        new Poplulate().execute(items);

    }


    private void showContent(View contentView, View loadingView, View emptyView) {
        contentView.setVisibility(View.VISIBLE);
        loadingView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }

    private void setupViews() {
        setContentView(R.layout.main_act);
        mSearchView = (EditText) findViewById(R.id.search_view);
        mLoadingView = (ProgressBar) findViewById(R.id.loading_view);
        mListView = (PinnedHeaderListView) findViewById(R.id.list_view);
        mEmptyView = (TextView) findViewById(R.id.empty_view);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        mSearchView.addTextChangedListener(filterTextWatcher);
        super.onPostCreate(savedInstanceState);
    }



    private TextWatcher filterTextWatcher = new TextWatcher() {
        public void afterTextChanged(Editable s) {
            String str = s.toString();
            if (str != null)
                new ListFilter().filter(str);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    };

    public class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // NOTE: this function is *always* called from a background thread,
            // and
            // not the UI thread.
            String constraintStr = constraint.toString().toLowerCase(Locale.getDefault());
            FilterResults result = new FilterResults();

            if (constraint != null && constraint.toString().length() > 0) {
                List<TestData> filterItems = new ArrayList<TestData>();

                synchronized (this) {
                    for (TestData item : items) {
//                        if (item.getName().toLowerCase(Locale.getDefault()).startsWith(constraintStr)) {
//                            filterItems.add(item);
//                        }

                        if(item.getName().toLowerCase().contains(constraintStr.toLowerCase())){
                            filterItems.add(item);
                        }
                    }
                    result.count = filterItems.size();
                    result.values = filterItems;
                }
            } else {
                synchronized (this) {
                    result.count = items.size();
                    result.values = items;
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<TestData> filtered = (List<TestData>) results.values;
            setIndexBarViewVisibility(constraint.toString());
            // sort array and extract sections in background Thread
            new Poplulate().execute(filtered);
        }

    }

    private void setIndexBarViewVisibility(String constraint) {
        // hide index bar for search results
        if (constraint != null && constraint.length() > 0) {
            mListView.setIndexBarVisibility(false);
        } else {
            mListView.setIndexBarVisibility(true);
        }
    }

    // sort array and extract sections in background Thread here we use
    // AsyncTask
    private class Poplulate extends AsyncTask<List<TestData>, Void, Void> {

        private void showLoading(View contentView, View loadingView, View emptyView) {
            contentView.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }

        private void showContent(View contentView, View loadingView, View emptyView) {
            contentView.setVisibility(View.VISIBLE);
            loadingView.setVisibility(View.GONE);
            emptyView.setVisibility(View.GONE);
        }

        private void showEmptyText(View contentView, View loadingView, View emptyView) {
            contentView.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            // show loading indicator
            showLoading(mListView, mLoadingView, mEmptyView);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(List<TestData>... params) {

            List<TestData> temp = params[0];
            adapterItems.clear();

            map.clear();
            adapterItems.addAll(ConvertData.convert(temp,map,TestData.class));
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (!isCancelled()) {
                if (adapterItems.size() <= 0) {
                    showEmptyText(mListView, mLoadingView, mEmptyView);
                } else {
                    sortAdapter.notifyDataSetChanged();
                    showContent(mListView, mLoadingView, mEmptyView);
                }
            }
            super.onPostExecute(result);
        }
    }







//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        if (mListItems != null && mListItems.size() > 0) {
//            outState.putStringArrayList("mListItems", mListItems);
//        }
//        if (mListSectionPos != null && mListSectionPos.size() > 0) {
//            outState.putIntegerArrayList("mListSectionPos", mListSectionPos);
//        }
//        String searchText = mSearchView.getText().toString();
//        if (searchText != null && searchText.length() > 0) {
//            outState.putString("constraint", searchText);
//        }
//        super.onSaveInstanceState(outState);
//    }



    // for handling configuration change
//    if (savedInstanceState != null) {
//        mListItems = savedInstanceState.getStringArrayList("mListItems");
//        mListSectionPos = savedInstanceState.getIntegerArrayList("mListSectionPos");
//
//        if (mListItems != null && mListItems.size() > 0 && mListSectionPos != null && mListSectionPos.size() > 0) {
//            setListAdaptor();
//        }
//
//        String constraint = savedInstanceState.getString("constraint");
//        if (constraint != null && constraint.length() > 0) {
//            mSearchView.setText(constraint);
//            setIndexBarViewVisibility(constraint);
//        }
//    } else {
//
//
//    }
}
