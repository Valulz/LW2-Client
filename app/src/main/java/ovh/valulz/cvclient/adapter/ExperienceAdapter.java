package ovh.valulz.cvclient.adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.model.Experience;

public class ExperienceAdapter extends BaseAdapter {

    private Activity activity;
    private List<Experience> exps;

    public ExperienceAdapter(Activity activity, List<Experience> exps) {
        this.activity = activity;
        this.exps = exps == null ? new ArrayList<Experience>() : exps;
    }

    @Override
    public int getCount() {
        return exps.size();
    }

    @Override
    public Object getItem(int position) {
        return exps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_date, parent, false);
        }

        final Experience exp = exps.get(position);

        Date begin = exp.getBegin();
        ((TextView)convertView.findViewById(R.id.begin)).setText(begin.getMonth()+"/"+begin.getYear());

        Date end = exp.getEnd();
        if(end != null) ((TextView)convertView.findViewById(R.id.end)).setText("-"+end.getMonth()+"/"+end.getYear());

        ((TextView)convertView.findViewById(R.id.value)).setText(exp.getName());


        return convertView;
    }

}
