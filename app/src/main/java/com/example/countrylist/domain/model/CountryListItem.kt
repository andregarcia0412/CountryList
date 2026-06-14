package com.example.countrylist.domain.model

data class CountryListItem(
    val flag: FlagDetail,
    val names: NameDetail,
    val codes: CodesDetail?,
    val continents: List<String>,
    val capitals: List<Capital>?,
    val population: Long,
) {
    val flagUrl: String?
        get() = flag.url_png ?: codes?.alpha_2?.lowercase()?.let {
            "https://flags.restcountries.com/v5/w640/$it.png"
        }
}
