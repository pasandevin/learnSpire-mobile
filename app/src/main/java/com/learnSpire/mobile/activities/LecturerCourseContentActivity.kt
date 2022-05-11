package com.learnSpire.mobile.activities


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

    /////
    var mAddAlarmFab: FloatingActionButton? = null
    var mAddPersonFab:FloatingActionButton? = null
    var mAddFab: ExtendedFloatingActionButton? = null
    var addAlarmActionText: TextView? = null
    var addPersonActionText:TextView? = null
    var isAllFabsVisible: Boolean? = null
    /////

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

//        binding.floatingActionButton.setOnClickListener {
//            val intent = Intent(this, AddContentActivity::class.java)
//            startActivity(intent)
//        }
        //////////////////////////////
        mAddFab = findViewById(R.id.add_fab)

        mAddAlarmFab = findViewById(R.id.add_alarm_fab)
        mAddPersonFab = findViewById(R.id.add_person_fab)


        addAlarmActionText = findViewById(R.id.add_alarm_action_text)
        addPersonActionText = findViewById(R.id.add_person_action_text)

        mAddAlarmFab?.visibility = View.GONE
        mAddPersonFab?.visibility = View.GONE
        addAlarmActionText?.visibility = View.GONE
        addPersonActionText?.visibility = View.GONE

        isAllFabsVisible = false

        mAddFab?.shrink()

        mAddFab?.setOnClickListener(
            View.OnClickListener {
                isAllFabsVisible = if (!isAllFabsVisible!!) {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs VISIBLE.
                    mAddAlarmFab?.show()
                    mAddPersonFab?.show()
                    addAlarmActionText?.setVisibility(View.VISIBLE)
                    addPersonActionText?.setVisibility(View.VISIBLE)

                    // Now extend the parent FAB, as
                    // user clicks on the shrinked
                    // parent FAB
                    mAddFab?.extend()

                    // make the boolean variable true as
                    // we have set the sub FABs
                    // visibility to GONE
                    true
                } else {

                    // when isAllFabsVisible becomes
                    // true make all the action name
                    // texts and FABs GONE.
                    mAddAlarmFab?.hide()
                    mAddPersonFab?.hide()
                    addAlarmActionText?.setVisibility(View.GONE)
                    addPersonActionText?.setVisibility(View.GONE)

                    // Set the FAB to shrink after user
                    // closes all the sub FABs
                    mAddFab?.shrink()

                    // make the boolean variable false
                    // as we have set the sub FABs
                    // visibility to GONE
                    false
                }
            })
        //////////////////////////////





    }
}