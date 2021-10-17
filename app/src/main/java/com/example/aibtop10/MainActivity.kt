package com.example.aibtop10

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var CMain:ConstraintLayout
    lateinit var RV:RecyclerView
    lateinit var thetopapp: MutableList<TopApp>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CMain=findViewById(R.id.constLay)
        RV=findViewById(R.id.RVmain)

        FetchQuestions().execute()


    }

    private inner class FetchQuestions : AsyncTask<Void, Void, MutableList<TopApp>>()
    {
        val parser = XmlParser()
        val progressD = ProgressDialog(this@MainActivity)

        override fun onPreExecute() {
            super.onPreExecute()
            progressD.setMessage("Please Wait")
            progressD.show()
        }

        override fun doInBackground(vararg params: Void?): MutableList<TopApp>
        {

            val url = URL("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
            val urlConnection = url.openConnection() as HttpURLConnection
            thetopapp =
                urlConnection.inputStream?.let { parser.parse(it) } as MutableList<TopApp>
            return thetopapp
        }

        override fun onPostExecute(result: MutableList<TopApp>?)
        {
            super.onPostExecute(result)
            progressD.dismiss()
            RV.adapter = myAdapter(result)
            RV.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}