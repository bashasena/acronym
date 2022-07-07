package com.nactem.acronym.repository

import com.nactem.acronym.network.RetrofitHelper

class AcronymApiRepository {

     fun getAbbriveiation(strword: String) = RetrofitHelper.api!!.getAcronym(word=strword)
}