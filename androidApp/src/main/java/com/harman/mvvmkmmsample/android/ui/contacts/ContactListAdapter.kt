package com.harman.mvvmkmmsample.android.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.harman.mvvmkmmsample.android.databinding.ContactListItemBinding
import com.harman.mvvmkmmsample.domain.model.Contact

class ContactListAdapter(private val listener: ContactListener) :
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    var contactsList:List<Contact>  = mutableListOf()

    class ViewHolder(
        private var binding: ContactListItemBinding,
        private val listener: ContactListener
    ) : RecyclerView.ViewHolder(binding.root) {
        var contact: Contact? = null

        init {
            binding.phoneImageView.setOnClickListener {
                contact?.let { listener.call(it.phone) }
            }
            binding.root.setOnClickListener {
                contact?.let {
                    listener.open(it.id) }
            }
        }

        fun bind(contact: Contact?) {
            this.contact = contact
            binding.contact = contact
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ContactListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object DiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact) = oldItem.id === newItem.id

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
    }

    override fun getItemCount(): Int {
       return contactsList.size ?: 0
    }

    fun setList(it:List<Contact>) {
        contactsList = it
       notifyDataSetChanged()
    }

   private fun getItem(pos:Int) = contactsList[pos]
}