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
import ovh.valulz.cvclient.model.Language;

public class LangAdapter extends BaseAdapter {

    private Activity activity;
    private List<Language> langs;

    public LangAdapter(Activity activity, List<Language> langs) {
        this.activity = activity;
        this.langs = langs == null ? new ArrayList<Language>() : langs;
    }

    @Override
    public int getCount() {
        return langs.size();
    }

    @Override
    public Object getItem(int position) {
        return langs.get(position);
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

        final Language lang = langs.get(position);

        ((TextView) convertView.findViewById(R.id.value)).setText(lang.getName());
        ((TextView) convertView.findViewById(R.id.level)).setText(""+lang.getLevel());
        ((TextView) convertView.findViewById(R.id.desc)).setText(lang.getDescription());

        return convertView;
    }

}
