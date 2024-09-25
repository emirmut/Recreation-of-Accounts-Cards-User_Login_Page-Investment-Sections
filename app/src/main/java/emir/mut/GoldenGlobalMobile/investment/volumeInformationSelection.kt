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

class volumeInformationSelection {
    companion object {
        var volumeInformationOptions = listOf(
            "1 - 50.000 TL",
            "50.001 - 500.000 TL",
            "500.000 TL ve üzeri",
        )
        var selectedVolumeInformationForVeryLowRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedVolumeInformationForLowRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedVolumeInformationForMediumRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedVolumeInformationForHighRisk: MutableState<String> = mutableStateOf("Seçiniz")
        var selectedVolumeInformationForVeryHighRisk: MutableState<String> = mutableStateOf("Seçiniz")
        @Composable
        fun volumeInformationSelection(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Hacim Bilgisi Seçimi"
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
                            volumeInformationOptions.forEachIndexed { index, volumeInformationOption ->
                                volumeInformationSelectionRow(
                                    volumeInformation = volumeInformationOption,
                                    onClick = {
                                        when (compatibilityTestRiskTypes.currentRiskType.value) {
                                            compatibilityTestRiskTypes.typesOfRisks[0] -> selectedVolumeInformationForVeryLowRisk.value = volumeInformationOption
                                            compatibilityTestRiskTypes.typesOfRisks[1] -> selectedVolumeInformationForLowRisk.value = volumeInformationOption
                                            compatibilityTestRiskTypes.typesOfRisks[2] -> selectedVolumeInformationForMediumRisk.value = volumeInformationOption
                                            compatibilityTestRiskTypes.typesOfRisks[3] -> selectedVolumeInformationForHighRisk.value = volumeInformationOption
                                            compatibilityTestRiskTypes.typesOfRisks.last()  -> selectedVolumeInformationForVeryHighRisk.value = volumeInformationOption
                                        }
                                        navController.popBackStack()
                                    }
                                )
                                if (index != volumeInformationOptions.size - 1) {
                                    MainActivity.horizontalDivider()
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun volumeInformationSelectionRow(
            volumeInformation: String,
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
                    text = volumeInformation,
                    fontSize = 14.sp
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun volumeInformationSelectionPreview() {
        GoldenGlobalMobileTheme {
            volumeInformationSelection(navController = rememberNavController())
        }
    }
}