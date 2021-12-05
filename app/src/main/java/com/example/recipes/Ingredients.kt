package com.example.recipes

import android.os.Parcelable
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ingredients(val nameIng: String = "",val imageIng: String = "", var uuidIng: String = ""): Parcelable {

    fun loadDatabase(firebaseData: DatabaseReference) {
        val savedIngredients: List<Ingredients> = mutableListOf(
            Ingredients("Eggs", "https://sc04.alicdn.com/kf/U57dfdec44179422ebae0521b4d1ca9914.jpg"),
            Ingredients("Carrot", "https://media.baamboozle.com/uploads/images/175927/1609638311_193004")
        )
        savedIngredients.forEach {
            val key = firebaseData.child("Ingredients").push().key
            it.uuidIng = key!!
            firebaseData.child("Ingredients").child(key).setValue(it)
        }
    }

    fun fillArray(){
        val arrayListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                snapshot.children.forEach {
                    val ingredients = it.getValue<Ingredients>(Ingredients::class.java)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        }
    }
}