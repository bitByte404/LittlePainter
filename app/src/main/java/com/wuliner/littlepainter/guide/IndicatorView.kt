package com.wuliner.littlepainter.guide

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.wuliner.littlepainter.R
import com.wuliner.littlepainter.utils.dp2px

class IndicatorView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private var mSelectedWidth = dp2px(40) //选中点的宽度
    private var mNormalWidth = dp2px(20) //默认宽度
    private var mSpace = dp2px(6) //间距
    private var mHeight = dp2px(12) //点的高度
    private var mCornerRadius = dp2px(6).toFloat() //圆角半径

    private var mCurrentIndex = 3 //默认选中第一个点
    private var mSelectedColor = resources.getColor(R.color.main_red, null)
    private var mNormalColor = resources.getColor(R.color.light_dark, null)

    private val mPaint = Paint().apply {
        color = mNormalColor
        style = Paint.Style.FILL
        isAntiAlias = true
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var m_width = MeasureSpec.getSize(widthMeasureSpec)
        val width_mode = MeasureSpec.getMode(widthMeasureSpec)
        if (width_mode != MeasureSpec.EXACTLY) { //需要自己确定
            m_width = 3 * mNormalWidth + mSelectedWidth + 3 * mSpace
        }

        var m_height = MeasureSpec.getSize(heightMeasureSpec)
        val height_mode = MeasureSpec.getMode(heightMeasureSpec)
        if (height_mode != MeasureSpec.EXACTLY) { //需要自己确定
            m_height = mHeight
        }
        //告诉父容器自己的尺寸
        setMeasuredDimension(m_width, m_height)
    }

    private var mStartX = 0f
    private var mTop = 0f
    private var mMoveSpace = 0f //动画时 宽度的变化值0 - 20
    private var mDirection: Direction = Direction.None //移动方向


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mStartX = (width - (3 * mNormalWidth + mSelectedWidth + 3 * mSpace)).toFloat() / 2
        mTop = (height - mHeight).toFloat() / 2
    }

    /**提供给外部设置当前选中哪一个*/
    fun select(index: Int) {
        if (mCurrentIndex == index) {
            mDirection = Direction.None
        } else {
            //确定移动的方向
            mDirection =  if (mCurrentIndex > index) { Direction.Left } else { Direction.Right }
            mCurrentIndex = index
            startMoveAnimation() //开启移动的动画
            invalidate()
        }
    }

    private fun startMoveAnimation() {
        ValueAnimator.ofFloat(0f, dp2px(20).toFloat()).apply {
            duration = 500
            addUpdateListener {
                mMoveSpace = it.animatedValue as Float
                invalidate()
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (i in 0 until 4){
            var left = mStartX + if (i > mCurrentIndex) {
                mSelectedWidth + mSpace + (i - 1) * (mNormalWidth + mSpace)
            } else {
                i * (mNormalWidth + mSpace)
            }
            //确定right
            var right = left + if (i == mCurrentIndex) mSelectedWidth else mNormalWidth


            if (mDirection != Direction.None) { //只有左右移动时才需要计算移动的变化
                when (i) {
                    mCurrentIndex -> {//判断是不是当前这个点
                        if (mDirection == Direction.Left) {
                            right -= dp2px(20)
                            right += mMoveSpace //left往右边移动
                        } else {
                            left += dp2px(20)
                            left -= mMoveSpace //往右边移动
                        }
                    }
                    mCurrentIndex-1 ->{  //上一个点
                        if (mDirection == Direction.Right){
                            right  += dp2px(20)
                            right -= mMoveSpace
                        }
                    }
                    mCurrentIndex+1->{ //下一个点
                        if (mDirection == Direction.Left){
                            left -= dp2px(20)
                            left += mMoveSpace
                        }
                    }
                }
            }

            //确定画笔颜色
            mPaint.color = if (i == mCurrentIndex) mSelectedColor else mNormalColor
            canvas?.drawRoundRect(
                left,mTop,right,mTop+mHeight,mCornerRadius,mCornerRadius,mPaint
            )
        }
    }

    /**接收用户交互*/
    var addPageChangeCallBack: ((page: Int) -> Unit)? = null //回调

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN){
            //判断点击的左边还是右边
            var index = 0
            if (event.x > width/2){
                //右边
                index = mCurrentIndex+1
                if (index > 3){
                    return true
                }
            }else{
                //左边
                index = mCurrentIndex-1
                if (index < 0){
                    return true
                }
            }
            addPageChangeCallBack?.let { it(index) } //回调出去
        }
        return true
    }

    enum class Direction {
        Left, Right, None
    }
}