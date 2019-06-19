package com.sandeep.scannertest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.sandeep.scannertest.R
import com.sandeep.scannertest.database.valueobjects.ScannerVo
import com.sandeep.scannertest.listners.ScannerMainListItemClickListner
import com.sandeep.scannertest.services.Utility


class ScannnerMainListAdapter(private val mContext: Context, private val listener: ScannerMainListItemClickListner) :
    RecyclerView.Adapter<ScannnerMainListAdapter.ViewHolder>() {
    internal var mScannerList: ArrayList<ScannerVo>? = null

    fun setData(ScannerList: ArrayList<ScannerVo>) {
        if (mScannerList != null) {
            mScannerList!!.clear()
            mScannerList!!.addAll(ScannerList)
        } else {
            this.mScannerList = ScannerList
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.scanner_main_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mScannerList != null) {
            holder.mTvHeader.setText(mScannerList!![position].name)
            holder.mTvSubHeader.setText(mScannerList!![position].tag)
            holder.mTvSubHeader.setTextColor(Utility.getSubHeaderColor(mContext, mScannerList!![position].color))
            holder.mContainerView.setOnClickListener(View.OnClickListener {
                listener.onListItemClick(mScannerList!![position].scannerID)
            })

        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var mTvHeader: TextView
        internal var mTvSubHeader: TextView
        internal var mContainerView: CardView

        init {
            mTvHeader = itemView.findViewById(R.id.tv_header)
            mTvSubHeader = itemView.findViewById(R.id.tv_subheader)
            mContainerView = itemView.findViewById(R.id.main_container)
        }

    }

    override fun getItemCount(): Int {
        return if (mScannerList != null) {
            mScannerList!!.size
        } else {
            0
        }
    }

}
