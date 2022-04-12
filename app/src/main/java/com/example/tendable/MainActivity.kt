package com.example.tendable

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.core_ui.presentation.observeEvent
import com.example.core_ui.utilis.NavigationEvent
import com.example.tendable.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.app_fragmentContainerView) as NavHostFragment
        val navController: NavController = navHostFragment.navController

        viewModel.navigationEvent.observeEvent(this) {
            when (it) {
                is NavigationEvent.SetDestinationAndGraph -> {
                    val navGraph = navHostFragment.navController.navInflater.inflate(
                        it.setGraph
                    )
                    navGraph.startDestination = it.startDestination
                    navController.graph = navGraph
                }
            }
        }
        setContentView(binding.root)
    }
}