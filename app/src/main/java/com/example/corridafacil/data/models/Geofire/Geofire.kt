package com.example.corridafacil.data.models.Geofire

import android.annotation.SuppressLint
import android.util.Log
import com.firebase.geofire.*
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase


class GeofireInFirebase() {
    var referenceDataBaseFirebase = FirebaseDatabase.getInstance().getReference("Location/Passageiros")
    var geoFire = GeoFire(referenceDataBaseFirebase)

    fun saveDataLocationInFirebaseDataBase(key:String?,location: GeoLocation){
        geoFire.setLocation(key, location, object : GeoFire.CompletionListener{
            override fun onComplete(key: String?, error: DatabaseError?) {
                if (error != null){
                    Log.i("OK","")
                } else{
                    Log.i("Error","")
                }
            }
        })
    }

    @SuppressLint("LongLogTag")
    fun updateDataLocationInDataBase(uidAuth: String, dataUpdated: Map<String,GeoLocation>){
        geoFire.databaseReference.child("Location/Passageiros").child(uidAuth).updateChildren(dataUpdated)
            .addOnCompleteListener {
                Log.i("OK in update data of location device ", "${it.isSuccessful}")
            }
            .addOnFailureListener {
                Log.i("Error in update data of location device", "${it.message}")
            }
    }

    fun removeLocationDevice(key: String?){
        geoFire.removeLocation(key)
    }

    // raioDeBusca distancia em KM
    fun buscandoDispositivosProximos(myLocation:LatLng,raioDeBusca: Double, geofireImp: GeoFireImp){
        val referenceDriversDatase = FirebaseDatabase.getInstance().getReference("Location/Drivers")
        val geoFire = GeoFire(referenceDriversDatase)
        val geoQuery =  geoFire.queryAtLocation(GeoLocation(myLocation.latitude,myLocation.longitude),raioDeBusca)
        val devicesLocations = ArrayList<GeoLocation>()
        geoQuery.addGeoQueryEventListener( object : GeoQueryEventListener{
            override fun onKeyEntered(key: String?, location: GeoLocation?) {
                Log.i("Entered verify",key.toString())
                devicesLocations.add(location!!)
                geofireImp.succesOnLocationResul(key!!,location)
            }

            override fun onKeyExited(key: String?) {
                Log.i("Exited verify",key.toString())
                geofireImp.getKeyExited(key!!)
            }

            override fun onKeyMoved(key: String?, location: GeoLocation?) {
                Log.i("Moved verify", key+" "+location!!.latitude.toString()+" "+location.longitude.toString())
                geofireImp.getKeyMoved(key,location)
            }

            override fun onGeoQueryReady() {
                Log.i("Read verify ","All initial data has been loaded and events have been fired!")
            }

            override fun onGeoQueryError(error: DatabaseError?) {
                Log.i("Error verify","There was an error with this query: " + error)
                geofireImp.onFailure(error.toString())

            }
        })
    }
}
