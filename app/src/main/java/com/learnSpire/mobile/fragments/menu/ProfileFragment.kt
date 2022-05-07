package com.learnSpire.mobile.fragments.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.learnSpire.mobile.R
import com.learnSpire.mobile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logOutButton.setOnClickListener {

            // Initialized Shared Preferences
            var sharedPreferences = requireContext()
                .getSharedPreferences("SharedPrefFile", Context.MODE_PRIVATE)

            // Clearing Shared Preferences
            sharedPreferences.edit().clear().commit()

            // navigate to the home login activity
            findNavController().navigate(R.id.action_ProfileFragment_to_LoginActivity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}