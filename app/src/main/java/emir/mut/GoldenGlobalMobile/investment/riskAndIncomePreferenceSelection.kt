package emir.mut.GoldenGlobalMobile.investment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class riskAndIncomePreferenceSelection {
    companion object {
        var currentRiskAndIncomePreference: MutableState<Int> = mutableStateOf(Int.MAX_VALUE)
        var riskAndIncomePreferences = listOf(
            "Olabildiğince riskten kaçınır",
            "Anaparadan çok az bir miktar kaybetmeyi göze alabilirim",
            "Anaparadan bir miktar kaybetmeyi göze alabilirim",
            "Anaparadan kaybetmeyi göze alabilirim",
            "Anaparayı tamamen kaybetmeyi göze alabilirim",
        )
        var riskAndIncomePreferencesInfoTexts = listOf(
            "Olabildiğince riskten kaçınır, güvenli yatırım araçlarını tercih eder, az ama düzenli getiri sağlamayı tercih ederim.",
            "Enflasyonun üzerinde getiri beklentisiyle düşük riskli ürünlere yatırım yapabilirim.",
            "Genel olarak orta riskli ürünleri tercih etmekle beraber, uzun vadede toplam getirimi arttırmak aracıyla riskli ürünlere de makul ölçüde yatırım yapabilirim.",
            "Yüksek getiri beklentisiyle yüksek riskli ürünlere yatırım yapabilirim.",
            "Çok yüksek getiri isterim ama çok yüksek riskli ürünlere yatırım yapabilirim. Gelişmiş yatırım ürünlerinin riskleri konusunda bilgiliyim ve bu ürünlere yatırım yapabilirim.",
        )
        var selectedRiskAndIncomePreference: String = "Seçiniz"
        @Composable
        fun riskAndIncomePreferenceSelection(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Risk ve Getiri Tercihi Seçimi"
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
                            riskAndIncomePreferences.forEachIndexed { index, riskAndIncomePreference ->
                                riskAndIncomePreferenceSelectionRow(
                                    riskAndIncomePreference = riskAndIncomePreference,
                                    isSelected = currentRiskAndIncomePreference.value == index,
                                    onClick = {
                                        currentRiskAndIncomePreference.value = index
                                        selectedRiskAndIncomePreference = riskAndIncomePreference
                                        navController.popBackStack()
                                    }
                                )
                                if (index != riskAndIncomePreferences.size - 1) {
                                    MainActivity.horizontalDivider()
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun riskAndIncomePreferenceSelectionRow(
            riskAndIncomePreference: String,
            isSelected: Boolean,
            onClick: () -> Unit
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 8.dp, 16.dp, 4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        onClick()
                    }
                )) {
                Row(modifier = Modifier
                    .fillMaxWidth()) {
                    Text(
                        text = riskAndIncomePreference,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.W700,
                        lineHeight = TextUnit(1.3f, TextUnitType.Em)
                    )
                    if (isSelected) {
                        Spacer(modifier = Modifier
                            .weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_done_red),
                            contentDescription = ""
                        )
                    }
                }
                Text(
                    text = when (riskAndIncomePreference) {
                        riskAndIncomePreferences[0] -> {
                            riskAndIncomePreferencesInfoTexts[0]
                        }
                        riskAndIncomePreferences[1] -> {
                            riskAndIncomePreferencesInfoTexts[1]
                        }
                        riskAndIncomePreferences[2] -> {
                            riskAndIncomePreferencesInfoTexts[2]
                        }
                        riskAndIncomePreferences[3] -> {
                            riskAndIncomePreferencesInfoTexts[3]
                        }
                        else -> {
                            riskAndIncomePreferencesInfoTexts.last()
                        }
                    },
                    color = colorResource(id = R.color.color_7F8084),
                    fontSize = 13.sp,
                    lineHeight = TextUnit(1.4f, TextUnitType.Em)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun riskAndIncomePreferenceSelectionPreview() {
        GoldenGlobalMobileTheme {
            riskAndIncomePreferenceSelection(navController = rememberNavController())
        }
    }
}