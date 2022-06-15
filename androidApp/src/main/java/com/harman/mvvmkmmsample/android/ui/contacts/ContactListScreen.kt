package com.harman.mvvmkmmsample.android.ui.contacts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.flowWithLifecycle
import com.harman.mvvmkmmsample.android.R
import com.harman.mvvmkmmsample.domain.model.Contact
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun contactListScreen(
    viewModel: ViewModel,
    OnItemClick: (contact: Contact) -> Unit
) {
     viewModel as ContactListViewModel
    Column(modifier = Modifier.background(MaterialTheme.colors.surface)) {
        var filter by remember { mutableStateOf(TextFieldValue("")) }
        val keyboardController = LocalSoftwareKeyboardController.current
        OutlinedTextField(
            placeholder = { Text(text = stringResource(id = R.string.filter_hint)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
            enabled = true,
            value = filter,
            onValueChange = {
                filter = it
                if(filter.text.isNotEmpty()) {
                    viewModel.filterContacts(it.text)
                }else{
                    viewModel.getContacts()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 3.dp, end = 3.dp, bottom = 15.dp, top = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            shape = MaterialTheme.shapes.small.copy(
                bottomEnd = CornerSize(2.dp),
                bottomStart = CornerSize(2.dp)
            )
        )
        contactList(
            viewModel,
            OnItemClick
        )
    }
}

@Composable
fun contactItem(contact: Contact, modifier: Modifier, OnItemClick: (contact: Contact) -> Unit) {
    Row(modifier = Modifier.selectable(
        selected = true,
        onClick = {
            OnItemClick(contact)
        }
    )
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.name_template, contact.firstName, contact.lastName),
                fontWeight = FontWeight.Bold, modifier = Modifier
                    .padding(2.dp, top = 5.dp)
            )
            Text(
                text = stringResource(R.string.name_phone, contact.phone),
                modifier = Modifier
                    .padding(2.dp)
            )
            Text(
                text = contact.email,
                modifier = Modifier
                    .padding(2.dp, bottom = 5.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_phone),
            contentDescription = "Phone", alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(40.dp)
                .wrapContentHeight(CenterVertically)
        //TODO add phone click event .
        )
    }
}

@Composable
fun contactList(
    viewModel: ContactListViewModel, onItemClick: (contact: Contact) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val exampleEntitiesFlowLifecycleAware = remember(viewModel.stateFlowContacts, lifecycleOwner) {
        viewModel.stateFlowContacts.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val stateFlowList: List<Contact> by exampleEntitiesFlowLifecycleAware.collectAsState(initial = emptyList())
    var mutableList = mutableStateOf(stateFlowList)

    LazyColumn(
        modifier = Modifier,
        ) {
        items(mutableList.value) { contact ->
            contactItem(contact = contact, modifier = Modifier, onItemClick)
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun <T> rememberFlow(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): Flow<T> {
    return remember(
        key1 = flow,
        key2 = lifecycleOwner
    ) { flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED) }
}





