package org.mbds.unice.github.ui.users

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import java.text.SimpleDateFormat
import java.util.Locale

//TODO : Use viewBinding instead of findviewbyid
class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var avatar: ImageView = itemView.findViewById(R.id.item_list_user_avatar)
    private val username: TextView = itemView.findViewById(R.id.item_list_user_username)
    private val creationDate: TextView = itemView.findViewById(R.id.item_list_user_creation_date)
    private val deleteButton: ImageButton = itemView.findViewById(R.id.item_list_user_delete_button)
    private val itemListUserConstraintLayout: ConstraintLayout = itemView.findViewById(R.id.item_list_user_constraint_layout)

    fun bind(user: User, callback: UserListAdapter.Listener) {
        avatar.load(user.avatarUrl) {
            placeholder(R.drawable.avatar_default)
            error(R.drawable.avatar_error)
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(user.creationDate)
        user.login.also { username.text = it }
        formattedDate.also { creationDate.text = it }
        deleteButton.setOnClickListener{
            callback.onClickDelete(user)
        }
        itemListUserConstraintLayout.setBackgroundColor(
            if (user.active) Color.WHITE else Color.RED
        )
        if (user.active) {
            deleteButton.setImageResource(R.drawable.ic_delete_black_24dp)
        } else {
            deleteButton.setImageResource(R.drawable.ic_restore_black_24dp)
        }
    }
}