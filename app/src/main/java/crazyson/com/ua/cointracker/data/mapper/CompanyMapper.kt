package crazyson.com.ua.cointracker.data.mapper

import crazyson.com.ua.cointracker.data.local.CompanyListingEntity
import crazyson.com.ua.cointracker.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}