package com.masterandroid.BWareMobileApp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.masterandroid.BWareMobileApp.databinding.ActivitySignUpBinding

class SignUp : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        emailFocusListener()
        passwordFocusListener()
        fullnameFocusListener()
        usernameFocusListener()
        ConfirmPasswordFocusListener()

        binding.sigUpButton.setOnClickListener{
            submitForm()
        }

        SignIn_Link()

    }
    private fun submitForm()
    {
        binding.EmailContainer.helperText = validEmail()
        binding.PasswordContainer.helperText = validPassword()
        binding.fullNameContainer.helperText = validFullName()
        binding.UsernameContainer.helperText = validUsername()
        binding.PasswordConfirmationContainer.helperText = validPasswordConfirmation()

        val validEmail = binding.EmailContainer.helperText == null
        val validPassword = binding.PasswordContainer.helperText == null
        val validUsername = binding.UsernameContainer.helperText == null
        val validFullName = binding.fullNameContainer.helperText == null
        val validConfirmationPassword = binding.PasswordConfirmationContainer.helperText == null

        if (validEmail && validPassword && validUsername && validFullName && validConfirmationPassword)
            resetForm()
        else
            invalidForm()
    }


    private fun invalidForm()
    {
        var message = ""
        if(binding.EmailContainer.helperText != null)
            message += "\n\nEmail: " + binding.EmailContainer.helperText
        if(binding.PasswordContainer.helperText != null)
            message += "\n\nPassword: " + binding.PasswordContainer.helperText
        if(binding.UsernameContainer.helperText != null)
            message += "\n\nUsername: " + binding.UsernameContainer.helperText
        if(binding.fullNameContainer.helperText != null)
            message += "\n\nFull Name: " + binding.fullNameContainer.helperText
        if(binding.PasswordConfirmationContainer.helperText != null)
            message += "\n\nConfirmed password: " + binding.PasswordConfirmationContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay"){ _,_ ->
                // do nothing
            }
            .show()
    }

    private fun resetForm()
    {
        var message = "Email: " + binding.inputEmailSignUp.text
        message += "\nPassword: " + binding.inputPasswordSignUp.text
        message += "\nFull name: " + binding.inputFullName.text
        message += "\nUsername: " + binding.inputUsernameSignUp.text
        message += "\nConfirmed password: " + binding.inputPasswordConfirmationSignUp.text
        AlertDialog.Builder(this)
            .setTitle("Form submitted")
            .setMessage(message)
            .setPositiveButton("Sign in"){ _,_ ->
                binding.inputEmailSignUp.text = null
                binding.inputPasswordSignUp.text = null
                binding.inputFullName.text = null
                binding.inputUsernameSignUp.text = null
                binding.inputPasswordConfirmationSignUp.text = null

            }

            .show()
    }

    private fun fullnameFocusListener()
    {
        binding.inputFullName.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.fullNameContainer.helperText = validFullName()
            }
        }
    }

    private fun validFullName(): String?
    {
        val fullNameText = binding.inputFullName.text.toString()

        if(fullNameText.length < 3)
        {
            return "Minimum 3 Character "
        }
        if(fullNameText.matches(".*[@#\$%^&+=].*".toRegex()))
        {
            return "Name cannot contain special characters"
        }
        return null
    }


    private fun emailFocusListener()
    {
        binding.inputEmailSignUp.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.EmailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String?
    {
        val emailText = binding.inputEmailSignUp.text.toString()
        if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches())
        {
            return "Invalid Email Address"
        }
        return null
    }

    private fun usernameFocusListener()
    {
        binding.inputUsernameSignUp.setOnFocusChangeListener { _, focused ->
            if(!focused)
            {
                binding.UsernameContainer.helperText = validUsername()
            }
        }
    }

    private fun validUsername(): String?
    {
        val usernameText = binding.inputUsernameSignUp.text.toString()

        if(usernameText.length < 3)
        {
            return "Minimum 3 Character "
        }
        if(usernameText.matches(".*[@#\$%^&+=';.].*".toRegex()))
        {
            return "Username cannot contain special characters"
        }
        return null
    }


    private fun passwordFocusListener() {
        binding.inputPasswordSignUp.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.PasswordContainer.helperText = validPassword()
            }
        }
    }

        private fun validPassword(): String?
        {
            val passwordText = binding.inputPasswordSignUp.text.toString()
            if(passwordText.length < 8)
            {
                return "Minimum 8 Character Password"
            }
            if(!passwordText.matches(".*[A-Z].*".toRegex()))
            {
                return "Must Contain 1 Upper-case Character"
            }
            if(!passwordText.matches(".*[a-z].*".toRegex()))
            {
                return "Must Contain 1 Lower-case Character"
            }
            if(!passwordText.matches(".*[@#\$%^&+=].*".toRegex()))
            {
                return "Must Contain 1 Special Character (@#\$%^&+=)"
            }

            return null
        }

    private fun ConfirmPasswordFocusListener() {
        binding.inputPasswordConfirmationSignUp.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.PasswordConfirmationContainer.helperText = validPasswordConfirmation()
            }
        }
    }

    private fun validPasswordConfirmation(): String?
    {
        val passwordConfirmationText = binding.inputPasswordConfirmationSignUp.text.toString().trim()
        val passwordInputText = binding.inputPasswordSignUp.text.toString().trim()

        if(!passwordConfirmationText.equals(passwordInputText))
        {
            return "Wrong password"
        }

        return null
    }

    private fun SignIn_Link()
    {
        binding.signInLink.setOnClickListener{

            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)
        }
    }


    }


/*

private fun initUI(){


    binding.sigUpButton.setOnClickListener{
        ///full name validation
        val name_validation = findViewById<EditText>(R.id.input_fullName)

        name_validation.noSpecialCharacters {
            name_validation.error = "No special characteers"
        }
        name_validation.minLength(3) {
            name_validation.error = "Minimum 3 characters"
        }
        name_validation.nonEmpty {
            name_validation.error = "Required"
        }


        ///email validation
        val email_validation = findViewById<EditText>(R.id.input_email)

        email_validation.noSpecialCharacters{
            email_validation.error = "No special characters allowed"
        }
        email_validation.atleastOneSpecialCharacters(){
            email_validation.error = "Email must contain the @ character"
        }
        email_validation.nonEmpty {
            email_validation.error = "Required"
        }

        //username validation
        val username_validation = findViewById<EditText>(R.id.input_username)
        username_validation.noSpecialCharacters()
        {
            username_validation.error = "No special characters allowed"
        }
        username_validation.minLength(3){
            username_validation.error = "At least 3 characters long"
        }
        username_validation.nonEmpty {
            username_validation.error = "Required"
        }


        //password validation
        val txtPassword = findViewById<EditText>(R.id.input_password_signup)

        txtPassword.minLength(8)
        {
            txtPassword.error="Minimum 8 characters"
        }

        txtPassword.atleastOneUpperCase()
        {
            txtPassword.error="Att least one upper case"
        }

        txtPassword.atleastOneSpecialCharacters()
        {
            txtPassword.error="At least one special character"
        }

        txtPassword.atleastOneNumber(){
            txtPassword.error="At least one number"
        }

        txtPassword.nonEmpty(){
            txtPassword.error="Required"
        }

        //confirm password
        val confirm_password = findViewById<EditText>(R.id.input_passwordsc)
        val parola = txtPassword.text.toString().trim()
        val confirmare = confirm_password.text.toString().trim()

        if(!parola.equals(confirmare))
        {
            confirm_password.error = "Wrong password"
        }

    }

    binding.signInLink.setOnClickListener{

            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)
        }
    }*/


