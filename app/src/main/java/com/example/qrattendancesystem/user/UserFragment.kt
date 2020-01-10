package com.example.qrattendancesystem.user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.qrattendancesystem.R

class UserFragment : Fragment() {

    companion object {
        fun newInstance() : UserFragment{
            return UserFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val flag = false
        if (flag) {
            // if user is not signed up
            return inflater.inflate(R.layout.user_signup_fragment, container, false)
        } else {
            return inflater.inflate(R.layout.fragment_user, container, false)
        }
    }

}
