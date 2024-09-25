package emir.mut.GoldenGlobalMobile.investment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.exchangeType
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class selectExchangeType {
    companion object {
        var currentExchangeType: MutableState<Int> = mutableStateOf(Int.MAX_VALUE)
        var exchangeTypes = listOf(
            exchangeType.exchangeTypes1[0], // Türk Lirası
            exchangeType.exchangeTypes1[1], // Dolar
            exchangeType.exchangeTypes1[2] // Euro
        )
        var selectedExchangeType: String = "Seçiniz"
        @Composable
        fun selectExchangeType(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Para Birimi Seçimi"
                            )
                        },
                        haveNavigationIcon = false,
                        haveActionIcon = true,
                        navigationIcon = {},
                        onNavigationIconClick = {},
                        actionIcon = {
                            topBar.close_icon()
                        },
                        onActionIconClick = {
                            navController.popBackStack()
                        }
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(top = 16.dp)) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 0.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            exchangeTypes.forEachIndexed { index, exchangeType ->
                                selectExchangeTypeOption(
                                    exchangeType = exchangeType,
                                    isSelected = currentExchangeType.value == index,
                                    onClick = {
                                        currentExchangeType.value = index
                                        selectedExchangeType = exchangeType
                                        navController.popBackStack()
                                    }
                                )
                                if (index != exchangeTypes.size - 1) {
                                    MainActivity.horizontalDivider()
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun selectExchangeTypeOption(
            exchangeType: String,
            isSelected: Boolean,
            onClick: () -> Unit
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 0.dp, 4.dp)
                .background(Color.White)
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
    }

    @Preview(showBackground = true)
    @Composable
    fun selectExchangeTypePreview() {
        GoldenGlobalMobileTheme {
            selectExchangeType(navController = rememberNavController())
        }
    }
}