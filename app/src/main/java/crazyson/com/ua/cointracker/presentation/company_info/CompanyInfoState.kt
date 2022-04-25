package crazyson.com.ua.cointracker.presentation.company_info

import crazyson.com.ua.cointracker.domain.model.CompanyInfo
import crazyson.com.ua.cointracker.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
