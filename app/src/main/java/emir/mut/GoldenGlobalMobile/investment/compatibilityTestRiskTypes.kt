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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
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
import emir.mut.GoldenGlobalMobile.navigationDrawer
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class compatibilityTestRiskTypes {
    companion object {
        var typesOfRisks = listOf(
            "Çok Düşük Riskli",
            "Düşük Riskli",
            "Orta Riskli",
            "Yüksek Riskli",
            "Çok Yüksek Riskli"
        )
        var currentRiskType: MutableState<String> = mutableStateOf(typesOfRisks[0])
        var isVeryLowRiskExpanded: MutableState<Boolean> = mutableStateOf(true)
        var isLowRiskExpanded: MutableState<Boolean> = mutableStateOf(false)
        var isMediumRiskExpanded: MutableState<Boolean> = mutableStateOf(false)
        var isHighRiskExpanded: MutableState<Boolean> = mutableStateOf(false)
        var isVeryHighRiskExpanded: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun compatibilityTestRiskTypes(navController: NavController) {
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
                            actionIcon = {},
                            onNavigationIconClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            },
                            onActionIconClick = {}
                        )
                    },
                    content = { innerPadding ->
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.app_background))
                            .padding(innerPadding)
                            .padding(32.dp, 24.dp, 32.dp, 0.dp)) {
                            item {
                                Text(
                                    text = stringResource(id = R.string.compatibilityTestRiskText),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.W500,
                                    lineHeight = TextUnit(1.4f, TextUnitType.Em),
                                    modifier = Modifier
                                        .padding(bottom = 24.dp)
                                )
                            }
                            item {
                                typesOfRisks.forEach { riskType ->
                                    when (riskType) {
                                        typesOfRisks[0] -> {
                                            riskTypeSection(
                                                navController = navController,
                                                title = riskType,
                                                isExpanded = isVeryLowRiskExpanded,
                                                onClick = {
                                                    expandHandler(riskType = riskType)
                                                },
                                                subText = stringResource(id = R.string.typesOfRisksSubTexts0),
                                                productInformationSelectionText = productInformationSelection.selectedProductInformationForVeryLowRisk.value,
                                                transactionFrequencyText = transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value,
                                                volumeInformationText = volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value
                                            )
                                        }
                                        typesOfRisks[1] -> {
                                            riskTypeSection(
                                                navController = navController,
                                                title = riskType,
                                                isExpanded = isLowRiskExpanded,
                                                onClick = {
                                                    expandHandler(riskType = riskType)
                                                },
                                                subText = stringResource(id = R.string.typesOfRisksSubTexts1),
                                                productInformationSelectionText = productInformationSelection.selectedProductInformationForLowRisk.value,
                                                transactionFrequencyText = transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value,
                                                volumeInformationText = volumeInformationSelection.selectedVolumeInformationForLowRisk.value
                                            )
                                        }
                                        typesOfRisks[2] -> {
                                            riskTypeSection(
                                                navController = navController,
                                                title = riskType,
                                                isExpanded = isMediumRiskExpanded,
                                                onClick = {
                                                    expandHandler(riskType = riskType)
                                                },
                                                subText = stringResource(id = R.string.typesOfRisksSubTexts2),
                                                productInformationSelectionText = productInformationSelection.selectedProductInformationForMediumRisk.value,
                                                transactionFrequencyText = transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value,
                                                volumeInformationText = volumeInformationSelection.selectedVolumeInformationForMediumRisk.value
                                            )
                                        }
                                        typesOfRisks[3] -> {
                                            riskTypeSection(
                                                navController = navController,
                                                title = riskType,
                                                isExpanded = isHighRiskExpanded,
                                                onClick = {
                                                    expandHandler(riskType = riskType)
                                                },
                                                subText = stringResource(id = R.string.typesOfRisksSubTexts3),
                                                productInformationSelectionText = productInformationSelection.selectedProductInformationForHighRisk.value,
                                                transactionFrequencyText = transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value,
                                                volumeInformationText = volumeInformationSelection.selectedVolumeInformationForHighRisk.value
                                            )
                                        }
                                        typesOfRisks.last() -> {
                                            riskTypeSection(
                                                navController = navController,
                                                title = riskType,
                                                isExpanded = isVeryHighRiskExpanded,
                                                onClick = {
                                                    expandHandler(riskType = riskType)
                                                },
                                                subText = stringResource(id = R.string.typesOfRisksSubTexts4),
                                                productInformationSelectionText = productInformationSelection.selectedProductInformationForVeryHighRisk.value,
                                                transactionFrequencyText = transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value,
                                                volumeInformationText = volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value
                                            )
                                        }
                                    }
                                }
                            }
                            item {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                                    .padding(top = 8.dp, bottom = 8.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        enabled = productInformationSelection.selectedProductInformationForVeryLowRisk.value != "Seçiniz" && productInformationSelection.selectedProductInformationForLowRisk.value != "Seçiniz" && productInformationSelection.selectedProductInformationForMediumRisk.value != "Seçiniz" && productInformationSelection.selectedProductInformationForHighRisk.value != "Seçiniz" && productInformationSelection.selectedProductInformationForVeryHighRisk.value != "Seçiniz" && transactionFrequencySelection.selectedTransactionFrequencyForVeryLowRisk.value != "Seçiniz" && transactionFrequencySelection.selectedTransactionFrequencyForLowRisk.value != "Seçiniz" && transactionFrequencySelection.selectedTransactionFrequencyForMediumRisk.value != "Seçiniz" && transactionFrequencySelection.selectedTransactionFrequencyForHighRisk.value != "Seçiniz" && transactionFrequencySelection.selectedTransactionFrequencyForVeryHighRisk.value != "Seçiniz" && volumeInformationSelection.selectedVolumeInformationForVeryLowRisk.value != "Seçiniz" && volumeInformationSelection.selectedVolumeInformationForLowRisk.value != "Seçiniz" && volumeInformationSelection.selectedVolumeInformationForMediumRisk.value != "Seçiniz" && volumeInformationSelection.selectedVolumeInformationForHighRisk.value != "Seçiniz" && volumeInformationSelection.selectedVolumeInformationForVeryHighRisk.value != "Seçiniz",
                                        onClick = {
                                            navController.navigate("page51")
                                        }
                                    ),
                                    horizontalArrangement = Arrangement.Center) {
                                    Text(
                                        text = "İleri",
                                        color = colorResource(id = R.color.golden_global)
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun riskTypeSection(
            navController: NavController,
            title: String,
            isExpanded: MutableState<Boolean>,
            onClick: () -> Unit,
            subText: String,
            productInformationSelectionText: String,
            transactionFrequencyText: String,
            volumeInformationText: String,
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onClick()
                    }
                )) {
                Text(
                    text = title,
                    color = colorResource(id = R.color.risk_types),
                    fontWeight = FontWeight.W500,
                    modifier = Modifier
                        .padding(bottom = if (isExpanded.value) 4.dp else 0.dp)
                )
                if (isExpanded.value) {
                    Text(
                        text = subText,
                        fontSize = 15.sp,
                        lineHeight = TextUnit(1.3f, TextUnitType.Em),
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                    )
                    riskTypeSectionOptions(
                        title = "Ürün Bilgisi",
                        option = productInformationSelectionText,
                        onClick = {
                            when (title) {
                                typesOfRisks[0] -> currentRiskType.value = typesOfRisks[0]
                                typesOfRisks[1] -> currentRiskType.value = typesOfRisks[1]
                                typesOfRisks[2] -> currentRiskType.value = typesOfRisks[2]
                                typesOfRisks[3] -> currentRiskType.value = typesOfRisks[3]
                                typesOfRisks.last() -> currentRiskType.value = typesOfRisks.last()
                            }
                            navController.navigate("page47")
                        }
                    )
                    riskTypeSectionOptions(
                        title = "İşlem Sıklığı",
                        option = transactionFrequencyText,
                        onClick = {
                            when (title) {
                                typesOfRisks[0] -> currentRiskType.value = typesOfRisks[0]
                                typesOfRisks[1] -> currentRiskType.value = typesOfRisks[1]
                                typesOfRisks[2] -> currentRiskType.value = typesOfRisks[2]
                                typesOfRisks[3] -> currentRiskType.value = typesOfRisks[3]
                                typesOfRisks.last() -> currentRiskType.value = typesOfRisks.last()
                            }
                            navController.navigate("page48")
                        }
                    )
                    riskTypeSectionOptions(
                        title = "Hacim Bilgisi (TL)",
                        option = volumeInformationText,
                        onClick = {
                            when (title) {
                                typesOfRisks[0] -> currentRiskType.value = typesOfRisks[0]
                                typesOfRisks[1] -> currentRiskType.value = typesOfRisks[1]
                                typesOfRisks[2] -> currentRiskType.value = typesOfRisks[2]
                                typesOfRisks[3] -> currentRiskType.value = typesOfRisks[3]
                                typesOfRisks.last() -> currentRiskType.value = typesOfRisks.last()
                            }
                            navController.navigate("page49")
                        }
                    )
                } else {
                    Text(
                        text = "...",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier
                            .offset(x = 0.dp, y = (-10).dp)
                    )
                }
            }
        }

        @Composable
        fun riskTypeSectionOptions(
            title: String,
            option: String,
            onClick: () -> Unit
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.W800,
                fontSize = 17.sp,
                modifier = Modifier
                    .padding(bottom = 12.dp)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onClick()
                    }
                ),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = option,
                    color = if (option == "Seçiniz") colorResource(id = R.color.color_7F8084) else Color.Black,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "",
                    modifier = Modifier
                        .size(13.dp)
                )
            }
        }

        private fun expandHandler(riskType: String) {
            when (riskType) {
                typesOfRisks[0] -> {
                    isVeryLowRiskExpanded.value = !isVeryLowRiskExpanded.value
                    if (isVeryLowRiskExpanded.value) {
                        isLowRiskExpanded.value = false
                        isMediumRiskExpanded.value = false
                        isHighRiskExpanded.value = false
                        isVeryHighRiskExpanded.value = false
                    }
                }
                typesOfRisks[1] -> {
                    isLowRiskExpanded.value = !isLowRiskExpanded.value
                    if (isLowRiskExpanded.value) {
                        isVeryLowRiskExpanded.value = false
                        isMediumRiskExpanded.value = false
                        isHighRiskExpanded.value = false
                        isVeryHighRiskExpanded.value = false
                    }
                }
                typesOfRisks[2] -> {
                    isMediumRiskExpanded.value = !isMediumRiskExpanded.value
                    if (isMediumRiskExpanded.value) {
                        isVeryLowRiskExpanded.value = false
                        isLowRiskExpanded.value = false
                        isHighRiskExpanded.value = false
                        isVeryHighRiskExpanded.value = false
                    }
                }
                typesOfRisks[3] -> {
                    isHighRiskExpanded.value = !isHighRiskExpanded.value
                    if (isHighRiskExpanded.value) {
                        isVeryLowRiskExpanded.value = false
                        isLowRiskExpanded.value = false
                        isMediumRiskExpanded.value = false
                        isVeryHighRiskExpanded.value = false
                    }
                }
                else -> {
                    isVeryHighRiskExpanded.value = !isVeryHighRiskExpanded.value
                    if (isVeryHighRiskExpanded.value) {
                        isVeryLowRiskExpanded.value = false
                        isLowRiskExpanded.value = false
                        isMediumRiskExpanded.value = false
                        isHighRiskExpanded.value = false
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun compatibilityTestRiskTypesPreview() {
        GoldenGlobalMobileTheme {
            compatibilityTestRiskTypes(navController = rememberNavController())
        }
    }
}