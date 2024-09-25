package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class cardShippingAddress {
    companion object {
        var recordedAddresses = listOf(
            "Akçatepe,2, 52000, Altınordu/ Ordu, Türkiye",
            "Yeşilyurt,25, 45750, Gördes/ Manisa, Türkiye",
            "Yeni,19, 37252, İhsangazi/ Kastamonu, Türkiye",
            "Çatköy Köyü,22, 56702, Pervari/ Siirt, Türkiye",
            "Yalnizca,15, 42630, Bozkir/ Konya, Türkiye",
            "Darik Mah.,7, 4402, Doğubayazit/ Ağri, Türkiye",
            "Güneşli Köyü,30, 67302, Ereğli/ Zonguldak, Türkiye",
            "Belçinar,28, 33580, Çamliyayla/ Mersin, Türkiye",
            "Haciahmetler,23, 14782, Göynük/ Bolu, Türkiye",
            "İğdebel,24, 1642, Tufanbeyli/ Adana, Türkiye",
        )
        @Composable
        fun cardShippingAddress(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kart Gönderim Adresi"
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
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(top = 24.dp)) {
                        item {
                            Row(modifier = Modifier
                                .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "KAYITLI ADRESLERİNİZ",
                                    color = colorResource(id = R.color.golden_global),
                                    fontWeight = FontWeight.W700,
                                    modifier = Modifier
                                        .padding(bottom = 8.dp)
                                )
                            }
                        }
                        items(recordedAddresses) { address ->
                            cardShippingAddressRow(
                                address = address,
                                onClick = {
                                    cardApplication2.cardShippingAddress = address
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            )
        }

        @Composable
        fun cardShippingAddressRow(
            address: String,
            onClick: (String) -> Unit
        ) {
            val interactionSource = remember { MutableInteractionSource() }
            Row(modifier = Modifier
                .padding(16.dp, 16.dp, 16.dp, 0.dp)
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onClick(address)
                    }
                )) {
                Text(
                    text = address,
                    lineHeight = TextUnit(1.4f, TextUnitType.Em),
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun cardShippingAddressPreview() {
        GoldenGlobalMobileTheme {
            cardShippingAddress(navController = rememberNavController())
        }
    }
}