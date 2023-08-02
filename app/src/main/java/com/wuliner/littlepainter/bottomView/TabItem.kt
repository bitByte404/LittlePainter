package com.wuliner.littlepainter.bottomView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.provider.ContactsContract.CommonDataKinds.Im
import android.text.Layout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.internal.ViewUtils.dpToPx
import com.wuliner.littlepainter.R
@SuppressLint("RestrictedApi")
class TabItem(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private lateinit var mTitle: String
    private lateinit var mIcon: Drawable
    private lateinit var mSelectedIcon: Drawable
    private var mColor: Int = 0
    private var mSelectColor: Int = 0

    private lateinit var mTabIcon: ImageView
    private lateinit var mTabTitle: TextView



    private var mIconSize = dpToPx(context,24).toInt() //图标的大小
    private var mTextSize = 12f
    private var mSpace = dpToPx(context, 3).toInt()
    var index = 0 //当前item的编号
    var addListenter: ((TabItem, Int) -> Unit)? = null //回调

    var fragmentId = 0 //导航的页面

    init {
        parseAttrs(attrs)
        addChild()
        initEvent()
    }

    private fun initEvent() {
        setOnClickListener {
            //加载动画资源
            val scalAnim = AnimationUtils.loadAnimation(context, R.anim.tab_item_select_anim).apply {
                interpolator = BounceInterpolator()
            }
            mTabIcon.startAnimation(scalAnim)
            mTabTitle.startAnimation(scalAnim)


            //事件回调
            addListenter?.let { it(this, index) }

        }
    }

    fun selected() {
        changeSlectState(true)
    }

    /**取消选中*/
    fun deSelected() {
        changeSlectState(false)
    }

    //更改对应状态显示的颜色
    private fun changeSlectState(state: Boolean) {
        val color = if (state) mSelectColor else mColor
        //设置选中图片
        mTabIcon.setImageDrawable(getColoredDrawable(mIcon, color))
        //更改文字的颜色
        mTabTitle.setTextColor(color)
    }

    /**给drawable渲染颜色*/
    private fun getColoredDrawable(drawable: Drawable, color: Int): Drawable {
        val compactDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(compactDrawable, color)
        return compactDrawable
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun parseAttrs(attrs: AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.TabItem).apply {
            mTitle = getString(R.styleable.TabItem_title) ?: "Tab"
            mIcon = getDrawable(R.styleable.TabItem_icon) ?: resources.getDrawable(R.drawable.ic_launcher_foreground, null)
            mSelectedIcon = getDrawable(R.styleable.TabItem_select_icon) ?: resources.getDrawable(R.drawable.ic_launcher_foreground, null)
            mColor = getColor(R.styleable.TabItem_normal_color, Color.BLACK)
            mSelectColor = getColor(R.styleable.TabItem_selected_color, mColor)
            recycle()
        }
    }

    private fun addChild() {
        //图标
        mTabIcon = ImageView(context).apply {
            setImageDrawable(mIcon)
            scaleType = ImageView.ScaleType.FIT_CENTER
        }
        //标题
        mTabTitle = TextView(context).apply {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSize)
            setTextColor(mColor)
            text = mTitle
        }
        addView(mTabIcon, mIconSize, mIconSize) //尺寸固定
        addView(mTabTitle, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)) //内容决定
        measureChild(mTabTitle, MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        measureChild(mTabIcon, MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
    }

    /**
     * 在ViewGroup中添加子控件 (自己的还是系统的)
     * 如果没有确认控件的尺寸时，但又需要获取控件的尺寸
     * 必须县测量这个控件本身，测量完毕后使用measureWidth 和 measureHeight 获取对饮的宽高
     * 只有在onLayout结束之后 子控件才能通过 height weight获得高度和宽度
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val iconLeft = (width - mTabIcon.measuredWidth) / 2
        val iconTop = (height - mIconSize - mTabTitle.measuredHeight - mSpace) / 2
        mTabIcon.layout(iconLeft, iconTop, iconLeft + mIconSize, iconTop + mIconSize)

        val titleLeft = (width - mTabTitle.measuredWidth) / 2
        val titleTop = height - iconTop - mTabTitle.measuredHeight
        mTabTitle.layout(titleLeft, titleTop, titleLeft + mTabTitle.measuredWidth, titleTop + mTabTitle.measuredHeight)
    }
}