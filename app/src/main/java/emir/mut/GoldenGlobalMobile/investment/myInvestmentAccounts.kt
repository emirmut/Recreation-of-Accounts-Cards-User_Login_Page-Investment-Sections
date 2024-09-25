package emir.mut.GoldenGlobalMobile.investment

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.account
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.exchangeType
import emir.mut.GoldenGlobalMobile.navigationDrawer
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class myInvestmentAccounts {
    companion object {
        var investmentAccounts = mutableStateListOf<account>()
        @Composable
        fun myInvestmentAccounts(navController: NavController) {
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
                                    text = "Yatırım Hesaplarım"
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
                            .padding(top = if (investmentAccounts.size != 0) 32.dp else 0.dp)) {
                            if (investmentAccounts.size != 0) {
                                LazyColumn(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(10.dp)
                                    )) {
                                    items(investmentAccounts) { investmentAccount ->
                                        myInvestmentAccountsRow(
                                            investmentAccount = investmentAccount,
                                            accountNumber = investmentAccount.accountNumber.toString(),
                                            accountBalance = investmentAccount.accountBalance
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier
                                    .height(32.dp))
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(10.dp)
                                    )
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            navController.navigate("page35")
                                        }
                                    ),
                                    horizontalArrangement = Arrangement.Center) {
                                    Text(
                                        text = "Yatırım Hesabı Aç",
                                        color = colorResource(id = R.color.golden_global),
                                        fontWeight = FontWeight.W700,
                                        modifier = Modifier
                                            .padding(top = 8.dp, bottom = 8.dp)
                                    )
                                }
                            } else {
                                Spacer(modifier = Modifier
                                    .weight(1f))
                                Column(modifier = Modifier
                                    .weight(1f)) {
                                    Text(
                                        text = stringResource(id = R.string.myInvestmentAccountsNoAccountsText),
                                        fontWeight = FontWeight.W700,
                                        lineHeight = TextUnit(1.3f, TextUnitType.Em),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                            .padding(24.dp, 0.dp, 24.dp, 16.dp)
                                    )
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp, 0.dp, 16.dp, 0.dp)
                                        .background(colorResource(id = R.color.golden_global))
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            onClick = {
                                                navController.navigate("page35")
                                            }
                                        ),
                                        horizontalArrangement = Arrangement.Center) {
                                        Text(
                                            text = "Yatırım Hesabı Aç",
                                            color = Color.White,
                                            fontWeight = FontWeight.W700,
                                            modifier = Modifier
                                                .padding(top = 16.dp, bottom = 16.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier
                                    .weight(1f))
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun myInvestmentAccountsRow(
            investmentAccount: account,
            accountNumber: String,
            accountBalance: Double
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()) {
                Text(
                    text = accountNumber,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = String.format("%.2f", accountBalance) + " " + exchangeType.getExchangeTypeAbbreviation(investmentAccount),
                        fontSize = 14.sp
                    )
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun myInvestmentAccountsPreview() {
        GoldenGlobalMobileTheme {
            myInvestmentAccounts(navController = rememberNavController())
        }
    }
}