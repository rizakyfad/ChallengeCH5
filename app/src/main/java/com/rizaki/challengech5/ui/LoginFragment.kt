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
import com.rizaki.challengech5.R
import com.rizaki.challengech5.database.UserDatabase
import com.rizaki.challengech5.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private var DbUser: UserDatabase? = null
    lateinit var sharedPreferences: SharedPreferences
    private val preference_name = "sfuser"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DbUser = UserDatabase.getInstance(requireContext())

        sharedPreferences =
            requireContext().getSharedPreferences(preference_name, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()
            //mengelola string
            val user = StringBuffer()
            val pass = StringBuffer()
            val id = StringBuffer()
            val fullname = StringBuffer()
            val email = StringBuffer()
            Thread {
                val userresult = DbUser?.UserDao()?.loginUser(username, password)
                activity?.runOnUiThread {
                    //ditampung
                    userresult?.forEach {
                        user.append(it.username)
                        pass.append(it.password)
                        fullname.append(it.fullname)
                        email.append(it.email)
                        id.append(it.id)
                    }

                    if (username == user.toString() && password == pass.toString()) {
                        Toast.makeText(
                            requireActivity(),
                            "Login Success", Toast.LENGTH_LONG
                        ).show()

                        editor.putString("id_key", id.toString())
                        editor.putString("login", username)
                        editor.putString("name_key", fullname.toString())
                        editor.putString("email_key", email.toString())
                        editor.putString("pass_key", pass.toString())
                        editor.apply()
                        findNavController().navigate(R.id.action_loginFragment2_to_homeFragment)
                    } else {
                        Toast.makeText(
                            requireActivity(),
                            "User not found", Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }
                .start()

        }
        binding.txtRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment2_to_registerFragment)
        }

    }
}
