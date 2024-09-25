package emir.mut.GoldenGlobalMobile.investment

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.R
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme

class compatibilityTestIsDone {
    companion object {
        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @Composable
        fun compatibilityTestIsDone(navController: NavController) {
            Scaffold(
                content = {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.app_background)),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier
                            .weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_transaction_done),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Uygunluk testiniz tamamlandı.",
                            color = colorResource(id = R.color.fifty_seven)
                        )
                        Spacer(modifier = Modifier
                            .weight(1f))
                        val interactionSource = remember { MutableInteractionSource() }
                        Image(
                            painter = painterResource(id = R.drawable.ic_cancel),
                            contentDescription = "",
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    colorResource(id = R.color.fifty_seven),
                                    shape = CircleShape
                                )
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = {
                                        navController.navigate("page40")
                                    }
                                )
                        )
                        Spacer(modifier = Modifier
                            .height(36.dp))
                    }
                }
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun compatibilityTestIsDonePreview() {
        GoldenGlobalMobileTheme {
            compatibilityTestIsDone(navController = rememberNavController())
        }
    }
}