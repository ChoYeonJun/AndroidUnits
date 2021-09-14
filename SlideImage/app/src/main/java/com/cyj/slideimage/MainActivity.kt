package com.cyj.slideimage

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.smarteist.autoimageslider.SliderView

class MainActivity : AppCompatActivity() , PopupMenu.OnMenuItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageSlider = findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
        imageList.add("https://images.ctfassets.net/hrltx12pl8hq/4plHDVeTkWuFMihxQnzBSb/aea2f06d675c3d710d095306e377382f/shutterstock_554314555_copy.jpg")
        imageList.add("https://media.istockphoto.com/photos/child-hands-formig-heart-shape-picture-id951945718?k=6&m=951945718&s=612x612&w=0&h=ih-N7RytxrTfhDyvyTQCA5q5xKoJToKSYgdsJ_mHrv0=")
        setImageInSlider(imageList, imageSlider)

        val button = findViewById<ImageButton>(R.id.options)
        button.setOnClickListener {
            showPopup(it)
        }
    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = false
        imageSlider.startAutoCycle()
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