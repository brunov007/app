package com.bruno.teste.view.activities

import android.os.Bundle
import com.bruno.teste.R
import com.bruno.teste.core.properties.ApplicationProperties
import com.bruno.teste.core.ui.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        ApplicationProperties.init(this)
    }

}