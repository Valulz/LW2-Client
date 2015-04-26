package ovh.valulz.cvclient.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.model.Experience;
import ovh.valulz.cvclient.model.School;

public class SchoolAdapter extends BaseAdapter {

    private Activity activity;
    private List<School> schools;

    public SchoolAdapter(Activity activity, List<School> schools) {
        this.activity = activity;
        this.schools = schools == null ? new ArrayList<School>() : schools;
    }

    @Override
    public int getCount() {
        return schools.size();
    }

    @Override
    public Object getItem(int position) {
        return schools.get(position);
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

        final School school = schools.get(position);

        Date begin = school.getBegin();
        ((TextView)convertView.findViewById(R.id.begin)).setText(begin.getMonth()+"/"+begin.getYear());

        Date end = school.getBegin();
        if(end != null) ((TextView)convertView.findViewById(R.id.end)).setText("-"+end.getMonth()+"/"+end.getYear());

        ((TextView)convertView.findViewById(R.id.value)).setText(school.getName());


        return convertView;
    }

}
