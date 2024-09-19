package com.sena.matsystemandroidview.Services

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sena.matsystemandroidview.Config.Urls
import com.sena.matsystemandroidview.Model.MPersona
import org.json.JSONObject
import java.util.Objects


class Login {
    companion object {
        val urlPersona= Urls.urlPersona
        val url = Urls.urlUsuario
        val urlLogin = Urls.urlLogin
        lateinit var context:Context

        fun init(requiredContext: Context){
            context = requiredContext
        }


        fun Login(username: String, password: String,returnfun:(access:Boolean) -> Unit) {
            try {
                val params = JSONObject()
                params.put("userName", username)
                params.put("password", password)
                val request = JsonObjectRequest(
                    Request.Method.POST,//metodo
                    urlLogin,//url
                    params,//parametros
                    { response ->//respuesta correcta
                        val user= response.getJSONObject("user")
                        val token= response.getString("token")
                        val userInfo = user.getJSONObject("user")
                        val userId = userInfo.getInt("id")
                        returnfun(true)
                        val UserSession: SharedPreferences = context.getSharedPreferences("SesionUsuario", MODE_PRIVATE)

                        val editor = UserSession.edit()
                        editor.putBoolean( "sesionIniciada",  true ) // O puede ser un token de autenticación
                        editor.putString( "usuarioToken",  token )
                        editor.putInt( "usuarioId",  userId )
                        editor.apply()
                        CargarUsuario(userId)

                        println(response)
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
        fun Cargar(context: Context) {
            try {
                val request = JsonObjectRequest(
                    Request.Method.GET,//metodo
                    "$url/all",//url
                    null,//parametros
                    { response ->//respuesta correcta

                        println(response)
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
        fun CargarUsuario(id:Int) {
            try {
                val request = JsonObjectRequest(
                    Request.Method.GET,//metodo
                    "$url/$id",//url
                    null,//parametros
                    { response ->//respuesta correcta
                        val data = response.getJSONObject("data")
                        val personaId = data.getInt("personaId")
                        CargarPersona(personaId)

                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
        fun CargarPersona(id:Int) {
            try {
                val request = JsonObjectRequest(
                    Request.Method.GET,//metodo
                    "$urlPersona/$id",//url
                    null,//parametros
                    { response ->//respuesta correcta
                        val data = response.getJSONObject("data")
                        val primerNombre = data.getString("primerNombre")
                        val segundoNombre = data.getString("segundoNombre")
                        val fechaNacimiento = data.getString("fechaNacimiento")
                        val primerApellido = data.getString("primerApellido")
                        val segundoApellido = data.getString("segundoApellido")
                        val correoElectronico = data.getString("correoElectronico")
                        val telefono = data.getInt("telefono")
                        val tipoDocumento = data.getString("tipoDocumento")
                        val numeroDocumento = data.getInt("numeroDocumento")
                        val genero = data.getString("genero")
                        val direccion = data.getString("direccion")
                        val municipioId = data.getInt("municipioId")

                        val UserSession: SharedPreferences = context.getSharedPreferences("SesionUsuario", MODE_PRIVATE)
                        val editor = UserSession.edit()
                        editor.putInt( "personaID",  id )
                        editor.putString( "personaprimerNombre",  primerNombre )
                        editor.putString( "personasegundoNombre",  segundoNombre )
                        editor.putString( "personafechaNacimiento",  fechaNacimiento )
                        editor.putString( "personaprimerApellido",  primerApellido )
                        editor.putString( "personasegundoApellido",  segundoApellido )
                        editor.putString( "personacorreoElectronico",  correoElectronico )
                        editor.putInt( "personatelefono",  telefono )
                        editor.putString( "personatipoDocumento",  tipoDocumento )
                        editor.putInt( "personanumeroDocumento",  numeroDocumento )
                        editor.putString( "personagenero",  genero )
                        editor.putString( "personadireccion",  direccion )
                        editor.putInt( "municipioId",  municipioId )
                        editor.apply()
                        println(response)
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
        fun GuardarUsuario(id:Int,username: String, password: String) {
            try {

                val params = JSONObject()
                params.put("userName", username)
                params.put("password", password)
                params.put("personaId", id)


                val request = JsonObjectRequest(
                    Request.Method.POST,//metodo
                    "$url",//url
                    params,//parametros
                    { response ->//respuesta correcta

                        println("guadado ok")
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
        fun GuardarPersona(Person:MPersona, password: String) {
            try {
                val params = JSONObject()
                params.put("primerNombre", Person.primerNombre)
                params.put("segundoNombre", Person.segundoNombre)
                params.put("fechaNacimiento", Person.fechaNacimiento)
                params.put("primerApellido", Person.primerApellido)
                params.put("segundoApellido", Person.segundoApellido)
                params.put("correoElectronico", Person.correoElectronico)
                params.put("telefono", Person.telefono)
                params.put("tipoDocumento", Person.tipoDocumento)
                params.put("numeroDocumento", Person.numeroDocumento)
                params.put("genero", Person.genero)
                params.put("direccion", Person.direccion)
                params.put("municipioId", Person.municipioId)

                val request = JsonObjectRequest(
                    Request.Method.POST,//metodo
                    "$urlPersona",//url
                    params,//parametros
                    { response ->//respuesta correcta
                        val data = response.getJSONObject("data")
                        GuardarUsuario(data.getInt("id"),Person.correoElectronico,password)
                        println("guadado ok")
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
        fun ActualizarPersona(Person:MPersona, id:Int) {
            try {
                val params = JSONObject()
                params.put("id", id)
                params.put("primerNombre", Person.primerNombre)
                params.put("segundoNombre", Person.segundoNombre)
                params.put("fechaNacimiento", Person.fechaNacimiento)
                params.put("primerApellido", Person.primerApellido)
                params.put("segundoApellido", Person.segundoApellido)
                params.put("correoElectronico", Person.correoElectronico)
                params.put("telefono", Person.telefono)
                params.put("tipoDocumento", Person.tipoDocumento)
                params.put("numeroDocumento", Person.numeroDocumento)
                params.put("genero", Person.genero)
                params.put("direccion", Person.direccion)
                params.put("municipioId", Person.municipioId)
                println(params)
                val request = JsonObjectRequest(
                    Request.Method.PUT,//metodo
                    "$urlPersona",//url
                    params,//parametros
                    { response ->//respuesta correcta

                        println("guadado ok")
                    },
                    { error ->//respuesta es incorrecta o no es la respuesta esperada
                        Toast.makeText(context, "Se genero un error", Toast.LENGTH_SHORT).show()
                    })
                val queue = Volley.newRequestQueue(context)
                queue.add(request)
            } catch (e: Exception) {
                Toast.makeText(context, "Error al cargar datos", Toast.LENGTH_SHORT).show()
                println(e)
            }
        }
    }
}