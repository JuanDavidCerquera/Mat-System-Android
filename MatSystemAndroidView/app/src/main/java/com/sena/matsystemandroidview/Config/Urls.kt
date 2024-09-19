package com.sena.matsystemandroidview.Config

class Urls {
    companion object {
        val baseUrl: String = "http://192.168.137.1:9193/api/"

        val urlMultimedia: String = baseUrl + "Multimedia"
        val urlCursos: String = baseUrl + "Programa"
        val urlArchivo: String = baseUrl + "Archivo"
        val urlInscripcion: String = baseUrl + "Inscripcion"

        val urlArchivoUsuario: String = baseUrl + "ArchivoUsuario"

        val urlMunicipio: String = baseUrl + "Municipio"

        val urlUsuario: String = baseUrl + "Usuario"
        val urlPersona: String = baseUrl + "Persona"

        val urlLogin: String = urlUsuario + "/LogIn"
    }
}