package ca.kallanou.itunesfinder.presentation.ui.features.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import ca.kallanou.itunesfinder.presentation.R
import ca.kallanou.itunesfinder.presentation.databinding.ActivityMainBinding
import ca.kallanou.itunesfinder.presentation.databinding.ActivityMainContentBinding
import ca.kallanou.itunesfinder.presentation.ui.features.search.SearchFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingContent: ActivityMainContentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(this.layoutInflater).also {
            this@MainActivity.setContentView(it.root)
            this.bindingContent = it.content
        }

        this.bindingContent.apply {
            this.toolbar.title = getString(R.string.fragment_search_title)
            this@MainActivity.setSupportActionBar(toolbar)

            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this@MainActivity.binding.drawerLayout,
                this.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            this@MainActivity.binding.drawerLayout.addDrawerListener(toggle)
            toggle.syncState()
        }

        this.binding.navView.setNavigationItemSelectedListener(this)
        this.selectDrawerMenu(R.id.nav_search)
    }

    override fun onBackPressed() {
        this.binding.apply {
            if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                this.drawerLayout.closeDrawer(GravityCompat.START)

            } else {
                super.onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_search -> navigateToSearch()
        }

        this.binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun selectDrawerMenu(resId: Int) {
        this.binding.apply {
            val item = this.navView.menu.findItem(resId)
            this.navView.setCheckedItem(resId)

            this@MainActivity.onNavigationItemSelected(item)
        }
    }

    private fun navigateToSearch() {
        this.supportFragmentManager.beginTransaction()
            .replace(R.id.container, SearchFragment(), "search")
            .commitNow()

        this.bindingContent.toolbar.title = this.getString(R.string.fragment_search_title)
    }
}
