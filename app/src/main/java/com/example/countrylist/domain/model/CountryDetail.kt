package com.example.countrylist.domain.model

data class CountryDetail(
    val flag: FlagDetail,
    val names: NameDetail,
    val codes: CodesDetail?,
    val capitals: List<Capital>,
    val continents: List<String>,
    val population: Long,
    val area: Area,
    val currencies: List<CurrencyDetail>,
    val languages: List<LanguageDetail>
) {
    val flagUrl: String?
        get() = flag.png ?: codes?.alpha_2?.lowercase()?.let {
            "https://flags.restcountries.com/v5/w640/$it.png"
        }
}
