package emir.mut.GoldenGlobalMobile.userLoginPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
import emir.mut.GoldenGlobalMobile.cards.cardApplication2.Companion.passwordVisible
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class addUser {
    companion object {
        var userNameInput: MutableState<String> = mutableStateOf("")
        var idNumberInput: MutableState<String> = mutableStateOf("")
        var customerNoInput: MutableState<String> = mutableStateOf("")
        var passwordInput: MutableState<String> = mutableStateOf("")
        @Composable
        fun addUser(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_golden_global_bank_text),
                                contentDescription = "",
                                colorFilter = ColorFilter.tint(Color.White)
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
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 0.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            addUserRow(
                                navController = navController,
                                text = "Kullanıcı Adı",
                                hasInfoIcon = false
                            )
                            MainActivity.horizontalDivider()
                            addUserRow(
                                navController = navController,
                                text = "T.C. Kimlik No",
                                hasInfoIcon = false
                            )
                            MainActivity.horizontalDivider()
                            addUserRow(
                                navController = navController,
                                text = "Müşteri No",
                                hasInfoIcon = false
                            )
                            MainActivity.horizontalDivider()
                            addUserRow(
                                navController = navController,
                                text = "Şifre",
                                hasInfoIcon = true
                            )
                        }
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        addUserButton(
                            navController = navController,
                            text = "Ekle"
                        )
                        Spacer(modifier = Modifier
                            .height(8.dp))
                        addUserButton(
                            navController = navController,
                            text = "Şifremi Unuttum"
                        )
                    }
                }
            )
        }

        @Composable
        fun addUserRow(
            navController: NavController,
            text: String,
            hasInfoIcon: Boolean
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                var isUserNameInputFocused: MutableState<Boolean> = mutableStateOf(false)
                var isIdNumberInputFocused: MutableState<Boolean> = mutableStateOf(false)
                var isCustomerNoInputFocused: MutableState<Boolean> = mutableStateOf(false)
                var isPasswordInputFocused: MutableState<Boolean> = mutableStateOf(false)
                Text(
                    text = text,
                    color = colorResource(id = R.color.subtitle_text_color),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(16.dp),
                    lineHeight = TextUnit(1.3f, TextUnitType.Em)
                )
                if (hasInfoIcon) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_info_red),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    navController.navigate("page33")
                                }
                            )
                    )
                }
                Spacer(modifier = Modifier
                    .weight(1f))
                TextField(
                    value = if (text == "Kullanıcı Adı") userNameInput.value else if (text == "T.C. Kimlik No") idNumberInput.value else if (text == "Müşteri No") customerNoInput.value else passwordInput.value,
                    onValueChange = {
                        if (text == "Kullanıcı Adı") {
                            userNameInput.value = it
                        } else if (text == "T.C. Kimlik No" && it.length <= 11) {
                            idNumberInput.value = it
                        } else if (text == "Müşteri No" && it.length <= 4) {
                            customerNoInput.value = it
                        } else if (text == "Şifre" && it.length <= 6) {
                            passwordInput.value = it
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    visualTransformation = if (text == "Şifre" && !passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
                        if (text == "Şifre") {
                            val image = if (passwordVisible.value) R.drawable.ic_visible else R.drawable.ic_invisible
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = "",
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            passwordVisible.value = !passwordVisible.value
                                        }
                                    )
                            )
                        }
                    },
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
                            if (text == "Kullanıcı Adı") {
                                isUserNameInputFocused.value = it.isFocused
                            } else if (text == "T.C. Kimlik No") {
                                isIdNumberInputFocused.value = it.isFocused
                            } else if (text == "Müşteri No") {
                                isCustomerNoInputFocused.value = it.isFocused
                            } else {
                                isPasswordInputFocused.value = it.isFocused
                            }
                        },
                    singleLine = true,
                    maxLines = 1
                )
            }
        }

        @Composable
        fun addUserButton(
            navController: NavController,
            text: String
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                .background(
                    color = if (text == "Ekle") colorResource(id = R.color.golden_global) else Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = userNameInput.value != "" && customerNoInput.value != "" && passwordInput.value != "" && idNumberInput.value != "",
                    onClick = {
                        val newUser: user = user(
                            userName = userNameInput.value,
                            customerNo = customerNoInput.value,
                            password = passwordInput,
                            idNumber = idNumberInput.value,
                            isSelected = false
                        )
                        if (text == "Ekle") {
                            userLoginPage.users.add(newUser)
                            navController.navigate("page34")
                        } else {
                            navController.navigate("page31")
                        }
                    }
                ),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    color = if (text == "Ekle") Color.White else colorResource(id = R.color.golden_global),
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun addUserPreview() {
        GoldenGlobalMobileTheme {
            addUser(navController = rememberNavController())
        }
    }
}