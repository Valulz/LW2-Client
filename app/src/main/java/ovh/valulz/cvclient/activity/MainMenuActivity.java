package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ovh.valulz.cvclient.R;

/**
 * Cette classe genere le menu principal de l'application
 */
public class MainMenuActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    public void displayListCV(View view) {
        //TODO New Activity Display CVs
        Intent intent = new Intent(this, DisplayCVsActivity.class);
        startActivity(intent);
    }


    public void displayACV(View view) {

    }


    public void addNewCV(View view) {
        Intent intent = new Intent(this, FormCVActivity.class);
        startActivity(intent);
    }
}
