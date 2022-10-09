package com.rizaki.challengech5.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rizaki.challengech5.R
import com.rizaki.challengech5.database.User
import com.rizaki.challengech5.database.UserDatabase
import com.rizaki.challengech5.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var DbUser: UserDatabase?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//


        DbUser = UserDatabase.getInstance(requireContext())
        binding.btnRegister.setOnClickListener {

            val username = binding.edtUsername.text.toString()
            val fullname = binding.edtFullname.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val objectUser  = User(null, username, fullname, email, password)
            if (binding.edtFullname.text.isNullOrEmpty() || binding.edtUsername.text.isNullOrBlank() || binding.edtEmail.text.isNullOrBlank() || binding.edtPassword.text.isNullOrBlank()) {
                Toast.makeText(
                    activity, "Please Check the field and type again", Toast.LENGTH_LONG
                ).show()
            } else {
                (binding.edtFullname.text!!.isNotEmpty() && binding.edtUsername.text!!.isNotEmpty() && binding.edtEmail.text!!.isNotEmpty() && binding.edtPassword.text!!.isNotEmpty())

                Thread{
                    val resultdb =  DbUser?.UserDao()?.Insertuser(objectUser)
                    Log.d("login", resultdb.toString())
                    activity?.runOnUiThread {
                        if (resultdb !=0 .toLong())
                        {
                            Toast.makeText(
                                requireContext(),
                                "Data berhasil ditambahkan", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(requireContext(),
                                "Data gagal ditambahkan", Toast.LENGTH_LONG).show()
                        }

                    }
                }
                    .start()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)

            }
        }
    }

}