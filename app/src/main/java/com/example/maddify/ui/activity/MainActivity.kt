package com.example.maddify.ui.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.maddify.R
import com.example.maddify.adapter.TutorialAdapter
import com.example.maddify.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TutorialAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up ViewPager2 adapter
        adapter = TutorialAdapter(this)
        binding.viewPager.adapter = adapter

        // Connect TabLayout & ViewPager2 with TabLayoutMediator
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setCustomView(R.layout.custom_tab)
        }.attach()

        // Handle page changes
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    // On Last Page: Show button with slide-up animation
                    binding.finishButton.visibility = View.VISIBLE
                    animateButtonSlideUp()
                } else {
                    // Not on last page: Hide button with slide-down animation
                    animateButtonSlideDown()
                }
            }
        })

        // Button click action
        binding.finishButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            MotionToast.createToast(this,
                "Welcome",
                "Welcome to the menu screen",
                MotionToastStyle.INFO,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.jakarta_sans_semibold))
            startActivity(intent)
        }

        binding.tvSkip.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            MotionToast.createToast(this,
                "Skipped",
                "Tutorial Screen Has Been Skipped",
                MotionToastStyle.SUCCESS,
                MotionToast.GRAVITY_BOTTOM,
                MotionToast.LONG_DURATION,
                ResourcesCompat.getFont(this,R.font.jakarta_sans_semibold))
            startActivity(intent)
        }
    }

    private fun animateButtonSlideUp() {
        // Stop any ongoing animation
        binding.finishButton.animate().cancel()

        // Only animate if the button is not already visible
        if (binding.finishButton.visibility != View.VISIBLE) {
            binding.finishButton.visibility = View.VISIBLE
            // Set the button initially off the screen (below the visible area)
            binding.finishButton.translationY = 500f  // Adjust this value based on your screen size
        }

        // Create and start the slide-up animation
        val slideUpAnimator = ObjectAnimator.ofFloat(binding.finishButton, "translationY", 500f, 0f)
        slideUpAnimator.duration = 500  // Duration of the animation
        slideUpAnimator.start()
    }

    private fun animateButtonSlideDown() {
        // Stop any ongoing animation
        binding.finishButton.animate().cancel()

        // Create and start the slide-down animation
        val slideDownAnimator = ObjectAnimator.ofFloat(binding.finishButton, "translationY", 0f, 500f)
        slideDownAnimator.duration = 500  // Duration of the animation

        slideDownAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                // No-op
            }

            override fun onAnimationEnd(animation: Animator) {
                // Ensure the button is hidden after the animation ends
                if (binding.viewPager.currentItem != adapter.itemCount - 1) {
                    binding.finishButton.visibility = View.GONE
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                // Ensure the button is hidden when the animation is canceled
                binding.finishButton.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animator) {
                // No-op
            }
        })
        slideDownAnimator.start()
    }
}
