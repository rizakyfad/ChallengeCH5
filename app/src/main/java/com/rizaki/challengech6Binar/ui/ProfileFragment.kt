package com.rizaki.challengech6Binar.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rizaki.ViewModel.UserViewModel
import com.rizaki.challengech6Binar.R
import com.rizaki.challengech6Binar.ViewModel.UserRepositoryViewModel
import com.rizaki.challengech6Binar.ViewModel.ViewModelFactory
import com.rizaki.challengech6Binar.database.local.User
import com.rizaki.challengech6Binar.databinding.FragmentProfileBinding
import com.rizaki.challengech6Binar.helper.ImageHelper
import com.rizaki.challengech6Binar.helper.UserDataStoreManager
import com.rizaki.challengech6Binar.helper.UserPreferences


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val userRepositoryViewModel by viewModels<UserRepositoryViewModel>()

    private lateinit var userPreferences: UserPreferences
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: UserDataStoreManager
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var bitmap: Bitmap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    handleCameraImage(result.data)
                }
            }

        userPreferences = UserPreferences(requireContext())
        pref = UserDataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        showProfile()

        binding.ivProfile.setOnClickListener {
            openCamera()
        }

        binding.btnUpdate.setOnClickListener {
            updateProfile()
        }

        binding.btnLogout.setOnClickListener {
//            viewModel.logoutUser()
            userPreferences.clearLoggedInUser()
            findNavController().navigate(
                R.id.action_profileFragment_to_loginFragment,
            )
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        resultLauncher.launch(cameraIntent)
    }

    private fun handleCameraImage(intent: Intent?) {
        bitmap = intent?.extras?.get("data") as Bitmap
        binding.ivProfile.setImageBitmap(bitmap)
    }

    private fun updateProfile() {
        viewModel.getId().observe(viewLifecycleOwner) {

            val user = userRepositoryViewModel.getUser(it)
            binding.apply {
                val updateUser = User(
                    id = user.id,
                    email = user.email,
                    password = user.password,
                    username = edtUsername.text.toString(),
                    fullName = edtFullName.text.toString(),
                    dateOfBirth = edtDateOfBirth.text.toString(),
                    address = edtAddress.text.toString(),
                    imageProfile = ImageHelper().convert(bitmap)
                )
                reset()
                userRepositoryViewModel.update(updateUser)
                Toast.makeText(
                    requireContext(),
                    "Profile was successfully updated",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun reset() {
        binding.apply {
            edtUsername.clearFocus()
            edtFullName.clearFocus()
            edtDateOfBirth.clearFocus()
            edtAddress.clearFocus()
        }
    }

    private fun showProfile() {
        viewModel.getId().observe(viewLifecycleOwner) {
            val user = userRepositoryViewModel.getUser(it)
            binding.apply {
                if (user.imageProfile != null) {
                    ivProfile.setImageBitmap(ImageHelper().convert(user.imageProfile))
                }
                edtUsername.setText(user.username)
                edtFullName.setText(user.fullName)
                edtDateOfBirth.setText(user.dateOfBirth)
                edtAddress.setText(user.address)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}