package com.harman.mvvmkmmsample.android.ui.about

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.harman.mvvmkmmsample.android.R

@Preview
@Composable
fun aboutScreen(){
    Text(text = stringResource(id = R.string.about_text),Modifier.padding(10.dp))
}