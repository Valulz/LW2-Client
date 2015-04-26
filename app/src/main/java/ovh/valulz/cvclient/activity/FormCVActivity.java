package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.adapter.ExperienceAdapter;
import ovh.valulz.cvclient.adapter.ShortCVAdapter;
import ovh.valulz.cvclient.handler.ShortCVHandler;
import ovh.valulz.cvclient.model.Experience;
import ovh.valulz.cvclient.model.ShortCV;
import ovh.valulz.cvclient.util.BKEY;

public class FormCVActivity extends Activity {

    private Spinner spi_gender;
    private EditText txt_name;
    private CheckBox radio_maiden;
    private EditText txt_first_name;
    private EditText txt_objective;
    private EditText txt_skill;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_form_cv);

        spi_gender = (Spinner)findViewById(R.id.spi_gender);
        txt_name = (EditText)findViewById(R.id.txt_name);
        radio_maiden = (CheckBox)findViewById(R.id.radio_maiden);
        txt_first_name = (EditText)findViewById(R.id.txt_first_name);
        txt_objective = (EditText)findViewById(R.id.txt_objective);
        txt_skill = (EditText)findViewById(R.id.txt_skill);
    }


    public void nextCV(View view) {
        Bundle b = new Bundle();
        b.putString(BKEY.K_NAME, txt_name.getText()+"");
        b.putString(BKEY.K_FIRST_NAME, txt_first_name.getText()+"");
        b.putString(BKEY.K_GENDER, spi_gender.getSelectedItem() + "");
        b.putString(BKEY.K_OBJECTIVE, txt_objective.getText() + "");
        b.putString(BKEY.K_SKILL, txt_skill.getText() + "");
        b.putBoolean(BKEY.K_MAIDEN, radio_maiden.isChecked());

        b.putInt(BKEY.K_NUM_EXP, 1);

        Intent intent = new Intent(this, FormCVExpActivity.class);
        intent.putExtra("bundle", b);
        startActivity(intent);

        //SendCVTask task = new SendCVTask();
        //task.execute(new String[]{"http://lw2-valulz.rhcloud.com/resume/"});
    }


}
