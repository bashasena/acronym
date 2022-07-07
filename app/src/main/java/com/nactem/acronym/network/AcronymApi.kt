package com.nactem.acronym.network

import com.nactem.acronym.model.AcronymData
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymApi {

    @GET("/software/acromine/dictionary.py")
     fun getAcronym(@Query("sf") word: String): Observable<List<AcronymData>>

}