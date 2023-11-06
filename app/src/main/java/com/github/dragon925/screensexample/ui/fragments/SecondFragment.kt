package com.github.dragon925.screensexample.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.dragon925.screensexample.databinding.FragmentSecondBinding
import com.github.dragon925.screensexample.domain.item.EventItem
import com.github.dragon925.screensexample.ui.adapters.EventListAdapter

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        val eventAdapter = EventListAdapter()
        binding.rvList.adapter = eventAdapter

        eventAdapter.submitList(
            List<EventItem>(10) {
                EventItem(it, "18:00", "#$it")
            }
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}