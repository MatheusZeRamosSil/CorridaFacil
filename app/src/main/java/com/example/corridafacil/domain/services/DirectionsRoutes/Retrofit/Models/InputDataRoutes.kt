package com.example.corridafacil.domain.services.DirectionsRoutes.Retrofit.Models

class InputDataRoutes {
     var origin:String? = null
     var destination:String? = null
     var rotasAlternativas: Boolean? = false
     var modeDriving:String? = null


    companion object Factory{
        fun create(): InputDataRoutes = InputDataRoutes()
    }

}



