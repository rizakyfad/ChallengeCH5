package com.rizaki.challengech5.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.rizaki.challengech5.R
import com.rizaki.challengech5.database.User
import com.rizaki.challengech5.database.UserRepository
import com.rizaki.challengech5.databinding.FragmentProfileBinding
import com.rizaki.challengech5.model.Preferences
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            Preferences().clearLoggedInUser(requireContext())
            findNavController().navigate(
                R.id.action_profileFragment_to_loginFragment,
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}