package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.handler.CVHandler;
import ovh.valulz.cvclient.util.BKEY;

public class FormCVCSSActivity extends Activity {

    private Bundle b;

    private EditText name;
    private NumberPicker level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_css);
        b = getIntent().getBundleExtra("bundle");

        name = (EditText) findViewById(R.id.name);
        level = (NumberPicker) findViewById(R.id.level);

        level.setMaxValue(20);
        level.setMinValue(0);
    }

    private void addValues() {
        int nb = b.getInt(BKEY.K_NUM_CSS);

        b.putString(BKEY.K_NAME_CSS + nb, name.getText() + "");
        b.putInt(BKEY.K_LEVEL_CSS + nb, level.getValue());
    }

    public void addNewCSS(View view) {
        addValues();
        b.putInt(BKEY.K_NUM_CSS, b.getInt(BKEY.K_NUM_CSS) + 1);

        Intent intent = new Intent(this, FormCVCSSActivity.class);
        intent.putExtra("bundle", b);
        startActivity(intent);
    }



    public void submitCV(View view) {
        addValues();
        SendCVTask task = new SendCVTask();
        task.execute(new String[]{"http://lw2-valulz.rhcloud.com/resume/"});
    }


    private class SendCVTask extends AsyncTask<String, Void, String> {

        private String constructCVFromBundle() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String body = "";

            body += "<cv>";
            body += "<name maidenName=\"" + b.getBoolean(BKEY.K_MAIDEN) + "\">" + b.getString(BKEY.K_NAME) + "</name>";
            body += "<firstName>" + b.getString(BKEY.K_FIRST_NAME) + "</firstName>";
            body += "<gender>" + b.getString(BKEY.K_GENDER) + "</gender>";
            body += "<objective>" + b.getString(BKEY.K_OBJECTIVE) + "</objective>";
            body += "<skill>" + b.getString(BKEY.K_SKILL) + "</skill>";

            int nbExp = b.getInt(BKEY.K_NUM_EXP);
            for (int i = 1; i <= nbExp; i++) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, b.getInt(BKEY.K_BEGIN_YEAR_EXP + i));
                cal.set(Calendar.MONTH, b.getInt(BKEY.K_BEGIN_MONTH_EXP + i));
                body += "<experiences begin=\"" + sdf.format(cal.getTime()) + "\" ";

                cal.set(Calendar.YEAR, b.getInt(BKEY.K_END_YEAR_EXP + i));
                cal.set(Calendar.MONTH, b.getInt(BKEY.K_END_MONTH_EXP + i));
                body += "end=\"" + sdf.format(cal.getTime()) + "\" >";

                body += b.getString(BKEY.K_VALUE_EXP + i) + "</experiences>";
            }

            int nbScho = b.getInt(BKEY.K_NUM_SCHO);
            for (int i = 1; i <= nbScho; i++) {
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, b.getInt(BKEY.K_BEGIN_YEAR_SCHO + i));
                cal.set(Calendar.MONTH, b.getInt(BKEY.K_BEGIN_MONTH_SCHO + i));
                body += "<schools begin=\"" + sdf.format(cal.getTime()) + "\" ";

                cal.set(Calendar.YEAR, b.getInt(BKEY.K_END_YEAR_SCHO + i));
                cal.set(Calendar.MONTH, b.getInt(BKEY.K_END_MONTH_SCHO + i));
                body += "end=\"" + sdf.format(cal.getTime()) + "\" >";

                body += b.getString(BKEY.K_VALUE_SCHO + i) + "</schools>";
            }

            int nbLang = b.getInt(BKEY.K_NUM_LANG);
            for (int i = 1; i <= nbLang; i++) {
                body += "<languages name=\"" + b.getString(BKEY.K_NAME_LANG)
                        + "\" level=\"" + b.getInt(BKEY.K_LEVEL_LANG)
                        + "\" description=\"" + b.getString(BKEY.K_DESC_LANG) + "\"/>";
            }

            int nbCSS = b.getInt(BKEY.K_NUM_CSS);
            for (int i = 1; i <= nbCSS; i++) {
                body += "<skills name=\"" + b.getString(BKEY.K_NAME_CSS+i)
                        + "\" level=\"" + b.getInt(BKEY.K_LEVEL_CSS+i) + "\"/>";
            }

            body += "</cv>";

            return body;
        }

        @Override
        protected String doInBackground(String... urls) {

            String body = constructCVFromBundle();
            URL url;
            HttpURLConnection connection = null;

            CVHandler cvHandler = new CVHandler();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = null;

            try {
                url = new URL(urls[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/xml");
                connection.setRequestProperty("Content-Length",
                        Integer.toString(body.getBytes().length));

                connection.setUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(
                        connection.getOutputStream ());
                wr.writeBytes(body);
                wr.flush();
                wr.close();

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = rd.readLine()) != null) {
                    response.append(line);
                }
                rd.close();


                saxParser = factory.newSAXParser();
                saxParser.parse(new InputSource(new StringReader(response.toString())), cvHandler);

                return cvHandler.getCV().getId();

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            } finally {
                if(connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response) {

            Intent intent = new Intent(FormCVCSSActivity.this, DisplayCVActivity.class);
            intent.putExtra("_id", response);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }


    }
}
