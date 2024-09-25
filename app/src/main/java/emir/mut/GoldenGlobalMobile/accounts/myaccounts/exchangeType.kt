package emir.mut.GoldenGlobalMobile.accounts.myaccounts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class exchangeType {
    companion object {
        var currentExchangeType: MutableState<Int> = mutableStateOf(Int.MAX_VALUE)
        var exchangeTypes1 = listOf(
            "Türk Lirası",
            "Amerikan Doları",
            "Euro",
            "Birleşik Arap Emirlikleri Dirhemi"
        )
        var abbreviationOfTypes1 = listOf(
            "TL",
            "USD",
            "EUR",
            "AED"
        )
        var exchangeTypes2 = listOf(
            "Gümüş",
            "Altın",
            "Platin",
            "Çeyrek Altın (22 ayar)"
        )
        var abbreviationOfTypes2 = listOf(
            "XAG", // Gümüş
            "XAU", // Altın
            "XPT", // Platin
            "XQAU" // Çeyrek Altın
        )
        @Composable
        fun exchangeType(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = if (openAccount.selectedAccountType.value != "Kıymetli Maden Hesabı") "Döviz Türü" else "Kıymetli Maden Türü"
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
                        if (openAccount.selectedAccountType.value != "Kıymetli Maden Hesabı") {
                            exchangeTypes1.forEachIndexed { index, exchangeType ->
                                exchangeTypeOption(
                                    exchangeType = exchangeType,
                                    isSelected = currentExchangeType.value == index,
                                    onClick = {
                                        currentExchangeType.value = index
                                        openAccount.selectedExchangeType.value = exchangeType
                                    }
                                )
                            }
                        } else {
                            exchangeTypes2.forEachIndexed { index, exchangeType ->
                                exchangeTypeOption(
                                    exchangeType = exchangeType,
                                    isSelected = currentExchangeType.value == index,
                                    onClick = {
                                        currentExchangeType.value = index
                                        openAccount.selectedExchangeType.value = exchangeType
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun exchangeTypeOption(
            exchangeType: String,
            isSelected: Boolean,
            onClick: () -> Unit
        ) {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .clickable {
                    onClick()
                }) {
                Text(
                    text = exchangeType,
                    fontSize = 14.sp
                )
                if (isSelected) {
                    Spacer(modifier = Modifier
                        .weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_done_red),
                        contentDescription = ""
                    )
                }
            }
        }

        fun getExchangeTypeAbbreviation(account: account): String {
            return when (account.exchangeType) {
                exchangeTypes1.first() -> {
                    abbreviationOfTypes1.first()
                }
                exchangeTypes1[1] -> {
                    abbreviationOfTypes1[1]
                }
                exchangeTypes1[2] -> {
                    abbreviationOfTypes1[2]
                }
                exchangeTypes1.last() -> {
                    abbreviationOfTypes1.last()
                }
                exchangeTypes2.first() -> {
                    abbreviationOfTypes2[0]
                }
                exchangeTypes2[1] -> {
                    abbreviationOfTypes2[1]
                }
                exchangeTypes2[2] -> {
                    abbreviationOfTypes2[2]
                }
                else -> {
                    abbreviationOfTypes2.last()
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun exchangeTypePreview() {
        GoldenGlobalMobileTheme {
            exchangeType(navController = rememberNavController())
        }
    }
}