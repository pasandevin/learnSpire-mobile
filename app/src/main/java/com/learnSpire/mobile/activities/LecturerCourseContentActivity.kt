package com.learnSpire.mobile.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.learnSpire.mobile.R
import com.learnSpire.mobile.adapters.CourseContentAdapter
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.ActivityLecturerCourseContentBinding
import com.learnSpire.mobile.models.Content
import com.learnSpire.mobile.models.GetContentRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LecturerCourseContentActivity : AppCompatActivity() {

    var mAddContentFab: FloatingActionButton? = null
    var mAddAnnouncementFab:FloatingActionButton? = null
    var mAddMarksFab:FloatingActionButton? = null
    var mAddFab: ExtendedFloatingActionButton? = null
    var addContentActionText: TextView? = null
    var addAnnouncementActionText:TextView? = null
    var addMarksActionText:TextView? = null
    var isAllFabsVisible: Boolean? = null

    private lateinit var binding: ActivityLecturerCourseContentBinding

    private val lmsApiService = LmsApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLecturerCourseContentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get course id and name
        val courseId = EnrolledCoursesAdapter.courseId
        val courseName = EnrolledCoursesAdapter.courseName

        // set activity title as course name
        setTitle(courseName)

        var courseContentList = ArrayList<Content>()

        // create get content request
        val getContentRequest = GetContentRequest(courseId)

        // call the get enrolled courses api
        var getContentResponse = lmsApiService.getContent(getContentRequest)

        getContentResponse.enqueue(object: Callback<List<Content>> {
            override fun onResponse(call: Call<List<Content>>, response: Response<List<Content>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        courseContentList = it as ArrayList<Content>

                        // set recycler view
                        val recyclerView = binding.recyclerviewCourseContent
                        recyclerView.layoutManager = LinearLayoutManager(this@LecturerCourseContentActivity)

                        // set adapter
                        val adapter = CourseContentAdapter(courseContentList)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Content>>, t: Throwable) {
                println("Get Content failed")
            }
        })

        mAddFab = findViewById(R.id.add_fab)

        mAddContentFab = findViewById(R.id.add_content_fab)
        mAddAnnouncementFab = findViewById(R.id.add_announcement_fab)
        mAddMarksFab = findViewById(R.id.add_marks_fab)


        addContentActionText = findViewById(R.id.add_content_action_text)
        addAnnouncementActionText = findViewById(R.id.add_announcement_action_text)
        addMarksActionText = findViewById(R.id.add_marks_action_text)


        mAddContentFab?.visibility = View.GONE
        mAddAnnouncementFab?.visibility = View.GONE
        mAddMarksFab?.visibility = View.GONE
        addContentActionText?.visibility = View.GONE
        addAnnouncementActionText?.visibility = View.GONE
        addMarksActionText?.visibility = View.GONE

        isAllFabsVisible = false

        mAddFab?.shrink()

        mAddFab?.setOnClickListener{
            clickAddFab()
        }

        mAddContentFab?.setOnClickListener {
            //go to add content activity
            Intent(this, AddContentActivity::class.java).also {
                startActivity(it)
            }

        }

        mAddAnnouncementFab?.setOnClickListener {
            //go to add announcement activity
            Intent(this, AddAnnouncementsActivity::class.java).also {
                startActivity(it)
            }
        }

        mAddMarksFab?.setOnClickListener {
            //go to add marks activity
            Intent(this, AddMarksActivity::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun clickAddFab () {
        isAllFabsVisible = if (!isAllFabsVisible!!) {

            mAddContentFab?.show()
            mAddAnnouncementFab?.show()
            mAddMarksFab?.show()
            addContentActionText?.setVisibility(View.VISIBLE)
            addAnnouncementActionText?.setVisibility(View.VISIBLE)
            addMarksActionText?.setVisibility(View.VISIBLE)

            mAddFab?.extend()

            true
        } else {

            mAddContentFab?.hide()
            mAddAnnouncementFab?.hide()
            mAddMarksFab?.hide()
            addContentActionText?.setVisibility(View.GONE)
            addAnnouncementActionText?.setVisibility(View.GONE)
            addMarksActionText?.setVisibility(View.GONE)

            mAddFab?.shrink()

            false
        }

    }
}