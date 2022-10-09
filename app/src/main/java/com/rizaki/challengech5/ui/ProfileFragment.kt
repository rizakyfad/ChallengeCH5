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
import com.rizaki.challengech5.database.UserDatabase
import com.rizaki.challengech5.databinding.FragmentProfileBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var DbUser: UserDatabase? = null
    private val preference_name = "sfuser"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DbUser = UserDatabase.getInstance(requireContext())
        val sharedPreferences: SharedPreferences = requireActivity().getSharedPreferences(
            preference_name,
            Context.MODE_PRIVATE
        )
        //get
        val update = sharedPreferences.getString("id_key", "default")
        val user = sharedPreferences.getString("login", "default")
        val name = sharedPreferences.getString("name_key", "default")
        val email2 = sharedPreferences.getString("email_key", "default")
        val pass = sharedPreferences.getString("pass_key", "default")


        binding.edtUsername.setText(user)
        binding.edtFullname.setText(name)
        binding.edtEmail.setText(email2)



        binding.btnUpdate.setOnClickListener {
            val objectUser = User(
                update?.toInt(),
                binding.edtFullname.text.toString(),
                binding.edtUsername.text.toString(),
                binding.edtEmail.text.toString(),
                password = pass
            )
            GlobalScope.async {
                val update = DbUser?.UserDao()?.updateUser(objectUser)
                activity?.runOnUiThread {
                    if (update != 0) {
                        Snackbar.make(view, "Data changed success", Snackbar.LENGTH_LONG).show()
                    } else {
                        Snackbar.make(view, "Data changed failed", Snackbar.LENGTH_LONG).show()
                    }

                }

            }

        }
        binding.btnlogOut.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear().apply()
            Toast.makeText(activity, "user berhasil logout", Toast.LENGTH_LONG).show()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment2())

        }
    }
}
