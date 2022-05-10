package com.learnSpire.mobile.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.adapters.EnrolledCoursesAdapter
import com.learnSpire.mobile.adapters.NotificationsAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.FragmentBrowseBinding
import com.learnSpire.mobile.databinding.FragmentNotificationsBinding
import com.learnSpire.mobile.models.Course
import com.learnSpire.mobile.models.GetNotificationsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val lmsApiService = LmsApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var notificationsList = ArrayList<GetNotificationsResponse>()

        // call the get enrolled courses api
        var getNotificationsResponse = lmsApiService.getAllNotifications()

        getNotificationsResponse.enqueue(object: Callback<List<GetNotificationsResponse>> {
            override fun onResponse(call: Call<List<GetNotificationsResponse>>, response: Response<List<GetNotificationsResponse>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        notificationsList = it as ArrayList<GetNotificationsResponse>

                        // reverse the list
                        if (notificationsList.size != 0) {
                            notificationsList = notificationsList.reversed() as ArrayList<GetNotificationsResponse>
                        }

                        // set recycler view
                        val recyclerView = binding.recyclerviewNotifications
                        recyclerView.layoutManager = LinearLayoutManager(activity)

                        // set adapter
                        val adapter = NotificationsAdapter(notificationsList)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<GetNotificationsResponse>>, t: Throwable) {
                println("Get All Notifications failed")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}