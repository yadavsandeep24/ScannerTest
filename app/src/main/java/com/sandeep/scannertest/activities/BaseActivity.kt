package com.sandeep.scannertest.activities

import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.sandeep.scannertest.R

open class BaseActivity : AppCompatActivity() {
    protected fun setToolbar(title: String) {
        val mToolbar = findViewById(R.id.toolbar) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(mToolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setTitle(title)
        mToolbar.title = title
        mToolbar.setTitleTextColor(Color.WHITE)
        mToolbar.setNavigationOnClickListener { onBackPressed() }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }

}