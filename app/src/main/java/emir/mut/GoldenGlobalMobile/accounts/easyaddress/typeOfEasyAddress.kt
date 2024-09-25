package emir.mut.GoldenGlobalMobile.accounts.easyaddress

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
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

class typeOfEasyAddress {
    companion object {
        @SuppressLint("UnrememberedMutableState")
        @Composable
        fun easyAddressType(navController: NavController) {
            var addressTypes = listOf(
                "Cep Telefonu Numarası",
                "T.C. Kimlik Numarası",
                "E-Posta",
                "Vergi Kimlik Numarası",
                "Pasaport Numarası",
                "Yabancı Kimlik Numarası"
            )
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kolay Adres Türü"
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
                        var easyAddressType: MutableState<Int> = mutableIntStateOf(Int.MAX_VALUE)
                        addressTypes.forEachIndexed { index, addressType ->
                            easyAddressTypeInstance(
                                title = addressType,
                                isSelected = easyAddressType.value == index,
                                onClick = {
                                    easyAddressType.value = index
                                    definingEasyAddress.selectedEasyAddress.value = addressType
                                }
                            )
                        }
                        MainActivity.horizontalDivider()
                    }
                }
            )
        }

        @Composable
        fun easyAddressTypeInstance(
            title: String,
            isSelected: Boolean,
            onClick: () -> Unit
        ) {
            MainActivity.horizontalDivider()
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .clickable {
                    onClick()
                }) {
                Text(
                    text = title,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                if (isSelected) {
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
    fun easyAddressTypePreview() {
        GoldenGlobalMobileTheme {
            easyAddressType(navController = rememberNavController())
        }
    }
}