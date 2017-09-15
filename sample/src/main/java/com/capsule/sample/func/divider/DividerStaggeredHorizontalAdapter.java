package com.capsule.sample.func.divider;

import com.capsule.recy.Adapter;
import com.capsule.recy.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 20:13
 */
public class DividerStaggeredHorizontalAdapter extends Adapter<Data,ViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_stagger_horizontal);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setText(R.id.title, item.getTitle());

  }
}