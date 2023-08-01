package com.wuliner.littlepainter.bottomView

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import com.wuliner.littlepainter.R

class TabItem(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private lateinit var mTitle: String
    private var mIcon = 0
    private var mSelectedIcon: Int = 0
    private var mColor: Int = 0
    private var mSelectColor: Int = 0

    init {
        parseAttrs(attrs)
    }

    private fun parseAttrs(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.TabItem).apply {
            mTitle = getString(R.styleable.TabItem_title) ?: "Tab"
            mIcon = getInteger(R.styleable.TabItem_icon, R.drawable.ic_launcher_foreground)
            mSelectedIcon = getInteger(R.styleable.TabItem_select_icon, mIcon)
            mColor = getColor(R.styleable.TabItem_normal_color, Color.BLACK)
            mSelectColor = getColor(R.styleable.TabItem_normal_color, mColor)
            recycle()
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        TODO("Not yet implemented")
    }
}