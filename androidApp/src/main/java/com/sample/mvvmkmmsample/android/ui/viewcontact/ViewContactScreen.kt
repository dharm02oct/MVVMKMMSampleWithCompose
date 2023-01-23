package com.sample.mvvmkmmsample.android.ui.viewcontact

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sample.mvvmkmmsample.android.R
import com.sample.mvvmkmmsample.domain.model.Contact

@Composable
fun contactView( viewModel: ViewContactViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), edit:()->Unit, call:()->Unit) {

    var contact: State<Contact> = viewModel.contact.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(
                id = R.string.name_template,
                contact.value.firstName,
                contact.value.lastName
            ),
            Modifier
                .padding(bottom = 20.dp, end = 5.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(id = R.string.name_phone, contact.value.phone),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )
        Text(text = contact.value.email)
        Image(
            painter = painterResource(id = R.drawable.ic_phone),
            contentDescription = "Phone", alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(5.dp)
                .clip(CircleShape)
                .size(50.dp)
                .wrapContentHeight(Alignment.CenterVertically).clickable {
                    call()
                }


        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp),
        horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = { viewModel.deleteContact() },
            modifier = Modifier.padding(end = 10.dp, bottom = 10.dp),  //avoid the oval shape
            border = BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),
        ) {
            Text(text = stringResource(id = R.string.delete))
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom
    ) {
        Button(
            onClick = {
                edit()
            },
            modifier = Modifier.padding(bottom = 10.dp, start = 10.dp),  //avoid the oval shape
            border = BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue),


            ) {
            Text(text = stringResource(id = R.string.edit))
        }

    }
}
