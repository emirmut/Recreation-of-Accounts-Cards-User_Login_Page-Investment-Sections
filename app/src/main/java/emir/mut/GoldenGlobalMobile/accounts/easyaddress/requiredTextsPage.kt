package emir.mut.GoldenGlobalMobile.accounts.easyaddress

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class requiredTextsPage {
    companion object {
        @Composable
        fun requiredTextsPage(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Aydınlatma Metni"
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
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                        .background(colorResource(id = R.color.app_background)),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        item {
                            requiredTextArea("Aydınlatma Metni", stringResource(id = R.string.lorem))
                            requiredTextArea("Açık Rıza Metni", stringResource(id = R.string.lorem))
                        }
                        item {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(top = 32.dp),
                                horizontalArrangement = Arrangement.Center) {
                                Text(
                                    text = "Okudum, onaylıyorum.",
                                    color = colorResource(id = R.color.golden_global),
                                    modifier = Modifier
                                        .clickable {
                                            navController.navigate("page2")
                                        }
                                )
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun requiredTextArea(
            title: String,
            paragraph: String
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .size(327.dp, 458.dp)) {
                LazyColumn(modifier = Modifier
                    .padding(32.dp, 24.dp, 32.dp, 32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    item {
                        Row(modifier = Modifier
                            .padding(bottom = 32.dp)) {
                            Text(
                                text = title,
                                fontSize = 17.sp
                            )
                        }
                    }
                    item {
                        Text(
                            text = paragraph
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun requiredTextsPagePreview() {
        GoldenGlobalMobileTheme {
            requiredTextsPage(navController = rememberNavController())
        }
    }
}