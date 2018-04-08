package com.capsule.chick.func.load;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import capsule.chick.ChickAdapter;
import capsule.chick.anim.impl.SlideInLeftAnimator;
import com.capsule.chick.R;
import com.capsule.chick.base.BaseActivity;
import com.capsule.chick.repo.Data;
import com.capsule.chick.repo.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoadActivity extends BaseActivity {

  private LoadAdapter        adapter;
  private RefreshLayout refreshLayout;
  private RecyclerView       recyclerView;

  private Repo repo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("加载更多");
    initRefresh();
    initRecyclerView();
    initAdapter();
    repo = Repo.getInstance(this);
    initData();
    adapter.notifyDataSetChanged();

    //addHeader();
    //addFooter();
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_load;
  }

  private void initRefresh() {
    refreshLayout = (RefreshLayout) findViewById(R.id.refresh);
    refreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        repo.refresh()
            .delay(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                refreshLayout.setRefreshing(false);
                adapter.notifyRefreshCompleted(datas);
              }
            });
      }
    });
  }

  private void initRecyclerView() {
    recyclerView = (RecyclerView) findViewById(R.id.rv);
    RecyclerView.LayoutManager manager =
        new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
    recyclerView.setLayoutManager(manager);
    recyclerView.setItemAnimator(new SlideInLeftAnimator());
    //recyclerView.addItemDecoration(new FootDecor(R.layout.layout_foot));

    //recyclerView.addItemDecoration(new EmptyDecor(R.layout.layout_empty));
  }

  private void initAdapter() {
    adapter = new LoadAdapter();

    //adapter.setEmptyView(R.layout.layout_empty);
    //adapter.setLoadMoreView(new SimpleLoadMoreView());
    recyclerView.setAdapter(adapter);

    adapter.setOnLoadMoreListener(new ChickAdapter.OnLoadMoreListener() {
      @Override public void onLoadMore() {
        repo.load(adapter.getLastData().getId())
            .delay(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                adapter.notifyLoadMoreCompleted(datas);
              }
            });
      }
    });

    //adapter.setOnRetryListener(new ChickAdapter.OnRetryListener() {
    //  @Override public void onRetry() {
    //    repo.load(adapter.getLastData().getId())
    //        .delay(300, TimeUnit.MILLISECONDS)
    //        .observeOn(AndroidSchedulers.mainThread())
    //        .subscribe(new Consumer<List<Data>>() {
    //          @Override public void accept(@NonNull List<Data> datas) throws Exception {
    //            adapter.notifyLoadMoreCompleted(datas);
    //          }
    //        });
    //  }
    //});
  }

  private void initData() {
    repo.refresh(5).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Data>>() {
      @Override public void accept(@NonNull List<Data> datas) throws Exception {
        adapter.notifyRefreshCompleted(datas);
      }
    });

    //adapter.setData(repo.refreshList());
    //adapter.setData(new ArrayList<SkillBean>());
  }

  //private void addHeader() {
  //  int height = (int) (getResources().getDisplayMetrics().density * 48);
  //  TextView tv = new TextView(this);
  //  RecyclerView.LayoutParams lp =
  //      new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
  //  tv.setBackgroundResource(R.color.colorAccent);
  //  tv.setLayoutParams(lp);
  //  tv.setText("我是头");
  //  adapter.setHeader(tv);
  //}
  //
  //private void addFooter() {
  //  int height = (int) (getResources().getDisplayMetrics().density * 48);
  //  TextView tv = new TextView(this);
  //  RecyclerView.LayoutParams lp =
  //      new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
  //  tv.setBackgroundResource(R.color.colorAccent);
  //  tv.setLayoutParams(lp);
  //  tv.setText("我是尾");
  //  adapter.setFooter(tv);
  //}
}
