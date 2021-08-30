package com.cyj.cropimage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri


class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, CropActivity::class.java)
            startActivityForResult(intent, 101)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("onresult", "result in")
        if (requestCode == 101) {
            val uri = data?.getStringExtra("Uri")
            Log.d("Uri", uri.toString())
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show()
            image.setImageURI(uri?.toUri())
        }
    }

}