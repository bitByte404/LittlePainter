package com.wuliner.littlepainter.utils

import android.view.View

fun View.dp2px(dp: Int): Int {
    return (context.resources.displayMetrics.density * dp).toInt()
}