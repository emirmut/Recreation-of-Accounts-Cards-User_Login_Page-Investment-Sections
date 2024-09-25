package emir.mut.GoldenGlobalMobile.userLoginPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class userLoginPage {
    companion object {
        var users: MutableList<user> = mutableStateListOf(
            user(
                userName = "Emir Mut",
                customerNo = "0001",
                password = mutableStateOf("abc123"),
                idNumber = "11111111111",
                isSelected = true,
            ),
            user(
                userName = "Ahmet Yılmaz",
                customerNo = "0002",
                password = mutableStateOf("xyz456"),
                idNumber = "22222222222",
                isSelected = false
            ),
            user(
                userName = "Ayşe Kaya",
                customerNo = "0003",
                password = mutableStateOf("def789"),
                idNumber = "33333333333",
                isSelected = false
            )
        )
        val enteredPassword: MutableState<String> = mutableStateOf("")
        @Composable
        fun userLoginPage(navController: NavController) {
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
                        haveActionIcon = true,
                        navigationIcon = {
                            topBar.user_settings_icon()
                        },
                        onNavigationIconClick = {
                            navController.navigate("page30")
                        },
                        actionIcon = {
                            topBar.notification_bell_icon()
                        },
                        onActionIconClick = {
                            // Bekleyen Onaylar sayfasına geçiş işlemi
                        }
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)) {
                        Spacer(modifier = Modifier
                            .weight(0.15f))
                        val interactionSource = remember { MutableInteractionSource() }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_user_image),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(80.dp)
                            )
                            users.forEach { user ->
                                if (user.isSelected) {
                                    Text(
                                        text = user.userName,
                                        fontSize = 17.sp
                                    )
                                    Row(modifier = Modifier
                                        .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Box(modifier = Modifier
                                            .size(300.dp, 42.dp)
                                            .border(
                                                width = 1.dp,
                                                color = colorResource(id = R.color.golden_global),
                                                shape = CircleShape
                                            ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            BasicTextField(
                                                value = enteredPassword.value,
                                                onValueChange = {
                                                    if (it.length <= 6) {
                                                        enteredPassword.value = it
                                                    }
                                                },
                                                visualTransformation = PasswordVisualTransformation(),
                                                singleLine = true,
                                                textStyle = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 16.sp,
                                                    textAlign = TextAlign.Center
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                decorationBox = { innerTextField ->
                                                    if (enteredPassword.value.isEmpty()) {
                                                        Text(
                                                            text = "Şifre giriniz",
                                                            color = Color.White,
                                                            fontSize = 16.sp,
                                                            textAlign = TextAlign.Center,
                                                            modifier = Modifier.fillMaxWidth()
                                                        )
                                                    }
                                                    innerTextField()
                                                }
                                            )
                                        }
                                    }
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 120.dp, end = 120.dp)
                                        .background(color = colorResource(id = R.color.golden_global), shape = RoundedCornerShape(10.dp))
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = null,
                                            enabled = enteredPassword.value == user.password.value,
                                            onClick = {
                                                navController.navigate("page6")
                                            }
                                        ),
                                        horizontalArrangement = Arrangement.Center) {
                                        Text(
                                            text = "Giriş Yap",
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .offset(x = 0.dp, y = (-8).dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        navController.navigate("page31")
                                    }
                                ),
                                horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "Şifremi Unuttum",
                                    color = colorResource(id = R.color.golden_global),
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                )
                            }
                            Spacer(modifier = Modifier
                                .height(32.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        navController.navigate("page30")
                                    }
                                ),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Kullanıcı / Şifre Ayarları",
                                    color = colorResource(id = R.color.fifty_seven),
                                    modifier = Modifier
                                        .padding(end = 8.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_arrow_right),
                                    contentDescription = "",
                                    colorFilter = ColorFilter.tint(colorResource(id = R.color.fifty_seven))
                                )
                            }
                        }
                        Spacer(modifier = Modifier
                            .weight(0.5f))
                    }
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun userLoginPagePreview() {
        GoldenGlobalMobileTheme {
            userLoginPage(navController = rememberNavController())
        }
    }
}