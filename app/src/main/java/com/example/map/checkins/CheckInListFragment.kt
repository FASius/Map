package com.example.map.checkins

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.map.R
import com.example.map.Repositories
import com.example.map.databinding.FragmentCheckInListBinding
import com.example.map.utils.viewModelCreator
import com.example.mapapplication.model.checkin.CheckIn

class CheckInListFragment : Fragment(R.layout.fragment_check_in_list) {

    private lateinit var binding: FragmentCheckInListBinding
    private lateinit var adapter: CheckInAdapter
    private val viewModel: CheckInListViewModel by viewModelCreator {
        CheckInListViewModel(Repositories.checkInsRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckInListBinding.bind(view)

        adapter = CheckInAdapter(object : CheckInActionListener{
            override fun onCheckInEdit(checkIn: CheckIn) {
                val args = CheckInEditFragmentArgs(checkIn.id)
                findNavController().navigate(R.id.action_profileFragment_to_checkInEditFragment, args.toBundle())
            }

            override fun onCheckInOpen(checkIn: CheckIn) {
                val args = CheckInDetailsFragmentArgs(checkIn.id)
                findNavController().navigate(R.id.action_profileFragment_to_checkInDetailsFragment, args.toBundle())
            }

            override fun onCheckInDelete(checkIn: CheckIn) {
                viewModel.deleteCheckIn(checkIn)
            }
        })

        binding.newCheckInButton.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_createCheckInFragment)
        }

        val layoutManager = LinearLayoutManager(requireContext())
        binding.checkinsRecyclerView.layoutManager = layoutManager
        binding.checkinsRecyclerView.adapter = adapter

        viewModel.checkIns.observe(viewLifecycleOwner) {
            adapter.checkInsList = it
        }
    }

}