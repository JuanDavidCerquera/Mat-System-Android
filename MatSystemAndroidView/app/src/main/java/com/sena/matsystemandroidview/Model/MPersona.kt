package com.sena.matsystemandroidview.Model

import java.time.LocalDate

data class MPersona (
    val primerNombre: String,
    val segundoNombre: String? = null,
    val fechaNacimiento:String,
    val primerApellido: String,
    val segundoApellido: String? = null,
    val correoElectronico: String,
    val telefono: Int,
    val tipoDocumento: String,
    val numeroDocumento: Int,
    val genero: String,
    val direccion: String,
    val municipioId:Int
)