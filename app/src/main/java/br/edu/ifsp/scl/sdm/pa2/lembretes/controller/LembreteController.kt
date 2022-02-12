package br.edu.ifsp.scl.sdm.pa2.lembretes.controller

import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.database.LembreDb
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.entity.Lembrete
import br.edu.ifsp.scl.sdm.pa2.lembretes.view.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class LembreteController(mainActivity: MainActivity) {

    val lembreteDb =
        Room.databaseBuilder(mainActivity.applicationContext, LembreDb::class.java, "lembrete").build()
    val lembreteDao = lembreteDb.lembreteDao()

    fun buscarLembretes(): MutableLiveData<MutableList<String>>{
        // Thread filha. Não é principal (concorrente)
        val lembretesMld = MutableLiveData<MutableList<String>>()
//        Thread {
//            val lembretesList = mutableListOf<String>()
//            lembreteDao.getLembrete().forEach() { lembrete ->
//                lembretesList.add(lembrete.texto)
//            }
//            lembretesMld.postValue(lembretesList)
//        }.start()
        GlobalScope.launch(Dispatchers.IO) {
            val lembretesList = mutableListOf<String>()
            lembreteDao.getLembrete().forEach() { lembrete ->
                lembretesList.add(lembrete.texto)
            }
            lembretesMld.postValue(lembretesList)
        }
        return lembretesMld
    }

    fun inserirLembrete(texto: String): MutableLiveData<Long>{
        val idLinhaMld = MutableLiveData<Long>()

//        Thread {
//            idLinhaMld.postValue(lembreteDao.insertLembrete(Lembrete(texto)))
//        }.start()

        GlobalScope.launch(Dispatchers.IO) {
            idLinhaMld.postValue(lembreteDao.insertLembrete(Lembrete(texto)))
        }

        return idLinhaMld
    }
}