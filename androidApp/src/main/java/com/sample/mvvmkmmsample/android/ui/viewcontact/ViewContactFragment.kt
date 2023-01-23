package com.sample.mvvmkmmsample.android.ui.viewcontact

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ViewContactFragment : Fragment() {

    val viewModel: ViewContactViewModel by viewModel()
    private val params by navArgs<ViewContactFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.getContact(params.contactId)
        deleteContact()
        return ComposeView(requireContext()).apply {
            setContent {
                contactView(viewModel = androidx.lifecycle.viewmodel.compose.viewModel(),{
                    findNavController().navigate(ViewContactFragmentDirections.editContact())
                 }){
                    call()
                }
            }

        }
    }

    private fun call() {
        viewModel.contact.value.phone?.let {
            var callingNumber = Uri.parse("tel:$it")
            val intent = Intent(Intent.ACTION_DIAL, callingNumber)
            this@ViewContactFragment.startActivity(intent)
        }
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    private fun deleteContact() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isDeleted.collectLatest {
                    if (it) {
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }


}