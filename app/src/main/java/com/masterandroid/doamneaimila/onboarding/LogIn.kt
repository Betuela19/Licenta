package com.masterandroid.doamneaimila.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

import com.masterandroid.doamneaimila.R
import com.masterandroid.doamneaimila.databinding.ActivityLogInBinding


class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)

        setContentView(binding.root)

        EmailFocusListener_Login()
        PasswordFocusListener_Login()
        SignUp_Button()
        initUI()
    }

    private fun EmailFocusListener_Login()
    {
        binding.inputEmailLogIn.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.EmailLoginContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {

        if(binding.inputEmailLogIn.text.toString() != "coroama.betuela@gmail.com")
        {
            return "Wrong Email Address"
        }else if (binding.inputEmailLogIn.text.toString() == "")
        {
            return "Email required"
        }
        return null
    }

    private fun PasswordFocusListener_Login()
    {
        binding.inputPasswordLogIn.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.PasswordContainerLogin.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String?
    {

        if(binding.inputPasswordLogIn.text.toString() != "1234")
        {
            return "Wrong Password"
        }else if(binding.inputPasswordLogIn.text.toString() == ""){
            return "Password required"
        }
        return null
    }

    private fun SignUp_Button()
    {
        binding.sigInButton.setOnClickListener{

            val intent = Intent(this,CostumBottomNavBar::class.java)
            startActivity(intent)
        }
    }

    private fun initUI(){

        /*binding.sigInButton.setOnClickListener{
            if (binding.inputUsernamelog.text.toString() == "user" && binding.inputPasswordlog.text.toString() == "1234"){
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_SHORT).show()
            }
        }*/

        binding.forgotPassword.setOnClickListener{

        }

        binding.signUpLink.setOnClickListener{

            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }

    }
}