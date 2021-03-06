package com.yuluyao.frog.func.adapter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Character
import com.yuluyao.frog.repo.DataStore
import kotlinx.android.synthetic.main.activity_fun_adapter.*
import kotlinx.android.synthetic.main.item_data_adapter.view.*
import yuluyao.frog.CleanAdapter

class AdapterActivity : BaseActivity() {

  override fun onGetLayoutId(): Int = R.layout.activity_fun_adapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Adapter"

    // init adapter
    val adapter = CleanAdapter<Character>(R.layout.item_data_adapter)
    adapter.onBind { holder, position ->
      val item = data[position]
      holder.itemView.icon.setImageResource(item.iconRes)
      holder.itemView.title.text = item.title
      holder.itemView.content.text = item.content
    }

    // setup recyclerview
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter

    // refresh data
    DataStore.refresh().subscribe { it ->
      adapter.data.clear()
      adapter.data.addAll(it)
      adapter.notifyDataSetChanged()
    }

  }

}