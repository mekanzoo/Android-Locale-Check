package net.makankor.localecheck

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        setupView()
    }

    fun setupView() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = CustomAdapter()
    }

    class CustomAdapter : RecyclerView.Adapter<VH>() {
        val list = ArrayList<Locale>()

       init {
           list.add(Locale.getDefault())
           val local = Locale.getAvailableLocales();
           for (curLocale in local) {
                list.add(curLocale)
           }
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH? {
            val textView = LayoutInflater.from(parent!!.context).inflate(R.layout.text_view, parent, false) as TextView

            return VH(view = textView)
        }

        override fun onBindViewHolder(holder: VH?, position: Int) {
            var curLocale = list.get(position)

            holder!!.view.text = curLocale.displayCountry + "." + curLocale.language
        }

    }

    class VH(view: TextView) : RecyclerView.ViewHolder(view) {
        val view = view
    }

}
