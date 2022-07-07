package com.nactem.acronym.model

import com.google.gson.annotations.SerializedName

data class AcronymData (

    @SerializedName("sf"  ) var sf  : String?        = null,
    @SerializedName("lfs" ) var lfs : ArrayList<Lfs> = arrayListOf()

)