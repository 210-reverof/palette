package com.smilegate.Easel

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smilegate.Easel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var lastSelectedIconId: Int? = null

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        /* Status Bar & Navigation Bar */
        val barColor = ContextCompat.getColor(this, R.color.white)
        val statusColor = ContextCompat.getColor(this, android.R.color.transparent)

        with(window) {
            statusBarColor = statusColor
            navigationBarColor = barColor
        }
        with(WindowInsetsControllerCompat(window, window.decorView)) {
            isAppearanceLightStatusBars = true
            isAppearanceLightNavigationBars = true
        }

        window.decorView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_container)
        setSupportActionBar(toolbar)

        // 타이틀을 비워서 보이지 않도록 설정
        supportActionBar?.title = ""
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        // 앱 초기 실행 시 홈화면으로 설정
        if (savedInstanceState == null) {
            binding.navView.selectedItemId = R.id.timelineFragment
            lastSelectedIconId = R.id.navigation_home
            updateIcon(R.id.navigation_home, R.drawable.ic_solid_home)
        }

        binding.navView.setOnNavigationItemSelectedListener { item ->
            onNavigationItemSelected(item)
        }

        var down = false
        var height:Float? = null
        val bottomNavView: BottomNavigationView = findViewById(R.id.nav_view)

        binding.navView.setOnScrollChangeListener { _, _, scY, _, odscY ->
            if (height == null) {
                height = bottomNavView.height.toFloat()
            }
            if (scY > odscY) {
                if (down) {
                    down = false
                    bottomNavView.clearAnimation()
                    bottomNavView.animate().translationY(height!!).duration = 200
                }
            } else {
                if (!down) {
                    down = true
                    bottomNavView.clearAnimation()
                    bottomNavView.animate().translationY(0f).duration = 200
                }
            }
        }

    }

    private fun updateIcon(itemId: Int, iconResId: Int) {
        val item = binding.navView.menu.findItem(itemId)
        // 현재 아이콘과 변경하려는 아이콘이 같지 않을 때만 변경
        if (item?.icon?.constantState != ContextCompat.getDrawable(this, iconResId)?.constantState) {
            item?.setIcon(iconResId)
        }
    }

    fun onNavigationItemSelected(item: MenuItem): Boolean {
        val currentIconId = item.itemId

        val selectedIcon = getSelectedIcon(currentIconId)
        item.setIcon(selectedIcon)

        // 이전에 선택된 아이콘 원래 아이콘으로 변경
        if (lastSelectedIconId != null && lastSelectedIconId != currentIconId) {
            val lastSelectedItem = binding.navView.menu.findItem(lastSelectedIconId!!)
            lastSelectedItem?.setIcon(getOriginalIcon(lastSelectedIconId!!))
        }

        // 이전에 선택된 아이콘 ID 업데이트
        lastSelectedIconId = currentIconId

        when (currentIconId) {
            R.id.navigation_home -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.timelineFragment)
                return true
            }
            R.id.navigation_search -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.searchFragment)
                return true
            }
            R.id.navigation_notice -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.noticeFragment)
                return true
            }
            R.id.navigation_message -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.messageFragment)
                return true
            }
        }
        return false
    }

    private fun getSelectedIcon(itemId: Int): Int {
        return when (itemId) {
            R.id.navigation_home -> R.drawable.ic_solid_home
            R.id.navigation_search -> R.drawable.ic_selected_search
            R.id.navigation_notice -> R.drawable.ic_solid_bell
            R.id.navigation_message -> R.drawable.ic_solid_mail
            else -> throw IllegalArgumentException("Invalid item ID")
        }
    }

    private fun getOriginalIcon(itemId: Int): Int {
        return when (itemId) {
            R.id.navigation_home -> R.drawable.ic_home
            R.id.navigation_search -> R.drawable.ic_search
            R.id.navigation_notice -> R.drawable.ic_bell
            R.id.navigation_message -> R.drawable.ic_mail
            else -> throw IllegalArgumentException("Invalid item ID")
        }
    }
}

