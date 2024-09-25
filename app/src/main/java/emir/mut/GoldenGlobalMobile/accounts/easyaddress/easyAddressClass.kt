package emir.mut.GoldenGlobalMobile.accounts.easyaddress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class easyAddressClass {
    companion object {
        @Composable
        fun easyAddress(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kolay Adres"
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
                            navController.navigate("page2") {
                                popUpTo("page1") {
                                    inclusive = true
                                }
                            }
                        }
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(32.dp, 32.dp, 32.dp, 0.dp)) {
                        Text(
                            text = "Kolay Adres, para transferi işlemlerinde hesap numaranızı veya IBAN’ınızı paylaşmak yerine cari hesaplarınıza " +
                                    "atayacağınız; cep telefonu numarası, T.C. kimlik numarası, e-posta adresi, vergi kimlik numarası, pasaport numarası, " +
                                    "yabancı kimlik numarası gibi sizin ve hesabınıza para transferi yapacak kişinin kolaylıkla hatırlayabileceği bilgilerdir.",
                            color = colorResource(id = R.color.easy_address_text)
                        )
                    }
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun myEasyAddressesPreview() {
        GoldenGlobalMobileTheme {
            easyAddress(navController = rememberNavController())
        }
    }
}