package com.example.crypto_compare.presentation.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.crypto_compare.R
import com.example.crypto_compare.databinding.FragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEvents()
    }

    private fun setupEvents() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                var isValid = true
                if (username.isEmpty() || password.isEmpty()) {
                    isValid = false
                    showNoYetImplementedToast(getString(R.string.text_error_login_field))
                }
                if (isValid) {
                    viewModel.saveUsernameToLocal(username)
                    hideKeyboard()
                }
            }
            tvFb.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            tvGoogle.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            tvForgotPassword.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            btnLoginWithFingerPrint.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            tvRegister.setOnClickListener {
                showNoYetImplementedToast(getString(R.string.text_not_yet_implemented))
            }
            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun showNoYetImplementedToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            requireView().windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN
        )
    }
}