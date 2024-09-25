package emir.mut.GoldenGlobalMobile.investment

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
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
import java.text.SimpleDateFormat
import java.util.Date

class compatibilityTest {
    companion object {
        val compatibilityTestOptions = listOf(
            "Yaş",
            "Eğitim Durumu",
            "Meslek",
            "Yatırım Değerlendirme Süresi",
            "Yatırım Tecrübesi",
            "Risk ve Getiri Tercihi",
        )
        val compatibilityTestOptionsRoutes = listOf(
            "page41",
            "page42",
            "page43",
            "page44",
            "page45",
            "page46",
        )
        var isCompatibilityTestIsDone: Boolean = false
        @SuppressLint("SimpleDateFormat")
        var simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        var currentDate = simpleDateFormat.format(Date())
        @Composable
        fun compatibilityTest(navController: NavController) {
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
                        LazyColumn(modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.app_background))
                            .padding(innerPadding)
                            .padding(16.dp, 16.dp, 16.dp, 0.dp)) {
                            if (!isCompatibilityTestIsDone) {
                                item {
                                    Text(
                                        text = stringResource(id = R.string.compatibilityTestInfoText),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W500
                                    )
                                    Spacer(modifier = Modifier
                                        .height(32.dp))
                                }
                                item {
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.White,
                                            shape = RoundedCornerShape(10.dp)
                                        )) {
                                        compatibilityTestOptions.forEachIndexed { index, text ->
                                            compatibilityTestRow(
                                                navController = navController,
                                                text1 = text,
                                                text2 = when (index) {
                                                    0 -> ageSelection.selectedAgeInterval
                                                    1 -> educationalBackgroundSelection.selectedEducationalBackground
                                                    2 -> occupationSelection.selectedOccupation
                                                    3 -> evaluationProcessSelection.selectedEvaluationProcess
                                                    4 -> investmentExperienceSelection.selectedInvestmentExperience
                                                    else -> riskAndIncomePreferenceSelection.selectedRiskAndIncomePreference
                                                },
                                                route = compatibilityTestOptionsRoutes[index]
                                            )
                                            if (index != compatibilityTestOptions.size - 1) {
                                                MainActivity.horizontalDivider()
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier
                                        .height(32.dp))
                                }
                                item {
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.White,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            enabled = ageSelection.selectedAgeInterval != "Seçiniz" && educationalBackgroundSelection.selectedEducationalBackground != "Seçiniz" && occupationSelection.selectedOccupation != "Seçiniz" && evaluationProcessSelection.selectedEvaluationProcess != "Seçiniz" && investmentExperienceSelection.selectedInvestmentExperience != "Seçiniz" && riskAndIncomePreferenceSelection.selectedRiskAndIncomePreference != "Seçiniz",
                                            onClick = {
                                                navController.navigate("page50")
                                            }
                                        ),
                                        horizontalArrangement = Arrangement.Center) {
                                        Text(
                                            text = "İleri",
                                            color = colorResource(id = R.color.golden_global)
                                        )
                                    }
                                }
                            } else {
                                item {
                                    Text(
                                        text = "Uygunluk Testim",
                                        color = colorResource(id = R.color.my_compatibility_test),
                                        modifier = Modifier
                                            .padding(bottom = 16.dp)
                                    )
                                }
                                item {
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            color = Color.White,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .padding(16.dp, 0.dp, 24.dp, 0.dp)) {
                                        compatibilityTestDoneRow(text = currentDate)
                                        MainActivity.horizontalDivider()
                                        compatibilityTestDoneRow(text = "Uygun:")
                                        MainActivity.horizontalDivider()
                                        compatibilityTestDoneRow(text = "Uygun Değil:")
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun compatibilityTestRow(
            navController: NavController,
            text1: String,
            text2: String,
            route: String
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp, 16.dp, 4.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        navController.navigate(route)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text1,
                    fontWeight = FontWeight.W500,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Text(
                    text = text2,
                    fontSize = 14.sp,
                    color = if (text2 == "Seçiniz") colorResource(id = R.color.extra_faded) else Color.Black,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .width(120.dp),
                    lineHeight = TextUnit(1.4f, TextUnitType.Em)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }
        }

        @SuppressLint("SimpleDateFormat")
        @Composable
        fun compatibilityTestDoneRow(text: String) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 4.dp)) {
                Text(
                    text = text + " " + if (text == "Uygun:") compatibilityTestResults.suitableResults.joinToString() else if (text == "Uygun Değil:") compatibilityTestResults.notSuitableResults.joinToString() else "",
                    color = if (text == "Uygun:") colorResource(id = R.color.suitable) else if (text == "Uygun Değil:") colorResource(id = R.color.not_suitable) else Color.Black,
                    fontWeight = if (text == currentDate) FontWeight.W800 else FontWeight.W400,
                    lineHeight = TextUnit(1.3f, TextUnitType.Em)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun compatibilityTestPreview() {
        GoldenGlobalMobileTheme {
            compatibilityTest(navController = rememberNavController())
        }
    }
}