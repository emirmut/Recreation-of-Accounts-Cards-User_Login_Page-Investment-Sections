package emir.mut.GoldenGlobalMobile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import emir.mut.GoldenGlobalMobile.userLoginPage.userLoginPage
import kotlinx.coroutines.launch

class mainPage {
    companion object {
        @Composable
        fun mainPage(navController: NavController) {
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
                                    text = "Ana Sayfa"
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
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .background(colorResource(id = R.color.app_background))
                            .padding(innerPadding)
                            .padding(16.dp, 16.dp, 16.dp, 0.dp)) {
                            Text(
                                text = "Merhabalar " + userLoginPage.users.find { it.isSelected }?.userName + "! Uygulamamızın size sunduğu tüm fırsatları ekranın sol üst köşesinde bulunan ikona basarak çıkan menünün içerisinde görüntülüyebilirsiniz.",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W500,
                                lineHeight = TextUnit(1.4f, TextUnitType.Em)
                            )
                        }
                    }
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun mainPagePreview() {
        GoldenGlobalMobileTheme {
            mainPage(navController = rememberNavController())
        }
    }
}