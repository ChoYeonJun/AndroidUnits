package com.cyj.cropimage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.theartofdev.edmodo.cropper.CropImageView
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var cropImageView: CropImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getContent =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                val intent = it.data
                val imageUris = ArrayList<Uri>()
                if (it.resultCode == RESULT_OK && intent != null) {
                    val mClipData = intent.clipData
                    val mData = intent.data
                    if (mClipData != null) {
                        val mArrayUri = arrayOfNulls<Uri>(mClipData.itemCount)
                        for (i in 0..mClipData.itemCount.minus(1)) {
                            val item = mClipData.getItemAt(i)
                            val uri = item.uri
//                            mArrayUri[i] = uri
                            imageUris.add(uri)
                            cropImageView.clearImage()
                            cropImageView.visibility = View.GONE
                            image.setImageURI(uri)
                        }
                        Log.d("count", mArrayUri.size.toString())
                    } else if (mData!= null) {
                        Log.v("LOG_TAG", "get data")
                        val uri: Uri = mData
                        imageUris.add(uri)
                        cropImageView.clearImage()
                        cropImageView.visibility = View.GONE
                        image.setImageURI(uri)
                    }
                }
            }
        image = findViewById(R.id.image)
        button = findViewById(R.id.button)
        button2 = findViewById(R.id.button4)
        cropImageView = findViewById(R.id.cropImageView)
        button.setOnClickListener {
            val intent = Intent()
            intent.setType("image/*")
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.setAction(Intent.ACTION_GET_CONTENT)
            intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            Intent.createChooser(intent, "Select Picture")
//            val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            getContent.launch(intent)
//            startActivity(intent)
//            getContent.launch(intent)
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cap)
            cropImageView.setAspectRatio(4, 3)
            cropImageView.setImageBitmap(bitmap)
        }

        button2.setOnClickListener {
            val cropped = cropImageView.getCroppedImage()
            image.setImageBitmap(cropped)
            cropImageView.clearImage()
            cropImageView.visibility = View.GONE

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
        } else if (requestCode == 102) {
            val bitmap = data?.getParcelableExtra<Bitmap>("image")
            image.setImageBitmap(bitmap)
        }
    }

}