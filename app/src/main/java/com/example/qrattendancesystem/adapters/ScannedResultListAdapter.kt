package com.example.qrattendancesystem.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.qrattendancesystem.R
import com.example.qrattendancesystem.db.models.QrResult
import kotlinx.android.synthetic.main.single_qr_result_item.view.*

class ScannedResultListAdapter(
    var context: Context,
    var listOfScannedResults: MutableList<QrResult>
) : RecyclerView.Adapter<ScannedResultListAdapter.ScannedResultListViewHolder>() {
    inner class ScannedResultListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(qrResult: QrResult, position: Int) {
            view.result.text = "Marked Present for ${qrResult.class_name}"
            view.tvTime.text = qrResult.class_date_time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScannedResultListViewHolder {
        return ScannedResultListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.single_qr_result_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfScannedResults.size
    }

    override fun onBindViewHolder(holder: ScannedResultListViewHolder, position: Int) {
        holder.bind(listOfScannedResults[position], position)
    }
}