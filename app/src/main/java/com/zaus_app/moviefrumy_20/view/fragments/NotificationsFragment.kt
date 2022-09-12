package com.zaus_app.moviefrumy_20.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zaus_app.moviefrumy_20.databinding.FragmentNotificationsBinding
import com.zaus_app.moviefrumy_20.utils.AnimationHelper


class NotificationsFragment : Fragment() {
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(binding.notificationFragmentRoot, requireActivity(), 3)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}