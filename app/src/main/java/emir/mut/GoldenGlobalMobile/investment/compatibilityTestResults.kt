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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.navigationDrawer
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class compatibilityTestResults {
    companion object {
        var checkedApproveCompatibilityTestResult: MutableState<Boolean> = mutableStateOf(false)
        var suitableResults = mutableSetOf<String>()
        var notSuitableResults = mutableSetOf<String>()
        @Composable
        fun compatibilityTestResults(navController: NavController) {
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
                                    text = "Uygunluk Testi"
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
                            .padding(16.dp, 16.dp, 16.dp, 0.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(16.dp, 0.dp, 16.dp, 0.dp)) {
                                compatibilityTestRiskTypes.typesOfRisks.forEachIndexed { index, riskType ->
                                    compatibilityTestResultsRow(
                                        compatibilityTestRiskType = riskType,
                                        suitability = getSuitability(riskType = riskType)
                                    )
                                    if (index != compatibilityTestRiskTypes.typesOfRisks.size - 1) {
                                        MainActivity.horizontalDivider()
                                    }
                                    if (getSuitability(riskType = riskType) == "Uygun") {
                                        suitableResults.add(riskType)
                                    } else {
                                        notSuitableResults.add(riskType)
                                    }
                                }
                            }
                            compatibilityTestResultsInfoSection(
                                text = stringResource(id = R.string.compatibilityTestResultsInfoText),
                                hasSwitch = false
                            )
                            compatibilityTestResultsInfoSection(
                                text = stringResource(id = R.string.compatibilityTestResultsInfoText2),
                                hasSwitch = true
                            )
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                .padding(top = 8.dp, bottom = 8.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    enabled = checkedApproveCompatibilityTestResult.value,
                                    onClick = {
                                        navController.navigate("page52")
                                        compatibilityTest.isCompatibilityTestIsDone = true
                                    }
                                ),
                                horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "Onay",
                                    color = colorResource(id = R.color.golden_global),
                                    fontWeight = FontWeight.W700
                                )
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun compatibilityTestResultsRow(
            compatibilityTestRiskType: String,
            suitability: String
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 12.dp, 8.dp, 12.dp)) {
                Text(
                    text = compatibilityTestRiskType
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Text(
                    text = suitability,
                    color = if (suitability == "Uygun") colorResource(id = R.color.suitable) else colorResource(id = R.color.not_suitable)
                )
            }
        }

        @Composable
        fun compatibilityTestResultsInfoSection(
            text: String,
            hasSwitch: Boolean
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 12.dp, 16.dp, if (!hasSwitch) 16.dp else 4.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_info_red),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Text(
                        text = text,
                        lineHeight = TextUnit(1.4f, TextUnitType.Em),
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                    )
                }
                if (hasSwitch) {
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
                            checked = checkedApproveCompatibilityTestResult.value,
                            onCheckedChange = {
                                checkedApproveCompatibilityTestResult.value = it
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
            }
        }

        fun getSuitability(riskType: String): String {
            if (compatibilityTestRiskTypes.typesOfRisks[0] == riskType) {
                if ((productInformationSelection.selectedProductInformationForVeryLowRisk.value == productInformationSelection.productInformationOptions[0])
                    || (productInformationSelection.selectedProductInformationForVeryLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[0]
                            && volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForVeryLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[1]
                            && volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForVeryLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value == volumeInformationSelection.volumeInformationOptions[1])
                    || (productInformationSelection.selectedProductInformationForVeryLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForVeryLowRisk.value == productInformationSelection.productInformationOptions[2]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])) {
                    return "Uygun Değil"
                } else {
                    return "Uygun"
                }
            }
            else if (compatibilityTestRiskTypes.typesOfRisks[1] == riskType) {
                if ((productInformationSelection.selectedProductInformationForLowRisk.value == productInformationSelection.productInformationOptions[0])
                    || (productInformationSelection.selectedProductInformationForLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[0]
                            && volumeInformationSelection.selectedVolumeInformationForLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[1]
                            && volumeInformationSelection.selectedVolumeInformationForLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForLowRisk.value == volumeInformationSelection.volumeInformationOptions[1])
                    || (productInformationSelection.selectedProductInformationForLowRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForLowRisk.value == productInformationSelection.productInformationOptions[2]
                            && transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForLowRisk.value == volumeInformationSelection.volumeInformationOptions[2])) {
                    return "Uygun Değil"
                } else {
                    return "Uygun"
                }
            } else if (compatibilityTestRiskTypes.typesOfRisks[2] == riskType) {
                if ((productInformationSelection.selectedProductInformationForMediumRisk.value == productInformationSelection.productInformationOptions[0])
                    || (productInformationSelection.selectedProductInformationForMediumRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value == transactionFrequencySelection.transactionFrequencyOptions[0]
                            && volumeInformationSelection.selectedVolumeInformationForMediumRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForMediumRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value == transactionFrequencySelection.transactionFrequencyOptions[1]
                            && volumeInformationSelection.selectedVolumeInformationForMediumRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForMediumRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForMediumRisk.value == volumeInformationSelection.volumeInformationOptions[1])
                    || (productInformationSelection.selectedProductInformationForMediumRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForMediumRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForMediumRisk.value == productInformationSelection.productInformationOptions[2]
                            && transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value  == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForMediumRisk.value == volumeInformationSelection.volumeInformationOptions[2])) {
                    return "Uygun Değil"
                } else {
                    return "Uygun"
                }
            } else if (compatibilityTestRiskTypes.typesOfRisks[3] == riskType) {
                if ((productInformationSelection.selectedProductInformationForHighRisk.value == productInformationSelection.productInformationOptions[0])
                    || (productInformationSelection.selectedProductInformationForHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[0]
                            && volumeInformationSelection.selectedVolumeInformationForHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[1]
                            && volumeInformationSelection.selectedVolumeInformationForHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForHighRisk.value == volumeInformationSelection.volumeInformationOptions[1])
                    || (productInformationSelection.selectedProductInformationForHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForHighRisk.value == productInformationSelection.productInformationOptions[2]
                            && transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])) {
                    return "Uygun Değil"
                } else {
                    return "Uygun"
                }
            } else {
                if ((productInformationSelection.selectedProductInformationForVeryHighRisk.value == productInformationSelection.productInformationOptions[0])
                    || (productInformationSelection.selectedProductInformationForVeryHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[0]
                            && volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForVeryHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[1]
                            && volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForVeryHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value == volumeInformationSelection.volumeInformationOptions[1])
                    || (productInformationSelection.selectedProductInformationForVeryHighRisk.value == productInformationSelection.productInformationOptions[1]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])
                    || (productInformationSelection.selectedProductInformationForVeryHighRisk.value == productInformationSelection.productInformationOptions[2]
                            && transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value == transactionFrequencySelection.transactionFrequencyOptions[2]
                            && volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value == volumeInformationSelection.volumeInformationOptions[2])) {
                    return "Uygun Değil"
                } else {
                    return "Uygun"
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun compatibilityTestResultsPreview() {
        GoldenGlobalMobileTheme {
            compatibilityTestResults(navController = rememberNavController())
        }
    }
}