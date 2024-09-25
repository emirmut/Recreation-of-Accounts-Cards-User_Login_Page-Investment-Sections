package emir.mut.GoldenGlobalMobile.investment

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import emir.mut.GoldenGlobalMobile.navigationDrawer
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class openInvestmentAccount {
    companion object {
        var investmentAccountName: MutableState<String> = mutableStateOf("")
        var checkedOpenInvestmentAccount: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun openInvestmentAccount(navController: NavController) {
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val coroutineScope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerState = drawerState,
                gesturesEnabled = drawerState.isOpen,
                drawerContent = {
                    ModalDrawerSheet {
                        navigationDrawer.navigationDrawerContent(
                            navController = navController,
                            drawerState = drawerState,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            ) {
                Scaffold(
                    topBar = {
                        topBar.centerAlignedTopBar(
                            title = {
                                Text(
                                    text = "Yatırım Hesabı Aç"
                                )
                            },
                            haveNavigationIcon = true,
                            haveActionIcon = false,
                            navigationIcon = {
                                topBar.hamburger_icon()
                            },
                            onNavigationIconClick = {
                                coroutineScope.launch {
                                    drawerState.open()
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
                            .padding(top = 32.dp)) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                                openInvestmentAccountRow(
                                    navController = navController,
                                    text1 = "Para Birimi",
                                    text2 = selectExchangeType.selectedExchangeType,
                                    hasTextField = false
                                )
                                MainActivity.horizontalDivider()
                                openInvestmentAccountRow(
                                    navController = navController,
                                    text1 = "Şube",
                                    text2 = "Sanal Şube",
                                    hasTextField = false
                                )
                                MainActivity.horizontalDivider()
                                openInvestmentAccountRow(
                                    navController = navController,
                                    text1 = "Hesap Adı",
                                    text2 = "",
                                    hasTextField = true
                                )
                            }
                            Spacer(modifier = Modifier
                                .height(32.dp))
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp, 12.dp, 16.dp, 4.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.ic_info_red),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(8.dp)
                                    )
                                    Text(
                                        text = stringResource(id = R.string.openInvestmentAccountInfoText),
                                        lineHeight = TextUnit(1.4f, TextUnitType.Em),
                                        fontWeight = FontWeight.W400,
                                        fontSize = 14.sp,
                                    )
                                }
                                MainActivity.horizontalDivider()
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp, 0.dp, 16.dp, 0.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Okudum, onaylıyorum."
                                    )
                                    Spacer(modifier = Modifier
                                        .weight(1f))
                                    Switch(
                                        checked = checkedOpenInvestmentAccount.value,
                                        onCheckedChange = {
                                            checkedOpenInvestmentAccount.value = it
                                        },
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.White,
                                            checkedTrackColor = colorResource(id = R.color.checked_switch),
                                            uncheckedThumbColor = Color.White,
                                            uncheckedTrackColor = colorResource(id = R.color.unchecked_switch),
                                            uncheckedBorderColor = Color.Transparent,
                                            checkedBorderColor = Color.Transparent
                                        ),
                                        interactionSource = remember { MutableInteractionSource() },
                                        modifier = Modifier
                                            .indication(
                                                interactionSource = remember { MutableInteractionSource() },
                                                indication = null
                                            )
                                    )
                                }
                            }
                            Spacer(modifier = Modifier
                                .height(16.dp))
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    enabled = checkedOpenInvestmentAccount.value && selectExchangeType.selectedExchangeType != "Seçiniz",
                                    onClick = {
                                        navController.navigate("page37")
                                    }
                                ),
                                horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "Onay",
                                    color = colorResource(id = R.color.golden_global),
                                    fontWeight = FontWeight.W700,
                                    modifier = Modifier
                                        .padding(top = 8.dp, bottom = 8.dp)
                                )
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun openInvestmentAccountRow(
            navController: NavController,
            text1: String,
            text2: String,
            hasTextField: Boolean
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = text2 == selectExchangeType.selectedExchangeType,
                    onClick = {
                        navController.navigate("page36")
                    }
                ),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text1,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                if (!hasTextField) {
                    Text(
                        text = text2,
                        fontSize = 14.sp,
                        color = if (text2 == "Seçiniz") colorResource(id = R.color.extra_faded) else Color.Black
                    )
                    if (text2 == selectExchangeType.selectedExchangeType) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_right),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 8.dp)
                        )
                    }
                } else {
                    BasicTextField(
                        value = investmentAccountName.value,
                        onValueChange = {
                            investmentAccountName.value = it
                        },
                        singleLine = true,
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        decorationBox = { innerTextField ->
                            if (investmentAccountName.value.isEmpty()) {
                                Text(
                                    text = "İsteğe Bağlı",
                                    color = colorResource(id = R.color.extra_faded),
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            }
                            innerTextField()
                        }
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun openInvestmentAccountPreview() {
        GoldenGlobalMobileTheme {
            openInvestmentAccount(navController = rememberNavController())
        }
    }
}