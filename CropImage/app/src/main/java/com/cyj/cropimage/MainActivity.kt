package com.cyj.cropimage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView


class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var button : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, CropImageActivity::class.java)
            startActivityForResult(intent, 101)
        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("onresult", "result in")
        if (requestCode == 101) {
                val uri = data?.getStringExtra("Uri")
                Log.d("Uri", uri.toString())
                Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
                image.setImageURI(uri?.toUri())
            }
        super.onActivityResult(requestCode, resultCode, data)
    }

}