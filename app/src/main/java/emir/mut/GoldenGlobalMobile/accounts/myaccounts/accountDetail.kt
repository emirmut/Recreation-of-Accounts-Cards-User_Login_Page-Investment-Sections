package emir.mut.GoldenGlobalMobile.accounts.myaccounts

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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class accountDetail {
    companion object {
        @Composable
        fun accountDetail(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Hesap Detayı"
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
                        .padding(top = 24.dp)) {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                            horizontalArrangement = Arrangement.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy((-6).dp)) {
                                if (myAccounts.accounts.last().accountName != "") {
                                    Text(
                                        text = myAccounts.accounts.last().accountName,
                                        color = colorResource(id = R.color.golden_global),
                                        fontSize = 14.sp
                                    )
                                }
                                Text(
                                    text = myAccounts.accounts.last().accountNumber.toString() + " " +
                                            myAccounts.accounts.last().accountType + " " +
                                            myAccounts.accounts.last().exchangeType,
                                    fontWeight = FontWeight.W800
                                )
                                Text(
                                    text = "Sanal Şube"
                                )
                            }
                        }
                        accountDetailRow(
                            text1 = "Bakiye",
                            text2 = String.format("%.2f", myAccounts.accounts.last().accountBalance) + " " + exchangeType.getExchangeTypeAbbreviation(myAccounts.accounts.last()),
                            hasIcon = false
                        )
                        accountDetailRow(
                            text1 = "IBAN",
                            text2 = myAccounts.accounts.last().IBANnumber,
                            hasIcon = true
                        )
                        MainActivity.horizontalDivider()
                    }
                }
            )
        }

        @Composable
        fun accountDetailRow(
            text1: String,
            text2: String,
            hasIcon: Boolean
        ) {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)) {
                Text(
                    text = text1,
                    color = colorResource(id = R.color.subtitle_text_color)
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Text(
                    text = text2,
                    fontSize = 14.sp
                )
                val interactionSource = remember { MutableInteractionSource() }
                if (hasIcon) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_share),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    // share IBAN to the people
                                }
                            )
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun accountDetailPreview() {
        GoldenGlobalMobileTheme {
            accountDetail(navController = rememberNavController())
        }
    }
}