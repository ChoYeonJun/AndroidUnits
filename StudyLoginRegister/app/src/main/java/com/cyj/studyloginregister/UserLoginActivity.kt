package com.cyj.studyloginregister

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyj.studyloginregister.databinding.ActivityUserloginBinding

class UserLoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUserloginBinding

    private lateinit var btn_login: Button
    private lateinit var btn_register: Button
    private lateinit var btn_findIdPwd: Button

    private lateinit var editText_email: EditText
    private lateinit var editText_pwd: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserloginBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        editText_email = binding.editTextEmail
        editText_pwd = binding.editTextPwd

        btn_login = binding.btnLogin
        btn_login.setOnClickListener{
            toast("main access")
        }

        btn_register = binding.btnRegister
        btn_register.setOnClickListener{
            val intent = Intent(this,UserRegisterActivity::class.java)
            startActivity(intent)
        }

        btn_findIdPwd = binding.btnFindIdPwd
        btn_findIdPwd.setOnClickListener{

        }
    }

    private fun toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}