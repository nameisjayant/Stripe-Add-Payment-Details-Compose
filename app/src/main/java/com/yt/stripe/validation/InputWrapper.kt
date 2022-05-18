package com.mvizn.ENEReady.validation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InputWrapper(
    val value: String = "",
    val errorId: Int? = null
) : Parcelable

typealias OnValueChange = (value: String) -> Unit
typealias OnImeKeyAction = () -> Unit