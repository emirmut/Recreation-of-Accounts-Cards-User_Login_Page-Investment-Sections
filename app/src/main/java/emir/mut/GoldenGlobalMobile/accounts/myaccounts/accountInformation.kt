package emir.mut.GoldenGlobalMobile.accounts.myaccounts

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import java.text.SimpleDateFormat
import java.util.Date

class accountInformation {
    companion object {
        @SuppressLint("SimpleDateFormat")
        @Composable
        fun accountInformation(navController: NavController) {
            // var calendar = Calendar.getInstance().time
            // var dateFormat = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar) // 01.01.1970
            var simpleDateFormat = SimpleDateFormat("dd.MM.yyyy") // current_day.current_month.current_year
            var currentDate = simpleDateFormat.format(Date())
            // var simpleTimeFormat = SimpleDateFormat("HH:mm:ss z") // current_hour.current_minute.current_second.current_timezone
            // var currentTime = simpleTimeFormat.format(Date())
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Hesap Bilgileri"
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
                        accountInformationRow(
                            text1 = "Açılış Tarihi",
                            text2 = currentDate
                        )
                        accountInformationRow(
                            text1 = "Hesap Türü",
                            text2 = openAccount.selectedAccountType.value
                        )
                        if (openAccount.accountName.value != "") {
                            accountInformationRow(
                                text1 = "Hesap Adı",
                                text2 = openAccount.accountName.value
                            )
                        }
                        accountInformationRow(
                            text1 = "Şube",
                            text2 = "Sanal Şube"
                        )
                        accountInformationRow(
                            text1 = "Döviz Türü",
                            text2 = openAccount.selectedExchangeType.value
                        )
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        val interactionSource = remember { MutableInteractionSource() }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = {
                                    navController.navigate("page14")
                                    myAccounts.accounts.add(
                                        account(
                                            accountNumber = openAccount.accountNumber,
                                            accountBalance = openAccount.accountBalance,
                                            accountType = openAccount.selectedAccountType.value,
                                            exchangeType = openAccount.selectedExchangeType.value,
                                            accountName = openAccount.accountName.value,
                                            IBANnumber = openAccount.IBANnumber,
                                            isSelected = false
                                        )
                                    )
                                    openAccount.accountNumber += 1
                                }
                            ),
                            horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "Onay",
                                color = colorResource(id = R.color.golden_global)
                            )
                        }
                    }
                }
            )
        }

        @Composable
        fun accountInformationRow(
            text1: String,
            text2: String
        ) {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp, 8.dp, 16.dp, 8.dp)) {
                Text(
                    text = text1,
                    color = colorResource(id = R.color.subtitle_text_color),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Text(
                    text = text2,
                    fontSize = 14.sp
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun accountInformationPreview() {
        GoldenGlobalMobileTheme {
            accountInformation(navController = rememberNavController())
        }
    }
}