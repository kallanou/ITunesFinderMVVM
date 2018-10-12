package ca.kallanou.itunesfinder.ui.features.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ca.kallanou.itunesfinder.R
import ca.kallanou.itunesfinder.ui.features.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.fragment_search_title)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        selectDrawerMenu(R.id.nav_search)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)

        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_search -> navigateToSearch()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun selectDrawerMenu(resId: Int) {
        val item = nav_view.menu.findItem(resId)
        nav_view.setCheckedItem(resId)
        onNavigationItemSelected(item)
    }

    private fun navigateToSearch() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment(), "search")
                .commitNow()
        toolbar.title = getString(R.string.fragment_search_title)
    }
}
