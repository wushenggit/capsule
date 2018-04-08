package com.capsule.chick.func.divider;

import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;

import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:03
 */
public class DividerVerticalAdapter extends ChickAdapter<Data,ChickViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ChickViewHolder holder, Data item) {
    holder.setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setImageResource(R.id.icon, item.getIconRes());

  }
}
