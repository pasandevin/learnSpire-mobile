package com.learnSpire.mobile.fragments.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.learnSpire.mobile.R
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.FragmentProfileBinding
import com.learnSpire.mobile.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val lmsApiService = LmsApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // call the get user api
        var getUserResponse = lmsApiService.getUser()

        getUserResponse.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val body = response.body()

                body.let {
                    if (it != null) {

                        val user = it

                        // generate random color
                        val generator = ColorGenerator.MATERIAL

                        // get the first letter of the name
                        val letter = user.firstName[0].toString()

                        // generate thumbnail image
                        val drawable = TextDrawable.builder()
                            .buildRound(letter, generator.getRandomColor())

                        // set data to views
                        binding.nameViewEdit.text = user.firstName + " " + user.lastName
                        binding.emailViewEdit.text = user.email
                        binding.roleViewEdit.text = user.role
                        binding.profilePictureImageView.setImageDrawable(drawable)
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                println("Get User failed")
            }
        })

        binding.logOutButton.setOnClickListener {

            // Initialized Shared Preferences
            var sharedPreferences = requireContext()
                .getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)

            // Clearing Shared Preferences
            sharedPreferences.edit().clear().commit()

            // navigate to the home login activity
            findNavController().navigate(R.id.action_ProfileFragment_to_LoginActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}