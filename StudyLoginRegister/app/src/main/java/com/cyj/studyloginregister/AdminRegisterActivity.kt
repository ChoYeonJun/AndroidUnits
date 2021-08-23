package com.cyj.studyloginregister

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cyj.studyloginregister.databinding.ActivityAdminregisterBinding

class AdminRegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAdminregisterBinding
    private lateinit var btn_advanceRegister: Button

    private lateinit var editText_name: EditText
    private lateinit var editText_pwd: EditText
    private lateinit var editText_pwdConfirm: EditText
    private lateinit var editText_email: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminregisterBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)

        btn_advanceRegister = binding.btnAdvanceRegister
        btn_advanceRegister.setOnClickListener {

        }

        editText_email = binding.editTextEmail
        editText_name = binding.editTextName
        editText_pwd = binding.editTextPwd
        editText_pwdConfirm = binding.editTextPwdConfirm
    }
}