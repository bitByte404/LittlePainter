package com.wuliner.littlepainter.utils

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation

object AnimUtils {
    fun addScaleAndAlphaAnim(
        target: View,
        fromScale: Float, toScale: Float,
        fromAlpha: Float, toAlpha: Float,
        interpolator: Interpolator = LinearInterpolator(),
        duration: Long = 500,
        onStart: () -> Unit = {},
        onEnd: () -> Unit = {},
        onRepeat: () -> Unit = {}
    ) {
        val alphaAnim = AlphaAnimation(fromAlpha, toAlpha)
        val scaleAnim = ScaleAnimation(
            fromScale,
            toScale,
            fromScale,
            toScale,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        AnimationSet(true).apply {
            addAnimation(alphaAnim)
            addAnimation(scaleAnim)
            this.duration = duration
            this.interpolator = interpolator
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    onStart()
                }

                override fun onAnimationEnd(animation: Animation?) {
                    onEnd()
                }

                override fun onAnimationRepeat(animation: Animation?) {
                    onRepeat()
                }
            })
            target.startAnimation(this)
        }
    }
}
