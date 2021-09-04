package com.cyj.cropimage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.ByteArrayOutputStream
import java.lang.Exception


class CropActivity :AppCompatActivity(){
    lateinit var cropImageView: CropImageView
    lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cropimage)

        cropImageView = findViewById(R.id.cropImageView)
        button = findViewById(R.id.button2)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cap)
        cropImageView.setAspectRatio(4,3)
        cropImageView.setImageBitmap(bitmap)

        button.setOnClickListener {
            val cropped = cropImageView.getCroppedImage()
            var fileName:String?= "myImage"
            val intent = Intent(this, MainActivity::class.java)
            try {
                val stream = ByteArrayOutputStream()
                cropped.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                val byteArray = stream.toByteArray()
                intent.putExtra("bitmap", byteArray)
                Toast.makeText(this, cropped.height.toString(), Toast.LENGTH_SHORT).show()

                stream.close()
            }catch(e: Exception){

            }

//            startActivityForResult(intent, 102)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
//        CropImage.activity()
//            .setGuidelines(CropImageView.Guidelines.ON)
//            .setAspectRatio(4, 3)
//            .start(this);

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                val resultUri: Uri = result.uri
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Uri", resultUri.toString())
                setResult(101, intent)
                finish()
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }
}