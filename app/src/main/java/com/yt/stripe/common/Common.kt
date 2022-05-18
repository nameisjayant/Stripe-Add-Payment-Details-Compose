package com.yt.aws.common

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mvizn.ENEReady.validation.InputWrapper
import com.mvizn.ENEReady.validation.OnValueChange



@Composable
fun CommonTextField(
    text: MutableState<String>,
    placeholder: String,
    iconColor: Color = Color.Transparent,
    keyboardType: KeyboardType = KeyboardType.Text,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    size: Dp = 25.dp,
    enable: Boolean = true,
    onClick: () -> Unit = {}

) {
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedLabelColor = Color.Gray,
            textColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            focusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Gray
        ),
        label = { Text(text = placeholder) },
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable { onClick() },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        enabled = enable
    )
}


@Composable
fun CommonTextField2(
    placeholder: String,
    iconColor: Color = Color.Transparent,
    keyboardType: KeyboardType = KeyboardType.Text,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    size: Dp = 25.dp,
    inputWrapper: InputWrapper,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: OnValueChange,
) {
    val fieldValue = remember {
        mutableStateOf(TextFieldValue(inputWrapper.value, TextRange(inputWrapper.value.length)))
    }

    TextField(
        value = fieldValue.value,
        onValueChange = {
            fieldValue.value = it
            onValueChange(it.text)
        },
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            focusedLabelColor = Color.Gray,
            textColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            unfocusedIndicatorColor = Color.Gray,
            focusedIndicatorColor = Color.Blue,
            disabledIndicatorColor = Color.Gray
        ),
        label = { Text(text = placeholder) },
        modifier = modifier.fillMaxWidth().padding(20.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}