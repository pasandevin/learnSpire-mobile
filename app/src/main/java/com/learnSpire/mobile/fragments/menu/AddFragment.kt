package com.learnSpire.mobile.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.learnSpire.mobile.R
import com.learnSpire.mobile.databinding.FragmentAddBinding
import com.learnSpire.mobile.databinding.FragmentBrowseBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddCourse.setOnClickListener {
            //navigate to add course activity
            it.findNavController().navigate(R.id.action_addFragment_to_addCourseActivity)
        }

        binding.buttonAddContent.setOnClickListener {
            //navigate to add content activity
            it.findNavController().navigate(R.id.action_addFragment_to_addContentActivity)
        }

        binding.buttonAddMarks.setOnClickListener {
            //navigate to add marks activity
            it.findNavController().navigate(R.id.action_addFragment_to_addMarksActivity)
        }

        binding.buttonAddAnnouncement.setOnClickListener {
            //navigate to add announcement activity
            it.findNavController().navigate(R.id.action_addFragment_to_addAnnouncementActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}