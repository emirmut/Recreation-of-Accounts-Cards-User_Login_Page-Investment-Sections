package emir.mut.GoldenGlobalMobile.cards

import androidx.compose.runtime.Composable
import emir.mut.GoldenGlobalMobile.userLoginPage.userNameAndPasswordSettings

data class card(
    var verticalImage: Int,
    var horizontalImage: Int,
    var name: String,
    var detail: @Composable () -> Unit,
    var cardNo: String?,
    var cardHolder: String = userNameAndPasswordSettings.selectedUser().userName,
    var limit: Double,
    var isSelected: Boolean,
    var last4Digit: String,
    var availableForUse: Boolean,
    var password: String
)
