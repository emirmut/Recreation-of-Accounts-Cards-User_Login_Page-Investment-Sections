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
import androidx.compose.ui.Alignment
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

class accountType {
    companion object {
        var currentAccountType: MutableState<Int> = mutableStateOf(Int.MAX_VALUE)
        var accountTypes = listOf(
            "Cari Hesap",
            "Standard Prepaid Kart",
            "Kıymetli Maden Hesabı"
        )
        @Composable
        fun accountType(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Hesap Türü"
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
                        Row(modifier = Modifier
                            .padding(16.dp, 8.dp, 0.dp, 4.dp)) {
                            Text(
                                text = "HESAP TÜRLERİ",
                                fontSize = 17.sp,
                                color = colorResource(id = R.color.account_types)
                            )
                        }
                        accountTypes.forEachIndexed { index, accountType ->
                            accountTypeOption(
                                navController = navController,
                                accountType = accountType,
                                isSelected = currentAccountType.value == index,
                                onClick = {
                                    currentAccountType.value = index
                                    openAccount.selectedAccountType.value = accountType
                                }
                            )
                        }
                    }
                }
            )
        }

        @Composable
        fun accountTypeOption(
            navController: NavController,
            accountType: String,
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
                },
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = accountType,
                    fontSize = 14.sp
                )
                if (isSelected) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_done_red),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier
                    .weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_info_red),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable {
                            if (accountType == accountTypes[0]) {
                                navController.navigate("page10")
                            } else if (accountType == accountTypes[1]) {
                                navController.navigate("page11")
                            } else {
                                navController.navigate("page12")
                            }
                        }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun accountTypePreview() {
        GoldenGlobalMobileTheme {
            accountType(navController = rememberNavController())
        }
    }
}