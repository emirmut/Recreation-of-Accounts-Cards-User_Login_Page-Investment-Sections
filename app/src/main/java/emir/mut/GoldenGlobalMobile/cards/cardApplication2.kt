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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class cardApplication2 {
    companion object {
        var selectedCard: card = cardApplication.cards[0]
        var cardShippingAddress: String = "Seçiniz"
        var cardPassword: MutableState<String> = mutableStateOf("")
        var passwordVisible: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun cardApplication2(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kart Başvurusu"
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
                        .padding(top = 48.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)) {
                            Column(modifier = Modifier
                                .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                Image(
                                    painter = painterResource(id = selectedCard.verticalImage),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(112.6.dp, 179.dp)
                                )
                                Text(
                                    text = selectedCard.name,
                                    fontWeight = FontWeight.W700,
                                    fontSize = 18.sp,
                                    color = Color.Black
                                )
                            }
                        }
                        cardApplication2Row(
                            navController = navController,
                            text1 = "Kart Teslimat Şekli",
                            text2 = "Posta",
                            hasContract = false,
                            hasTextField = false
                        )
                        cardApplication2Row(
                            navController = navController,
                            text1 = "Kart Gönderim Adresi",
                            text2 = cardShippingAddress,
                            hasContract = false,
                            hasTextField = false
                        )
                        cardApplication2Row(
                            navController = navController,
                            text1 = "Ön Ödemeli Kart Sözleşmesi",
                            text2 = "",
                            hasContract = true,
                            hasTextField = false
                        )
                        cardApplication2Row(
                            navController = navController,
                            text1 = "Kart Şifresi",
                            text2 = "",
                            hasContract = false,
                            hasTextField = true
                        )
                        Spacer(modifier = Modifier
                            .weight(1f))
                        val interactionSource = remember { MutableInteractionSource() }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.golden_global))
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                enabled = cardShippingAddress != "Seçiniz" && cardContract.checkedCardContract.value,
                                onClick = {
                                    myCards.existingCards.add(selectedCard)
                                    navController.navigate("page23")
                                }
                            ),
                            horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "BAŞVUR",
                                color = Color.White,
                                fontWeight = FontWeight.W500
                            )
                        }
                        Spacer(modifier = Modifier
                            .height(36.dp))
                    }
                }
            )
        }

        @Composable
        fun cardApplication2Row(
            navController: NavController,
            text1: String,
            text2: String,
            hasContract: Boolean,
            hasTextField: Boolean
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            Row(modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = text2 != "Posta",
                    onClick = {
                        if (!hasContract) {
                            navController.navigate("page21")
                        } else {
                            navController.navigate("page22")
                        }
                    }
                ),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text1,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.subtitle_text_color),
                    modifier = Modifier
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                if (!hasContract && !hasTextField) {
                    Text(
                        text = text2,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.W700,
                        modifier = Modifier
                            .padding(16.dp, 16.dp, if (text2 != "Posta") 8.dp else 16.dp, 16.dp)
                    )
                    if (text2 != "Posta") {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(end = 8.dp)
                        )
                    }
                } else if (!hasTextField) {
                    Image(
                        painter =  if (cardContract.checkedCardContract.value) painterResource(id = R.drawable.ic_done_red) else painterResource(id = R.drawable.ic_contracts),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    navController.navigate("page22")
                                }
                            )
                            .padding(16.dp)
                    )
                } else {
                    var isSetPasswordInputFocused: MutableState<Boolean> = mutableStateOf(false)
                    TextField(
                        value = cardPassword.value,
                        onValueChange = {
                            if (it.length <= 4) {
                                cardPassword.value = it
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
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
                                isSetPasswordInputFocused.value = it.isFocused
                            },
                        singleLine = true,
                        maxLines = 1
                    )
                    selectedCard.password = cardPassword.value
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun cardApplication2Preview() {
        GoldenGlobalMobileTheme {
            cardApplication2(navController = rememberNavController())
        }
    }
}