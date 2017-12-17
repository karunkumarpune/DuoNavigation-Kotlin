package com.happynewyear

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView

import com.happynewyear.chrisymas.ChristmasActivity

import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle


class MainActivity : AppCompatActivity() {
    private var mViewHolder: ViewHolder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewHolder = ViewHolder()
        handleDrawer()

    }


    private fun handleDrawer() {
        val duoDrawerToggle = DuoDrawerToggle(this,
                mViewHolder!!.mDuoDrawerLayout,
                mViewHolder!!.mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)


        mViewHolder!!.btn_christmas.setOnClickListener { v -> startActivity(Intent(this@MainActivity, ChristmasActivity::class.java)) }


        mViewHolder!!.mDuoDrawerLayout.setDrawerListener(duoDrawerToggle)
        duoDrawerToggle.syncState()

        mViewHolder!!.mToolbar.setNavigationIcon(R.drawable.ic_lines_menu)
        mViewHolder!!.mToolbar.hideOverflowMenu();
        mViewHolder!!.mToolbar.showContextMenu();


    }


    //  mViewHolder.mDuoDrawerLayout.closeDrawer();


    private inner class ViewHolder internal constructor() {
        val mDuoDrawerLayout: DuoDrawerLayout
        val mToolbar: Toolbar
        val btn_christmas: TextView

        init {
            mDuoDrawerLayout = findViewById(R.id.drawer)
            mToolbar = findViewById(R.id.toolbar)
            btn_christmas = findViewById(R.id.btn_christmas)
        }
    }


    override fun onBackPressed() {

        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("")
                .setMessage(resources.getString(R.string.txt_close_app))
                .setPositiveButton(resources.getString(R.string.txt_yes)) { dialog, which -> finish() }
                .setNegativeButton(resources.getString(R.string.txt_No), null)
                .show()
    }


    override fun onStop() {
        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
        super.onStop()
    }

    override fun onResume() {
        super.onResume()

        if (mViewHolder!!.mDuoDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mViewHolder!!.mDuoDrawerLayout.closeDrawer(GravityCompat.START)
        }
    }
}
