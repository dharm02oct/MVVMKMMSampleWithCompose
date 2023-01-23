package com.sample.mvvmkmmsample.android

import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController


class MainActivity : AppCompatActivity() {
    var listener: Listener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.action_toolbar))
        listener =  Listener()
        this.registerReceiver(listener,  IntentFilter("android.intent.action.SAVE_CONTACT"));
        setupActionBarWithNavController(findNavController(R.id.container))
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.container).navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
            listener?.let {
                unregisterReceiver(listener)
            }

    }
}
