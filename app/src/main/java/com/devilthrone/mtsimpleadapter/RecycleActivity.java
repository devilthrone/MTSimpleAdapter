package com.devilthrone.mtsimpleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.devilthrone.MTSimpleAdapter.adapter.RecyclerAdapter;
import com.devilthrone.MTSimpleAdapter.bean.IItemBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private List<IItemBean> mList;
    private RecyclerAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        initData();
        mRecycleView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new RecyclerAdapter(this,mList);
        mAdapter.addProvider(HeadLineProvider.class);
        mAdapter.addProvider(ConversationProvider.class);
        mAdapter.setLoadingProvider(LoadingProvider.class);
        mAdapter.setEmptyProvider(EmptyProvider.class);
        mRecycleView.setLayoutManager(new LinearLayoutManager(RecycleActivity.this));
        // 设置RecyclerView的点击事件
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(RecycleActivity.this, "Click item : "
                        + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener(){

            @Override
            public void onItemLongClick(int position) {
                Toast.makeText(RecycleActivity.this, "Long Click item : "
                        + mAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
        mRecycleView.setAdapter(mAdapter);

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
        } else if (item.getItemId() == R.id.deleteAll) {
            removeAll();
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeAll() {
        mAdapter.clear();
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
