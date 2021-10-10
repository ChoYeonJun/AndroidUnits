package com.cyj.cropimage

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.esafirm.imagepicker.features.ImagePickerConfig
import com.esafirm.imagepicker.features.ImagePickerMode
import com.esafirm.imagepicker.features.ImagePickerSavePath
import com.esafirm.imagepicker.features.registerImagePicker
import com.esafirm.imagepicker.model.Image
import com.theartofdev.edmodo.cropper.CropImageView
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var image: ImageView
    private val images = arrayListOf<Image>()
    private val results = arrayListOf<Bitmap>()
    lateinit var button: Button
    lateinit var button2: Button
    lateinit var cropImageView: CropImageView
    private val imagePickerLauncher = registerImagePicker {
        images.clear()
        images.addAll(it)
        getImageFromCustom()
    }
    val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            setImage(it)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        button = findViewById(R.id.start)
        button2 = findViewById(R.id.button4)
        cropImageView = findViewById(R.id.cropImageView)

        button.setOnClickListener {
            start()
        }


        button2.setOnClickListener {
            val cropped = cropImageView.getCroppedImage()
            image.setImageBitmap(cropped)
            cropImageView.clearImage()
            cropImageView.visibility = View.GONE
        }
    }

    private fun start() {
        imagePickerLauncher.launch(createConfig())
    }

    private fun defaultImageAccess()
    {
        val intent = Intent()
        intent.setType("image/*")
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.setAction(Intent.ACTION_GET_CONTENT)
        intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        Intent.createChooser(intent, "Select Picture")
//            val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            intent.action = Intent.ACTION_GET_CONTENT
//            intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        getContent.launch(intent)
//            startActivity(intent)
//            getContent.launch(intent)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cap)
        cropImageView.setAspectRatio(4, 3)
        cropImageView.setImageBitmap(bitmap)

    }
    private fun createConfig(): ImagePickerConfig {
        val folderMode = true
        return ImagePickerConfig {
            ImagePickerMode.SINGLE // multi mode (default mode)

            language = "in" // Set image picker language
            theme = R.style.ImagePickerTheme

            // set whether pick action or camera action should return immediate result or not. Only works in single mode for image picker
//            returnMode = if (returnAfterCapture) ReturnMode.ALL else ReturnMode.NONE
//
            isFolderMode = folderMode // set folder mode (false by default)
//            isIncludeVideo = includeVideo // include video (false by default)
//            isOnlyVideo = onlyVideo // include video (false by default)
            arrowColor = Color.WHITE // set toolbar arrow up color

            folderTitle = "Folder" // folder selection title
            imageTitle = "Tap to select" // image selection title
            doneButtonText = "DONE" // done button text
            showDoneButtonAlways = true // Show done button always or not
            limit = 10 // max images can be selected (99 by default)
            isShowCamera = true // show camera or not (true by default)
            savePath = ImagePickerSavePath("Camera") // captured image directory name ("Camera" folder by default)
            savePath = ImagePickerSavePath(Environment.getExternalStorageDirectory().path, isRelative = false) // can be a full path

//            if (isExclude) {
//                excludedImages = images.toFiles() // don't show anything on this selected images
//            } else {
            selectedImages = images  // original selected images, used in multi mode
//            }
        }
    }

    private fun getImageFromCustom(){
        val size = images.size
        cropImageView.setAspectRatio(4, 3)

        cropImageView.clearImage()
        cropImageView.visibility = View.VISIBLE

        cropImageView.setImageUriAsync(images[0].uri)
//        results.add(cropImageView.getCroppedImage())
//        image.setImageBitmap(cropImageView.getCroppedImage())
//        for (i in 0..size.minus(1)){
//
//        }

        Log.d("result", results.size.toString())
    }
    private fun setImage(it: ActivityResult){
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