package com.learnSpire.mobile.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.R
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.FragmentLecturerCoursesBinding
import com.learnSpire.mobile.models.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LecturerCoursesFragment : Fragment() {

    private var _binding: FragmentLecturerCoursesBinding? = null
    private val binding get() = _binding!!

    private val lmsApiService = LmsApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLecturerCoursesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var enrolledCoursesList = ArrayList<Course>()

        // call the get enrolled courses api
        var getEnrolledCoursesResponse = lmsApiService.getEnrolledCourses()

        getEnrolledCoursesResponse.enqueue(object: Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        enrolledCoursesList = it as ArrayList<Course>

                        // set recycler view
                        val recyclerView = binding.recyclerviewEnrolledCourses
                        recyclerView.layoutManager = LinearLayoutManager(activity)

                        // set adapter
                        val adapter = EnrolledCoursesAdapter(enrolledCoursesList)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                println("Get Enrolled Courses failed")
            }
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_CoursesFragment_to_AddCourseActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}