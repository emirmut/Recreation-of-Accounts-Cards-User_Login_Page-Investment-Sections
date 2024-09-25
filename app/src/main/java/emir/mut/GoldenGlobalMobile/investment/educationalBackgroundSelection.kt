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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class educationalBackgroundSelection {
    companion object {
        var currentEducationalBackground: MutableState<Int> = mutableStateOf(Int.MAX_VALUE)
        var educationalBackgrounds = listOf(
            "İlköğretim / Ortaöğretim",
            "Lise",
            "Lisans ve Üstü"
        )
        var selectedEducationalBackground: String = "Seçiniz"
        @Composable
        fun educationalBackgroundSelection(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Eğitim Durumu Seçimi"
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
                            educationalBackgrounds.forEachIndexed { index, educationalBackground ->
                                educationalBackgroundSelectionRow(
                                    educationalBackground = educationalBackground,
                                    isSelected = currentEducationalBackground.value == index,
                                    onClick = {
                                        currentEducationalBackground.value = index
                                        selectedEducationalBackground = educationalBackground
                                        navController.popBackStack()
                                    }
                                )
                                if (index != educationalBackgrounds.size - 1) {
                                    MainActivity.horizontalDivider()
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun educationalBackgroundSelectionRow(
            educationalBackground: String,
            isSelected: Boolean,
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
                    text = educationalBackground,
                    fontSize = 14.sp
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
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun educationalBackgroundSelectionPreview() {
        GoldenGlobalMobileTheme {
            educationalBackgroundSelection(navController = rememberNavController())
        }
    }
}