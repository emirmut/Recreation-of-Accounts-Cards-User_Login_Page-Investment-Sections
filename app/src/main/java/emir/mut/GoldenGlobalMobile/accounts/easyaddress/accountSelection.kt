package emir.mut.GoldenGlobalMobile.accounts.easyaddress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.myAccounts
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class accountSelection {
    companion object {
        @Composable
        fun accountSelection(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Hesap Seçimi"
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
                        .padding(top = 32.dp)) {
                        items(myAccounts.accounts) { account ->
                            myAccounts.accountInstance(
                                account = account,
                                onAccountClick = {
                                    navController.popBackStack()
                                    account.isSelected = true
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

    @Preview(showBackground = true)
    @Composable
    fun accountSelectionPreview() {
        GoldenGlobalMobileTheme {
            accountSelection(navController = rememberNavController())
        }
    }
}