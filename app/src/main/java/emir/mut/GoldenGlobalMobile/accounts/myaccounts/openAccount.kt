package emir.mut.GoldenGlobalMobile.accounts.myaccounts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class openAccount {
    companion object {
        var accountNumber: Int = 1
        var accountBalance: Double = 0.0
        var selectedAccountType: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedExchangeType: MutableState<String> = mutableStateOf("Seçiniz")
        var accountName: MutableState<String> = mutableStateOf("")
        var IBANnumber: String = "TR34 0000 0000 0000 0000 0000 00"
        var checkedOpenAccount: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun openAccount(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Hesap Aç"
                            )
                        },
                        haveNavigationIcon = true,
                        haveActionIcon = false,
                        navigationIcon = {
                            topBar.back_icon()
                        },
                        onNavigationIconClick = {
                            navController.popBackStack()
                        },
                        actionIcon = {},
                        onActionIconClick = {}
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(top = 32.dp)) {
                        openAccountRow(
                            navController = navController,
                            paddingStart = 16, paddingTop = 16, paddingEnd = 16, paddingBottom = 16,
                            text1 = "Hesap Türü",
                            text2 = selectedAccountType.value,
                            hasRightArrow = true,
                            hasTextField = false
                        )
                        if (selectedAccountType.value != "Seçiniz") {
                            openAccountRow(
                                navController = navController,
                                paddingStart = 16, paddingTop = 16, paddingEnd = 16, paddingBottom = 16,
                                text1 = "Döviz Türü",
                                text2 = selectedExchangeType.value,
                                hasRightArrow = true,
                                hasTextField = false
                            )
                        }
                        openAccountRow(
                            navController = null,
                            paddingStart = 16, paddingTop = 16, paddingEnd = 16, paddingBottom = 16,
                            text1 = "Şube",
                            text2 = "Sanal Şube",
                            hasRightArrow = false,
                            hasTextField = false
                        )
                        openAccountRow(
                            navController = null,
                            paddingStart = 16, paddingTop = 0, paddingEnd = 0, paddingBottom = 0,
                            text1 = "Hesap Adı",
                            text2 = "",
                            hasRightArrow = false,
                            hasTextField = true
                        )
                        MainActivity.horizontalDivider()
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp, 16.dp, 32.dp, 8.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_yellow_exclamation),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(25.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.openAccountText),
                                modifier = Modifier
                                    .padding(start = 16.dp),
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.subtitle_text_color),
                                lineHeight = TextUnit(1.3f, TextUnitType.Em)
                            )
                        }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp, 0.dp, 16.dp, 8.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Okudum, onaylıyorum",
                                color = colorResource(id = R.color.subtitle_text_color)
                            )
                            Spacer(modifier = Modifier
                                .weight(1f))
                            val interactionSource = remember { MutableInteractionSource() }
                            Switch(
                                checked = checkedOpenAccount.value,
                                onCheckedChange = {
                                    checkedOpenAccount.value = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = colorResource(id = R.color.checked_switch),
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = colorResource(id = R.color.unchecked_switch),
                                    uncheckedBorderColor = Color.Transparent,
                                    checkedBorderColor = Color.Transparent
                                ),
                                interactionSource = interactionSource,
                                modifier = Modifier
                                    .scale(0.75f)
                                    .indication(
                                        interactionSource = interactionSource,
                                        indication = null
                                    )
                            )
                        }
                        MainActivity.horizontalDivider()
                        val interactionSource = remember { MutableInteractionSource() }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                enabled = checkedOpenAccount.value && selectedAccountType.value != "Seçiniz" && selectedExchangeType.value != "Seçiniz",
                                onClick = {
                                    navController.navigate("page13")
                                }
                            ),
                            horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "İleri",
                                color = if (checkedOpenAccount.value && selectedAccountType.value != "Seçiniz" && selectedExchangeType.value != "Seçiniz") colorResource(id = R.color.golden_global) else colorResource(id = R.color.subtitle_text_color)
                            )
                        }
                        MainActivity.horizontalDivider()
                    }
                }
            )
        }

        @Composable
        fun openAccountRow(
            navController: NavController?,
            paddingStart: Int, paddingTop: Int, paddingEnd: Int, paddingBottom: Int,
            text1: String,
            text2: String,
            hasRightArrow: Boolean,
            hasTextField: Boolean
        ) {
            MainActivity.horizontalDivider()
            val interactionSource = remember { MutableInteractionSource() }
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = paddingStart.dp, top = paddingTop.dp, end = paddingEnd.dp, bottom = paddingBottom.dp)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = text2 != "Sanal Şube",
                    onClick = {
                        if (text1 == "Hesap Türü") {
                            navController?.navigate("page8")
                        } else if (text1 == "Döviz Türü") {
                            navController?.navigate("page9")
                        }
                    }
                )) {
                Text(
                    text = text1,
                    color = colorResource(id = R.color.subtitle_text_color),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (!hasTextField) {
                        Text(
                            text = text2,
                            color = if (text2 == "Seçiniz") colorResource(id = R.color.extra_faded) else Color.Black,
                            fontSize = 14.sp
                        )
                    } else {
                        var isAccountNameFocused: MutableState<Boolean> = mutableStateOf(false)
                        TextField(
                            value = accountName.value,
                            onValueChange = {
                                accountName.value = it
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            placeholder = {
                                Row {
                                    Spacer(modifier = Modifier
                                        .weight(1f))
                                    Text(
                                        text = "Giriniz (İsteğe Bağlı)",
                                        color = colorResource(id = R.color.placeholder_text_color),
                                        fontSize = 14.sp
                                    )
                                }
                            },
                            textStyle = TextStyle(
                                textAlign = TextAlign.End,
                                fontSize = 14.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .onFocusChanged {
                                    isAccountNameFocused.value = it.isFocused
                                },
                            singleLine = true,
                            maxLines = 1
                        )
                    }
                    if (hasRightArrow) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun openAccountPreview() {
        GoldenGlobalMobileTheme {
            openAccount(navController = rememberNavController())
        }
    }
}