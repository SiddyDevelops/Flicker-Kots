package com.siddydevelops.flicker_kots

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.navigation.ui.AppBarConfiguration
import com.siddydevelops.flicker_kots.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySearchBinding

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activateToolbar(true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

}