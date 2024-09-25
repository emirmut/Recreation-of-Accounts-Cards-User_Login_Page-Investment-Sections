package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.dialogues.Companion.reportLostStolenWhenSwitchIsClosedDialog
import emir.mut.GoldenGlobalMobile.dialogues.Companion.reportLostStolenWhenSwitchIsOpenDialog
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class cardSettings {
    companion object {
        var checkedSwitch: MutableState<Boolean> = mutableStateOf(false)
        var openReportLostStolenWhenSwitchIsOpenDialog: MutableState<Boolean> = mutableStateOf(false)
        var openReportLostStolenWhenSwitchIsClosedDialog: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun cardSettings(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kart Ayarları"
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
                        goldenGlobalCardRow()
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        cardSettingsButtons(
                            navController = navController,
                            text = "Kart İşlem İzinleri",
                            hasSwitch = false
                        )
                        cardSettingsButtons(
                            navController = navController,
                            text = "Kart Şifresi Belirle",
                            hasSwitch = false
                        )
                        cardSettingsButtons(
                            navController = navController,
                            text = "Kart İşlem Limitleri",
                            hasSwitch = false
                        )
                        cardSettingsButtons(
                            navController = navController,
                            text = "Kayıp Çalıntı Bildir",
                            hasSwitch = false
                        )
                        cardSettingsButtons(
                            navController = navController,
                            text = "Kartımı geçici olarak kapat",
                            hasSwitch = true
                        )
                    }
                }
            )
        }

        @Composable
        fun goldenGlobalCardRow() {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Golden Global Kart\n" + selectedCard()?.cardNo,
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    lineHeight = TextUnit(1.2f, TextUnitType.Em),
                    textAlign = TextAlign.Center
                )
            }
            MainActivity.horizontalDivider()
        }

        fun selectedCard(): card? {
            for (i in 0 until myCards.existingCards.size) {
                if (myCards.existingCards[i].isSelected) {
                    return myCards.existingCards[i]
                    break
                }
            }
            return null
        }

        @Composable
        fun cardSettingsButtons(
            navController: NavController,
            text: String,
            hasSwitch: Boolean
        ) {
            Row(modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 8.dp)
                .fillMaxWidth()
                .background(
                    color = if (text == "Kayıp Çalıntı Bildir" || text == "Kartımı geçici olarak kapat") Color.White else colorResource(id = R.color.golden_global),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    border = if (text == "Kayıp Çalıntı Bildir")
                        BorderStroke(2.dp, colorResource(id = R.color.golden_global))
                    else BorderStroke(0.dp, Color.Transparent), shape = RoundedCornerShape(10.dp)
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = text != "Kartımı geçici olarak kapat",
                    onClick = {
                        if (text == "Kart İşlem Limitleri") {
                            navController.navigate("page19")
                        } else if (text == "Kart Şifresi Belirle") {
                            navController.navigate("page26")
                        } else if (text == "Kart İşlem İzinleri") {
                            navController.navigate("page25")
                        } else {
                            if (checkedSwitch.value) {
                                openReportLostStolenWhenSwitchIsOpenDialog.value = true
                            } else {
                                openReportLostStolenWhenSwitchIsClosedDialog.value = true
                            }
                        }
                    }
                ),
                horizontalArrangement = if (!hasSwitch) Arrangement.Center else Arrangement.Start) {
                Text(
                    text = text,
                    color =  if (text == "Kayıp Çalıntı Bildir" || text == "Kartımı geçici olarak kapat") Color.Black else Color.White,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier
                        .padding(if (text == "Kartımı geçici olarak kapat") 16.dp else 0.dp, 12.dp, 0.dp, 12.dp)
                )
                if (hasSwitch) {
                    Spacer(modifier = Modifier
                        .weight(1f))
                    val interactionSource = remember { MutableInteractionSource() }
                    Switch(
                        checked = checkedSwitch.value,
                        onCheckedChange = {
                            checkedSwitch.value = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = colorResource(id = R.color.checked_switch),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = colorResource(id = R.color.unchecked_switch),
                            uncheckedBorderColor = Color.Transparent,
                            checkedBorderColor = Color.Transparent
                        ),
                        interactionSource = interactionSource,
                        modifier = Modifier
                            .scale(0.75f)
                            .indication(
                                interactionSource = interactionSource,
                                indication = null
                            )
                            .padding(end = 8.dp)
                    )
                }
                if (openReportLostStolenWhenSwitchIsOpenDialog.value) {
                    reportLostStolenWhenSwitchIsOpenDialog(
                        navController = navController,
                        card = selectedCard()
                    )
                }
                if (openReportLostStolenWhenSwitchIsClosedDialog.value) {
                    reportLostStolenWhenSwitchIsClosedDialog(navController = navController)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun cardSettingsPreview() {
        GoldenGlobalMobileTheme {
            cardSettings(navController = rememberNavController())
        }
    }
}