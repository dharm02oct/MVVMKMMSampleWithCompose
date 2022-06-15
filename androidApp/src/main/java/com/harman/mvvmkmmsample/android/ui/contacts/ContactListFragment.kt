package com.harman.mvvmkmmsample.android.ui.contacts

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.iterator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.harman.mvvmkmmsample.android.R
import org.koin.androidx.viewmodel.ext.android.viewModel


class ContactListFragment : Fragment() {
    private val viewModel: ContactListViewModel by viewModel()

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewModel.getContacts()
        return ComposeView(requireContext()).apply {
            setContent {
                contactListScreen(viewModel){
                    findNavController().navigate(
                        ContactListFragmentDirections.viewContact(it.id ?: -1)
                    )
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onStop() {
        super.onStop()

    }

    private fun call(phone: String) {
        val number = Uri.parse("tel:$phone")
        val callIntent = Intent(Intent.ACTION_DIAL, number)
        context?.startActivity(callIntent)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_menu, menu)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            menu.iterator().forEach {
                it.isEnabled = !isLoading
            }
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.open_about -> findNavController().navigate(ContactListFragmentDirections.openAbout())
            R.id.add_contact -> findNavController().navigate(ContactListFragmentDirections.editContact())
            R.id.github_users -> findNavController().navigate(ContactListFragmentDirections.viewGithubUsers())
        }
        return true
    }

    companion object {
        private const val READ_REQUEST_CODE = 1
        private const val WRITE_REQUEST_CODE = 2
        private const val MIME_TYPE = "application/json"
        private const val EXPORT_FILE_NAME = "contacts.json"
    }
}
