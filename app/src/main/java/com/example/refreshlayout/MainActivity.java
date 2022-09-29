package com.example.refreshlayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> URLS = new ArrayList<>();
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;

    private ImagesAdapter adapter;

    private int currentPage;
    //每一页加载10
    private int pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URLS.add("https://t7.baidu.com/it/u=4198287529,2774471735&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=727460147,2222092211&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=2511982910,2454873241&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=825057118,3516313570&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3435942975,1552946865&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3569419905,626536365&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3779234486,1094031034&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=2397542458,3133539061&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=2763645735,2016465681&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3911840071,2534614245&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3908717,2002330211&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=318887420,2894941323&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3248413060,1409880777&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=938052523,709452322&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=91844991,1983235714&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3796392429,3515260353&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=4186603715,52512814&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3915743384,2060495762&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=4198287529,2774471735&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=727460147,2222092211&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=2511982910,2454873241&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=825057118,3516313570&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3435942975,1552946865&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3569419905,626536365&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3779234486,1094031034&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=2397542458,3133539061&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=2763645735,2016465681&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3911840071,2534614245&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3908717,2002330211&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=318887420,2894941323&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3248413060,1409880777&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=938052523,709452322&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=91844991,1983235714&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3796392429,3515260353&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=4186603715,52512814&fm=193&f=GIF");
        URLS.add("https://t7.baidu.com/it/u=3915743384,2060495762&fm=193&f=GIF");


        refreshLayout = findViewById(R.id.refreshLayout);
        //经典的加载头
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

        //监听刷新，监听加载更多
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getImageList(false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getImageList(true);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ImagesAdapter();
        recyclerView.setAdapter(adapter);

        //自动刷新
        refreshLayout.autoRefresh();
    }

    /**
     * 加载
     * @param refresh 是不是刷新操作
     */
    private void getImageList(boolean refresh) {
        if (refresh) {
            currentPage = 0;
            List<String> data = URLS.subList(0, pageSize);
            adapter.setDataList(data);

            refreshLayout.finishRefresh();
        } else {
            int page = currentPage + 1;
            //page = 1 已经加载了10个，加载更多从10开始
            //
            int fromIndex = page * pageSize;
            if (fromIndex < URLS.size()) {
                int toIndex = fromIndex + pageSize;
                if (toIndex >= URLS.size()) {
                    //超过了最大长度就不能行了
                    toIndex = URLS.size() - 1;
                }
                List<String> data = URLS.subList(fromIndex, toIndex);
                if (data.size() == 0) {
                    //没有了，已经是最后一页
                    Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.addDataList(data);
                    currentPage = page;
                }
            } else {
                Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
            }

            refreshLayout.finishLoadMore();
        }
    }
}