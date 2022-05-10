package com.learnSpire.mobile.fragments.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learnSpire.mobile.R
import com.learnSpire.mobile.api.UmsApiService
import com.learnSpire.mobile.databinding.FragmentSigninBinding
import androidx.navigation.fragment.findNavController
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.models.SigninRequest
import com.learnSpire.mobile.models.SigninResponse
import com.learnSpire.mobile.models.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private val umsApiService = UmsApiService.create()
    private val lmsApiService = LmsApiService.create()

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

                    // Initialized Shared Preferences
                    var sharedPreferences = requireContext().getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)
                    var editor = sharedPreferences.edit()

                    // create new signIn request
                    var signInRequest: SigninRequest = SigninRequest(userEmail, userPassword)

                    // call the signIn api
                    var signInResponse = umsApiService.signIn(signInRequest)

                    signInResponse.enqueue(object: Callback<SigninResponse> {
                        override fun onResponse(call: Call<SigninResponse>, response: Response<SigninResponse>) {
                            val body = response.body()

                            body.let {
                                if (it != null) {

                                    // save token to shared preferences
                                    editor.apply() {
                                        putString("token", it.token)
                                    }.apply()

                                    // call the get user api
                                    var getUserResponse = lmsApiService.getUser()

                                    getUserResponse.enqueue(object: Callback<User> {
                                        override fun onResponse(call: Call<User>, response: Response<User>) {
                                            val body = response.body()

                                            body.let {
                                                if (it != null) {

                                                    // save role to shared preferences
                                                    editor.apply() {
                                                        putString("role", it.role)
                                                    }.apply()

                                                    // call the add user api
                                                    var addUserResponse = lmsApiService.addUser()

                                                    addUserResponse.enqueue(object: Callback<ResponseBody> {
                                                        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                                                            // navigate to the home menu activity
                                                            if (sharedPreferences.getString("role", null) == "lecturer") {
                                                                println("lecturer")
                                                                findNavController().navigate(R.id.action_SignInFragment_to_LecturerMenuActivity)
                                                            } else if (sharedPreferences.getString("role", null) == "student") {
                                                                println("student")
                                                                findNavController().navigate(R.id.action_SignInFragment_to_MenuActivity)
                                                            }
                                                        }

                                                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                                                            println("Failed to add user")
                                                        }
                                                    })
                                                }
                                            }
                                        }

                                        override fun onFailure(call: Call<User>, t: Throwable) {
                                            println("Get User failed")
                                        }
                                    })

                                } else {
                                    binding.textView2.text = "Email or Password incorrect"
                                }
                            }
                        }

                        override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                            println("Sign in failed")
                        }
                    })
                }
            }
        }

        binding.LoginRegisterButton.setOnClickListener {
            findNavController().navigate(R.id.action_SignInFragment_to_SignUpFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}