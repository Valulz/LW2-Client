package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.zip.Inflater;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.adapter.ShortCVAdapter;
import ovh.valulz.cvclient.handler.ShortCVHandler;
import ovh.valulz.cvclient.model.ShortCV;

public class DisplayCVsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cvs);

        ParseCVTask task = new ParseCVTask();
        task.execute(new String[]{"http://lw2-valulz.rhcloud.com/resume"});
    }

    private class ParseCVTask extends AsyncTask<String, Void, List<ShortCV>>{

        @Override
        protected List<ShortCV> doInBackground(String... urls) {

            ShortCVHandler cvHandler = new ShortCVHandler();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = null;

            for(String url : urls){
                try {
                    saxParser = factory.newSAXParser();
                    URL urlCo = new URL(url);
                    saxParser.parse(new InputSource(urlCo.openStream()), cvHandler);
                } catch (ParserConfigurationException | SAXException | IOException e) {
                    e.printStackTrace();
                }
            }

            return cvHandler.getCvs();
        }

        @Override
        protected void onPostExecute(List<ShortCV> cvs) {
            ListView listCV = (ListView) findViewById(R.id.cvs);
            listCV.setAdapter(new ShortCVAdapter(DisplayCVsActivity.this, cvs));

            LayoutInflater inflater = DisplayCVsActivity.this.getLayoutInflater();
            View v = inflater.inflate(R.layout.list_short_cv_header, null, false);
            listCV.addHeaderView(v);
        }
    }

}
