package com.capsule.sample.func.divider;

import com.capsule.trunk.Adapter;
import com.capsule.trunk.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:03
 */
public class DividerVerticalAdapter extends Adapter<Data,ViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setImageResource(R.id.icon, item.getIconRes());

  }
}
