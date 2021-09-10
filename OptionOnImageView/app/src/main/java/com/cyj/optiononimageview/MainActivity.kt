package com.cyj.optiononimageview

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyj.optiononimageview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() , PopupMenu.OnMenuItemClickListener{
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.options.setOnClickListener {
//            Toast.makeText(this,"menu click", Toast.LENGTH_SHORT).show()
//            val popupMenu = PopupMenu(this, binding.options)
//            popupMenu.inflate(R.menu.main_menu)
//            popupMenu.setOnMenuItemClickListener { item ->
//                when (item.itemId) {
//                    R.id.menu1 ->
//                        onClick(1)
//                       //handle menu1 click
////                        true
//                    R.id.menu2 ->                         //handle menu2 click
//                        onClick(2)
//
//                    else -> false
//                }
//            }
//            openOptionsMenu()
            showPopup(it)
        }
    }

    fun onClick(num: Int): Boolean{
        Toast.makeText(this,"menu"+ num.toString()+  "click", Toast.LENGTH_SHORT).show()
        return true
    }

    fun showPopup(v: View?) {
        val popup = PopupMenu(this, v)
        popup.setOnMenuItemClickListener (this)
        val inflater = popup.menuInflater
        inflater.inflate(R.menu.main_menu, popup.menu)
        popup.show()
    }


    override fun onMenuItemClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu1 -> {
                onClick(1)
                true
            }
            R.id.menu2 -> {
                onClick(2)
                true
            }
            else -> false
        }
    }
}