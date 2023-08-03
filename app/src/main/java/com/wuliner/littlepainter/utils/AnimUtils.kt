package com.wuliner.littlepainter.utils

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationSet
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import androidx.core.animation.addListener

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

    fun addRotateAndAlphaAnimation(
        target: View,
        fromAlpha: Float = 0f, toAlpha: Float = 1f,
        fromDegree: Float = 180f, toDegree: Float = 360f,
        onStart: () -> Unit = {},
        onEnd: () -> Unit = {},
        onCancle: () -> Unit = {},
        onRepeat: () -> Unit = {}
        ) {

        val rotate = ObjectAnimator.ofFloat(target, "rotationX", fromDegree, toDegree)
        val alpha = ObjectAnimator.ofFloat(target, "alpha", fromAlpha, toAlpha)

        AnimatorSet().apply {
            duration = 1000
            //interpolator = BounceInterpolator()
            playTogether(rotate, alpha)
            addListener { object : AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    onStart()
                }

                override fun onAnimationEnd(animation: Animator) {
                    onEnd()
                }

                override fun onAnimationCancel(animation: Animator) {
                    onCancle()
                }

                override fun onAnimationRepeat(animation: Animator) {
                    onRepeat()
                }
            } }
            start()
        }
    }
}
