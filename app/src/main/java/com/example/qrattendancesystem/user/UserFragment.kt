package com.example.qrattendancesystem.user


import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.AppDatabase
import com.example.qrattendancesystem.db.models.User
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private var user: User? = null

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = AppDatabase.getAppDatabase(context!!)?.getUserDAO()?.getUser()
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        userIdView.text = user?._id
        nameView.text = user?.name
        rollidView.text = user?.roll_id
    }
}
