package com.learnSpire.mobile.fragments.menu.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.learnSpire.mobile.adapters.MarksAdapter
import com.learnSpire.mobile.api.LmsApiService
import com.learnSpire.mobile.databinding.FragmentMarksBinding
import com.learnSpire.mobile.models.MarksResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarksFragment : Fragment() {

    private var _binding: FragmentMarksBinding? = null

    private val binding get() = _binding!!

    private val lmsApiService = LmsApiService.create()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMarksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var marksList = ArrayList<MarksResponse>()

        // call the get all marks of a student api
        var getAllMarksResponse = lmsApiService.getAllMarks()

        getAllMarksResponse.enqueue(object: Callback<List<MarksResponse>> {
            override fun onResponse(call: Call<List<MarksResponse>>, response: Response<List<MarksResponse>>) {
                val body = response.body()

                body.let {
                    if (it != null) {
                        marksList = it as ArrayList<MarksResponse>
                        val marksList2 = marksList.toList()

                        // set recycler view
                        val recyclerView = binding.recyclerviewMarks
                        recyclerView.layoutManager = LinearLayoutManager(activity)

                        // set adapter
                        val adapter = activity?.let { it1 -> MarksAdapter(it1,marksList2) }
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<MarksResponse>>, t: Throwable) {
                println("Get all marks of a student failed")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}