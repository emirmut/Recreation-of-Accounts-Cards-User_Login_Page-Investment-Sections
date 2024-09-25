package emir.mut.GoldenGlobalMobile.accounts.myaccounts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
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

class myAccounts {
    companion object {
        var accounts = mutableStateListOf<account>()
        @Composable
        fun myAccounts(navController: NavController) {
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
                                    text = "Hesaplarım"
                                )
                            },
                            haveNavigationIcon = true,
                            haveActionIcon = true,
                            navigationIcon = {
                                topBar.hamburger_icon()
                            },
                            onNavigationIconClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            },
                            actionIcon = {
                                topBar.plus_icon()
                            },
                            onActionIconClick = {
                                navController.navigate("page7")
                            }
                        )
                    },
                    content = { innerPadding ->
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.app_background))
                            .padding(innerPadding)
                            .padding(top = 32.dp)
                        ) {
                            var totalBalance = accounts.sumOf {
                                it.accountBalance
                            }
                            item {
                                MainActivity.horizontalDivider()
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .padding(16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(
                                        text = "Toplam:",
                                        color = colorResource(id = R.color.subtitle_text_color),
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = String.format("%.2f TL", totalBalance),
                                        fontWeight = FontWeight.W600
                                    )
                                }
                            }
                            items(accounts) { account ->
                                accountInstance(
                                    account = account,
                                    onAccountClick = {
                                        navController.navigate("page15")
                                    }
                                )
                            }
                            item {
                                MainActivity.horizontalDivider()
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun accountInstance(
            account: account,
            onAccountClick: (account) -> Unit
        ) {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
                .clickable {
                    onAccountClick(account)
                },
                verticalAlignment = if (account.accountName == "") Alignment.CenterVertically else Alignment.Top) {
                Column(verticalArrangement = Arrangement.Center) {
                    if (account.accountName != "") {
                        Text(
                            text = account.accountName,
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.golden_global)
                        )
                    }
                    Text(
                        text = String.format("%d", account.accountNumber),
                        color = colorResource(id = R.color.account_number),
                        fontSize = 18.sp
                    )
                }
                Spacer(modifier = Modifier
                    .weight(1f))
                Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
                    Spacer(modifier = Modifier
                        .weight(1f))
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = String.format("%.2f", account.accountBalance) +  " " + exchangeType.getExchangeTypeAbbreviation(account),
                            color = colorResource(id = R.color.account_balance),
                            fontWeight = FontWeight.W700,
                            fontSize = 18.sp,
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = account.accountType,
                            color = colorResource(id = R.color.account_type),
                            modifier = Modifier
                                .align(Alignment.End)
                                .width(160.dp)
                                .wrapContentWidth(align = Alignment.End),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.account_type)),
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun myAccountsPreview() {
        GoldenGlobalMobileTheme {
            myAccounts(navController = rememberNavController())
        }
    }
}