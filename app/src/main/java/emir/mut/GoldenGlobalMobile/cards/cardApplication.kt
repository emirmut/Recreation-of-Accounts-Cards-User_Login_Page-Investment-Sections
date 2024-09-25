package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
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

class cardApplication {
    companion object {
        var cards = mutableStateListOf(
            card(
                verticalImage = R.drawable.classic_card,
                horizontalImage = R.drawable.classic_card_landscape,
                name = "Classic",
                detail = {
                    cardDetailState(cardName = "Classic")
                },
                cardNo = "1234 5678 9012 3456",
                limit = 55126.44,
                isSelected = false,
                last4Digit = "3456",
                availableForUse = true,
                password = ""
            ),
            card(
                verticalImage = R.drawable.black_card,
                horizontalImage = R.drawable.black_card_landscape,
                name = "Black",
                detail = {
                    cardDetailState(cardName = "Black")
                },
                cardNo = "7890 1234 5678 9012",
                limit = 55126.44,
                isSelected = false,
                last4Digit = "9012",
                availableForUse = true,
                password = ""
            ),
            card(
                verticalImage = R.drawable.gold_card,
                horizontalImage = R.drawable.gold_card_landscape,
                name = "Gold",
                detail = {
                    cardDetailState(cardName = "Gold")
                },
                cardNo = "3456 7890 1234 5678",
                limit = 55126.44,
                isSelected = false,
                last4Digit = "5678",
                availableForUse = true,
                password = ""
            ),
            card(
                verticalImage = R.drawable.business_card,
                horizontalImage = R.drawable.business_card_landscape,
                name = "Business",
                detail = {
                    cardDetailState(cardName = "Business")
                },
                cardNo = "9012 3456 7890 1234",
                limit = 55126.44,
                isSelected = false,
                last4Digit = "1234",
                availableForUse = true,
                password = ""
            ),
            card(
                verticalImage = R.drawable.turquiose_card,
                horizontalImage = R.drawable.turquoise_card_landscape,
                name = "Turquoise",
                detail = {
                    cardDetailState(cardName = "Turquoise")
                },
                cardNo = "5678 9012 3456 7890",
                limit = 55126.44,
                isSelected = false,
                last4Digit = "7890",
                availableForUse = true,
                password = ""
            ),
            card(
                verticalImage = R.drawable.metal_card,
                horizontalImage = R.drawable.metal_card_landscape,
                name = "Metal",
                detail = {
                    cardDetailState(cardName = "Metal")
                },
                cardNo = "2345 6789 0123 4567",
                limit = 55126.44,
                isSelected = false,
                last4Digit = "4567",
                availableForUse = true,
                password = ""
            )
        )
        var cardDetails = listOf(
            R.string.cardsText1,
            R.string.cardsText2,
            R.string.cardsText3,
            R.string.cardsText4,
            R.string.cardsText5,
            R.string.cardsText6,
            R.string.cardsText7,
            R.string.cardsText8,
            R.string.cardsText9,
            R.string.cardsText10,
            R.string.cardsText11,
        )
        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun cardApplication(navController: NavController) {
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
                                    text = "Kart Başvurusu"
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
                            .padding(top = 48.dp)) {
                            Column {
                                val pagerState = rememberPagerState(
                                    pageCount = {
                                        cards.size
                                    }
                                )
                                HorizontalPager(
                                    state = pagerState,
                                    key = {
                                        cards[it].verticalImage
                                    },
                                    pageSize = PageSize.Fill
                                ) { index ->
                                    Row(modifier = Modifier
                                        .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically) {
                                        val interactionSource = remember { MutableInteractionSource() }
                                        if (pagerState.currentPage != 0) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_back),
                                                contentDescription = "",
                                                colorFilter = ColorFilter.tint(colorResource(id = R.color.golden_global)),
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .weight(1f)
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
                                                .weight(1f))
                                        }
                                        Image(
                                            painter = painterResource(id = cards[index].verticalImage),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(112.6.dp, 179.dp)
                                                .weight(1f)
                                        )
                                        if (pagerState.currentPage.plus(1) != cards.size) {
                                            Image(
                                                painter = painterResource(id = R.drawable.ic_forward),
                                                contentDescription = "",
                                                colorFilter = ColorFilter.tint(colorResource(id = R.color.golden_global)),
                                                modifier = Modifier
                                                    .size(20.dp)
                                                    .weight(1f)
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
                                                .weight(1f))
                                        }
                                    }
                                }
                                cards[pagerState.currentPage].detail()
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
                                            cardApplication2.selectedCard = cards[pagerState.currentPage]
                                            navController.navigate("page20")
                                        }
                                    ),
                                    horizontalArrangement = Arrangement.Center) {
                                    Text(
                                        text = "HEMEN BAŞVUR",
                                        color = Color.White,
                                        fontWeight = FontWeight.W500
                                    )
                                }
                                Spacer(modifier = Modifier
                                    .height(36.dp))
                            }
                        }
                    }
                )
            }
        }

        @Composable
        fun cardDetailState(cardName: String) {
            when (cardName) {
                "Turquoise" -> {
                    cardDetailSection(
                        header = "Turquoise",
                        textSequence = listOf(
                            stringResource(id = cardDetails[6]),
                            stringResource(id = cardDetails[1]),
                            stringResource(id = cardDetails[7]),
                            stringResource(id = cardDetails[3]),
                            stringResource(id = cardDetails[4]),
                            stringResource(id = cardDetails[5])
                        )
                    )
                }
                "Metal" -> {
                    cardDetailSection(
                        header = "Metal",
                        textSequence = listOf(
                            stringResource(id = cardDetails[5]),
                            stringResource(id = cardDetails[8]),
                            stringResource(id = cardDetails[9]),
                            stringResource(id = cardDetails[0]),
                            stringResource(id = cardDetails[1]),
                            stringResource(id = cardDetails[2]),
                            stringResource(id = cardDetails[10]),
                            stringResource(id = cardDetails[4]),
                            stringResource(id = cardDetails[5])
                        )
                    )
                }
                "Gold" -> {
                    cardDetailSection(
                        header = "Gold",
                        textSequence = listOf(
                            stringResource(id = cardDetails[0]),
                            stringResource(id = cardDetails[1]),
                            stringResource(id = cardDetails[2]),
                            stringResource(id = cardDetails[3]),
                            stringResource(id = cardDetails[4]),
                            stringResource(id = cardDetails[5])
                        )
                    )
                }
                "Business" -> {
                    cardDetailSection(
                        header = "Business",
                        textSequence = listOf(
                            stringResource(id = cardDetails[0]),
                            stringResource(id = cardDetails[1]),
                            stringResource(id = cardDetails[2]),
                            stringResource(id = cardDetails[3]),
                            stringResource(id = cardDetails[4]),
                            stringResource(id = cardDetails[5])
                        )
                    )
                }
                "Black" -> {
                    cardDetailSection(
                        header = "Black",
                        textSequence = listOf(
                            stringResource(id = cardDetails[0]),
                            stringResource(id = cardDetails[1]),
                            stringResource(id = cardDetails[2]),
                            stringResource(id = cardDetails[3]),
                            stringResource(id = cardDetails[4]),
                            stringResource(id = cardDetails[5])
                        )
                    )
                }
                "Classic" -> {
                    cardDetailSection(
                        header = "Classic",
                        textSequence = listOf(
                            stringResource(id = cardDetails[0]),
                            stringResource(id = cardDetails[1]),
                            stringResource(id = cardDetails[2]),
                            stringResource(id = cardDetails[3]),
                            stringResource(id = cardDetails[4]),
                            stringResource(id = cardDetails[5])
                        )
                    )
                }
            }
        }

        @Composable
        fun cardDetailSection(
            header: String,
            textSequence: List<String>
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 28.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = header,
                    fontWeight = FontWeight.W700,
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            textSequence.forEach { text ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 0.dp, 12.dp, 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_done_red),
                        contentDescription = "",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = text,
                        fontWeight = FontWeight.W500,
                        fontSize = 12.sp,
                        lineHeight = TextUnit(1.4f, TextUnitType.Em)
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun cardApplicationPreview() {
        GoldenGlobalMobileTheme {
            cardApplication(navController = rememberNavController())
        }
    }
}