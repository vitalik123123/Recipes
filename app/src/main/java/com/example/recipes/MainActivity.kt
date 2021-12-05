package com.example.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ingredients_row.*
import kotlinx.android.synthetic.main.ingredients_row.view.*

class MainActivity : AppCompatActivity() {


    companion object {
        var currentChoiceIng: Ingredients? = null
        var arrayChoiceIng: ArrayList<String> = arrayListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val switch_choice_ing_ingredients_row = findViewById<Switch>(R.id.switch_choice_ing_ingredients_row)
//        var checkbox_choice_ing_ingredients_row = findViewById<CheckBox>(R.id.checkbox_choice_ing_ingredients_row)

//        val adapter =GroupAdapter<GroupieViewHolder>()
//
//        recyclerview_main.adapter = adapter
//        adapter.add(IngredientsRow())
//        adapter.add(IngredientsRow())

//        checkbox_choice_ing_ingredients_row.isChecked = false
//        checkbox_choice_ing_ingredients_row.setOnCheckedChangeListener { buttonView, isChecked ->
//
//        }

        showAllIng()
        val ref = FirebaseDatabase.getInstance("https://recipes-12872-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference()

//        fillArray(ref)
//        if (checkbox_choice_ing_ingredients_row != null) {
//            onCheckboxClicked(checkbox_choice_ing_ingredients_row)
//        }

//        if (switch_choice_ing_ingredients_row != null)
//            onSwitchClicked(switch_choice_ing_ingredients_row)


//        if (switch_choice_ing_ingredients_row != null) {
//            if (switch_choice_ing_ingredients_row.isChecked) {
//                arrayChoiceIng.add(textview_name_ing_ingredients_row.text as String)
//                Log.d("MainActivity", "Current array: $arrayChoiceIng")
//            } else {
//                arrayChoiceIng.remove(textview_name_ing_ingredients_row.text)
//                Log.d("MainActivity", "Current array: $arrayChoiceIng")
//            }
//        }

    }

    private fun showAllIng() {
        val ref = FirebaseDatabase.getInstance("https://recipes-12872-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("/Ingredients")
//        var checkbox_choice_ing_ingredients_row = findViewById<CheckBox>(R.id.checkbox_choice_ing_ingredients_row)

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val adapter = GroupAdapter<GroupieViewHolder>()

                snapshot.children.forEach {
                    Log.d("MainActivity", "Add Ing")
                    currentChoiceIng = it.getValue(Ingredients::class.java)
                    if (currentChoiceIng != null) {
                        adapter.add(IngredientsRow(currentChoiceIng!!))

                    }
                }

//                adapter.setOnItemClickListener { item, view ->
//
//                    if (switch_choice_ing_ingredients_row.isChecked){
//                        arrayChoiceIng.add(textview_name_ing_ingredients_row.text.toString())
//                        Log.d("FillArray", "Current array: $arrayChoiceIng")
//                    }else{
//                        arrayChoiceIng.remove(textview_name_ing_ingredients_row.text.toString())
//                        Log.d("FillArray", "Current array: $arrayChoiceIng")
//                    }
//                }


                recyclerview_main.adapter = adapter

            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }



    fun fillArray() {

        val arrayListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val ingredient = snapshot.getValue(Ingredients::class.java)


//                if (switch_choice_ing_ingredients_row.isChecked) {
//                    arrayChoiceIng.add(ingredient?.nameIng!!)
//                    Log.d("AddArray", "Current array: $arrayChoiceIng")
//                } else {
//                    arrayChoiceIng.remove(ingredient?.nameIng!!)
//                    Log.d("AddArray", "Current array: $arrayChoiceIng")
//                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

//        firebaseData.child("Ingredients").addListenerForSingleValueEvent(arrayListener)
    }




    fun onSwitchClicked(view: View){
        val textview = textview_name_ing_ingredients_row.text as String

        val ref = FirebaseDatabase.getInstance("https://recipes-12872-default-rtdb.europe-west1.firebasedatabase.app/")
            .getReference("/Ingredients")

        ref.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val ingredient = snapshot.getValue(Ingredients::class.java) ?: return

                if (view is Switch){
                    val checked: Boolean = view.isChecked

                    when (view.id){
                        R.id.switch_choice_ing_ingredients_row -> {
                            if (checked) {
                                arrayChoiceIng.add(ingredient.nameIng)
                                Log.d("AddArray", "Current array: $arrayChoiceIng")
                            } else {
                                arrayChoiceIng.remove(ingredient.nameIng)
                                Log.d("AddArray", "Current array: $arrayChoiceIng")
                            }
                        }
                    }
                }
//                if (switch_choice_ing_ingredients_row != null) {
//                    if (switch_choice_ing_ingredients_row.isChecked) {
//                        arrayChoiceIng.add(ingredient.nameIng)
//                        Log.d("AddArray", "Current array: $arrayChoiceIng")
//                    } else {
//                        arrayChoiceIng.remove(ingredient.nameIng)
//                        Log.d("AddArray", "Current array: $arrayChoiceIng")
//                    }
//                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

//    fun onCheckboxClicked(view: View){
////        var ingredient: Ingredients?
//        var checkbox_choice_ing_ingredients_row = findViewById<CheckBox>(R.id.checkbox_choice_ing_ingredients_row)
//        var ref = FirebaseDatabase.getInstance("https://recipes-12872-default-rtdb.europe-west1.firebasedatabase.app/")
//            .getReference("/Ingredients")
//
//
//        ref.addChildEventListener(object : ChildEventListener{
//            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
//                val ingredient = snapshot.getValue(Ingredients::class.java) ?: return
//
//                if (view is CheckBox){
//                    val checked: Boolean = view.isChecked
//
//
//                    when (view.id){
//                        R.id.checkbox_choice_ing_ingredients_row -> {
//                            if (checked){
//                                if (checkbox_choice_ing_ingredients_row.text == ingredient.nameIng) {
//                                    arrayChoiceIng.add(ingredient?.nameIng!!)
//                                    Log.d("MainActivity", "Current array: $arrayChoiceIng")
//                                }
//                            }else{
//                                if (checkbox_choice_ing_ingredients_row.text == ingredient.nameIng) {
//                                    arrayChoiceIng.remove(ingredient?.nameIng)
//                                    Log.d("MainActivity", "Current array: $arrayChoiceIng")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildRemoved(snapshot: DataSnapshot) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//
//
//
////        if (view is CheckBox){
////            val checked: Boolean = view.isChecked
////
////
////            when (view.id){
////                R.id.checkbox_choice_ing_ingredients_row -> {
////                    if (checked){
////                        arrayChoiceIng?.add(checkbox_choice_ing_ingredients_row.text as String)
////                        Log.d("MainActivity","Current array: $arrayChoiceIng")
////                    }else{
////                        arrayChoiceIng?.remove(checkbox_choice_ing_ingredients_row.text)
////                        Log.d("MainActivity","Current array: $arrayChoiceIng")
////                    }
////                }
////            }
////        }
//    }



}

class IngredientsRow(var ingredient:Ingredients): Item<GroupieViewHolder>(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.textview_name_ing_ingredients_row.text = ingredient.nameIng

        Picasso.get().load(ingredient.imageIng).into(viewHolder.itemView.imageview_image_ing_ingredients_row)



    }

    override fun getLayout(): Int {
        return R.layout.ingredients_row
    }

}

