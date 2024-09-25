package emir.mut.GoldenGlobalMobile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.cards.card
import emir.mut.GoldenGlobalMobile.cards.cardApplication
import emir.mut.GoldenGlobalMobile.cards.cardSettings
import emir.mut.GoldenGlobalMobile.cards.myCards
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class dialogues {
    companion object {
        var openReportLostStolenWhenSwitchIsOpenConfirmedDialog: MutableState<Boolean> = mutableStateOf(false)
        var openReportLostStolenWhenSwitchIsClosedConfirmedDialog: MutableState<Boolean> = mutableStateOf(false)
        @Composable
        fun easyAddressCompletedDialog(navController: NavController) {
            Dialog(onDismissRequest = {
                navController.navigate("page0")
            }) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_transaction_done_white_background),
                            contentDescription = ""
                        )
                        Text(
                            text = "Kolay adresiniz başarıyla tanımlanmıştır.",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                navController.navigate("page0")
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.golden_global),
                                contentColor = Color.White,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = Color.Unspecified
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Tamam"
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun changePasswordNotDoneDialog(navController: NavController) {
            Dialog(onDismissRequest = {
                navController.navigate("page26")
            }) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_yellow_exclamation),
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Text(
                            text = "Şifreniz değiştirilemedi. \"Yeni Şifre\" ile \"Yeni Şifre Tekrar\" aynı olmalıdır.",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                navController.navigate("page26")
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.golden_global),
                                contentColor = Color.White,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = Color.Unspecified
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Tamam"
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun reportLostStolenWhenSwitchIsOpenDialog(
            navController: NavController,
            card: card?
        ) {
            Dialog(onDismissRequest = {
                navController.navigate("page18") {
                    popUpTo("page18") {
                        inclusive = true
                    }
                }
            }) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 8.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_yellow_exclamation),
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Text(
                            text = "Son dört hanesi " + cardSettings.selectedCard()?.last4Digit + " olan " + cardSettings.selectedCard()?.name + " kartınız geçici olarak kullanıma kapanacaktır. Onaylıyor musunuz?",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(1.5f, TextUnitType.Em)
                        )
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("page18") {
                                        popUpTo("page18") {
                                            inclusive = true
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(5.dp),
                                border = BorderStroke(1.dp, colorResource(id = R.color.golden_global))
                            ) {
                                Text(
                                    text = "Vazgeç",
                                    color = colorResource(id = R.color.golden_global)
                                )
                            }
                            Button(
                                onClick = {
                                    card?.availableForUse = false
                                    openReportLostStolenWhenSwitchIsOpenConfirmedDialog.value = true
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.golden_global),
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Unspecified,
                                    disabledContentColor = Color.Unspecified,
                                )) {
                                Text(
                                    text = "Tamam"
                                )
                            }
                            if (openReportLostStolenWhenSwitchIsOpenConfirmedDialog.value) {
                                reportLostStolenWhenSwitchIsOpenConfirmedDialog(navController = navController)
                            }
                        }
                    }
                }
            }
        }

        @Composable
        fun reportLostStolenWhenSwitchIsOpenConfirmedDialog(navController: NavController) {
            Dialog(onDismissRequest = {
                navController.navigate("page18")
            }) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_done_green),
                            contentDescription = ""
                        )
                        Text(
                            text = "Son dört hanesi " + cardSettings.selectedCard()?.last4Digit + " olan " + cardSettings.selectedCard()?.name + " kartınız geçici olarak kullanıma kapatılmıştır.",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                navController.navigate("page18")
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.golden_global),
                                contentColor = Color.White,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = Color.Unspecified
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Tamam"
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun reportLostStolenWhenSwitchIsClosedDialog(navController: NavController) {
            Dialog(onDismissRequest = {
                navController.navigate("page18") {
                    popUpTo("page18") {
                        inclusive = true
                    }
                }
            }) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 8.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_yellow_exclamation),
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Text(
                            text = "Son dört hanesi " + cardSettings.selectedCard()?.last4Digit + " olan " + cardSettings.selectedCard()?.name + " kartınız iptal edilecektir. Onaylıyor musunuz?",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(1.5f, TextUnitType.Em)
                        )
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("page18") {
                                        popUpTo("page18") {
                                            inclusive = true
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(5.dp),
                                border = BorderStroke(1.dp, colorResource(id = R.color.golden_global))
                            ) {
                                Text(
                                    text = "Vazgeç",
                                    color = colorResource(id = R.color.golden_global)
                                )
                            }
                            Button(
                                onClick = {
                                    myCards.existingCards.remove(cardSettings.selectedCard())
                                    openReportLostStolenWhenSwitchIsClosedConfirmedDialog.value = true
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.golden_global),
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Unspecified,
                                    disabledContentColor = Color.Unspecified,
                                )) {
                                Text(
                                    text = "Tamam"
                                )
                            }
                            if (openReportLostStolenWhenSwitchIsClosedConfirmedDialog.value) {
                                reportLostStolenWhenSwitchIsClosedConfirmedDialog(navController = navController)
                            }
                        }
                    }
                }
            }
        }

        @Composable
        fun reportLostStolenWhenSwitchIsClosedConfirmedDialog(navController: NavController) {
            Dialog(onDismissRequest = {
                navController.navigate("page18")
            }) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_done_green),
                            contentDescription = ""
                        )
                        Text(
                            text = "Son dört hanesi " + cardSettings.selectedCard()?.last4Digit + " olan " + cardSettings.selectedCard()?.name + " kartınız iptal edilmiştir.",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Button(
                            onClick = {
                                navController.navigate("page18")
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.golden_global),
                                contentColor = Color.White,
                                disabledContainerColor = Color.Unspecified,
                                disabledContentColor = Color.Unspecified
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Tamam"
                            )
                        }
                    }
                }
            }
        }

        @Composable
        fun deleteUserDialog(
            userName: String,
            onConfirm: () -> Unit,
            onDismiss: () -> Unit
        ) {
            Dialog(
                onDismissRequest = {
                    onDismiss()
                }
            ) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(top = 8.dp, bottom = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_yellow_exclamation),
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                        )
                        Text(
                            text = "Bu kullanıcıyı silmek istediğinize emin misiniz? ($userName)",
                            fontSize = 14.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            lineHeight = TextUnit(1.5f, TextUnitType.Em),
                            modifier = Modifier
                                .padding(start = 36.dp, end = 36.dp)
                        )
                        Row(modifier = Modifier
                            .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly) {
                            OutlinedButton(
                                onClick = {
                                    onDismiss()
                                },
                                shape = RoundedCornerShape(5.dp),
                                border = BorderStroke(1.dp, colorResource(id = R.color.golden_global))
                            ) {
                                Text(
                                    text = "Vazgeç",
                                    color = colorResource(id = R.color.golden_global)
                                )
                            }
                            Button(
                                onClick = {
                                    onConfirm()
                                },
                                shape = RoundedCornerShape(5.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colorResource(id = R.color.golden_global),
                                    contentColor = Color.White,
                                    disabledContainerColor = Color.Unspecified,
                                    disabledContentColor = Color.Unspecified,
                                )) {
                                Text(
                                    text = "Tamam"
                                )
                            }
                        }
                    }
                }
            }
        }

        @Composable
        fun customerNoDialog(
            userName: String,
            customerNo: String,
            onConfirm: () -> Unit,
            onDismiss: () -> Unit
        ) {
            AlertDialog(
                onDismissRequest = {
                    onDismiss()
                },
                title = {
                    Text(
                        text = "Kullanıcı müşteri numarası"
                    )
                },
                text = {
                    Text(
                        text = "$userName adlı müşterinin müşteri numarası: $customerNo"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text("Tamam")
                    }
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun customerNoDialogDialogPreview() {
        GoldenGlobalMobileTheme {
            customerNoDialog(
                userName = "Emir Mut",
                customerNo = "0001",
                onConfirm = {},
                onDismiss = {}
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun deleteUserDialogPreview() {
        GoldenGlobalMobileTheme {
            deleteUserDialog(
                userName = "Emir Mut",
                onConfirm = {},
                onDismiss = {}
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun reportLostStolenWhenSwitchIsClosedConfirmedDialogPreview() {
        GoldenGlobalMobileTheme {
            reportLostStolenWhenSwitchIsClosedConfirmedDialog(navController = rememberNavController())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun reportLostStolenWhenSwitchIsClosedDialogPreview() {
        GoldenGlobalMobileTheme {
            reportLostStolenWhenSwitchIsClosedDialog(navController = rememberNavController())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun reportLostStolenWhenSwitchIsOpenConfirmedDialogPreview() {
        GoldenGlobalMobileTheme {
            reportLostStolenWhenSwitchIsOpenConfirmedDialog(navController = rememberNavController())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun reportLostStolenWhenSwitchIsOpenDialogPreview() {
        GoldenGlobalMobileTheme {
            reportLostStolenWhenSwitchIsOpenDialog(
                navController = rememberNavController(),
                card = cardApplication.cards[0]
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun changePasswordNotDoneDialogPreview() {
        GoldenGlobalMobileTheme {
            changePasswordNotDoneDialog(navController = rememberNavController())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun easyAddressCompletedDialogPreview() {
        GoldenGlobalMobileTheme {
            easyAddressCompletedDialog(navController = rememberNavController())
        }
    }
}