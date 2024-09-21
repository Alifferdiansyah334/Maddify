package com.example.maddify.ui.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.maddify.R
import com.google.android.material.navigation.NavigationView
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

class MenuActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var hamburgerIcon: ImageView
    private var isDrawerOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        drawerLayout = findViewById(R.id.drawer_layout)
        hamburgerIcon = findViewById(R.id.hamburger_icon)

        val carousel: ImageCarousel = findViewById(R.id.carousel)
        carousel.registerLifecycle(lifecycle)
        val list = mutableListOf<CarouselItem>()

        list.add(
            CarouselItem(
                imageUrl = "https://c4.wallpaperflare.com/wallpaper/861/893/223/god-lord-allah-wallpaper-preview.jpg",
                caption = "Find the article about the importance of tajiwd Madd"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVDq8zUHKMJ3WQ5BE4eBMLkAOFl4tIZTTs-HYlPsyBjGNDgzygBwnXWda-jEcr4oSs5ag&usqp=CAU",
                caption = "Find the beatiful things about tajwid Madd"
            )
        )

        list.add(
            CarouselItem(
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmSyRqlJ68aC__DW2yV1grnVuXMrEzBuTZMA&s",
                caption = "Find the true meaning about the importance of tajiwd Madd"
            )
        )

        carousel.setData(list)

        // Open drawer when the hamburger icon is clicked
        hamburgerIcon.setOnClickListener {
            if (isDrawerOpen) {
                closeDrawer()
            } else {
                openDrawer()
            }
        }

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_menu -> {
                    // Handle Home click
                    closeDrawerAndResetIcon()
                    true
                }
                R.id.nav_about -> {
                    // Handle Profile click
                    closeDrawerAndResetIcon()
                    true
                }
                R.id.nav_learn -> {
                    // Handle Settings click
                    closeDrawerAndResetIcon()
                    true
                }
                else -> false
            }
        }

        // Close the drawer and reset the icon when the background is clicked
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            }

            override fun onDrawerOpened(drawerView: View) {
            }

            override fun onDrawerClosed(drawerView: View) {
                closeDrawerAndResetIcon()
            }

            override fun onDrawerStateChanged(newState: Int) {
            }
        })
    }

    // Function to open the drawer animator
    private fun openDrawer() {
        drawerLayout.openDrawer(findViewById<NavigationView>(R.id.nav_view))
        rotateIcon(0f, 180f)
        hamburgerIcon.setImageResource(R.drawable.ic_close) // Change to close icon
        isDrawerOpen = true
    }

    // Function to close the drawer animator
    private fun closeDrawer() {
        drawerLayout.closeDrawer(findViewById<NavigationView>(R.id.nav_view))
        rotateIcon(180f, 0f)
        isDrawerOpen = false
    }

    // Function to close the drawer animator and reset ico
    private fun closeDrawerAndResetIcon() {
        closeDrawer() // Close the drawer
        hamburgerIcon.setImageResource(R.drawable.ic_menu) // Reset to hamburger icon
    }

    // Animation Function
    private fun rotateIcon(startAngle: Float, endAngle: Float) {
        ObjectAnimator.ofFloat(hamburgerIcon, "rotation", startAngle, endAngle).apply {
            duration = 300
            start()
        }
    }
}


