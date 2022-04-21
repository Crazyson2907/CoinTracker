package crazyson.com.ua.cointracker.presentation.company_listings

import crazyson.com.ua.cointracker.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
