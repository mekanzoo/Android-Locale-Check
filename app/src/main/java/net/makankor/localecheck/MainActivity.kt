package net.makankor.localecheck

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var textDefault: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        textDefault = findViewById(R.id.text_default) as TextView
        setupView()
    }

    fun setupView() {
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView!!.adapter = CustomAdapter()
    }

    inner class CustomAdapter : RecyclerView.Adapter<VH>() {
        val list = ArrayList<Locale>()

        init {
            textDefault!!.text = Locale.getDefault().toString()

            val local = Locale.getAvailableLocales();
            val otherList = ArrayList<Locale>()
            for (curLocale in local) {
                if (!curLocale.displayCountry.trim().isEmpty()) {
                    list.add(curLocale)
                } else {
                    otherList.add(curLocale)
                }
            }

            Collections.sort(list, { t, t2 ->
                t.displayCountry.compareTo(t2.displayCountry)
            })

            list.addAll(otherList)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VH? {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.table_content, parent, false)

            return VH(view)
        }

        override fun onBindViewHolder(holder: VH?, position: Int) {
            var curLocale = list[position]

            var displayCountry = curLocale.displayCountry
            if (displayCountry.trim().isEmpty()) {
                displayCountry = getText(R.string.other_country).toString()
            }

            holder!!.lblCountry!!.text = displayCountry
            holder!!.lblLanguage!!.text = curLocale.displayLanguage
            holder!!.lblLocaleCode!!.text = curLocale.toString()
        }

    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        var lblCountry: TextView? = null
        var lblLanguage: TextView? = null
        var lblLocaleCode: TextView? = null

        init {
            lblCountry = view.findViewById(R.id.lbl_country) as TextView
            lblLanguage = view.findViewById(R.id.lbl_language) as TextView
            lblLocaleCode = view.findViewById(R.id.lbl_locale_code) as TextView
        }
    }

}
