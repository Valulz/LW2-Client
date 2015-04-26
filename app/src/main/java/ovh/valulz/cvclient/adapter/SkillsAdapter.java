package ovh.valulz.cvclient.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.model.ComputerScienceSkill;

public class SkillsAdapter extends BaseAdapter {

    private Activity activity;
    private List<ComputerScienceSkill> css;

    public SkillsAdapter(Activity activity, List<ComputerScienceSkill> css) {
        this.activity = activity;
        this.css = css == null ? new ArrayList<ComputerScienceSkill>() : css;
    }

    @Override
    public int getCount() {
        return css.size();
    }

    @Override
    public Object getItem(int position) {
        return css.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_level, parent, false);
        }

        final ComputerScienceSkill skill = css.get(position);

        ((TextView) convertView.findViewById(R.id.value)).setText(skill.getName());
        ((TextView) convertView.findViewById(R.id.level)).setText(""+skill.getLevel());
        ((TextView) convertView.findViewById(R.id.desc)).setVisibility(View.INVISIBLE);

        return convertView;
    }

}
