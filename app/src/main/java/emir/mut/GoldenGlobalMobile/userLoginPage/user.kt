package emir.mut.GoldenGlobalMobile.userLoginPage

import androidx.compose.runtime.MutableState

data class user(
    var userName: String,
    var customerNo: String,
    var password: MutableState<String>,
    var idNumber: String,
    var isSelected: Boolean
)
