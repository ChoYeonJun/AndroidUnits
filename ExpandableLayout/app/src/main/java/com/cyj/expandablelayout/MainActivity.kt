package com.cyj.expandablelayout

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyj.expandablelayout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var targetView: View
    private lateinit var drawerButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        var drawerToggle = false
        targetView = binding.content
        drawerButton = binding.drawer


        drawerButton.setOnClickListener {
            toast(drawerToggle.toString())
            if (drawerToggle == false) {
                val objectAnimator = ObjectAnimator.ofFloat(targetView, "translationY", -1200f)
                objectAnimator.duration = 500 //0.5초에 걸쳐 진행.
                objectAnimator.start()
            } else {
                val objectAnimator = ObjectAnimator.ofFloat(targetView, "translationY", 0f)
                objectAnimator.duration = 500 //0.5초에 걸쳐 진행.
                objectAnimator.start()
            }
            drawerToggle = !drawerToggle
        }

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}