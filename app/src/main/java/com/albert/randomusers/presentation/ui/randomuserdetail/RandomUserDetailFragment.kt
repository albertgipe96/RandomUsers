package com.albert.randomusers.presentation.ui.randomuserdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.albert.randomusers.R
import com.albert.randomusers.presentation.models.RandomUserUIModel
import com.albert.randomusers.util.Constants.ARG_RANDOM_USER
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomUserDetailFragment : Fragment() {

    companion object {
        fun newInstance(randomUser: RandomUserUIModel): RandomUserDetailFragment {
            val fragment = RandomUserDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_RANDOM_USER, randomUser)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_user_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val user = arguments?.get(ARG_RANDOM_USER) as RandomUserUIModel

        val ivUserImage: ImageView = view?.findViewById(R.id.iv_random_user_image_details)
        val tvUserNameDetails: TextView = view?.findViewById(R.id.tv_random_user_name_details)
        val tvUserEmailDetails: TextView = view?.findViewById(R.id.tv_random_user_email_details)
        val tvUserPhoneDetails: TextView = view?.findViewById(R.id.tv_random_user_phone_details)
        val tvUserGenderDetails: TextView = view?.findViewById(R.id.tv_random_user_gender_details)
        val tvUserLocationDetails: TextView = view?.findViewById(R.id.tv_random_user_location_details)
        val tvUserRegisteredDetails: TextView = view?.findViewById(R.id.tv_random_user_registered_details)

        Picasso.get().load(user.pictureThumbnail).into(ivUserImage)
        tvUserNameDetails.text = user.name?.first + " " + user.name?.last
        tvUserEmailDetails.text = user.email
        tvUserPhoneDetails.text = user.phone
        tvUserGenderDetails.text = user.gender
        tvUserLocationDetails.text = user.location?.street + ", " + user.location?.city + ", " + user.location?.state
        tvUserRegisteredDetails.text = user.registeredDate
    }
}