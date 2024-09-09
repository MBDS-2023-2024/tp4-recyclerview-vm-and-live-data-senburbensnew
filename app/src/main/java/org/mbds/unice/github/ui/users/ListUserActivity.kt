package org.mbds.unice.github.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ActivityListUserBinding
import java.util.Locale

class ListUserActivity : AppCompatActivity(), UserListAdapter.Listener {
    private lateinit var binding : ActivityListUserBinding

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
        binding = ActivityListUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        binding.activityListUserRv.adapter = adapter
    }

    private fun configureFab() {
        binding.activityListUserFab.setOnClickListener {
            viewModel.generateRandomUser()
        }
    }

    override fun onClickDelete(user: User) {
        Log.d("ListUserActivity", "Tentative de suppression de l'utilisateur : ${user.login}")
        val action = if (user.active) "désactiver" else "restaurer"

        AlertDialog.Builder(this)
            .setTitle("${action.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }} utilisateur")
            .setMessage("Voulez-vous $action ${user.login}?")
            .setPositiveButton("Oui") { dialog, _ ->
                deleteUser(user)
                dialog.dismiss()
            }
            .setNegativeButton("Non") { dialog, _ ->
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