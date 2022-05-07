package com.learnSpire.mobile.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.learnSpire.mobile.R
import com.learnSpire.mobile.api.UmsApiService
import com.learnSpire.mobile.databinding.FragmentSignupBinding
import com.learnSpire.mobile.models.SignupRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val umsApiService = UmsApiService.create()

    private var firstName: String = ""
    private var lastName: String = ""
    private var userEmail: String = ""
    private var userPassword: String = ""
    private var userConfirmPassword: String = ""
    private var userRole: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LoginButton.setOnClickListener {
            binding.apply {

                // Get the input from the user
                userEmail = registerEmail.text.toString()
                firstName = registerFirstName.text.toString()
                lastName = registerLastName.text.toString()
                userPassword = registerPassword.text.toString()
                userConfirmPassword = registerConfirmPassword.text.toString()

                if(userEmail=="" || userPassword=="" || userConfirmPassword=="" ||
                    firstName=="" || lastName==""){

                    // Show enter again msg if user doesn't input anything
                    binding.textView3.text = "Please fill all the fields"

                } else {

                    // check if the password and confirm password are the same
                    if(userPassword == userConfirmPassword){

                        // get role from toggle button
                        if(binding.toggleButton.isChecked){
                            userRole = "lecturer"
                        } else {
                            userRole = "student"
                        }

                        // create new signUp request
                        var signUpRequest: SignupRequest = SignupRequest(
                            userEmail, firstName, lastName, userRole, userPassword)

                        // call the api
                        var signUpResponse = umsApiService.signUp(signUpRequest)

                        signUpResponse.enqueue(object: Callback<ResponseBody> {
                            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                                if (response.code() == 200) {
                                    // navigate to the signIn fragment
                                    findNavController().navigate(R.id.action_SignUpFragment_to_SignInFragment)
                                } else if (response.code() == 400) {
                                    binding.textView3.text = "Email already exists"
                                } else {
                                    binding.textView3.text = "Something went wrong"
                                }
                            }

                            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                println("Failed to sign up")
                            }
                        })

                    } else {
                        binding.textView3.text = "Password and Confirm Password are not the same"
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}