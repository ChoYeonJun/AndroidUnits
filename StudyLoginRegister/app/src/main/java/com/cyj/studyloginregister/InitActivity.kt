package com.cyj.studyloginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.cyj.studyloginregister.databinding.ActivityInitBinding

class InitActivity : AppCompatActivity() {
    private lateinit var btn_user: Button
    private lateinit var btn_admin: Button
    private lateinit var binding: ActivityInitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        btn_admin = binding.btnAdmin
        btn_user = binding.btnUser

        btn_user.setOnClickListener{
            val intent = Intent(this, UserLoginActivity::class.java)
            startActivity(intent)
        }

        btn_admin.setOnClickListener{
            val intent = Intent(this, AdminLoginActivity::class.java)
            startActivity(intent)
        }

    }
}