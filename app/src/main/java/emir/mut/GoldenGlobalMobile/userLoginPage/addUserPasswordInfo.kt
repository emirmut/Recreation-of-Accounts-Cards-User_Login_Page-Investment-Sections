package emir.mut.GoldenGlobalMobile.userLoginPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class addUserPasswordInfo {
    companion object {
        @Composable
        fun addUserPasswordInfo(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Şifre"
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
                            navController.popBackStack()
                        }
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(36.dp, 32.dp, 36.dp, 0.dp)) {
                        addUserPasswordInfoRow(
                            text = "Şifre;",
                            hasDot = false
                        )
                        addUserPasswordInfoRow(
                            text = stringResource(id = R.string.addUserPasswordInfo0),
                            hasDot = true
                        )
                        addUserPasswordInfoRow(
                            text = stringResource(id = R.string.addUserPasswordInfo1),
                            hasDot = true
                        )
                        addUserPasswordInfoRow(
                            text = stringResource(id = R.string.addUserPasswordInfo2),
                            hasDot = true
                        )
                        Spacer(modifier = Modifier
                            .height(16.dp))
                        addUserPasswordInfoRow(
                            text = stringResource(id = R.string.addUserPasswordInfo3),
                            hasDot = false
                        )
                        Spacer(modifier = Modifier
                            .height(16.dp))
                        addUserPasswordInfoRow(
                            text = stringResource(id = R.string.addUserPasswordInfo4),
                            hasDot = false
                        )
                    }
                }
            )
        }

        @Composable
        fun addUserPasswordInfoRow(
            text: String,
            hasDot: Boolean
        ) {
            Row {
                if (hasDot) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_dot),
                        contentDescription = ""
                    )
                }
                Text(
                    text = text,
                    color = colorResource(id = R.color.subtitle_text_color),
                    lineHeight = TextUnit(1.3f, TextUnitType.Em)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun addUserPasswordInfoPreview() {
        GoldenGlobalMobileTheme {
            addUserPasswordInfo(navController = rememberNavController())
        }
    }
}