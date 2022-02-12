package br.edu.ifsp.scl.sdm.pa2.lembretes.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.dao.LembreteDao
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.entity.Lembrete

@Database(entities = [Lembrete::class], version = 1)
abstract class LembreDb: RoomDatabase() {
    abstract fun lembreteDao(): LembreteDao
}