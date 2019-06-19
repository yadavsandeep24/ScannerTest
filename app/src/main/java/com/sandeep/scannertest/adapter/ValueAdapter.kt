package com.sandeep.scannertest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.scannertest.R


class ValueAdapter : RecyclerView.Adapter<ValueAdapter.ViewHolder>() {
    internal var mValueList: List<Double>? = null

    fun setData(valueList: List<Double>) {
        this.mValueList = valueList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.value_adapter_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mValueList != null) {
            holder.mTvHeader.setText(mValueList!![position].toString())
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mTvHeader: TextView
        init {
            mTvHeader = itemView.findViewById(R.id.tv_dynamic_content)
        }

    }

    override fun getItemCount(): Int {
        return if (mValueList != null) {
            mValueList!!.size
        } else {
            0
        }
    }

}
