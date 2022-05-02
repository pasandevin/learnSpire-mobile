package com.learnSpire.mobile.fragments.menu.marks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.learnSpire.mobile.databinding.FragmentMarksBinding

class MarksFragment : Fragment() {

    private var _binding: FragmentMarksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val marksViewModel =
            ViewModelProvider(this).get(MarksViewModel::class.java)

        _binding = FragmentMarksBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textMarks
        marksViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}