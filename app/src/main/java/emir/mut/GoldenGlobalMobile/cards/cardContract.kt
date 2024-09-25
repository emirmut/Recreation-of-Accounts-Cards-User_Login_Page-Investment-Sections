package emir.mut.GoldenGlobalMobile.cards

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class cardContract {
    companion object {
        var checkedCardContract: MutableState<Boolean> = mutableStateOf(false)
        @SuppressLint("RememberReturnType")
        @Composable
        fun cardContract(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kart Sözleşmesi"
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
                        Column(modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(10.dp))) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Okudum, onaylıyoum",
                                    color = colorResource(id = R.color.subtitle_text_color)
                                )
                                Spacer(modifier = Modifier
                                    .weight(1f))
                                val interactionSource = remember { MutableInteractionSource() }
                                Switch(
                                    checked = checkedCardContract.value,
                                    onCheckedChange = {
                                        checkedCardContract.value = it
                                        if (checkedCardContract.value) {
                                            navController.popBackStack()
                                        }
                                    },
                                    colors = SwitchDefaults.colors(
                                        checkedThumbColor = Color.White,
                                        checkedTrackColor = colorResource(id = R.color.checked_switch),
                                        uncheckedThumbColor = Color.White,
                                        uncheckedTrackColor = colorResource(id = R.color.unchecked_switch),
                                        uncheckedBorderColor = Color.Transparent,
                                        checkedBorderColor = Color.Transparent
                                    ),
                                    interactionSource = interactionSource,
                                    modifier = Modifier
                                        .scale(0.75f)
                                        .indication(
                                            interactionSource = interactionSource,
                                            indication = null
                                        )
                                )
                            }
                            MainActivity.horizontalDivider()
                            LazyColumn(modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize()
                                .background(colorResource(id = R.color.app_background))) {
                                item {
                                    cardContractArea(
                                        title = "Kart Sözleşmesi 1",
                                        paragraph = stringResource(id = R.string.lorem)
                                    )
                                    cardContractArea(
                                        title = "Kart Sözleşmesi 2",
                                        paragraph = stringResource(id = R.string.lorem)
                                    )
                                    cardContractArea(
                                        title = "Kart Sözleşmesi 3",
                                        paragraph = stringResource(id = R.string.lorem)
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }

        @Composable
        fun cardContractArea(
            title: String,
            paragraph: String
        ) {
            Column(modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .size(375.dp, 458.dp)) {
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
    fun cardContractPreview() {
        GoldenGlobalMobileTheme {
            cardContract(navController = rememberNavController())
        }
    }
}