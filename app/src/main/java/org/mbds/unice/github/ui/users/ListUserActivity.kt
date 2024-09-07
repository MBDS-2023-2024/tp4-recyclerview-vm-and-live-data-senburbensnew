package org.mbds.unice.github.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
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
            .show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteUser(user: User) {
        viewModel.deleteUser(user)
        adapter.notifyDataSetChanged()
    }
}