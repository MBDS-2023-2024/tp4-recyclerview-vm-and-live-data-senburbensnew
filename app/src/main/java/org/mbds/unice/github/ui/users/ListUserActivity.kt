package org.mbds.unice.github.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    // TODO : Utiliser viewBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton

    // By lazy permet de faire du chargement parresseux,
    // L'adapteur sera crée au premier appel
    private val adapter: UserListAdapter by lazy {
        UserListAdapter(this)
    }

    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_user)
        configureFab()
        configureRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem: MenuItem? = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView

        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            // @SuppressLint("NotifyDataSetChanged")
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.updateList(viewModel.searchUserByUsername(newText))
                adapter.notifyDataSetChanged()
                return true
            }
        })

        return true
    }

    // Handle menu item clicks
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_alpha_asc -> {
                sortUsersByName(ascending = true)
                true
            }
            R.id.sort_alpha_desc -> {
                sortUsersByName(ascending = false)
                true
            }
            R.id.sort_date_asc -> {
                sortUsersByDate(ascending = true)
                true
            }
            R.id.sort_date_desc -> {
                sortUsersByDate(ascending = false)
                true
            }
            R.id.sort_active -> {
                filterUsersByStatus(active = true)
                true
            }
            R.id.sort_inactive -> {
                filterUsersByStatus(active = false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun sortUsersByName(ascending: Boolean) {
        viewModel.sortUsersByName(ascending)
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun sortUsersByDate(ascending: Boolean) {
        viewModel.sortUsersByDate(ascending)
        adapter.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun filterUsersByStatus(active: Boolean) {
        viewModel.filterUsersByStatus(active)
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.users.observe(this) {
            adapter.updateList(it)
        }
    }
    private fun configureRecyclerView() {
        recyclerView = findViewById(R.id.activity_list_user_rv)
        recyclerView.adapter = adapter
    }

    private fun configureFab() {
        fab = findViewById(R.id.activity_list_user_fab)
        fab.setOnClickListener {
            viewModel.generateRandomUser()
        }
    }

    override fun onClickDelete(user: User) {
        Log.d("ListUserActivity", "Attempting to delete user: ${user.login}")
        val action = if (user.active) "delete" else "restore"

        AlertDialog.Builder(this)
            .setTitle("Delete User")
            .setMessage("Are you sure you want to $action ${user.login}?")
            .setPositiveButton("Yes") { dialog, _ ->
                deleteUser(user)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(R.drawable.ic_delete_user_dialog_24dp)
            .show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteUser(user: User) {
        viewModel.deleteUser(user)
        adapter.notifyDataSetChanged()
    }
}