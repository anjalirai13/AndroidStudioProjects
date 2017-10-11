package org.example.com.top10downloader;

//import android.app.Application;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class ParseXmlData {
    private String xmlData;
    private ArrayList<Application> applications;
    public ParseXmlData(String data){
        Log.d("parseApplication","constructor"+data);
        this.xmlData = data;
        applications = new ArrayList<Application>();
    }

    public ArrayList<Application> getApplications(){
        return applications;
    }
    public boolean process(){
        Log.d("ParseDebug","insideProcess");
        String textValue = "";
        Application currentRecord = null;
        Boolean inEntry = false;
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(xmlData));

            int eventType = xpp.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String name = xpp.getName();
                Log.d("ParseDebug","eventType"+eventType);
                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        if(name.equalsIgnoreCase("entry")){
                            inEntry = true;
                            Log.d("ParseDebug","tagName"+name);
                            currentRecord = new Application();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry) {
                            if (name.equalsIgnoreCase("name")) {
                                Log.d("ParseDebug", "Name");
                                currentRecord.setTitle(textValue);
                            } else if (name.equalsIgnoreCase("artist")) {
                                Log.d("ParseDebug", "Artist");
                                currentRecord.setAuthor(textValue);
                            } else if (name.equalsIgnoreCase("releaseDate")) {
                                Log.d("ParseDebug", "ReleaseDate");
                                currentRecord.setReleaseDate(textValue);
                            } else if (name.equalsIgnoreCase("entry")) {
                                Log.d("ParseDebug", "Entry");
                                applications.add(currentRecord);
                                inEntry = false;
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }catch(Exception e) {
            Log.d("parse xml", "Exception ~ " + e.getMessage());
        }

        for (Application app: applications) {
            Log.d("ParseApplication", "Title" + app.getTitle());
            Log.d("ParseApplication", "Artist" + app.getAuthor());
            Log.d("ParseApplication", "Release Date" + app.getReleaseDate());
        }
        return true;
    }
}
