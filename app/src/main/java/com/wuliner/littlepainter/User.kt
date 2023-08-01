package com.wuliner.littlepainter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(val name: String?, val age: Int = 0) : Parcelable {

}