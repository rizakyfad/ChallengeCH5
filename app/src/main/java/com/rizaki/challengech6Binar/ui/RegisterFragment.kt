package com.rizaki.challengech6Binar.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.rizaki.ViewModel.UserViewModel
import com.rizaki.challengech6Binar.ViewModel.UserRepositoryViewModel
import com.rizaki.challengech6Binar.database.local.User
import com.rizaki.challengech6Binar.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private val userRepositoryViewModel by viewModels<UserRepositoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        insertUser()
    }

    private fun insertUser() {
        binding.apply {
            btnRegister.setOnClickListener {
                val username = edtUsername.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                val confirmPassword = edtConfirmPassword.text.toString()
                if (username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || confirmPassword.isNullOrEmpty()) {
                    Toast.makeText(
                        requireContext(), "Field cannot be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (password != confirmPassword) {
                    Toast.makeText(
                        requireContext(),
                        "Password mismatch", Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val user = User(
                        id = 0,
                        username = username,
                        email = email,
                        password = password
                    )
                    userRepositoryViewModel.insert(user)
                    reset()
                    Toast.makeText(
                        requireContext(), "Registered Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun reset() {
        binding.apply {
            edtUsername.setText("")
            edtUsername.clearFocus()
            edtEmail.setText("")
            edtEmail.clearFocus()
            edtPassword.setText("")
            edtPassword.clearFocus()
            edtConfirmPassword.setText("")
            edtConfirmPassword.clearFocus()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}