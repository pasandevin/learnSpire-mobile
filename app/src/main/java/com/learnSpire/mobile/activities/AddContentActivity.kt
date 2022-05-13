package com.learnSpire.mobile.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityAddContentBinding
import com.learnSpire.mobile.models.AddContentRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContentBinding

    private val lmsApiService = LmsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val courseId = EnrolledCoursesAdapter.courseId
        val courseName = EnrolledCoursesAdapter.courseName

        title = "Add Content"

        binding.buttonSubmit.setOnClickListener {
            var contentTitle = binding.editTextContentTitle.text.toString()
            var content = binding.editTextContent.text.toString()

            // Check if the fields are empty or not
            if (contentTitle.isEmpty() || content.isEmpty()) {

                binding.errorTextView.text = "Please fill all the fields"

            } else {

                var courseContent = AddContentRequest(courseId, contentTitle, content)

                // call the add course api
                var addContentResponse = lmsApiService.addContent(courseContent)

                addContentResponse.enqueue(object : Callback<ResponseBody> {

                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {

                            // show success message
                            Toast.makeText(
                                this@AddContentActivity,
                                "Content Added Successfully",
                                Toast.LENGTH_SHORT
                            ).show()

                            // go to the course list fragment
                            Intent(this@AddContentActivity, LecturerMenuActivity::class.java).also {
                                startActivity(it)
                            }

                        } else {
                            // show error message
                            println("Failed to add content")
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        println("Failed to add content")
                    }
                })
            }
        }
    }
}