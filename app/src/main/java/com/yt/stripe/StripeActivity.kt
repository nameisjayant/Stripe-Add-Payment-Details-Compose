package com.yt.aws

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.mvizn.ENEReady.validation.creditCardFilter
import com.mvizn.ENEReady.viewmodels.InputValidationAutoViewModel
import com.stripe.android.model.ConfirmSetupIntentParams
import com.stripe.android.model.PaymentMethod
import com.stripe.android.model.PaymentMethodCreateParams
import com.stripe.android.payments.paymentlauncher.PaymentLauncher
import com.stripe.android.payments.paymentlauncher.PaymentResult
import com.yt.aws.common.CommonTextField
import com.yt.aws.common.CommonTextField2
import com.yt.stripe.R
import com.yt.stripe.ui.theme.StripeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StripeActivity : ComponentActivity() {

    private val viewModel: InputValidationAutoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StripeTheme() {
                Column {
                    val creditCardNumber by viewModel.creditCardNumber.collectAsState()
                    val holderName = remember { mutableStateOf("") }
                    val expiryDate = remember { mutableStateOf("") }
                    val cvv = remember { mutableStateOf("") }
                    var isDialog by remember { mutableStateOf(false) }
                    var expiryDateArray by remember { mutableStateOf(arrayOf("")) }
                    val scope = rememberCoroutineScope()

                    expiryDateArray = expiryDate.value.split("/").toTypedArray()


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 30.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Stripe", fontWeight = FontWeight.Bold)
                    }

                    CommonTextField2(
                        placeholder = resources.getString(R.string.enter_card_no),
                        visualTransformation = ::creditCardFilter,
                        inputWrapper = creditCardNumber,
                        onValueChange = viewModel::onCardNumberEntered,
                        keyboardType = KeyboardType.Phone
                    )

                    CommonTextField(text = holderName, placeholder = "Enter holder name")

                    CommonTextField(text = expiryDate, placeholder = "03/2022")

                    CommonTextField(text = cvv, placeholder = "Enter cvv number")

                    Button(
                        onClick = {


                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Done",
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }

                    if (isDialog) {
                        AlertDialog(
                            onDismissRequest = { },
                            buttons = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            },
                            backgroundColor = Color.Transparent
                        )
                    }
                }
            }
        }
    }


}

