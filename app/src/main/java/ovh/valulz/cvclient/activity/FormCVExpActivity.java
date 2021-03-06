package ovh.valulz.cvclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.util.BKEY;

public class FormCVExpActivity extends Activity {

    private TextView txt_num_exp;
    private EditText txt_value;
    private NumberPicker year_beg;
    private NumberPicker month_beg;
    private NumberPicker year_end;
    private NumberPicker month_end;


    private Bundle b;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = getIntent().getBundleExtra("bundle");
        setContentView(R.layout.activity_exp);

        txt_num_exp = (TextView) findViewById(R.id.txt_num_exp);
        txt_value = (EditText) findViewById(R.id.txt_value);
        year_beg = (NumberPicker) findViewById(R.id.year_beg);
        month_beg = (NumberPicker) findViewById(R.id.month_beg);
        year_end = (NumberPicker) findViewById(R.id.year_end);
        month_end = (NumberPicker) findViewById(R.id.month_end);

        year_beg.setMinValue(1900);
        year_beg.setMaxValue(2100);
        month_beg.setMinValue(1);
        month_beg.setMaxValue(12);
        year_end.setMinValue(1900);
        year_end.setMaxValue(2100);
        month_end.setMinValue(1);
        month_end.setMaxValue(12);

        int nb = b.getInt(BKEY.K_NUM_EXP);

        txt_num_exp.setText("Experience #" + nb);
    }

    private void addValues() {
        int nb = b.getInt(BKEY.K_NUM_EXP);

        b.putString(BKEY.K_VALUE_EXP + nb, txt_value.getText() + "");
        b.putInt(BKEY.K_BEGIN_YEAR_EXP + nb, year_beg.getValue());
        b.putInt(BKEY.K_BEGIN_MONTH_EXP + nb, month_beg.getValue());
        b.putInt(BKEY.K_END_YEAR_EXP + nb, year_end.getValue());
        b.putInt(BKEY.K_END_MONTH_EXP + nb, month_end.getValue());

    }

    public void addNewExp(View view) {
        addValues();
        b.putInt(BKEY.K_NUM_EXP, b.getInt(BKEY.K_NUM_EXP) + 1);

        Intent intent = new Intent(this, FormCVExpActivity.class);
        intent.putExtra("bundle", b);
        startActivity(intent);
    }

    public void next(View view) {
        addValues();
        b.putInt(BKEY.K_NUM_SCHO, 1);

        Intent intent = new Intent(this, FormCVSchoActivity.class);
        intent.putExtra("bundle", b);
        startActivity(intent);
    }
}
