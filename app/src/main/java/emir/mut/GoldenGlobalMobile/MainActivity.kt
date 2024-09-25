package emir.mut.GoldenGlobalMobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import emir.mut.GoldenGlobalMobile.accounts.easyaddress.accountSelection
import emir.mut.GoldenGlobalMobile.accounts.easyaddress.definingEasyAddress
import emir.mut.GoldenGlobalMobile.accounts.easyaddress.easyAddressClass
import emir.mut.GoldenGlobalMobile.accounts.easyaddress.myEasyAddressesClass
import emir.mut.GoldenGlobalMobile.accounts.easyaddress.requiredTextsPage
import emir.mut.GoldenGlobalMobile.accounts.easyaddress.typeOfEasyAddress
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.accountDetail
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.accountInformation
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.accountIsOpened
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.accountType
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.accountTypeInformation
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.exchangeType
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.myAccounts
import emir.mut.GoldenGlobalMobile.accounts.myaccounts.openAccount
import emir.mut.GoldenGlobalMobile.cards.applicationIsDone
import emir.mut.GoldenGlobalMobile.cards.cardApplication2
import emir.mut.GoldenGlobalMobile.cards.cardContract
import emir.mut.GoldenGlobalMobile.cards.cardShippingAddress
import emir.mut.GoldenGlobalMobile.cards.cardApplication
import emir.mut.GoldenGlobalMobile.cards.cardSettings
import emir.mut.GoldenGlobalMobile.cards.changePassword
import emir.mut.GoldenGlobalMobile.cards.changePasswordIsDone
import emir.mut.GoldenGlobalMobile.cards.changePasswrodInfo
import emir.mut.GoldenGlobalMobile.cards.myCards
import emir.mut.GoldenGlobalMobile.cards.transactionLimits
import emir.mut.GoldenGlobalMobile.cards.transactionPermissions
import emir.mut.GoldenGlobalMobile.investment.ageSelection
import emir.mut.GoldenGlobalMobile.investment.compatibilityTest
import emir.mut.GoldenGlobalMobile.investment.compatibilityTestIsDone
import emir.mut.GoldenGlobalMobile.investment.compatibilityTestResults
import emir.mut.GoldenGlobalMobile.investment.compatibilityTestRiskTypes
import emir.mut.GoldenGlobalMobile.investment.educationalBackgroundSelection
import emir.mut.GoldenGlobalMobile.investment.evaluationProcessSelection
import emir.mut.GoldenGlobalMobile.investment.investmentAccountInformation
import emir.mut.GoldenGlobalMobile.investment.investmentAccountIsOpened
import emir.mut.GoldenGlobalMobile.investment.investmentExperienceSelection
import emir.mut.GoldenGlobalMobile.investment.myInvestmentAccounts
import emir.mut.GoldenGlobalMobile.investment.occupationSelection
import emir.mut.GoldenGlobalMobile.investment.openInvestmentAccount
import emir.mut.GoldenGlobalMobile.investment.productInformationSelection
import emir.mut.GoldenGlobalMobile.investment.riskAndIncomePreferenceSelection
import emir.mut.GoldenGlobalMobile.investment.selectExchangeType
import emir.mut.GoldenGlobalMobile.investment.transactionFrequencySelection
import emir.mut.GoldenGlobalMobile.investment.volumeInformationSelection
import emir.mut.GoldenGlobalMobile.ui.theme.GoldenGlobalMobileTheme
import emir.mut.GoldenGlobalMobile.userLoginPage.addUser
import emir.mut.GoldenGlobalMobile.userLoginPage.addUserPasswordInfo
import emir.mut.GoldenGlobalMobile.userLoginPage.forgotPassword
import emir.mut.GoldenGlobalMobile.userLoginPage.userIsAdded
import emir.mut.GoldenGlobalMobile.userLoginPage.userLoginPage
import emir.mut.GoldenGlobalMobile.userLoginPage.userNameAndPasswordSettings

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoldenGlobalMobileTheme {
                main()
            }
            // Set system bar colors to match the top bar
            WindowCompat.getInsetsController(window, window.decorView)?.apply {
                isAppearanceLightStatusBars = false // Use light icons if the status bar background is dark
                isAppearanceLightNavigationBars = false // Use light icons if the navigation bar background is dark
                window.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.top_bar_background)
                window.navigationBarColor = ContextCompat.getColor(this@MainActivity, R.color.top_bar_background)
            }
        }
    }

    @Composable
    fun main() {
        pageTransitions()
    }

    companion object {
        @Composable
        fun horizontalDivider() {
            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        }

        @Composable
        fun pageTransitions() {
            var navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "page29" // login screen
            ) {
                composable(route = "page0") {
                    myEasyAddressesClass.myEasyAddresses(navController = navController)
                }
                composable(route = "page1") {
                    easyAddressClass.easyAddress(navController = navController)
                }
                composable(route = "page2") {
                    definingEasyAddress.definingEasyAddress(navController = navController)
                }
                composable(route = "page3") {
                    typeOfEasyAddress.easyAddressType(navController = navController)
                }
                composable(route = "page4") {
                    requiredTextsPage.requiredTextsPage(navController = navController)
                }
                composable(route = "page5") {
                    accountSelection.accountSelection(navController = navController)
                }
                composable(route = "page6") {
                    mainPage.mainPage(navController = navController)
                }
                composable(route = "page7") {
                    openAccount.openAccount(navController = navController)
                }
                composable(route = "page8") {
                    accountType.accountType(navController = navController)
                }
                composable(route = "page9") {
                    exchangeType.exchangeType(navController = navController)
                }
                composable(route = "page10") {
                    accountTypeInformation.accountTypeInformation(
                        navController = navController,
                        accountType = accountType.accountTypes[0],
                        informationText = stringResource(id = R.string.cariAccount)
                    )
                }
                composable(route = "page11") {
                    accountTypeInformation.accountTypeInformation(
                        navController = navController,
                        accountType = accountType.accountTypes[1],
                        informationText = stringResource(id = R.string.standardPrepaidKart)
                    )
                }
                composable(route = "page12") {
                    accountTypeInformation.accountTypeInformation(
                        navController = navController,
                        accountType = accountType.accountTypes[2],
                        informationText = stringResource(id = R.string.kiymetliMadenAccount)
                    )
                }
                composable(route = "page13") {
                    accountInformation.accountInformation(navController = navController)
                }
                composable(route = "page14") {
                    accountIsOpened.accountIsOpened(navController = navController)
                }
                composable(route = "page15") {
                    accountDetail.accountDetail(navController = navController)
                }
                composable(route = "page16") {
                    myAccounts.myAccounts(navController = navController)
                }
                composable(route = "page17") {
                    cardApplication.cardApplication(navController = navController)
                }
                composable(route = "page18") {
                    cardSettings.cardSettings(navController = navController)
                }
                composable(route = "page19") {
                    transactionLimits.transactionLimits(navController = navController)
                }
                composable(route = "page20") {
                    cardApplication2.cardApplication2(navController = navController)
                }
                composable(route = "page21") {
                    cardShippingAddress.cardShippingAddress(navController = navController)
                }
                composable(route = "page22") {
                    cardContract.cardContract(navController = navController)
                }
                composable(route = "page23") {
                    applicationIsDone.applicationIsDone(navController = navController)
                }
                composable(route = "page24") {
                    myCards.myCards(navController = navController)
                }
                composable(route = "page25") {
                    transactionPermissions.transactionPermissions(navController = navController)
                }
                composable(route = "page26") {
                    changePassword.changePassword(navController = navController)
                }
                composable(route = "page27") {
                    changePasswrodInfo.changePasswordInfo(navController = navController)
                }
                composable(route = "page28") {
                    changePasswordIsDone.changePasswordIsDone(navController = navController)
                }
                composable(route = "page29") {
                    userLoginPage.userLoginPage(navController = navController)
                }
                composable(route = "page30") {
                    userNameAndPasswordSettings.userNameAndPasswordSettings(navController = navController)
                }
                composable(route = "page31") {
                    forgotPassword.forgotPassword(navController = navController)
                }
                composable(route = "page32") {
                    addUser.addUser(navController = navController)
                }
                composable(route = "page33") {
                    addUserPasswordInfo.addUserPasswordInfo(navController = navController)
                }
                composable(route = "page34") {
                    userIsAdded.userIsAdded(navController = navController)
                }
                composable(route = "page35") {
                    openInvestmentAccount.openInvestmentAccount(navController = navController)
                }
                composable(route = "page36") {
                    selectExchangeType.selectExchangeType(navController = navController)
                }
                composable(route = "page37") {
                    investmentAccountInformation.investmentAccountInformation(navController = navController)
                }
                composable(route = "page38") {
                    investmentAccountIsOpened.investmentAccountIsOpened(navController = navController)
                }
                composable(route = "page39") {
                    myInvestmentAccounts.myInvestmentAccounts(navController = navController)
                }
                composable(route = "page40") {
                    compatibilityTest.compatibilityTest(navController = navController)
                }
                composable(route = "page41") {
                    ageSelection.ageSelection(navController = navController)
                }
                composable(route = "page42") {
                    educationalBackgroundSelection.educationalBackgroundSelection(navController = navController)
                }
                composable(route = "page43") {
                    occupationSelection.occupationSelection(navController = navController)
                }
                composable(route = "page44") {
                    evaluationProcessSelection.evaluationProcessSelection(navController = navController)
                }
                composable(route = "page45") {
                    investmentExperienceSelection.investmentExperienceSelection(navController = navController)
                }
                composable(route = "page46") {
                    riskAndIncomePreferenceSelection.riskAndIncomePreferenceSelection(navController = navController)
                }
                composable(route = "page47") {
                    productInformationSelection.productInformationSelection(navController = navController)
                }
                composable(route = "page48") {
                    transactionFrequencySelection.transactionFrequencySelection(navController = navController)
                }
                composable(route = "page49") {
                    volumeInformationSelection.volumeInformationSelection(navController = navController)
                }
                composable(route = "page50") {
                    compatibilityTestRiskTypes.compatibilityTestRiskTypes(navController = navController)
                }
                composable(route = "page51") {
                    compatibilityTestResults.compatibilityTestResults(navController = navController)
                }
                composable(route = "page52") {
                    compatibilityTestIsDone.compatibilityTestIsDone(navController = navController)
                }
            }
        }
    }
}