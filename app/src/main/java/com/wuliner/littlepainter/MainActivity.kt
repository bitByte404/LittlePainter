package com.wuliner.littlepainter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wuliner.littlepainter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //不行
        //findNavController(R.id.fragmentContainerView)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        /**让BottomNavigationView 和 NavController关联*/
//        binding.bottomNavigationView.setupWithNavController(navController)

//        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
//            //清空栈信息
//            navController.popBackStack()
//            navController.navigate(menuItem.itemId)
////            when (menuItem.itemId) {
////                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
////                R.id.welcomeFragment -> navController.navigate(R.id.welcomeFragment)
////                R.id.guideFragment -> navController.navigate(R.id.guideFragment)
////                R.id.drawFragment -> navController.navigate(R.id.drawFragment)
////            }
//            true
//        }
    }
}