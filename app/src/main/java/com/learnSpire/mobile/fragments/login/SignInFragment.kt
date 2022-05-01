package com.learnSpire.mobile.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learnSpire.mobile.api.UmsApiService
import com.learnSpire.mobile.databinding.FragmentSigninBinding
import com.learnSpire.mobile.models.SigninRequest
import com.learnSpire.mobile.models.SigninResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private val umsApiService = UmsApiService.create()

    private var userEmail: String = ""
    private var userPassword: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.LoginButton.setOnClickListener {
            binding.apply {

                // Get the input from the user
                userEmail = LoginEmail.text.toString()
                userPassword = LoginPassword.text.toString()

                if(userEmail=="" || userPassword=="") {
                    // Show enter again msg if user doesn't input anything
                    binding.textView2.text = "Please enter the Email and Password"
                } else {
                    //get and set data in Shared Preferences
//                    var sharedPreferences = requireContext().getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)
//                    var editor = sharedPreferences.edit()
//
//                    val savedEmail = sharedPreferences.getString("Email",null)
//                    val savedPassword = sharedPreferences.getString("Password",null)

                    // create new signIn request
                    var signInRequest: SigninRequest = SigninRequest(userEmail, userPassword)

                    // call the api
                    var signInResponse = umsApiService.signIn(signInRequest)

//                    // check the email and password correct or not
//                    if(signInResponse != null) {
//
////                        editor.apply() {
////                            putBoolean("LoggedIn", LoginSwitch.isChecked)
////                        }.apply()
//
//                        // Navigate to the Home screen
//                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//
//                    } else {
//                        binding.textView2.text = "Email or Password incorrect"
//                    }

                    signInResponse.enqueue(object: Callback<SigninResponse> {
                        override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                            val body = response.body()

                            body.let {
                                if (it != null) {
                                    // get the token from the response
                                    println("Sign in success")
                                    println(it.token)
                                } else {
                                    println("Email or Password incorrect")
                                }
                            }
                        }

                        override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                            // will update in the future
                            println("Sign in failed")
                        }
                    })

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}