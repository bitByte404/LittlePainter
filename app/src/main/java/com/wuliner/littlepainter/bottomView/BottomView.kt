package com.wuliner.littlepainter.bottomView

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.internal.ViewUtils.dpToPx
import com.wuliner.littlepainter.R

/**
 * View：
 * onMeasure -> 宽高由父容器 或者 自己决定
 * onDraw    ->
 *
 * ViewGroup
 * onMeasure -> 由父容器 自己 子控件 决定
 * onLayout -> 布局每一个子控件
 *
 * childCount 子控件个数
 * getChildAt
 */
class BottomView(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private var mSize = 0
    init {
        setBackgroundColor(resources.getColor(R.color.dark, null))
    }

    @SuppressLint("RestrictedApi")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val mWidth = MeasureSpec.getSize(widthMeasureSpec)
        val mHeight = dpToPx(context, 64).toInt()
        setMeasuredDimension(mWidth, mHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mSize = width/4
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for (i in 0 until childCount) {
            //获取对应的子控件
            val mChild = getChildAt(i)
            //布局这个子控件
            val left = i * mSize
            mChild.layout(left, 0, left + mSize, height)
        }
    }



    //private fun dp2px(dp: Int) = context.resources.displayMetrics.density*dp

}