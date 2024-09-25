package emir.mut.GoldenGlobalMobile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@OptIn(FlowPreview::class)
class MainViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _navigationMainItems = MutableStateFlow(navigationDrawer.navigationMainItems)
    val navMainItems = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_navigationMainItems) { text, navMainItems ->
            if(text.isBlank()) {
                navMainItems
            } else {
                delay(2000L)
                navMainItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _navigationMainItems.value
        )

    private val _navigationAccountsSubItems = MutableStateFlow(navigationDrawer.navigationAccountsSubItems)
    val navAccountsSubItems = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_navigationAccountsSubItems) { text, navAccountsSubItems ->
            if(text.isBlank()) {
                navAccountsSubItems
            } else {
                delay(2000L)
                navAccountsSubItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _navigationAccountsSubItems.value
        )

    private val _navigationCardsSubItems = MutableStateFlow(navigationDrawer.navigationCardsSubItems)
    val navCardsSubItems = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_navigationCardsSubItems) { text, navCardsSubItems ->
            if(text.isBlank()) {
                navCardsSubItems
            } else {
                delay(2000L)
                navCardsSubItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _navigationCardsSubItems.value
        )

    private val _navigationMoneyTransfersSubItems = MutableStateFlow(navigationDrawer.navigationMoneyTransfersSubItems)
    val navMoneyTransfersSubItems = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_navigationMoneyTransfersSubItems) { text, navMoneyTransfersSubItems ->
            if(text.isBlank()) {
                navMoneyTransfersSubItems
            } else {
                delay(2000L)
                navMoneyTransfersSubItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _navigationMoneyTransfersSubItems.value
        )

    private val _navigationRequestPaymentSubItems = MutableStateFlow(navigationDrawer.navigationRequestPaymentSubItems)
    val navRequestPaymentSubItems = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_navigationRequestPaymentSubItems) { text, navRequestPaymentSubItems ->
            if(text.isBlank()) {
                navRequestPaymentSubItems
            } else {
                delay(2000L)
                navRequestPaymentSubItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _navigationRequestPaymentSubItems.value
        )

    private val _navigationInvestmentSubItems = MutableStateFlow(navigationDrawer.navigationInvestmentSubItems)
    val navInvestmentSubItems = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(_navigationInvestmentSubItems) { text, navInvestmentSubItems ->
            if(text.isBlank()) {
                navInvestmentSubItems
            } else {
                delay(2000L)
                navInvestmentSubItems.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _navigationInvestmentSubItems.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}