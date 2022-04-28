package com.example.map.checkins

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.map.R
import com.example.map.Repositories
import com.example.map.databinding.FragmentCreateCheckInBinding
import com.example.map.utils.viewModelCreator
import com.example.mapapplication.model.checkin.CheckIn
import com.google.android.gms.maps.model.LatLng
import java.text.SimpleDateFormat
import java.util.*

class CreateCheckInFragment : Fragment(R.layout.fragment_create_check_in) {

    private lateinit var binding: FragmentCreateCheckInBinding
    private val viewModel: CheckInListViewModel by viewModelCreator {
        CheckInListViewModel(Repositories.checkInsRepository)
    }
    private lateinit var userLocation: LatLng

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCreateCheckInBinding.bind(view)
        prepareInfo()

        binding.cancelButton.setOnClickListener{
            findNavController().popBackStack()
        }
        binding.postButton.setOnClickListener{
            onPostClick()
        }
    }

    private fun prepareInfo(){
        userLocation = getUserLocate()
        binding.dateTextView.text = getTime()
        binding.locationTextView.text = getUserLocationName()
    }

    private fun onPostClick(){
        val date = getTime()
        val locate = binding.locationTextView.text.toString()
        val description = binding.descriptionEditText.text.toString()
        val checkIn = CheckIn(viewModel.generateId(), locate, date, description, userLocation)
        viewModel.createCheckIn(checkIn)
        findNavController().popBackStack()
    }

    private fun getUserLocate(): LatLng {
        // todo
        return LatLng(0.0, 0.0)
    }

    private fun getUserLocationName(): String{
        // todo
        return ""
    }

    private fun getTime(): String {
        val time = Calendar.getInstance().time
        val format = SimpleDateFormat("MMM dd HH:mm")
        return format.format(time)
    }


}