package com.siddydevelops.flicker_kots

import android.os.Bundle
import androidx.navigation.ui.AppBarConfiguration
import com.siddydevelops.flicker_kots.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        activateToolbar(true)

    }
}