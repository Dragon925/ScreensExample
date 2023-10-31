package com.github.dragon925.screensexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.dragon925.screensexample.R
import com.github.dragon925.screensexample.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null

    private var count = 0

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.tvText?.text = count.toString()
        binding.btnEnter.setOnClickListener {

            val loginError: String? = checkTextField(binding.etLogin, "Пустой логин")

            val passwordError = checkTextField(binding.etPassword, "Пустое пароль")

            binding.tilLogin.error = loginError
            binding.tilPassword.error = passwordError

            if (loginError == null && passwordError == null) {
                findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
            }
        }
        binding.btnRegistration.setOnClickListener {
            Snackbar.make(it, "Когда-то потом", Snackbar.LENGTH_LONG).show()
            count++
            binding.tvText?.text = count.toString()
        }
    }

    private fun checkTextField(editText: TextInputEditText, message: String): String? {
        val text = editText.text?.toString()
        return if (text.isNullOrBlank()) message else null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}







