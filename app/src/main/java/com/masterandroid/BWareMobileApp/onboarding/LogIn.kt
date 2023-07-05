package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.masterandroid.BWareMobileApp.R

import com.masterandroid.BWareMobileApp.databinding.ActivityLogInBinding
import okhttp3.*
import java.io.IOException


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

        val google = findViewById<ImageView>(R.id.google_icon)
        google.setOnClickListener{
            val url = "https://accounts.google.com/v3/signin/identifier?dsh=S1275193554%3A1688514110455885&continue=https%3A%2F%2Fmyaccount.google.com%3Futm_source%3Daccount-marketing-page%26utm_medium%3Dgo-to-account-button&ifkv=AeDOFXg9NO4fEcshGNxz-SIEFcQKzAQKDoU0GTOtoHFClJR_GTmFmeS6psZlHB0SqKnUuax3jTCfGQ&service=accountsettings&flowName=GlifWebSignIn&flowEntry=ServiceLogin"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        val facebook = findViewById<ImageView>(R.id.facebook_icon)
        facebook.setOnClickListener{
            val url = "https://web.facebook.com/login/?_rdc=1&_rdr"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
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

       /* if(binding.inputEmailLogIn.text.toString() != "coroama.betuela@gmail.com")
        {
            return "Wrong Email Address"
        }else if (binding.inputEmailLogIn.text.toString() == "")
        {
            return "Email required"
        }*/
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

        /*if(binding.inputPasswordLogIn.text.toString() != "1234")
        {
            return "Wrong Password"
        }else if(binding.inputPasswordLogIn.text.toString() == ""){
            return "Password required"
        }*/
        return null
    }
    private fun InvalidPassword(): String?
    {

        if(binding.inputPasswordLogIn.text.toString() != "Test12345@")
        {
            return "Wrong Password"
        }else if(binding.inputPasswordLogIn.text.toString() == ""){
            return "Password required"
        }
        return null
    }

    private fun InvalidEmail(): String?
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

    private fun SignUp_Button()
    {
        binding.sigInButton.setOnClickListener{

            val password = binding.inputPasswordLogIn.text.toString()
            val email = binding.inputEmailLogIn.text.toString()

            if(password == "Test12345@" && email == "coroama.betuela@gmail.com")
            {
                val intent = Intent(this,CostumBottomNavBar::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }

           /* val url = "https://bwaremobileapi.azurewebsites.net/User/login"

            val requestBody = FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build()

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val client = OkHttpClient()

            var body : String? = ""

            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    body = response?.body()?.string()
                    // Handle the response body as needed
                    println(body)
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                    e?.printStackTrace()
                }
            })

            println(body)
            if(body == "true")
            {
                val intent = Intent(this,CostumBottomNavBar::class.java)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }*/

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