package com.learnSpire.mobile.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.adapters.CourseContentAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.FragmentCourseContentBinding
import com.learnSpire.mobile.models.Content
import com.learnSpire.mobile.models.Course
import com.learnSpire.mobile.models.GetContentRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseContentFragment : Fragment() {

    private var _binding: FragmentCourseContentBinding? = null
    private val binding get() = _binding!!

    private val lmsApiService = LmsApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseContentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var courseContentList = ArrayList<Content>()

        //get bundled data
        var args = this.arguments
        val courseId = args?.get("courseId").toString()

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
                        recyclerView.layoutManager = LinearLayoutManager(activity)

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}