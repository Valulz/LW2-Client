package ovh.valulz.cvclient.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ovh.valulz.cvclient.R;
import ovh.valulz.cvclient.activity.DisplayCVActivity;
import ovh.valulz.cvclient.model.ShortCV;

public class ShortCVAdapter extends BaseAdapter implements View.OnClickListener{

    private static int ODD_COLOR  = 0xFFFFCB60;
    private static int EVEN_COLOR = 0xFFFDEE00;

    private Activity activity;
    private List<ShortCV> cvs;

    public ShortCVAdapter(Activity activity, List<ShortCV> cvs) {
        this.activity = activity;
        this.cvs = cvs;
    }

    @Override
    public int getCount() {
        return cvs.size();
    }

    @Override
    public Object getItem(int position) {
        return cvs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_short_cv, parent, false);
        }

        ShortCV cv = (ShortCV) getItem(position);

        Button btnId = (Button) convertView.findViewById(R.id.btn_cv_id);
        btnId.setText(cv.getId());
        btnId.setOnClickListener(this);

        TextView txtName = (TextView) convertView.findViewById(R.id.txt_cv_name);
        txtName.setText(cv.getName());

        TextView txtFirstName = (TextView) convertView.findViewById(R.id.txt_cv_first_name);
        txtFirstName.setText(cv.getFirstName());

        convertView.setBackgroundColor(position %2 == 0 ? EVEN_COLOR : ODD_COLOR);

        return convertView;
    }

    @Override
    public void onClick(View v) {

        String id = ((TextView)v).getText()+"";

        Intent intent = new Intent(activity, DisplayCVActivity.class);
        intent.putExtra("_id", id);
        activity.startActivity(intent);
    }
}
