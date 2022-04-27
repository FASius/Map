package com.example.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.map.databinding.ActivityMainBinding
import com.example.map.model.account.Account

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Repositories.initAppContext(applicationContext)
        Repositories.initCurrentAccount()
        val id = Repositories.currentAccount.getCurrentAccountId()
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        navController = getNavController()
        val isSignedIn = userIsSignedIn(id)
        prepareNavController(isSignedIn, navController)
        navController.navigate(navController.graph.startDestinationId)
    }

    private fun userIsSignedIn(id: Long): Boolean {
        return id != Account.NO_ACCOUNT
    }

    private fun getNavController(): NavController {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        return navHost.navController
    }

    private fun prepareNavController(isSignedIn: Boolean, navController: NavController){
        val graph = navController.navInflater.inflate(getMainNavigationGraphId())
        graph.setStartDestination(
            if (isSignedIn) {
                getTabsDestination()
            } else {
                getSignInDestination()
            }
        )
        navController.graph = graph
    }

    private fun getMainNavigationGraphId(): Int = R.navigation.main_graph

    private fun getTabsDestination(): Int = R.id.tabsFragment

    private fun getSignInDestination(): Int = R.id.signInFragment

}
