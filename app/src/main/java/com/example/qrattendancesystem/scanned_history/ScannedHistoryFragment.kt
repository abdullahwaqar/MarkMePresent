package com.example.qrattendancesystem.scanned_history


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.adapters.ScannedResultListAdapter
import com.example.qrattendancesystem.db.AppDatabase
import com.example.qrattendancesystem.db.models.QrResult
import kotlinx.android.synthetic.main.fragment_scanned_history.view.*

class ScannedHistoryFragment : Fragment() {

    enum class ResultListType {
        ALL_RESULT
    }

    companion object {

        private const val ARGUMENT_RESULT_TYPE = "ArgumentResultListType"

        fun newInstance(screenType: ResultListType): ScannedHistoryFragment {
            val bundle = Bundle()
            bundle.putSerializable(ARGUMENT_RESULT_TYPE, screenType)
            val fragment = ScannedHistoryFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var resultType: ResultListType
    private lateinit var renderView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        renderView = inflater.inflate(R.layout.fragment_scanned_history, container, false)
        init()
        setSwipeRefreshLayout()
        return renderView
    }

    private fun setSwipeRefreshLayout() {
        renderView.swipeRefresh.setOnRefreshListener {
            renderView.swipeRefresh.isRefreshing = false
            showAllResults()
        }
    }

    private fun init() {
        showAllResults()
    }

    private fun showAllResults() {
        var listOfResults = AppDatabase.getAppDatabase(context!!)?.getQrResultDAO()?.getQrResults()

        if (listOfResults != null) {
            if (listOfResults.isEmpty()) {
                showEmptyState()
            } else {
                initRecyclerView(listOfResults)
            }
        }
    }

    private fun initRecyclerView(listOfResults: List<QrResult>) {
        renderView.scannedHistoryRecyclerView.layoutManager = LinearLayoutManager(context)
        renderView.scannedHistoryRecyclerView.adapter = ScannedResultListAdapter(
            context!!,
            listOfScannedResults = listOfResults.toMutableList()
        )
    }

    private fun showEmptyState() {
        renderView.scannedHistoryRecyclerView.isGone
        renderView.noResultFound.isVisible
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleArguments()
    }

    private fun handleArguments() {
        resultType = arguments?.getSerializable(ARGUMENT_RESULT_TYPE) as ResultListType
    }
}
