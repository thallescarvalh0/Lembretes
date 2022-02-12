package br.edu.ifsp.scl.sdm.pa2.lembretes.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lembrete(
    @PrimaryKey @ColumnInfo(name = "texto") val texto: String
)
