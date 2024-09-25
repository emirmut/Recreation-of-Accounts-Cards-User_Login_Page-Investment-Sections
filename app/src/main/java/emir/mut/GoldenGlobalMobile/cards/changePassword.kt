package emir.mut.GoldenGlobalMobile.cards

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.cards.cardApplication2.Companion.passwordVisible
import emir.mut.GoldenGlobalMobile.dialogues
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class changePassword {
    companion object {
        var newPasswordInput: MutableState<String> = mutableStateOf("")
        var newPasswordAgainInput: MutableState<String> = mutableStateOf("")
        @Composable
        fun changePassword(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Şifre Değiştir"
                            )
                        },
                        haveNavigationIcon = true,
                        haveActionIcon = true,
                        navigationIcon = {
                            topBar.back_icon()
                        },
                        onNavigationIconClick = {
                            navController.popBackStack()
                        },
                        actionIcon = {
                            topBar.question_icon()
                        },
                        onActionIconClick = {
                            navController.navigate("page27")
                        }
                    )
                },
                content = { innerPadding ->
                    var isDialogueOpen: MutableState<Boolean> = mutableStateOf(false)
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(top = 32.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 16.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                            verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_info),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(start = 16.dp, end = 16.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.changePassword),
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                                color = colorResource(id = R.color.change_password_text),
                                modifier = Modifier
                                    .padding(0.dp, 16.dp, 16.dp, 16.dp)
                            )
                        }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 0.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            changePasswordRow(placeholder = "Yeni Şifre (4 haneli bir şifre belirleyiniz.)")
                            changePasswordRow(placeholder = "Yeni Şifre Tekrar (şifreyi tekrardan giriniz.)")
                        }
                        Spacer(modifier = Modifier
                            .weight(1f))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.golden_global))
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    if (newPasswordInput.value == newPasswordAgainInput.value) {
                                        cardApplication2.selectedCard.password = newPasswordInput.value
                                        navController.navigate("page28")
                                    } else {
                                        isDialogueOpen.value = true
                                    }
                                }
                            ),
                            horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "ŞİFRE DEĞİŞTİR",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W600
                            )
                        }
                        Spacer(modifier = Modifier
                            .height(36.dp))
                    }
                    if (isDialogueOpen.value) {
                        dialogues.changePasswordNotDoneDialog(navController = navController)
                    }
                }
            )
        }

        @Composable
        fun changePasswordRow(
            placeholder: String
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)) {
                var isNewPasswordInputFocused: MutableState<Boolean> = mutableStateOf(false)
                var isNewPasswordAgainInputFocused: MutableState<Boolean> = mutableStateOf(false)
                TextField(
                    value = if (placeholder == "Yeni Şifre (4 haneli bir şifre belirleyiniz.)") newPasswordInput.value else newPasswordAgainInput.value,
                    onValueChange = {
                        if (placeholder == "Yeni Şifre (4 haneli bir şifre belirleyiniz.)") {
                            newPasswordInput.value = it
                        } else {
                            newPasswordAgainInput.value = it
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.new_password_underline),
                        unfocusedIndicatorColor = colorResource(id = R.color.new_password_underline)
                    ),
                    visualTransformation = if (!passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
                    trailingIcon = {
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
                    },
                    placeholder = {
                        Text(
                            text = placeholder,
                            color = colorResource(id = R.color.placeholder_text_color),
                            fontSize = 14.sp
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged {
                            if (placeholder == "Yeni Şifre (4 haneli bir şifre belirleyiniz.)") {
                                isNewPasswordInputFocused.value = it.isFocused
                            } else {
                                isNewPasswordAgainInputFocused.value = it.isFocused
                            }
                        }
                        .padding(bottom = 16.dp),
                    singleLine = true,
                    maxLines = 1
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun changePasswordPreview() {
        GoldenGlobalMobileTheme {
            changePassword(navController = rememberNavController())
        }
    }
}