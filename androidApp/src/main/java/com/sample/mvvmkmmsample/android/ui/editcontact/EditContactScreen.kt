package com.sample.mvvmkmmsample.android.ui.editcontact

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sample.mvvmkmmsample.android.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun editContactScreen(viewModel: EditContactViewModel) {
    var firstNameEdit: MutableState<TextFieldValue> =
        remember { mutableStateOf(TextFieldValue("")) }
    var lastNameEdit: MutableState<TextFieldValue> =
        remember { mutableStateOf(TextFieldValue("")) }
    var phoneEdit: MutableState<TextFieldValue> =
        remember { mutableStateOf(TextFieldValue("")) }
    var emailEdit: MutableState<TextFieldValue> =
        remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 15.dp)
    ) {
        TextField(
            value = firstNameEdit.value,
            onValueChange = {
                firstNameEdit.value =it
                viewModel.firstname.value = it.text
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
            shape = MaterialTheme.shapes.small.copy(
                bottomEnd = CornerSize(2.dp),
                bottomStart = CornerSize(2.dp)
            ),
            label = {
                Text(text = stringResource(id = R.string.firstname))
            }
            )
        TextField(
            value = lastNameEdit.value,
            onValueChange = {
                lastNameEdit.value =it
                viewModel.firstname.value = it.text
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            shape = MaterialTheme.shapes.small.copy(
                bottomEnd = CornerSize(2.dp),
                bottomStart = CornerSize(2.dp)
            ),
            label = {
                Text(text = stringResource(id = R.string.lastname))
            }
        )
        TextField(
            value = phoneEdit.value,
            onValueChange = {

                phoneEdit.value = it
                viewModel.phone.value = it.text.toLong()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            shape = MaterialTheme.shapes.small.copy(
                bottomEnd = CornerSize(2.dp),
                bottomStart = CornerSize(2.dp)
            ),
            label = {
                Text(text = stringResource(id = R.string.phone))
            }
        )
        TextField(
            value = emailEdit.value,
            onValueChange = {
                emailEdit.value =it
                viewModel.email.value = it.text
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()}
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            shape = MaterialTheme.shapes.small.copy(
                all = CornerSize(3.dp)
            ),
            label = {
                Text(text = stringResource(id = R.string.email))
            }
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp),
        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {
            },
            modifier = Modifier.padding(end = 15.dp, bottom = 15.dp),  //avoid the oval shape
            border = BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),
        ) {
            Text(text = stringResource(id = R.string.cancel))
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp),
        horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {
                viewModel.saveContact()
            },
            modifier = Modifier.padding(bottom = 15.dp, start = 15.dp),  //avoid the oval shape
            border = BorderStroke(2.dp, Color.Blue),
            contentPadding = PaddingValues(1.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),
        ) {
            Text(text = stringResource(id = R.string.save))
        }

    }
}