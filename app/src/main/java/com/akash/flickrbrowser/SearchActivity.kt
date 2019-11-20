package com.akash.flickrbrowser

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.widget.SearchView

private const val TAG = "SearchActivity"
private const val SEARCH_QUERY ="SEARCH_QUERY"

class SearchActivity : BaseActivity() {

    private var searchView: android.widget.SearchView? = null
    private val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        activateToolbar(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.app_bar_search).actionView as android.widget.SearchView
        val searchableInfo = searchManager.getSearchableInfo(componentName)
        searchView?.setSearchableInfo(searchableInfo)
        searchView?.isIconified = false

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                Toast.makeText(context, query, Toast.LENGTH_SHORT).show()

                val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
                sharedPreferences.edit().putString(SEARCH_QUERY, query).apply()
                searchView?.clearFocus()

                finish()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                Toast.makeText(context, newText, Toast.LENGTH_SHORT).show()
                return true
            }
        })
        return true
//        return super.onCreateOptionsMenu(menu)

    }
}
