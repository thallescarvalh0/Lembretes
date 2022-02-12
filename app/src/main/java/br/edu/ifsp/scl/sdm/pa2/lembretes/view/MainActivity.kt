package br.edu.ifsp.scl.sdm.pa2.lembretes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.edu.ifsp.scl.sdm.pa2.lembretes.databinding.ActivityMainBinding
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.dao.LembreteDao
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.database.LembreDb
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.entity.Lembrete
import br.edu.ifsp.scl.sdm.pa2.lembretes.view.adapter.LembretesAdapter

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // RecyclerView
        val lembretesList = mutableListOf<String>()
        for (i in 1..100) {
            lembretesList.add("Lembrete $i")
        }
        val lembretesAdapter = LembretesAdapter(lembretesList)
        activityMainBinding.lembretesRv.adapter = lembretesAdapter
        activityMainBinding.lembretesRv.layoutManager = LinearLayoutManager(this)

        //AcessoBD
        val lembreteDb =
            Room.databaseBuilder(applicationContext, LembreDb::class.java, "lembrete").build()
        val lembreteDao = lembreteDb.lembreteDao()

        activityMainBinding.salvarBt.setOnClickListener{
            Thread {
                val texto = activityMainBinding.lembreteEt.text.toString()
                lembreteDao.insertLembrete(Lembrete(texto))
                runOnUiThread{ //Para utilizar thread que interage com o usuário
                    lembretesList.add(texto)
                    lembretesAdapter.notifyDataSetChanged()
                }
            }.start()
        }

        // Thread filha. Não é principal (concorrente)
        Thread {
            lembreteDao.getLembrete().forEach() { lembrete ->
                lembretesList.add(lembrete.texto)
            }
            lembretesAdapter.notifyDataSetChanged()
        }
    }
}