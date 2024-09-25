package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.navigationDrawer
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import kotlinx.coroutines.launch

class myCards {
    companion object {
        var existingCards = mutableStateListOf<card>(
            cardApplication.cards[0] // geçici
        )
        var checkedInternetShopping: MutableState<Boolean> = mutableStateOf(false)
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun myCards(navController: NavController) {
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
                                    text = "Kartlarım"
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
                            .padding(top = if (existingCards.size != 0) 64.dp else 0.dp),
                            verticalArrangement = if (existingCards.size == 0) Arrangement.Center else Arrangement.Top,
                            horizontalAlignment = if (existingCards.size == 0) Alignment.CenterHorizontally else Alignment.Start) {
                            if (existingCards.size != 0) {
                                Column {
                                    val pagerState = rememberPagerState(
                                        pageCount = {
                                            existingCards.size
                                        }
                                    )
                                    HorizontalPager(
                                        state = pagerState,
                                        key = {
                                            existingCards[it].horizontalImage
                                        },
                                        pageSize = PageSize.Fill
                                    ) { index ->
                                        Column(modifier = Modifier
                                            .padding(bottom = 16.dp)) {
                                            Row(modifier = Modifier
                                                .fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Center) {
                                                Text(
                                                    text = "Kart Görseli Temsilidir",
                                                    color = colorResource(id = R.color.checked_switch),
                                                    fontWeight = FontWeight.W700,
                                                    fontSize = 14.sp
                                                )
                                            }
                                            Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(bottom = 16.dp),
                                                verticalAlignment = Alignment.CenterVertically) {
                                                val interactionSource = remember { MutableInteractionSource() }
                                                if (pagerState.currentPage != 0) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.ic_back),
                                                        contentDescription = "",
                                                        colorFilter = ColorFilter.tint(colorResource(id = R.color.golden_global)),
                                                        modifier = Modifier
                                                            .size(20.dp)
                                                            .weight(0.2f)
                                                            .clickable(
                                                                interactionSource = interactionSource,
                                                                indication = null,
                                                                onClick = {
                                                                    coroutineScope.launch {
                                                                        pagerState.animateScrollToPage(
                                                                            pagerState.currentPage - 1
                                                                        )
                                                                    }
                                                                }
                                                            )
                                                    )
                                                } else {
                                                    Spacer(modifier = Modifier
                                                        .weight(0.2f))
                                                }
                                                cardSettings.selectedCard()
                                                    ?.let {
                                                        Image(
                                                            painter = painterResource(id = it.horizontalImage),
                                                            contentDescription = null,
                                                            modifier = Modifier
                                                                .size(310.dp, 196.39.dp)
                                                                .weight(1f)
                                                        )
                                                    }
                                                if (pagerState.currentPage.plus(1) != existingCards.size) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.ic_forward),
                                                        contentDescription = "",
                                                        colorFilter = ColorFilter.tint(colorResource(id = R.color.golden_global)),
                                                        modifier = Modifier
                                                            .size(20.dp)
                                                            .weight(0.2f)
                                                            .clickable(
                                                                interactionSource = interactionSource,
                                                                indication = null,
                                                                onClick = {
                                                                    coroutineScope.launch {
                                                                        pagerState.animateScrollToPage(
                                                                            pagerState.currentPage + 1
                                                                        )
                                                                    }
                                                                }
                                                            )
                                                    )
                                                } else {
                                                    Spacer(modifier = Modifier
                                                        .weight(0.2f))
                                                }
                                            }
                                        }
                                    }
                                    Column(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 24.dp, end = 24.dp)) {
                                        for (i in 0 until existingCards.size) {
                                            when (existingCards[i].name) {
                                                "Turquoise" -> {
                                                    cardDetailSection(card = existingCards[i])
                                                    existingCards[i].isSelected = true
                                                    for (j in 0 until existingCards.size) {
                                                        if (j != i) {
                                                            existingCards[j].isSelected = false
                                                        }
                                                    }
                                                    break
                                                }
                                                "Metal" -> {
                                                    cardDetailSection(card = existingCards[i])
                                                    existingCards[i].isSelected = true
                                                    for (j in 0 until existingCards.size) {
                                                        if (j != i) {
                                                            existingCards[j].isSelected = false
                                                        }
                                                    }
                                                    break
                                                }
                                                "Gold" -> {
                                                    cardDetailSection(card = existingCards[i])
                                                    existingCards[i].isSelected = true
                                                    for (j in 0 until existingCards.size) {
                                                        if (j != i) {
                                                            existingCards[j].isSelected = false
                                                        }
                                                    }
                                                    break
                                                }
                                                "Business" -> {
                                                    cardDetailSection(card = existingCards[i])
                                                    existingCards[i].isSelected = true
                                                    for (j in 0 until existingCards.size) {
                                                        if (j != i) {
                                                            existingCards[j].isSelected = false
                                                        }
                                                    }
                                                    break
                                                }
                                                "Black" -> {
                                                    cardDetailSection(card = existingCards[i])
                                                    existingCards[i].isSelected = true
                                                    for (j in 0 until existingCards.size) {
                                                        if (j != i) {
                                                            existingCards[j].isSelected = false
                                                        }
                                                    }
                                                    break
                                                }
                                                "Classic" -> {
                                                    cardDetailSection(card = existingCards[i])
                                                    existingCards[i].isSelected = true
                                                    for (j in 0 until existingCards.size) {
                                                        if (j != i) {
                                                            existingCards[j].isSelected = false
                                                        }
                                                    }
                                                    break
                                                }
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier
                                        .weight(1f))
                                    val interactionSource = remember { MutableInteractionSource() }
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .background(colorResource(id = R.color.golden_global))
                                        .padding(top = 8.dp, bottom = 8.dp)
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = null,
                                            onClick = {
                                                navController.navigate("page18")
                                            }
                                        ),
                                        horizontalArrangement = Arrangement.Center) {
                                        Text(
                                            text = "KART AYARLARI",
                                            color = Color.White,
                                            fontWeight = FontWeight.W500
                                        )
                                    }
                                    Spacer(modifier = Modifier
                                        .height(36.dp))
                                }
                            } else {
                                Text(
                                    text = "Hesabınız adına herhangi bir kartınız bulunmamaktadır.",
                                    color = colorResource(id = R.color.eft),
                                    textAlign = TextAlign.Center,
                                    lineHeight = TextUnit(1.3f, TextUnitType.Em),
                                    modifier = Modifier
                                        .width(200.dp)
                                )
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun cardDetailSection(card: card) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = card.name,
                    fontWeight = FontWeight.W700,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            cardDetailRow(
                text1 = "Kart Numarası",
                text2 = card.cardNo ?: "---- ---- ---- ----",
                hasSwitch = false
            )
            cardDetailRow(
                text1 = "Kullanılabilir Limit",
                text2 = String.format("%.2f", card.limit) + " TL",
                hasSwitch = false
            )
            cardDetailRow(
                text1 = "Günlük Kullanılabilir Limit",
                text2 = String.format("%.2f", 10000.0) + " TL",
                hasSwitch = false
            )
            cardDetailRow(
                text1 = "Aylık Harcama",
                text2 = String.format("%.2f", 0.0) + " TL",
                hasSwitch = false
            )
            cardDetailRow(
                text1 = "İnternet Üzerinden Alışveriş",
                text2 = "",
                hasSwitch = true
            )
            cardDetailRow(
                text1 = "Kullanıma Açıklık Durumu",
                text2 = if (card.availableForUse) "Açık" else "Geçici Olarak Kapalı",
                hasSwitch = false
            )
        }

        @Composable
        fun cardDetailRow(
            text1: String,
            text2: String,
            hasSwitch: Boolean
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()) {
                Text(
                    text = text1,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier
                    .weight(1f))
                if (!hasSwitch) {
                    Text(
                        text = text2,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                enabled = text2 == "Geçici Olarak Kapalı",
                                onClick = {

                                }
                            )
                    )
                } else {
                    val interactionSource = remember { MutableInteractionSource() }
                    Switch(
                        checked = checkedInternetShopping.value,
                        onCheckedChange = {
                            checkedInternetShopping.value = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = colorResource(id = R.color.unchecked_switch),
                            checkedTrackColor = colorResource(id = R.color.checked_switch),
                            uncheckedThumbColor = colorResource(id = R.color.unchecked_switch),
                            uncheckedTrackColor = Color.White,
                            uncheckedBorderColor = Color.Transparent,
                            checkedBorderColor = Color.Transparent
                        ),
                        interactionSource = interactionSource,
                        modifier = Modifier
                            .scale(0.6f)
                            .indication(
                                interactionSource = interactionSource,
                                indication = null
                            )
                            .height(20.dp)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun myCardsPreview() {
        GoldenGlobalMobileTheme {
            myCards(navController = rememberNavController())
        }
    }
}