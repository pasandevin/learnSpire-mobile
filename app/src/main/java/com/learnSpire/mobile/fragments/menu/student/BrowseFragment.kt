package com.learnSpire.mobile.fragments.menu.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.adapters.AvailableCoursesAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.FragmentBrowseBinding
import com.learnSpire.mobile.models.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private val lmsApiService = LmsApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var availableCoursesList = ArrayList<Course>()

        // call the get enrolled courses api
        var getAvailableCoursesResponse = lmsApiService.getAvailableCourses()

        getAvailableCoursesResponse.enqueue(object: Callback<List<Course>> {
            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        availableCoursesList = it as ArrayList<Course>

                        // set recycler view
                        val recyclerView = binding.recyclerviewAvailableCourses
                        recyclerView.layoutManager = LinearLayoutManager(activity)

                        // set adapter
                        val adapter = AvailableCoursesAdapter(availableCoursesList)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                println("Get Available Courses failed")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}