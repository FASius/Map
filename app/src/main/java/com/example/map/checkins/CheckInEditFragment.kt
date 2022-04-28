package com.example.map.checkins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.map.R
import com.example.map.Repositories
import com.example.map.databinding.FragmentCheckInEditBinding
import com.example.map.utils.viewModelCreator
import com.example.mapapplication.model.checkin.CheckIn

class CheckInEditFragment : Fragment(R.layout.fragment_check_in_edit) {

    private lateinit var binding: FragmentCheckInEditBinding
    private val viewModel: CheckInListViewModel by viewModelCreator {
        CheckInListViewModel(Repositories.checkInsRepository)
    }

    private lateinit var checkIn: CheckIn

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCheckInEditBinding.bind(view)
        prepareInfo()
        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.saveButton.setOnClickListener {
            onSaveButton()
        }
    }

    private fun prepareInfo(){
        val bundle = arguments ?: return
        val id = CheckInEditFragmentArgs.fromBundle(bundle).id
        checkIn = viewModel.getCheckInById(id)
        binding.dateTextView.text = checkIn.date
        binding.descriptionEditText.setText(checkIn.description)
        binding.locationTextView.text = checkIn.locate
    }

    private fun onSaveButton(){
        val date = binding.dateTextView.text.toString()
        val locate = binding.locationTextView.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val newCheckIn = CheckIn(checkIn.id, locate, date, description, checkIn.location)
        viewModel.editCheckIn(newCheckIn)

        findNavController().popBackStack()
    }
}