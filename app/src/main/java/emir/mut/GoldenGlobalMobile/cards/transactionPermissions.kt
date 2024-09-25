package emir.mut.GoldenGlobalMobile.cards

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
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

class transactionPermissions {
    companion object {
        var domesticTransactionForEcommerce: MutableState<Boolean> = mutableStateOf(false)
        var internationalTransactionForEcommerce: MutableState<Boolean> = mutableStateOf(false)
        var domesticTransactionForContactless: MutableState<Boolean> = mutableStateOf(false)
        var internationalTransactionForContactless: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun transactionPermissions(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "İşlem İzinleri"
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
                        .padding(innerPadding)) {
                        cardSettings.goldenGlobalCardRow()
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        transactionPermissionsSection(title = "E-TİCARET")
                        transactionPermissionsSection(title = "TEMASSIZ İŞLEM")
                        Spacer(modifier = Modifier
                            .weight(1f))
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.golden_global))
                            .padding(top = 8.dp, bottom = 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    navController.navigate("page18")
                                }
                            ),
                            horizontalArrangement = Arrangement.Center) {
                            Text(
                                text = "KAYDET",
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W600
                            )
                        }
                        Spacer(modifier = Modifier
                            .height(36.dp))
                    }
                }
            )
        }

        @Composable
        fun transactionPermissionsSection(
            title: String
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp, 0.dp, 2.dp)) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.color_7F8084)
                    )
                }
                transactionPermissionRow(title = title, text = "Yurt İçi İşlem")
                MainActivity.horizontalDivider()
                transactionPermissionRow(title = title, text = "Yurt Dışı İşlem")
            }
        }

        @Composable
        fun transactionPermissionRow(
            title: String,
            text: String
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                .background(Color.White),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    fontWeight = FontWeight.W500,
                    color = colorResource(id = R.color.color_7F8084),
                    modifier = Modifier
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                val interactionSource = remember { MutableInteractionSource() }
                Switch(
                    checked = if (title == "E-TİCARET") {
                        if (text == "Yurt İçi İşlem") {
                            domesticTransactionForEcommerce.value
                        } else {
                            internationalTransactionForEcommerce.value
                        }
                    } else {
                        if (text == "Yurt İçi İşlem") {
                            domesticTransactionForContactless.value
                        } else {
                            internationalTransactionForContactless.value
                        }
                    },
                    onCheckedChange = {
                        if (title == "E-TİCARET") {
                            if (text == "Yurt İçi İşlem") {
                                domesticTransactionForEcommerce.value = it
                            } else {
                                internationalTransactionForEcommerce.value = it
                            }
                        } else {
                            if (text == "Yurt İçi İşlem") {
                                domesticTransactionForContactless.value = it
                            } else {
                                internationalTransactionForContactless.value = it
                            }
                        }
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
                        .indication(
                            interactionSource = interactionSource,
                            indication = null
                        )
                        .padding(end = 16.dp)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun transactionPermissionsPreview() {
        GoldenGlobalMobileTheme {
            transactionPermissions(navController = rememberNavController())
        }
    }
}