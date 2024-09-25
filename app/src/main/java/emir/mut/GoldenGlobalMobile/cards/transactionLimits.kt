package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class transactionLimits {
    companion object {
        var options = listOf(
            "Nakit Çekim",
            "Alışveriş",
            "E-Ticaret"
        )
        var subOptions = listOf(
            "Günlük Limit",
            "Haftalık Limit",
            "Aylık Limit",
            "Yıllık Limit"
        )
        var isCashWithdrawalExpanded: MutableState<Boolean> = mutableStateOf(false)
        var isShoppingExpanded: MutableState<Boolean> = mutableStateOf(false)
        var isECommerceExpanded: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun transactionLimits(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "İşlem Limitleri"
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
                        cardSettings.goldenGlobalCardRow()
                        Spacer(modifier = Modifier
                            .height(32.dp))
                        options.forEach { option ->
                            expandableRow(
                                title = option,
                                isExpanded = if (option == "Nakit Çekim") isCashWithdrawalExpanded.value else if (option == "Alışveriş") isShoppingExpanded.value else isECommerceExpanded.value,
                                onClick = {
                                    if (option == "Nakit Çekim") {
                                        isCashWithdrawalExpanded.value = !isCashWithdrawalExpanded.value
                                        if (isCashWithdrawalExpanded.value) {
                                            isShoppingExpanded.value = false
                                            isECommerceExpanded.value = false
                                        }
                                    } else if (option == "Alışveriş") {
                                        isShoppingExpanded.value = !isShoppingExpanded.value
                                        if (isShoppingExpanded.value) {
                                            isCashWithdrawalExpanded.value = false
                                            isECommerceExpanded.value = false
                                        }
                                    } else {
                                        isECommerceExpanded.value = !isECommerceExpanded.value
                                        if (isECommerceExpanded.value) {
                                            isCashWithdrawalExpanded.value = false
                                            isShoppingExpanded.value = false
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            )
        }

        @Composable
        fun expandableRow(
            title: String,
            isExpanded: Boolean,
            onClick: () -> Unit
        ) {
            Column(modifier = Modifier
                .padding(8.dp, 0.dp, 8.dp, 8.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onClick()
                        }
                    )
                    .padding(16.dp, 8.dp, 16.dp, 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier
                        .weight(1f))
                    Image(
                        painterResource(id = if (isExpanded) R.drawable.ic_arrow_up else R.drawable.ic_down_arrow),
                        contentDescription = null
                    )
                }
                if (isExpanded) {
                    expandableRowContent()
                }
            }
        }

        @Composable
        fun expandableRowContent() {
            Column(modifier = Modifier
                .background(
                    color = Color.LightGray,
                    shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp)
                )
                .padding(16.dp, 8.dp, 16.dp, 8.dp)) {
                subOptions.forEach { subOption ->
                    expandableRowContentEachRow(
                        text1 = subOption,
                        value = if (subOption == subOptions[0]) {
                            cardSettings.selectedCard()?.limit?.div(30) ?: 0.0
                        } else if (subOption == subOptions[1]) {
                            cardSettings.selectedCard()?.limit?.div(4) ?: 0.0
                        }  else if (subOption == subOptions[2]) {
                            cardSettings.selectedCard()?.limit ?: 0.0
                        }  else {
                            cardSettings.selectedCard()?.limit?.times(12) ?: 0.0
                        }
                    )
                }
            }
        }

        @Composable
        fun expandableRowContentEachRow(
            text1: String,
            value: Double
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()) {
                Text(
                    text = text1,
                    fontSize = 14.sp,
                    color = colorResource(id = R.color.fifty_seven)
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                Text(
                    text = String.format("%.2f", value) + " TL",
                    fontWeight = FontWeight.W700,
                    fontSize = 14.sp
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun transactionLimitsPreview() {
        GoldenGlobalMobileTheme {
            transactionLimits(navController = rememberNavController())
        }
    }
}