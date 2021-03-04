package com.example.pokemonapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        val texto: EditText = findViewById(R.id.pokemon)
        val busqueda: Button = findViewById(R.id.buscar)
        val info: TextView = findViewById(R.id.PokeInfo)

        val opciones = "0123456789"
        val espacio = " "

        busqueda.setOnClickListener {
            run {

                val retrofit2 = Retrofit2()

                val find = texto.text.toString().toLowerCase()

                if(texto!!.text.any {it in opciones}) {
                    val toast = Toast.makeText(applicationContext, "No puede ingresar numeros", Toast.LENGTH_SHORT)
                    toast.show()
                    info.text = ""
                }
                if (find.length > 50) {
                    val toast = Toast.makeText(applicationContext, "No pueden ser mas 50 caracteres", Toast.LENGTH_SHORT)
                    toast.show()
                    info.text = ""
                }
                if (texto!!.text.any {it in espacio}) {
                    val toast = Toast.makeText(applicationContext, "No puede tener espacios en blanco", Toast.LENGTH_SHORT)
                    toast.show()
                    info.text = ""
                }

                val call: Call<JsonObject> = retrofit2.getService().getPokemonById(texto)

                call.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        if (response.isSuccessful){
                            val poke: JsonObject? = response.body()
                            val name = poke!!["name"].asString.capitalize()

                            assert(poke != null)

                            var datos = ("\n Nombre: " + name
                                    + "\n Altura: ${poke!!["height"]}"
                                    + "\n Peso: ${poke!!["weight"]}"
                                    + "\n Experiencia Inicial: ${poke!!["base_experience"]}")
                            info.text = datos
                            Log.i("detalle", poke.toString())
                        } else{0
                            Log.e("error", "Hubo un error!")
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