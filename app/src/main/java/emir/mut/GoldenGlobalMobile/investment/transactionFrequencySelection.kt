package emir.mut.GoldenGlobalMobile.investment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

class transactionFrequencySelection {
    companion object {
        var transactionFrequencyOptions = listOf(
            "Yılda Birkaç Kez",
            "Ayda Birkaç Kez",
            "Haftada Birkaç Kez"
        )
        var selectedTransactionFrequencyForVeryLowRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedTransactionFrequencyForLowRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedTransactionFrequencyForMediumRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedTransactionFrequencyForHighRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedTransactionFrequencyForVeryHighRisk: MutableState<String> = mutableStateOf("Seçiniz")
        @Composable
        fun transactionFrequencySelection(navController: NavController,) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "İşlem Sıklığı Seçimi"
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
                        .padding(16.dp, 16.dp, 16.dp, 0.dp)) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            transactionFrequencyOptions.forEachIndexed { index, transactionFrequencyOption ->
                                transactionFrequencySelectionRow(
                                    transactionFrequency = transactionFrequencyOption,
                                    onClick = {
                                        when (compatibilityTestRiskTypes.currentRiskType.value) {
                                            compatibilityTestRiskTypes.typesOfRisks[0] -> selectedTransactionFrequencyForVeryLowRisk.value = transactionFrequencyOption
                                            compatibilityTestRiskTypes.typesOfRisks[1] -> selectedTransactionFrequencyForLowRisk.value = transactionFrequencyOption
                                            compatibilityTestRiskTypes.typesOfRisks[2] -> selectedTransactionFrequencyForMediumRisk.value = transactionFrequencyOption
                                            compatibilityTestRiskTypes.typesOfRisks[3] -> selectedTransactionFrequencyForHighRisk.value = transactionFrequencyOption
                                            compatibilityTestRiskTypes.typesOfRisks.last() -> selectedTransactionFrequencyForVeryHighRisk.value = transactionFrequencyOption
                                        }
                                        navController.popBackStack()
                                    }
                                )
                                if (index != transactionFrequencyOptions.size - 1) {
                                    MainActivity.horizontalDivider()
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun transactionFrequencySelectionRow(
            transactionFrequency: String,
            onClick: () -> Unit
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onClick()
                    }
                )) {
                Text(
                    text = transactionFrequency,
                    fontSize = 14.sp
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun transactionFrequencySelectionPreview() {
        GoldenGlobalMobileTheme {
            transactionFrequencySelection(navController = rememberNavController())
        }
    }
}