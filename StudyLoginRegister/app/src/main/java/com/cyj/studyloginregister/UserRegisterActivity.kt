package com.cyj.studyloginregister

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cyj.studyloginregister.databinding.ActivityUserregisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import java.util.regex.Matcher
import java.util.regex.Pattern

class UserRegisterActivity: AppCompatActivity() {
    private lateinit var binding: ActivityUserregisterBinding
    private lateinit var btn_advanceRegister: Button

    private lateinit var editText_name: EditText
    private lateinit var editText_pwd: EditText
    private lateinit var editText_pwdConfirm: EditText
    private lateinit var editText_email: EditText

    private lateinit var auth : FirebaseAuth
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityUserregisterBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        btn_advanceRegister = binding.btnAdvanceRegister
        btn_advanceRegister.setOnClickListener {
            val email = editText_email.text
            val pwd = editText_pwd.text.toString()
            val pwdConfirm = editText_pwdConfirm.toString()


            if(!isEmailValid(email.toString())){
                toast("이메일 형식을 확인해 주세요.")
            }else if(!pwd.equals(pwdConfirm)){
                toast("비밀번호가 일치하지 않습니다.")
            }else{

            }
        }

        editText_email = binding.editTextEmail
        editText_name = binding.editTextName
        editText_pwd = binding.editTextPwd
        editText_pwdConfirm = binding.editTextPwdConfirm
    }

    private fun isEmailValid(email: String): Boolean{
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern: Pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}