package com.mbs.motivation.auxiliaryclasses

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)
    /* instanciada a classe do tipo "SHAREDPREFERENCES" em modo privado
     usando o contexto(context do tipo Context) da Activity em questão(this). */

    fun storeName(key: String, value: String) {
        security.edit().putString(key, value).apply()
    }
    // armazena(metodo .edit()) as chaves e valores e aplica com apply().

    fun getName(key: String): String {
        return security.getString(key, "") ?: ""
    }
    /* obtém o valor através da chave passada como parametro em getString(key), sendo o segundo
    parametro o valor padrão e o operador elvis (?:) servindo para indicar o que acontecerá no
    caso de receber um valor NUll. Ou seja, caso seja Null, retornará uma string vazia ("") */
}