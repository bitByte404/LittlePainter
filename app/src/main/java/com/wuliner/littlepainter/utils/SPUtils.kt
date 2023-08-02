package com.wuliner.littlepainter.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.BoringLayout
import androidx.core.content.edit
import java.lang.ref.WeakReference

class SPUtils private constructor() {
    private lateinit var weakContext: WeakReference<Context>
    private val sp: SharedPreferences by lazy {
        weakContext.get()!!.getSharedPreferences(Constants.SHARED_FILE_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        var instance: SPUtils? = null

        fun defaultUtils(context: Context): SPUtils {
            return instance?: SPUtils().also {
                instance = it
                it.weakContext = WeakReference(context)
            }
        }
    }

    var isFirst: Boolean = true
        set(value) {
            field = value
            sp.edit {
                putBoolean(Constants.IS_FIRST_KEY, value)
                apply()
            }
        }
        get() {
            return sp.getBoolean(Constants.IS_FIRST_KEY, true)
        }

}