package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.adapter.ExperienceAdapter;
import ovh.valulz.cvclient.adapter.LangAdapter;
import ovh.valulz.cvclient.adapter.SchoolAdapter;
import ovh.valulz.cvclient.adapter.SkillsAdapter;
import ovh.valulz.cvclient.handler.CVHandler;
import ovh.valulz.cvclient.model.CV;

public class DisplayCVActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_cv);
        String id = getIntent().getStringExtra("_id");

        ParseCVTask task = new ParseCVTask();
        task.execute(new String[]{"http://lw2-valulz.rhcloud.com/resume/"+id});

    }

    private class ParseCVTask extends AsyncTask<String, Void, CV> {

        @Override
        protected CV doInBackground(String... urls) {

            CVHandler cvHandler = new CVHandler();
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

            return cvHandler.getCV();
        }

        @Override
        protected void onPostExecute(CV cv) {

            ((TextView)findViewById(R.id.gender)).setText(""+(cv.getGender() == null ? "" : cv.getGender()));
            ((TextView)findViewById(R.id.maiden)).setText(cv.getName().isMaidenName() ? "nee " : "");
            ((TextView)findViewById(R.id.name)).setText(cv.getName().getName());
            ((TextView)findViewById(R.id.first_name)).setText(cv.getFirstName());
            ((ListView)findViewById(R.id.exps)).setAdapter(new ExperienceAdapter(DisplayCVActivity.this, cv.getExps()));
            ((ListView)findViewById(R.id.schools)).setAdapter(new SchoolAdapter(DisplayCVActivity.this, cv.getSchools()));
            ((ListView)findViewById(R.id.langs)).setAdapter(new LangAdapter(DisplayCVActivity.this, cv.getLangs()));
            ((ListView)findViewById(R.id.css)).setAdapter(new SkillsAdapter(DisplayCVActivity.this, cv.getSkills()));

        }
    }

}
