package emir.mut.GoldenGlobalMobile.accounts.myaccounts

data class account(
    var accountNumber: Int,
    var accountBalance: Double,
    var accountType: String,
    var exchangeType: String,
    var accountName: String,
    var IBANnumber: String,
    var isSelected: Boolean
)
