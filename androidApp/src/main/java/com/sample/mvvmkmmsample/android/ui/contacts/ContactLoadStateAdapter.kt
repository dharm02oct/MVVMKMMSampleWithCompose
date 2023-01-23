package com.sample.mvvmkmmsample.android.ui.contacts
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.mvvmkmmsample.android.databinding.ContactListFooterItemBinding


class ContactLoadStateAdapter : LoadStateAdapter<ContactLoadStateAdapter.ViewHolder>() {
    class ViewHolder(private var binding: ContactListFooterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMsg = loadState.error.localizedMessage
            }
            binding.isLoading = loadState is LoadState.Loading
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(ContactListFooterItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
}
