package emir.mut.GoldenGlobalMobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import emir.mut.GoldenGlobalMobile.userLoginPage.userLoginPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class navigationDrawer {
    companion object {
        data class navigationItem(
            var title: String,
            var icon: Int?,
            var badge: Int?,
            var route: String
        ) {
            fun doesMatchSearchQuery(query: String): Boolean {
                val matchingCombinations = listOf(
                    title
                )
                return matchingCombinations.any {
                    it.contains(query, ignoreCase = true)
                }
            }
        }

        var navigationAccountsSubItems = listOf(
            navigationItem(
                title = "Geri",
                icon = R.drawable.ic_back_2,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Hesaplarım",
                icon = null,
                badge = null,
                route = "page16"
            ),
            navigationItem(
                title = "Kolay Adreslerim",
                icon = null,
                badge = null,
                route = "page0"
            )
        )

        var navigationCardsSubItems = listOf(
            navigationItem(
                title = "Geri",
                icon = R.drawable.ic_back_2,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Kartlarım",
                icon = null,
                badge = null,
                route = "page24"
            ),
            navigationItem(
                title = "Kart Başvurusu",
                icon = null,
                badge = null,
                route = "page17"
            )
        )

        var navigationRequestPaymentSubItems = listOf(
            navigationItem(
                title = "Geri",
                icon = R.drawable.ic_back_2,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Ödeme İsteği Oluştur",
                icon = null,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Gelen Giden Ödeme İstekleri",
                icon = null,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Ödeme İste İzin Tercihleri",
                icon = null,
                badge = null,
                route = ""
            )
        )

        var navigationMoneyTransfersSubItems = listOf(
            navigationItem(
                title = "Geri",
                icon = R.drawable.ic_back_2,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Kolay Adrese Transfer",
                icon = null,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "IBAN'a Transfer",
                icon = null,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Ödeme İste",
                icon = null,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            )
        )

        var navigationInvestmentSubItems = listOf(
            navigationItem(
                title = "Geri",
                icon = R.drawable.ic_back_2,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Yatırım Hesabı Aç",
                icon = null,
                badge = null,
                route = "page35"
            ),
            navigationItem(
                title = "Yatırım Hesaplarım",
                icon = null,
                badge = null,
                route = "page39"
            ),
            navigationItem(
                title = "Uygunluk Testi",
                icon = null,
                badge = null,
                route = "page40"
            )
        )

        var navigationMainItems = listOf(
            navigationItem(
                title = "Ana Sayfa",
                icon = R.drawable.ic_home,
                badge = null,
                route = "page6"
            ),
            navigationItem(
                title = "Hesaplar",
                icon = R.drawable.ic_accounts,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Kartlar",
                icon = R.drawable.ic_cards,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Para Transferleri",
                icon = R.drawable.ic_money_transfers,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Döviz / Kıymetli Maden",
                icon = R.drawable.ic_currency,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Ödemeler",
                icon = R.drawable.ic_payments,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Yatırım",
                icon = R.drawable.ic_investment,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Ayarlar",
                icon = R.drawable.ic_settings,
                badge = R.drawable.ic_arrow_right_2,
                route = ""
            ),
            navigationItem(
                title = "Bize Yazın",
                icon = R.drawable.ic_envelope,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Karekod İşlemleri",
                icon = R.drawable.ic_qr,
                badge = null,
                route = ""
            ),
            navigationItem(
                title = "Sözleşmeler",
                icon = R.drawable.ic_contracts,
                badge = null,
                route = ""
            )
        )

        var drawerContent: MutableState<Int> = mutableStateOf(0)

        @Composable
        fun navigationDrawerContent(
            navController: NavController,
            drawerState: DrawerState,
            coroutineScope: CoroutineScope
        ) {
            val viewModel = viewModel<MainViewModel>()
            val searchText by viewModel.searchText.collectAsState()
            val isSearching by viewModel.isSearching.collectAsState()
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.golden_global))
                .padding(24.dp, 32.dp, 0.dp, 8.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_user_image),
                        contentDescription = ""
                    )
                    userLoginPage.users.forEach { user ->
                        if (user.isSelected) {
                            Text(
                                text = user.userName,
                                color = colorResource(id = R.color.user_icon)
                            )
                        }
                    }
                }
            }
            TextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp, 16.dp, 8.dp),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = ""
                    )
                },
                placeholder = {
                    Text(
                        text = "Menüde ara",
                        color = Color.White
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.search_bar_background),
                    unfocusedContainerColor = colorResource(id = R.color.search_bar_background),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = colorResource(id = R.color.golden_global)
                ),
                shape = RoundedCornerShape(10.dp)
            )
            HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
            if (isSearching) {
                Box(modifier = Modifier
                    .fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center),
                        color = colorResource(id = R.color.golden_global)
                    )
                }
            } else {
                when (drawerContent.value) {
                    0 -> {
                        mainNavigationDrawerContent(coroutineScope = coroutineScope)
                    }
                    1 -> {
                        accountsNavigationDrawerContent(
                            navController = navController,
                            drawerState = drawerState,
                            coroutineScope = coroutineScope
                        )
                    }
                    2 -> {
                        cardsNavigationDrawerContent(
                            navController = navController,
                            drawerState = drawerState,
                            coroutineScope = coroutineScope
                        )
                    }
                    3 -> {
                        moneyTransfersNavigationDrawerContent(
                            navController = navController,
                            drawerState = drawerState,
                            coroutineScope = coroutineScope
                        )
                    }
                    4 -> {
                        requestPaymentNavigationDrawerContent(
                            navController = navController,
                            drawerState = drawerState,
                            coroutineScope = coroutineScope
                        )
                    }
                    5 -> {
                        investmentNavigationDrawerContent(
                            navController = navController,
                            drawerState = drawerState,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }

        @Composable
        fun mainNavigationDrawerContent(coroutineScope: CoroutineScope) {
            val viewModel = viewModel<MainViewModel>()
            val navMainItems by viewModel.navMainItems.collectAsState()
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(navMainItems) { navMainItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = navMainItem.title
                            )
                        },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                if (navMainItem.title == "Hesaplar") {
                                    drawerContent.value = 1
                                } else if (navMainItem.title == "Kartlar") {
                                    drawerContent.value = 2
                                } else if (navMainItem.title == "Para Transferleri") {
                                    drawerContent.value = 3
                                } else if (navMainItem.title == "Yatırım") {
                                    drawerContent.value = 5
                                }
                            }
                        },
                        icon = {
                            if (navMainItem.icon != null) {
                                Image(
                                    painter = painterResource(id = navMainItem.icon!!), // nullable yapıdan kurtarıldı
                                    contentDescription = ""
                                )
                            }
                        },
                        badge = {
                            if (navMainItem.badge != null) {
                                Image(
                                    painter = painterResource(id = navMainItem.badge!!),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = 8.dp
                                        )
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
                }
            }
        }

        @Composable
        fun accountsNavigationDrawerContent(
            navController: NavController,
            drawerState: DrawerState,
            coroutineScope: CoroutineScope
        ) {
            val viewModel = viewModel<MainViewModel>()
            val navAccountsSubItems by viewModel.navAccountsSubItems.collectAsState()
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(navAccountsSubItems) { navAccountsSubItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = navAccountsSubItem.title,
                                modifier = Modifier
                                    .offset(
                                        x = if (navAccountsSubItem.title == "Geri") (-8).dp else 0.dp
                                    )
                            )
                        },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                if (navAccountsSubItem.title == "Geri") {
                                    drawerContent.value = 0
                                } else {
                                    navController.navigate(navAccountsSubItem.route)
                                    drawerState.close()
                                }
                            }
                        },
                        icon = {
                            if (navAccountsSubItem.icon != null) {
                                Image(
                                    painter = painterResource(id = navAccountsSubItem.icon!!), // nullable yapıdan kurtardık
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = (-12).dp
                                        )
                                )
                            }
                        },
                        badge = {
                            if (navAccountsSubItem.badge != null) {
                                Image(
                                    painter = painterResource(id = navAccountsSubItem.badge!!),
                                    contentDescription = ""
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
                }
            }
        }

        @Composable
        fun cardsNavigationDrawerContent(
            navController: NavController,
            drawerState: DrawerState,
            coroutineScope: CoroutineScope
        ) {
            val viewModel = viewModel<MainViewModel>()
            val navCardsSubItems by viewModel.navCardsSubItems.collectAsState()
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(navCardsSubItems) { navCardsSubItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = navCardsSubItem.title,
                                modifier = Modifier
                                    .offset(
                                        x = if (navCardsSubItem.title == "Geri") (-8).dp else 0.dp
                                    )
                            )
                        },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                if (navCardsSubItem.title == "Geri") {
                                    drawerContent.value = 0
                                } else {
                                    navController.navigate(navCardsSubItem.route)
                                    drawerState.close()
                                }
                            }
                        },
                        icon = {
                            if (navCardsSubItem.icon != null) {
                                Image(
                                    painter = painterResource(id = navCardsSubItem.icon!!), // nullable yapıdan kurtardık
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = (-12).dp
                                        )
                                )
                            }
                        },
                        badge = {
                            if (navCardsSubItem.badge != null) {
                                Image(
                                    painter = painterResource(id = navCardsSubItem.badge!!),
                                    contentDescription = ""
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
                }
            }
        }

        @Composable
        fun moneyTransfersNavigationDrawerContent(
            navController: NavController,
            drawerState: DrawerState,
            coroutineScope: CoroutineScope
        ) {
            val viewModel = viewModel<MainViewModel>()
            val navMoneyTransfersSubItems by viewModel.navMoneyTransfersSubItems.collectAsState()
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(navMoneyTransfersSubItems) { navMoneyTransfersSubItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = navMoneyTransfersSubItem.title,
                                modifier = Modifier
                                    .offset(
                                        x = if (navMoneyTransfersSubItem.title == "Geri") (-8).dp else 0.dp
                                    )
                            )
                        },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                if (navMoneyTransfersSubItem.title == "Ödeme İste") {
                                    drawerContent.value = 4
                                } else if (navMoneyTransfersSubItem.title == "Geri") {
                                    drawerContent.value = 0
                                } else {
                                    navController.navigate(navMoneyTransfersSubItem.route)
                                    drawerState.close()
                                }
                            }
                        },
                        icon = {
                            if (navMoneyTransfersSubItem.icon != null) {
                                Image(
                                    painter = painterResource(id = navMoneyTransfersSubItem.icon!!),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = (-12).dp
                                        )
                                )
                            }
                        },
                        badge = {
                            if (navMoneyTransfersSubItem.badge != null) {
                                Image(
                                    painter = painterResource(id = navMoneyTransfersSubItem.badge!!),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = 8.dp
                                        )
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
                }
            }
        }

        @Composable
        fun requestPaymentNavigationDrawerContent(
            navController: NavController,
            drawerState: DrawerState,
            coroutineScope: CoroutineScope
        ) {
            val viewModel = viewModel<MainViewModel>()
            val navRequestPaymentSubItems by viewModel.navRequestPaymentSubItems.collectAsState()
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(navRequestPaymentSubItems) { navRequestPaymentSubItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = navRequestPaymentSubItem.title,
                                modifier = Modifier
                                    .offset(
                                        x = if (navRequestPaymentSubItem.title == "Geri") (-8).dp else 0.dp
                                    )
                            )
                        },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                if (navRequestPaymentSubItem.title == "Geri") {
                                    drawerContent.value = 3
                                } else {
                                    navController.navigate(navRequestPaymentSubItem.route)
                                    drawerState.close()
                                }
                            }
                        },
                        icon = {
                            if (navRequestPaymentSubItem.icon != null) {
                                Image(
                                    painter = painterResource(id = navRequestPaymentSubItem.icon!!),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = (-12).dp
                                        )
                                )
                            }
                        },
                        badge = {
                            if (navRequestPaymentSubItem.badge != null) {
                                Image(
                                    painter = painterResource(id = navRequestPaymentSubItem.badge!!),
                                    contentDescription = ""
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
                }
            }
        }

        @Composable
        fun investmentNavigationDrawerContent(
            navController: NavController,
            drawerState: DrawerState,
            coroutineScope: CoroutineScope
        ) {
            val viewModel = viewModel<MainViewModel>()
            val navInvestmentSubItems by viewModel.navInvestmentSubItems.collectAsState()
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(navInvestmentSubItems) { navInvestmentSubItem ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = navInvestmentSubItem.title,
                                modifier = Modifier
                                    .offset(
                                        x = if (navInvestmentSubItem.title == "Geri") (-8).dp else 0.dp
                                    )
                            )
                        },
                        selected = false,
                        onClick = {
                            coroutineScope.launch {
                                if (navInvestmentSubItem.title == "Geri") {
                                    drawerContent.value = 0
                                } else {
                                    navController.navigate(navInvestmentSubItem.route)
                                    drawerState.close()
                                }
                            }
                        },
                        icon = {
                            if (navInvestmentSubItem.icon != null) {
                                Image(
                                    painter = painterResource(id = navInvestmentSubItem.icon!!),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .offset(
                                            x = (-12).dp
                                        )
                                )
                            }
                        },
                        badge = {
                            if (navInvestmentSubItem.badge != null) {
                                Image(
                                    painter = painterResource(id = navInvestmentSubItem.badge!!),
                                    contentDescription = ""
                                )
                            }
                        },
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    HorizontalDivider(thickness = 1.dp, color = colorResource(id = R.color.golden_global))
                }
            }
        }
    }
}