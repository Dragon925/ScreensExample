package com.github.dragon925.screensexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.dragon925.screensexample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.btnEnter.setOnClickListener {

            val loginError: String? = checkTextField(binding.etLogin, "Пустой логин")

            val passwordError = checkTextField(binding.etPassword, "Пустое пароль")

            binding.tilLogin.error = loginError
            binding.tilPassword.error = passwordError

            if (loginError == null && passwordError == null) {
                Snackbar.make(it, "Вход...", Snackbar.LENGTH_LONG).show()
            }
        }
        binding.btnRegistration.setOnClickListener {
            Snackbar.make(it, "Когда-то потом", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun checkTextField(editText: TextInputEditText, message: String): String? {
        val text = editText.text?.toString()
        return if (text.isNullOrBlank()) message else null
    }
}



