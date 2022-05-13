package com.learnSpire.mobile.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.learnSpire.mobile.R
import com.learnSpire.mobile.activities.LecturerMenuActivity
import com.learnSpire.mobile.adapters.CourseMarksAdapter.ViewHolder
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.models.GetCourseMarksResponse
import com.learnSpire.mobile.models.AddMarksRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseMarksAdapter(private val courseMarksList: List<GetCourseMarksResponse>) :
    RecyclerView.Adapter<ViewHolder>() {

    private val lmsApiService = LmsApiService.create()

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_add_marks, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courseMarks: GetCourseMarksResponse = courseMarksList[position]

        // generate random color
        val generator = ColorGenerator.MATERIAL

        // get the first letter of the student name
        val letter = courseMarks.firstName[0].toString()

        // generate thumbnail image
        val drawable = TextDrawable.builder()
            .buildRound(letter, generator.randomColor)

        // set the thumbnail image
        holder.imageView.setImageDrawable(drawable)

        // set the views
        holder.textviewStudentName.text = courseMarks.firstName + " " + courseMarks.lastName
        holder.textviewMark.text = "Marks: " + courseMarks.marks.toString()

        // set the onclick listener
        holder.updateButton.setOnClickListener {


            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(it.context)
            builder.setTitle("Update Marks for " + holder.textviewStudentName.text)

            // Set up the input
            val input = EditText(it.context)
            // Specify the type of input expected
            input.hint = "Enter Marks"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            // Set up the buttons
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                // Here you get get input text from the Edittext
                var mark = input.text.toString()
                if (mark.isEmpty()) {
                    //makeToast
                    Toast.makeText(it.context, "Please enter marks", Toast.LENGTH_SHORT).show()
                } else {

                    var courseId = EnrolledCoursesAdapter.courseId
                    var email = courseMarks.email

                    val addMarksRequest = AddMarksRequest(courseId, email, mark.toInt())

                    // call the add course marks api
                    var addMarksResponse = lmsApiService.addMarks(addMarksRequest)

                    addMarksResponse.enqueue(object : Callback<ResponseBody> {

                        override fun onResponse(
                            call: Call<ResponseBody>,
                            response: Response<ResponseBody>
                        ) {
                            if (response.isSuccessful) {

                                // show success message
                                Toast.makeText(
                                    it.context,
                                    "Marks Added Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()


                            } else {
                                // show error message
                                println("Failed to add marks")
                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                            println("Failed to add marks")
                        }
                    })


                }
            })
            builder.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

            builder.show()
        }
    }

    override fun getItemCount(): Int {
        return courseMarksList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textviewStudentName: TextView = itemView.findViewById(R.id.text_student_name)
        val textviewMark: TextView = itemView.findViewById(R.id.text_marks)
        val imageView: ImageView = itemView.findViewById(R.id.profilePictureImageView)
        val updateButton: Button = itemView.findViewById(R.id.updateButton)
    }

}