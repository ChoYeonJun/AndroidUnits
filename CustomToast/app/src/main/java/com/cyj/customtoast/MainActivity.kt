package com.cyj.customtoast

import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.cyj.customtoast.databinding.ActivityMainBinding
import com.cyj.customtoast.databinding.ToastCustomBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            SampleToast.createToast(this, "Welcome to custom toast")?.show()
        }
    }

    object SampleToast {

        fun createToast(context: Context, message: String): Toast? {
            val inflater = LayoutInflater.from(context)
            val binding: ToastCustomBinding =
                DataBindingUtil.inflate(inflater, R.layout.toast_custom, null, false)

            binding.tvSample.text = message

            return Toast(context).apply {
                setGravity(Gravity.BOTTOM or Gravity.CENTER, 0, 16.toPx())
                duration = Toast.LENGTH_LONG
                view = binding.root
            }
        }

        private fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
    }
}