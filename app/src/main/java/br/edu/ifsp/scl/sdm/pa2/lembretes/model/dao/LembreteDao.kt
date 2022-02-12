package br.edu.ifsp.scl.sdm.pa2.lembretes.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.entity.Lembrete

@Dao
interface LembreteDao {
    @Query("SELECT * FROM Lembrete")
    fun getLembrete(): MutableList<Lembrete>

    @Insert
    fun insertLembrete(lembrete: Lembrete): Long
}