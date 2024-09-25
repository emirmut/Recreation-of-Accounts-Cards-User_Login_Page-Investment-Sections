package emir.mut.GoldenGlobalMobile.userLoginPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.MainActivity
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.dialogues
import emir.mut.GoldenGlobalMobile.topBar
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class userNameAndPasswordSettings {
    companion object {
        var deleteIconClickedUser: MutableState<user> = mutableStateOf(selectedUser())
        var customerNoIconClickedUser: MutableState<user> = mutableStateOf(selectedUser())
        @Composable
        fun userNameAndPasswordSettings(navController: NavController) {
            Scaffold(
                topBar = {
                    topBar.centerAlignedTopBar(
                        title = {
                            Text(
                                text = "Kullanıcı / Şifre Ayarları"
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
                    var showDeleteUserDialog = remember { mutableStateOf(false) }
                    var showCustomerNoDialog = remember { mutableStateOf(false) }
                    LazyColumn(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background))
                        .padding(innerPadding)
                        .padding(top = 32.dp)) {
                        item {
                            Column(modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp)
                                .background(Color.White, shape = RoundedCornerShape(10.dp))) {
                                userLoginPage.users.forEach { user ->
                                    userNameAndPasswordSettingsRow(
                                        navController = navController,
                                        hasDeleteIcon = true,
                                        onDeleteIconClick = {
                                            deleteIconClickedUser.value = user
                                            showDeleteUserDialog.value = true
                                        },
                                        text1 = user.userName,
                                        hasSettingsIcon = true,
                                        onSettingsIconClick = {
                                            customerNoIconClickedUser.value = user
                                            showCustomerNoDialog.value = true
                                        }
                                    )
                                    MainActivity.horizontalDivider()
                                }
                                userNameAndPasswordSettingsRow(
                                    navController = navController,
                                    hasDeleteIcon = false,
                                    onDeleteIconClick = {},
                                    text1 = "Kullanıcı Ekle",
                                    hasSettingsIcon = false,
                                    onSettingsIconClick = {}
                                )
                            }
                        }
                    }
                    if (showDeleteUserDialog.value) {
                        dialogues.deleteUserDialog(
                            userName = deleteIconClickedUser.value.userName,
                            onConfirm = {
                                userLoginPage.users.remove(deleteIconClickedUser.value)
                                showDeleteUserDialog.value = false
                            },
                            onDismiss = {
                                showDeleteUserDialog.value = false
                            }
                        )
                    }
                    if (showCustomerNoDialog.value) {
                        dialogues.customerNoDialog(
                            userName = customerNoIconClickedUser.value.userName,
                            customerNo = customerNoIconClickedUser.value.customerNo,
                            onConfirm = {
                                showCustomerNoDialog.value = false
                            },
                            onDismiss = {
                                showCustomerNoDialog.value = false
                            }
                        )
                    }
                }
            )
        }

        @Composable
        fun userNameAndPasswordSettingsRow(
            navController: NavController,
            hasDeleteIcon: Boolean,
            onDeleteIconClick: () -> Unit,
            text1: String,
            hasSettingsIcon: Boolean,
            onSettingsIconClick: () -> Unit,
        ) {
            Row(modifier = Modifier
                .padding(16.dp, 0.dp, 16.dp, 0.dp)
                .fillMaxWidth()
                .background(Color.White),
                verticalAlignment = Alignment.CenterVertically) {
                if (hasDeleteIcon) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(0.dp, 8.dp, 16.dp, 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    onDeleteIconClick()
                                }
                            )
                    )
                }
                Text(
                    text = text1,
                    color = if (hasDeleteIcon) Color.Black else colorResource(id = R.color.golden_global),
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 8.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            enabled = text1 == "Kullanıcı Ekle",
                            onClick = {
                                navController.navigate("page32")
                            }
                        )
                )
                if (hasSettingsIcon) {
                    Spacer(modifier = Modifier
                        .weight(1f))
                    Image(
                        painter = painterResource(id = R.drawable.ic_settings),
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.subtitle_text_color)),
                        modifier = Modifier
                            .padding(0.dp, 8.dp, 0.dp, 8.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    onSettingsIconClick()
                                }
                            )
                    )
                }
            }
        }

        fun selectedUser(): user {
            userLoginPage.users.forEach { user ->
                if (user.isSelected) {
                    return user
                }
            }
            return userLoginPage.users.first()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun userNameAndPasswordSettings() {
        GoldenGlobalMobileTheme {
            userNameAndPasswordSettings(navController = rememberNavController())
        }
    }
}