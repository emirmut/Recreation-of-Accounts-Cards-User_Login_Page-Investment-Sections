package emir.mut.GoldenGlobalMobile.accounts.easyaddress

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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.account
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.myAccounts
import emir.mut.GoldenGlobalMobile.dialogues
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class definingEasyAddress {
    companion object {
        var selectedEasyAddress: MutableState<String> = mutableStateOf("Seçiniz")
        var easyAddressInput: MutableState<String> = mutableStateOf("")
        var easyAddressNameInput: MutableState<String> = mutableStateOf("ilk kolay adres hesabım")
        var checkedDescriptionText: MutableState<Boolean> = mutableStateOf(false)
        var checkedExpressContextText: MutableState<Boolean> = mutableStateOf(false)
        var showDialog1: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun definingEasyAddress(navController: NavController) {
            var coroutineScope = rememberCoroutineScope()
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kolay Adres Tanımlama"
                            )
                        },
                        haveNavigationIcon = true,
                        haveActionIcon = false,
                        navigationIcon = {
                            topBar.back_icon()
                        },
                        onNavigationIconClick = {
                            navController.navigate("page0") {
                                popUpTo("page2") {
                                    inclusive = true
                                }
                            }
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
                        .padding(top = 48.dp)) {
                        MainActivity.horizontalDivider()
                        val interactionSource = remember { MutableInteractionSource() }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp, 16.dp, 16.dp, 8.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    navController.navigate("page5")
                                }
                            )) {
                            Text(
                                text = "Hesap Seçimi",
                                color = colorResource(id = R.color.subtitle_text_color),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier
                                .weight(1f))
                            if (myAccounts.accounts.isNotEmpty()) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = selectedAccount().accountNumber.toString(),
                                        color = colorResource(id = R.color.color_7F8084),
                                        fontSize = 14.sp
                                    )
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = selectedAccount().accountType,
                                            color = colorResource(id = R.color.extra_faded),
                                            fontSize = 14.sp,
                                            modifier = Modifier
                                                .width(75.dp),
                                            overflow = TextOverflow.Ellipsis,
                                            maxLines = 1
                                        )
                                        Image(
                                            painter = painterResource(id = R.drawable.ic_arrow_right),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .size(20.dp)
                                                .padding(start = 8.dp)
                                        )
                                    }
                                    Text(
                                        text = String.format("%.2f TL", selectedAccount().accountBalance),
                                        fontSize = 12.sp
                                    )
                                }
                            } else {
                                Text(
                                    text = "Seçiniz",
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.extra_faded)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_arrow_right),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(start = 8.dp)
                                        .align(Alignment.CenterVertically)
                                )
                            }
                        }
                        MainActivity.horizontalDivider()
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp)
                            .clickable {
                                navController.navigate("page3")
                            }) {
                            Text(
                                text = "Kolay Adres Türü",
                                color = colorResource(id = R.color.subtitle_text_color),
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier
                                .weight(1f))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = selectedEasyAddress.value,
                                    color = if (selectedEasyAddress.value == "Seçiniz") colorResource(id = R.color.extra_faded) else Color.Black,
                                    fontSize = 14.sp
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_arrow_right),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .padding(start = 8.dp)
                                )
                            }
                        }
                        MainActivity.horizontalDivider()
                        if (selectedEasyAddress.value != "Seçiniz") {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = selectedEasyAddress.value,
                                    color = colorResource(id = R.color.subtitle_text_color),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier
                                    .weight(1f))
                                var isEasyAddressInputFocused: MutableState<Boolean> = mutableStateOf(false)
                                TextField(
                                    value = easyAddressInput.value,
                                    onValueChange = {
                                        easyAddressInput.value = it
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
                                                text = "Giriniz",
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
                                            isEasyAddressInputFocused.value = it.isFocused
                                        },
                                    singleLine = true,
                                    maxLines = 1
                                )
                            }
                            MainActivity.horizontalDivider()
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(start = 16.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Kolay Adres Adı",
                                    color = colorResource(id = R.color.subtitle_text_color),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier
                                    .weight(1f))
                                var isEasyAddressNameInputFocused: MutableState<Boolean> = mutableStateOf(false)
                                TextField(
                                    value = easyAddressNameInput.value,
                                    onValueChange = {
                                        easyAddressNameInput.value = it
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
                                            isEasyAddressNameInputFocused.value = it.isFocused
                                        },
                                    singleLine = true,
                                    maxLines = 1
                                )
                            }
                            MainActivity.horizontalDivider()
                        }
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        requiredText(
                            "Aydınlatma metnini okudum, anladım.",
                            navigateToRequiredTexts = {
                                navController.navigate("page4")
                            }
                        )
                        requiredText(
                            "Açık rıza metnini okudum, anladım.",
                            navigateToRequiredTexts = {
                                navController.navigate("page4")
                            }
                        )
                        MainActivity.horizontalDivider()
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        MainActivity.horizontalDivider()
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(top = 8.dp, bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center) {
                            val interactionSource = remember { MutableInteractionSource() }
                            Text(
                                text = "Onay",
                                color = if (selectedEasyAddress.value != "Seçiniz" && easyAddressInput.value != "" && checkedDescriptionText.value && checkedExpressContextText.value) {
                                    colorResource(id = R.color.golden_global)
                                } else {
                                    colorResource(id = R.color.subtitle_text_color)
                                },
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        enabled = selectedEasyAddress.value != "Seçiniz" && easyAddressInput.value != "" && checkedDescriptionText.value && checkedExpressContextText.value,
                                        onClick = {
                                            coroutineScope.launch {
                                                myEasyAddressesClass.easyAddresses.add(
                                                    easyAddressAccount(
                                                        account = selectedAccount(),
                                                        easyAddressType = selectedEasyAddress.value,
                                                        easyAddressInput = easyAddressInput.value,
                                                        easyAddressNameInput = easyAddressNameInput.value
                                                    )
                                                )
                                                showDialog1.value = true
                                            }
                                        }
                                    )
                            )
                        }
                        MainActivity.horizontalDivider()
                        if (showDialog1.value) {
                            dialogues.easyAddressCompletedDialog(navController = navController)
                        }
                    }
                }
            )
            showDialog1.value = false
        }

        @Composable
        fun requiredText(
            subtitle: String,
            navigateToRequiredTexts: () -> Unit
        ) {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = subtitle,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                val interactionSource = remember { MutableInteractionSource() }
                Switch(
                    checked = if (subtitle == "Aydınlatma metnini okudum, anladım.") checkedDescriptionText.value else checkedExpressContextText.value,
                    onCheckedChange = {
                        if (subtitle == "Aydınlatma metnini okudum, anladım." && !checkedDescriptionText.value) {
                            checkedDescriptionText.value = true
                            checkedExpressContextText.value = true
                            navigateToRequiredTexts()
                        } else if (subtitle == "Açık rıza metnini okudum, anladım." && !checkedExpressContextText.value) {
                            checkedExpressContextText.value = true
                            checkedDescriptionText.value = true
                            navigateToRequiredTexts()
                        } else if (subtitle == "Aydınlatma metnini okudum, anladım." && checkedDescriptionText.value) {
                            checkedDescriptionText.value = false
                        } else if (subtitle == "Açık rıza metnini okudum, anladım." && checkedExpressContextText.value) {
                            checkedExpressContextText.value = false
                        }
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
        }

        fun selectedAccount(): account {
            for (i in myAccounts.accounts.indices) {
                if (myAccounts.accounts[i].isSelected) {
                    return myAccounts.accounts[i]
                }
            }
            return myAccounts.accounts.last()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun definingEasyAddressPreview() {
        GoldenGlobalMobileTheme {
            definingEasyAddress(navController = rememberNavController())
        }
    }
}