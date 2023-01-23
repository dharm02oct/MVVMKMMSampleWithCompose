package com.sample.mvvmkmmsample.android.ui.editcontact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sample.mvvmkmmsample.android.R
import com.sample.mvvmkmmsample.android.ui.Validators
import com.sample.mvvmkmmsample.android.vo.State
import org.koin.androidx.viewmodel.ext.android.viewModel


class EditContactFragment : Fragment() {
    private val viewModel: EditContactViewModel by viewModel()
    //private val params by navArgs<EditContactFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.savedEvent.observe(viewLifecycleOwner) {
            val data = it.data
            if (data is State.Success) {
               // findNavController().navigate(EditContactFragmentDirections.viewContact(data.value))
            }
        }

        return ComposeView(requireContext()).apply { 
            setContent { 
                editContactScreen(viewModel = (androidx.lifecycle.viewmodel.compose.viewModel()))
            }
        }
    }

    private fun getError(error: Validators.ValidationError?, @StringRes patternErrorRestId: Int): String? {
        return when (error) {
            Validators.ValidationError.REQUIRED -> resources.getString(R.string.required_error)
            Validators.ValidationError.PATTERN -> resources.getString(patternErrorRestId)
            else -> null
        }
    }
}
