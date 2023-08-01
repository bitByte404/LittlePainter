package com.wuliner.littlepainter

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MyBottomNavigationView(context: Context, attrs: AttributeSet?) : BottomNavigationView(context, attrs) {
    private var navController: NavController
    init {

        val navHostFragment =( context as AppCompatActivity).supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        /**让BottomNavigationView 和 NavController关联*/
        setupWithNavController(navController)

        setOnItemSelectedListener { menuItem ->
            //清空栈信息
            navController.popBackStack()
            navController.navigate(menuItem.itemId)
            true
        }
    }
}