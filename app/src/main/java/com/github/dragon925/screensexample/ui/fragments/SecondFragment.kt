package com.github.dragon925.screensexample.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.dragon925.screensexample.databinding.FragmentSecondBinding
import com.github.dragon925.screensexample.domain.item.EventItem
import com.github.dragon925.screensexample.ui.adapters.EventListAdapter
import com.github.dragon925.screensexample.ui.viewmodels.EventViewModel

class SecondFragment : Fragment() {

    private val viewModel: EventViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val eventAdapter = EventListAdapter()

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
        initViewModels()
    }

    private fun initViews() {
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.rvList.adapter = eventAdapter

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                viewModel.submitQuery(text?.toString())
            }

        })

        binding.tilEvent.setEndIconOnClickListener {
            val text = binding.etEvent.text?.toString()
            if (!text.isNullOrBlank()) {
                viewModel.addEvent(text)
                binding.etEvent.text = null
            }
        }
    }

    private fun initViewModels() {
        viewModel.events.observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}