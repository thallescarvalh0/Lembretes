package br.edu.ifsp.scl.sdm.pa2.lembretes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import br.edu.ifsp.scl.sdm.pa2.lembretes.controller.LembreteController
import br.edu.ifsp.scl.sdm.pa2.lembretes.databinding.ActivityMainBinding
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.dao.LembreteDao
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.database.LembreDb
import br.edu.ifsp.scl.sdm.pa2.lembretes.model.entity.Lembrete
import br.edu.ifsp.scl.sdm.pa2.lembretes.view.adapter.LembretesAdapter

class MainActivity : AppCompatActivity() {

    private val activityMainBinding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val lembreteController: LembreteController by lazy {
        LembreteController(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)

        // RecyclerView
        val lembretesList = mutableListOf<String>()

        val lembretesAdapter = LembretesAdapter(lembretesList)
        activityMainBinding.lembretesRv.adapter = lembretesAdapter
        activityMainBinding.lembretesRv.layoutManager = LinearLayoutManager(this)

        lembreteController.buscarLembretes().observe(this, Observer{
            lembretesList.clear()
            lembretesList.addAll(it)
            lembretesAdapter.notifyDataSetChanged()
        })

        activityMainBinding.salvarBt.setOnClickListener{
            val texto = activityMainBinding.lembreteEt.text.toString()
            lembreteController.inserirLembrete(texto).observe(this, Observer {
                Toast.makeText(this, "Lembrete $it foi inserido.", Toast.LENGTH_LONG).show()
                activityMainBinding.lembreteEt.setText("")
                lembretesList.add(texto)
                lembretesAdapter.notifyDataSetChanged()
            })
        }


    }
}