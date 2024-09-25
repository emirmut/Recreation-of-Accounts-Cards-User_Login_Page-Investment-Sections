package emir.mut.GoldenGlobalMobile.userLoginPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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

class forgotPassword {
    companion object {
        var selectedOption: MutableState<String> = mutableStateOf("Bireysel")
        @Composable
        fun forgotPassword(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Şifremi Unuttum"
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
                        .padding(top = 8.dp)) {
                        toggleButton()
                        Column(modifier = Modifier
                            .padding(16.dp, 4.dp, 16.dp, 16.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            forgotPasswordRow(text = "Müşteri No / T.C. Kimlik No")
                            MainActivity.horizontalDivider()
                            forgotPasswordRow(text = "Kart Numarasının Son 4 Hanesi")
                            MainActivity.horizontalDivider()
                            forgotPasswordRow(text = "CVV")
                            MainActivity.horizontalDivider()
                            forgotPasswordRow(text = "Kart Şifresi")
                        }
                        Row(modifier = Modifier
                            .padding(16.dp, 0.dp, 16.dp, 0.dp)
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                            horizontalArrangement = Arrangement.Center) {
                            val interactionSource = remember { MutableInteractionSource() }
                            Text(
                                text = "Onay",
                                color = colorResource(id = R.color.golden_global),
                                modifier = Modifier
                                    .padding(top = 8.dp, bottom = 8.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        enabled = true, // değiştircem
                                        onClick = {

                                        }
                                    )
                            )
                        }
                    }
                }
            )
        }

        @Composable
        fun toggleButton() {
            Row(modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 8.dp)
                .height(40.dp)
                .background(
                    colorResource(id = R.color.search_bar_background),
                    shape = RoundedCornerShape(10.dp)
                ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val buttonModifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                toggleButtonOption(
                    text = "Bireysel",
                    isSelected = selectedOption.value == "Bireysel",
                    modifier = buttonModifier
                )
                toggleButtonOption(
                    text = "Kurumsal",
                    isSelected = selectedOption.value == "Kurumsal",
                    modifier = buttonModifier
                )
            }
        }

        @Composable
        fun toggleButtonOption(
            text: String,
            isSelected: Boolean,
            modifier: Modifier = Modifier
        ) {
            Surface(modifier = modifier,
                color = if (isSelected) Color.White else colorResource(id = R.color.balance),
                shape = RoundedCornerShape(10.dp)
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = {
                                if (text == "Bireysel") {
                                    selectedOption.value = "Bireysel"
                                } else {
                                    selectedOption.value = "Kurumsal"
                                }
                            }
                        )
                ) {
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        color = if (isSelected) Color.Black else colorResource(id = R.color.eft),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        @Composable
        fun forgotPasswordRow(
            text: String
        ) {
            Row(modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                .fillMaxWidth()
                .background(Color.White),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    color = colorResource(id = R.color.subtitle_text_color),
                    fontSize = 14.sp,
                    modifier = Modifier
                        .width(120.dp)
                        .padding(8.dp),
                    lineHeight = TextUnit(1.3f, TextUnitType.Em)
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                var isAccountNameFocused: MutableState<Boolean> = mutableStateOf(false)
                TextField(
                    value = "",
                    onValueChange = {

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
                        .padding(8.dp)
                        .onFocusChanged {
                            isAccountNameFocused.value = it.isFocused
                        },
                    singleLine = true,
                    maxLines = 1
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun forgotPasswordPreview() {
        GoldenGlobalMobileTheme {
            forgotPassword(navController = rememberNavController())
        }
    }
}