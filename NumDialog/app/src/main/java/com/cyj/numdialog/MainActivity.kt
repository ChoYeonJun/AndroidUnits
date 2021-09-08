package com.cyj.numdialog

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.cyj.numdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
//            val dialog = CustomDialog()
//            dialog.show(supportFragmentManager, "CustomDialog")
            var builder =AlertDialog.Builder(this)

            builder.setView(layoutInflater.inflate(R.layout.dialog_layout, null))

            builder.setPositiveButton("확인", null)
            builder.setNegativeButton("취소", null)
            val alert = builder.create()
            alert.setCanceledOnTouchOutside(true)
            alert.show()

            val negativeBtn = alert.getButton(DialogInterface.BUTTON_NEGATIVE)
            negativeBtn.setTextColor(ContextCompat.getColor(this, R.color.dialog))
            val positiveBtn = alert.getButton(DialogInterface.BUTTON_POSITIVE)
            positiveBtn.setTextColor(ContextCompat.getColor(this, R.color.dialog))
        }
    }
}