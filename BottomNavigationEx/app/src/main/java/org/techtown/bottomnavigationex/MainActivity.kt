package org.techtown.bottomnavigationex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.techtown.bottomnavigationex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 페이저에 어댑터 연결
        binding.pager.adapter = ViewPagerAdapter(this)

        // 슬라이드하여 페이지가 변경되면 바텀네비게이션의 탭도 그 페이지로 활성화
        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
                }
            }
        )

        // 리스너 연결
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_one -> {
                // ViewPager의 현재 item에 첫 번째 화면을 대입
                binding.pager.currentItem = 0
                return true
            }
            R.id.item_two -> {
                // ViewPager의 현재 item에 두 번째 화면을 대입
                binding.pager.currentItem = 1
                return true
            }
            R.id.item_three -> {
                // ViewPager의 현재 item에 세 번째 화면을 대입
                binding.pager.currentItem = 2
                return true
            }
            else -> {
                return false
            }
        }
    }
}