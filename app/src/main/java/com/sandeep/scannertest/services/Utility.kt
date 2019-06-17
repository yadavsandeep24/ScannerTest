package com.sandeep.scannertest.services

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import com.sandeep.scannertest.R


@SuppressLint("StaticFieldLeak")
object Utility {
    var mDialog: Dialog? = null;
    private var mProgresDetailMsg: TextView? = null
    fun getRectangleBorder(solidColor: Int, radius: FloatArray, strokeWidth: Int, strokeColor: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(solidColor)
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadii = radius
        gradientDrawable.setStroke(strokeWidth, strokeColor)
        return gradientDrawable
    }

    fun setSelectorRoundedCorner(
        context: Context, v: View, Stroke: Int, PrimarySolidColor: Int,
        PressedSolidColor: Int, PrimaryBorderColor: Int, PressedBOrderColor: Int,
        radius: Int
    ) {
        val states = StateListDrawable()

        states.addState(
            intArrayOf(android.R.attr.state_pressed), getRectangleBorder(
                context.resources
                    .getColor(PressedSolidColor),
                floatArrayOf(
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat()
                ),
                Stroke,
                context.resources.getColor(PressedBOrderColor)
            )
        )
        states.addState(
            intArrayOf(), getRectangleBorder(
                context.resources.getColor(
                    PrimarySolidColor
                ),
                floatArrayOf(
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat()
                ),
                Stroke,
                context.resources.getColor(PrimaryBorderColor)
            )
        )
        v.setBackgroundDrawable(states)

    }

    fun showToast(context: Context, msg: String, toastType: Int, gravity: Int) {
        Toast.makeText(context, msg, toastType).show()
    }

    fun showProgressDialog(message: String, context: Context) {
        if (mDialog == null) {
            val factory = LayoutInflater.from(context)
            val rootView: View
            rootView = factory.inflate(R.layout.dialogfragment_mobile, null, false)
            mProgresDetailMsg = rootView.findViewById(R.id.tvmessage)
            mProgresDetailMsg!!.setText(message)
            mDialog = Dialog(context)
            mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog!!.setContentView(rootView)
            mDialog!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mDialog!!.setCancelable(false)
            if (!mDialog!!.isShowing()) {
                mDialog!!.show()
            }
        }
    }

    fun dismissDialog() {
        try {
            if (mDialog != null && mDialog!!.isShowing()) {
                mDialog!!.dismiss()
            }
            mDialog = null
            mProgresDetailMsg = null
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun isNetworkConnectionAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = cm.activeNetworkInfo ?: return false
        val network = info.state
        return network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING
    }

    fun getSubHeaderColor(context: Context,color: String):Int {
        if(color.equals("red",ignoreCase = true)) {
            return context.resources.getColor(R.color.red)
        }else  if(color.equals("green",ignoreCase = true)) {
            return context.resources.getColor(R.color.green)
        }
        return context.resources.getColor(R.color.black)
    }
}