package com.capsule.sample.func.load;

import com.capsule.trunk.Adapter;
import com.capsule.trunk.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 21:44
 */
public class LoadAdapter extends Adapter<Data, ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setClickableId(R.id.icon);
  }
}
