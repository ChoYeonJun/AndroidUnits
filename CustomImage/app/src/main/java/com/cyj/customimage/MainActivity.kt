package com.cyj.customimage

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.cyj.customimage.databinding.ActivityMainBinding
import com.esafirm.imagepicker.features.*
import com.esafirm.imagepicker.model.Image

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val images = arrayListOf<Image>()
    private val imagePickerLauncher = registerImagePicker {
        images.clear()
        images.addAll(it)
        printImages(images)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        setupButtons()
    }

    private fun start() {
        imagePickerLauncher.launch(createConfig())
    }

    private fun setupButtons() = binding.run {
        buttonPickImage.setOnClickListener { start() }
    }

    private fun printImages(images: List<Image>?) {
        if (images == null) return
        binding.textView.text = images.joinToString("\n")
        binding.textView.setOnClickListener {
            ImageViewerActivity.start(this@MainActivity, images)
        }
    }

    private fun createConfig(): ImagePickerConfig {
//        val returnAfterCapture = binding.switchReturnAfterCapture.isChecked
//        val isSingleMode = binding.switchSingle.isChecked
//        val useCustomImageLoader = binding.switchImageloader.isChecked
        val folderMode = binding.switchFolderMode.isChecked
//        val includeVideo = binding.switchIncludeVideo.isChecked
//        val onlyVideo = binding.switchOnlyVideo.isChecked
//        val isExclude = binding.switchIncludeExclude.isChecked

//        ImagePickerComponentsHolder.setInternalComponent(
//            CustomImagePickerComponents(this, useCustomImageLoader)
//        )

        return ImagePickerConfig {

//            mode = if (isSingleMode) {
//                ImagePickerMode.SINGLE
//            } else {
                ImagePickerMode.MULTIPLE // multi mode (default mode)
//            }

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
}