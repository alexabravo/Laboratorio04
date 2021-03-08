 package com.example.pokemonapp

 import androidx.appcompat.app.AppCompatActivity
 import android.os.Bundle
 import android.util.Log
 import android.widget.Button
 import android.widget.EditText
 import android.widget.TextView
 import android.widget.Toast
 import com.example.pokemonapp.Retrofit2.Retrofit2
 import com.example.pokemonapp.databinding.ActivityMainBinding
 import com.google.gson.JsonObject
 import retrofit2.Call
 import retrofit2.Callback
 import retrofit2.Response

 class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)

         //variables del main activity
         val pokemonFind: EditText = findViewById(R.id.busqueda)
         val buscar: Button = findViewById(R.id.pokeBusqueda)
         val informacion: TextView = findViewById(R.id.pokeInfo)

         //Variables para las restricciones
         val numeros = "0123456789"
         val vacio = ""



         buscar.setOnClickListener {
             run {

                 val retrofit2 = Retrofit2()

                 val buscarpokemon = pokemonFind.text.toString().toLowerCase()

                 //Restricciones

                 //+50 caracteres
                 if (buscarpokemon.length > 50) {
                     val toast = Toast.makeText(applicationContext, "No se puede exeder de 50 caracteres", Toast.LENGTH_SHORT)
                     toast.show()
                     informacion.text = ""
                 }
                 //No numeros
                 if (pokemonFind!!.text.any { it in numeros }) {
                     val toast = Toast.makeText(
                         applicationContext,
                         "No puede ingresar numeros",
                         Toast.LENGTH_SHORT
                     )
                     toast.show()
                     informacion.text = ""
                 }

                 //No espacios
                 if(pokemonFind!!.text.any {it in vacio}) {
                     val toast = Toast.makeText(applicationContext, "No puede contener espacios", Toast.LENGTH_SHORT)
                     toast.show()
                     informacion.text = ""
                 }

                 val call: Call<JsonObject> = retrofit2.getService().getPokemonById(buscarpokemon)

                 call.enqueue(object : Callback<JsonObject> {
                     override fun onResponse(
                         call: Call<JsonObject>,
                         response: Response<JsonObject>
                     ) {
                         if (response.isSuccessful) {
                             val pokemon: JsonObject? = response.body()

                             assert(pokemon != null)

                             var PokeDatos = ("\n Nombre del Pokemon: ${pokemon!!["name"].asString.capitalize()}"
                                     + "\n Experiencia Base: ${pokemon!!["base_experience"]}"
                                     + "\n Altura: ${pokemon!!["height"]}"
                                     + "\n Peso: ${pokemon!!["weight"]}")
                             informacion.text = PokeDatos
                             Log.i("Detalles", pokemon.toString())
                         } else {
                             0
                             Log.e("Error", "Error!")
                         }

                     }

                     override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                         Log.e("error", t.toString())
                     }

                 })

             }
         }

     }

 }






