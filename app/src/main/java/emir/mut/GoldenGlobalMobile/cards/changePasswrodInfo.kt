package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class changePasswrodInfo {
    companion object {
        @Composable
        fun changePasswordInfo(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "İşlem Açıklaması"
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
                        .padding(top = 16.dp)) {
                        Text(
                            text = "Kart Şifre Değiştirme İşlemi",
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W800,
                            modifier = Modifier
                                .padding(16.dp, 0.dp, 0.dp, 16.dp)
                        )
                        Text(
                            text = "Bu adımı kullanarak kartlarınızın mevcut şifresini değiştirebilirsiniz.",
                            modifier = Modifier
                                .padding(start = 16.dp)
                        )
                    }
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun changePasswordInfoPreview() {
        GoldenGlobalMobileTheme {
            changePasswordInfo(navController = rememberNavController())
        }
    }
}