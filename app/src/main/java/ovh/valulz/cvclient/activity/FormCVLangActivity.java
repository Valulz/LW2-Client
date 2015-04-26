package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.util.BKEY;

public class FormCVLangActivity extends Activity {

    private Bundle bundle;

    private EditText name;
    private NumberPicker level;
    private EditText desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lang);
        bundle = getIntent().getBundleExtra("bundle");

        name = (EditText)findViewById(R.id.name);
        level = (NumberPicker)findViewById(R.id.level);
        desc = (EditText)findViewById(R.id.description);

        level.setMaxValue(20);
        level.setMinValue(0);

    }

    private void addValues(){
        int nb = bundle.getInt(BKEY.K_NUM_LANG);

        bundle.putString(BKEY.K_NAME_LANG+nb, name.getText()+"");
        bundle.putInt(BKEY.K_LEVEL_LANG + nb, level.getValue());
        bundle.putString(BKEY.K_DESC_LANG + nb, desc.getText() + "");
    }

    public void addNewLang(View view) {
        addValues();
        bundle.putInt(BKEY.K_NUM_LANG, bundle.getInt(BKEY.K_NUM_LANG)+1);

        Intent intent = new Intent(this, FormCVLangActivity.class);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    public void next(View view) {
        addValues();
        bundle.putInt(BKEY.K_NUM_CSS, 1);

        Intent intent = new Intent(this, FormCVCSSActivity.class);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }
}
