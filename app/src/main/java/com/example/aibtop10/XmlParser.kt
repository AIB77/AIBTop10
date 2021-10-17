package com.example.aibtop10

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream

class XmlParser {
    private val thetopapp = ArrayList<TopApp>()
    private var text: String? = null

    private var Title = ""


    fun parse(inputStream: InputStream): List<TopApp> {
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name
                when (eventType) {
                    XmlPullParser.TEXT -> text = parser.text
                    XmlPullParser.END_TAG -> when {
                        tagName.equals("title", ignoreCase = true) -> {
                            Title = text.toString()
                        }

                        tagName.equals("entry", ignoreCase = true) -> {
                            thetopapp.add(TopApp(Title+"\n"))
                        }
                        else -> {}
                    }

                    else -> {
                    }
                }
                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return thetopapp
    }
}