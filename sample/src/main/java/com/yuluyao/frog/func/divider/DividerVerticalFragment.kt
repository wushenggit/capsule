package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Character
import com.yuluyao.frog.repo.DataStore
import kotlinx.android.synthetic.main.fragment_divider_linear_vertical.*
import kotlinx.android.synthetic.main.item_divider_data_linear_vertical.view.*
import yuluyao.frog.CleanAdapter
import yuluyao.frog.Divider
import yuluyao.frog.drag.DragCallback

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:00
 */
class DividerVerticalFragment : Fragment() {
  val adapter = object : CleanAdapter<Character>(R.layout.item_divider_data_linear_vertical){
    override fun onBindViewHolder(holder: Holder, position: Int) {
      holder.itemView.icon.setImageResource(data[position].iconRes)
      holder.itemView.title.text = data[position].title
      holder.itemView.content.text = data[position].content

    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_linear_vertical, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.layoutManager = LinearLayoutManager(context)
    recycler.addItemDecoration(Divider(8f, context!!.resources.getColor(R.color.item_decoration),false))
    setDrag(recycler)

    recycler.adapter = adapter

    DataStore.refresh().subscribe {
      adapter.data.clear()
      adapter.data.addAll(it)
      adapter.notifyDataSetChanged()
    }
  }

  private fun setDrag(recyclerView: RecyclerView) {
    val helper = ItemTouchHelper(DragCallback())
    helper.attachToRecyclerView(recyclerView)
  }

}
