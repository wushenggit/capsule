package com.capsule.sample.func.click;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.capsule.recy.click.ItemClickListener;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.repo.Data;
import com.capsule.sample.repo.Repo;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * Created by wusheng on 2017/9/2.
 */

public class ClickActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private ClickAdapter adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("点击事件");
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addOnItemTouchListener(new ItemClickListener() {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position, View childView) {
        if (childView != null) {
          Toast.makeText(ClickActivity.this, "点击 child:" + childView.toString(), Toast.LENGTH_SHORT)
              .show();
        } else {
          Toast.makeText(ClickActivity.this, "点击：" + position, Toast.LENGTH_SHORT).show();
        }
      }
    });

    adapter = new ClickAdapter();
    recyclerView.setAdapter(adapter);
    Repo.getInstance(this).refresh()
        .subscribe(new Consumer<List<Data>>() {
          @Override public void accept(@NonNull List<Data> datas) throws Exception {
            adapter.notifyRefreshCompleted(datas);
          }
        });
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_click;
  }

  //Toast.makeText(ClickActivity.this, "点击："+position, Toast.LENGTH_SHORT).show();
}
