package com.example.boardofmessageapp_kt2.open.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import com.example.boardofmessageapp_kt2.R
import com.example.boardofmessageapp_kt2.base.BaseActivity
import com.example.boardofmessageapp_kt2.closed.PrivateActivity
import com.example.boardofmessageapp_kt2.closed.preferences.PreferencesActivity
import dagger.hilt.android.AndroidEntryPoint

@Composable
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupToolbar()
    }

    private fun setupToolbar(): Toolbar? {
        findViewById<Toolbar>(R.id.toolbar)?.let {
            setTitle(null)
            setSupportActionBar(it)
            return it
        } ?: return null
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun getNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        return navHostFragment.navController
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.item_preferences) {
            Intent(this, PreferencesActivity::class.java).also {
                startActivity(it)
            }

            return super.onOptionsItemSelected(item)
        }

        val navController = getNavController()
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

    private fun goToPrivateActivity() {
        startActivity(Intent(this, PrivateActivity::class.java))
    }
}
