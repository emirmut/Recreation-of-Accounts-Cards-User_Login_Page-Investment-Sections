package emir.mut.GoldenGlobalMobile.accounts.easyaddress

import emir.mut.GoldenGlobalMobile.accounts.myaccounts.account

data class easyAddressAccount(
    var account: account,
    var easyAddressType: String,
    var easyAddressInput: String,
    var easyAddressNameInput: String,
    var beingEdited: Boolean = false
)
