package emir.mut.GoldenGlobalMobile.accounts.easyaddress

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.myAccounts
import emir.mut.GoldenGlobalMobile.navigationDrawer
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class myEasyAddressesClass {
    companion object {
        var easyAddresses = mutableStateListOf<easyAddressAccount>()
        @Composable
        fun myEasyAddresses(navController: NavController) {
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
                                    text = "Kolay Adreslerim"
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
                            .padding(top = 32.dp)) {
                            if (easyAddresses.isEmpty()) {
                                item {
                                    MainActivity.horizontalDivider()
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White)
                                        .padding(16.dp, 8.dp, 16.dp, 16.dp),
                                        horizontalArrangement = Arrangement.Center) {
                                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(32.dp)) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_info),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .size(30.dp)
                                            )
                                            Text(
                                                text = "Size para göndermesini istediğiniz kişiye hesap numaranız (IBAN) yerine bankamız " +
                                                        "kanallarında tanımlayabildiğiniz, girişi ve kullanımı daha kolay bilgileri verebilirsiniz.",
                                                lineHeight = TextUnit(1.5f, TextUnitType.Em),
                                                modifier = Modifier
                                                    .padding(start = 9.dp)
                                            )
                                            Column {
                                                easyAddressOptions("Cep Telefonu Numarası", R.drawable.ic_dot)
                                                easyAddressOptions("T.C. Kimlik Numarası", R.drawable.ic_dot)
                                                easyAddressOptions("E-posta", R.drawable.ic_dot)
                                                easyAddressOptions("Vergi Kimlik Numarası", R.drawable.ic_dot)
                                                easyAddressOptions("Pasaport Numarası", R.drawable.ic_dot)
                                                easyAddressOptions("Yabancı Kimlik Numarası ile işlemlerinize devam edebilirsiniz.", R.drawable.ic_dot)
                                            }
                                        }
                                    }
                                    MainActivity.horizontalDivider()
                                    Spacer(modifier = Modifier
                                        .height(48.dp))
                                }
                            } else {
                                items(easyAddresses) { easyAddress ->
                                    easyAddressInstance(easyAddress)
                                    Spacer(modifier = Modifier.height(24.dp))
                                }
                            }
                            item {
                                MainActivity.horizontalDivider()
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.White)
                                    .padding(top = 8.dp, bottom = 8.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            navController.navigate("page1") {
                                                popUpTo("page0") {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    ),
                                    horizontalArrangement = Arrangement.Center) {
                                    Text(
                                        text = "Kolay Adres Ekle",
                                        color = colorResource(id = R.color.golden_global),
                                        fontSize = 14.sp
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun easyAddressOptions(
            title: String,
            prefix: Int
        ) {
            Row {
                Image(
                    painter = painterResource(id = prefix),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(
                    text = title
                )
            }
        }

        @Composable
        fun easyAddressInstance(easyAddressAccount: easyAddressAccount) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)) {
                easyAddressInstanceRow(
                    paddingStart = 16,
                    paddingTop = 32,
                    paddingEnd = 32,
                    paddingBottom = 0,
                    text1 = "Hesap",
                    text2 = easyAddressAccount.account.accountNumber.toString(),
                    hasRightArrow = false
                )
                easyAddressInstanceRow(
                    paddingStart = 16,
                    paddingTop = 0,
                    paddingEnd = 0,
                    paddingBottom = 0,
                    text1 = "Kolay Adres Türü",
                    text2 = easyAddressAccount.easyAddressType,
                    hasRightArrow = true
                )
                easyAddressInstanceRow(
                    paddingStart = 16,
                    paddingTop = 0,
                    paddingEnd = 32,
                    paddingBottom = 0,
                    text1 = easyAddressAccount.easyAddressType,
                    text2 = easyAddressAccount.easyAddressInput,
                    hasRightArrow = false
                )
                if (definingEasyAddress.easyAddressNameInput.value != "") {
                    easyAddressInstanceRow(
                        paddingStart = 16,
                        paddingTop = 0,
                        paddingEnd = 32,
                        paddingBottom = 0,
                        text1 = "Kolay Adres Adı",
                        text2 = easyAddressAccount.easyAddressNameInput,
                        hasRightArrow = false
                    )
                }
            }
        }
        
        @Composable
        fun easyAddressInstanceRow(
            paddingStart: Int,
            paddingTop: Int,
            paddingEnd: Int,
            paddingBottom: Int,
            text1: String,
            text2: String,
            hasRightArrow: Boolean
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(paddingStart.dp, paddingTop.dp, paddingEnd.dp, paddingBottom.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text1,
                    color = colorResource(id = R.color.color_7F8084),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                if (myAccounts.accounts.isNotEmpty()) {
                    Text(
                        text = text2,
                        fontSize = 14.sp
                    )
                }
                if (hasRightArrow) {
                    Spacer(modifier = Modifier
                        .width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(15.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun myEasyAddressesPreview() {
        GoldenGlobalMobileTheme {
            myEasyAddresses(navController = rememberNavController())
        }
    }
}