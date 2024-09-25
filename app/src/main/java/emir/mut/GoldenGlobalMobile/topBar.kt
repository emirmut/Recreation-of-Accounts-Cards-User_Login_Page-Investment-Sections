package emir.mut.GoldenGlobalMobile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class topBar {
    companion object {
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun centerAlignedTopBar(
            title: @Composable () -> Unit,
            haveNavigationIcon: Boolean,
            haveActionIcon: Boolean,
            navigationIcon: @Composable () -> Unit = {},
            onNavigationIconClick: () -> Unit = {},
            actionIcon: @Composable () -> Unit = {},
            onActionIconClick: () -> Unit = {}
        ) {
            CenterAlignedTopAppBar(
                title = {
                    title()
                },
                navigationIcon = {
                    if (haveNavigationIcon) {
                        IconButton(
                            onClick = {
                                onNavigationIconClick()
                            }
                        ) {
                            navigationIcon()
                        }
                    }
                },
                actions = {
                    if (haveActionIcon) {
                        IconButton(
                            onClick = {
                                onActionIconClick()
                            }
                        ) {
                            actionIcon()
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.top_bar_background),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                modifier = Modifier
                    .statusBarsPadding()
                )
        }

        @Composable
        fun back_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp),
                colorFilter =  ColorFilter.tint(Color.White)
            )
        }

        @Composable
        fun close_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .background(Color.White, shape = CircleShape),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.golden_global))
            )
        }

        @Composable
        fun hamburger_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_hamburger),
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
            )
        }

        @Composable
        fun plus_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }

        @Composable
        fun user_settings_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_user_settings),
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
            )
        }

        @Composable
        fun notification_bell_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_notification_bell),
                contentDescription = "",
                modifier = Modifier
                    .size(32.dp)
            )
        }

        @Composable
        fun question_icon() {
            Image(
                painter = painterResource(id = R.drawable.ic_question),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun myEasyAddressesPreview() {
        GoldenGlobalMobileTheme {
            centerAlignedTopBar(
                {},
                true,
                true,
                navigationIcon = {},
                onNavigationIconClick = {},
                actionIcon = {},
                onActionIconClick = {}
            )
        }
    }
}