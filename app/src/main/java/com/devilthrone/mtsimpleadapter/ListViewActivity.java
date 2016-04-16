package com.devilthrone.mtsimpleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


import com.devilthrone.MTSimpleAdapter.adapter.ListViewAdapter;
import com.devilthrone.MTSimpleAdapter.bean.IItemBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private List<IItemBean> mList;
    private ListView mListView;
    private ListViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initData();
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new ListViewAdapter(this,mList);
        mAdapter.addProvider(HeadLineProvider.class);
        mAdapter.addProvider(ConversationProvider.class);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        mList = new ArrayList<IItemBean>();
        for (int i = 0 ; i < 40;i++){
            if (i%5!=0){
                ConversationBean bean = new ConversationBean("长江"+i+"号","看你妹","13:02");
                mList.add(bean);
            }else{
                HeadLineBean bean = new HeadLineBean("qq新闻","14:44","明天下雪！");
                mList.add(bean);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add){
            addNewsData();
        }else if(item.getItemId() == R.id.delete){
            removeNewsData();
        }
        return super.onOptionsItemSelected(item);
    }
    private void removeNewsData() {
        mAdapter.removeProviderAndData(ConversationProvider.class);
    }

    private void addNewsData() {
        mAdapter.addProvider(ConversationProvider.class);
        for(int i = 0 ; i< 5 ;i++){
            ConversationBean bean = new ConversationBean("长江"+i+"号","看你妹","13:32");
            mList.add(bean);
        }
        Collections.shuffle(mList);
        mAdapter.notifyDataSetChanged();
    }
}
