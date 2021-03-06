package yuluyao.frog.touch

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * 单击监听，在连续点击中，只有第一次生效,
 * timeGap: 点击最小间隔，小于此间隔的点击不被响应
 */
abstract class OnItemSingleClickListener(val timeGap: Long = 500L) : BaseTouchListener() {
  override val listenedChildrenIds: IntArray = intArrayOf() // 这里不用处理子View手势
  abstract fun onItemClicked(position: Int)

  // 上次点击的时刻
  private var lastClickTimeMills = 0L

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {
    private var itemView: View? = null

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      itemView = recyclerView?.findChildViewUnder(e.x, e.y)
      // 设置 item view 为可点击
      itemView?.isClickable = true
      return super.onDown(e)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      itemView ?: return false
      recyclerView ?: return false

      val position = recyclerView!!.getChildAdapterPosition(itemView!!)
      if (position == -1) {
        return false
      }
      // 丢弃多余的点击
      val clickTimeMills = System.currentTimeMillis()
      if (clickTimeMills - lastClickTimeMills < timeGap) {
        lastClickTimeMills = clickTimeMills
        return true
      }
      lastClickTimeMills = clickTimeMills

      itemView!!.dispatchTouchEvent(getTransformedMotionEvent(e, itemView!!))
      onItemClicked(position)
      return true
    }


  }


}