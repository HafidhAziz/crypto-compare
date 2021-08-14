package com.example.crypto_compare.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.crypto_compare.R
import com.example.crypto_compare.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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
                val username = etUsername.text
                val password = etPassword.text
            }
            tvFb.setOnClickListener {
                showNoYetImplementedToast()
            }
            tvGoogle.setOnClickListener {
                showNoYetImplementedToast()
            }
            tvForgotPassword.setOnClickListener {
                showNoYetImplementedToast()
            }
            btnLoginWithFingerPrint.setOnClickListener {
                showNoYetImplementedToast()
            }
            tvRegister.setOnClickListener {
                showNoYetImplementedToast()
            }
            ivBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

    private fun showNoYetImplementedToast() {
        Toast.makeText(
            requireContext(),
            getString(R.string.text_not_yet_implemented),
            Toast.LENGTH_LONG
        ).show()
    }
}