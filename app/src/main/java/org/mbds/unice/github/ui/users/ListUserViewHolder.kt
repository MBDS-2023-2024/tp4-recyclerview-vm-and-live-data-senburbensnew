package org.mbds.unice.github.ui.users

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.mbds.unice.github.R
import org.mbds.unice.github.data.model.User
import org.mbds.unice.github.databinding.ItemListUserBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ListUserViewHolder(private val binding : ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, callback: UserListAdapter.Listener) {
         binding.itemListUserAvatar.load(user.avatarUrl) {
            placeholder(R.drawable.avatar_default)
            error(R.drawable.avatar_error)
        }

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(user.creationDate)
        user.login.also { binding.itemListUserUsername.text = it }
        formattedDate.also { binding.itemListUserCreationDate.text = it }
        binding.itemListUserDeleteButton.setOnClickListener{
            callback.onClickDelete(user)
        }
        binding.itemListUserConstraintLayout.setBackgroundColor(
            if (user.active) Color.WHITE else Color.RED
        )
        if (user.active) {
            binding.itemListUserDeleteButton.setImageResource(R.drawable.ic_delete_black_24dp)
        } else {
            binding.itemListUserDeleteButton.setImageResource(R.drawable.ic_restore_black_24dp)
        }
    }
}