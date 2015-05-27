package pogoda.com.pogodoview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import pogoda.com.pogodoview.R;
import com.survivingwithandroid.weather.lib.model.City;

import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class AdapterMiasta extends ArrayAdapter<City> {

    private Context ctx;
    private List<City> cityList;

    public AdapterMiasta(Context ctx, List<City> cityList) {
        super(ctx, R.layout.fragment_item_list, cityList);
        this.cityList = cityList;
        this.ctx = ctx;
    }


    @Override
    public int getCount() {

        return cityList.size();
    }


    @Override
    public City getItem(int position) {

        return cityList.get(position);
    }


    @Override
    public long getItemId(int position) {
        City city = cityList.get(position);
        return city.getId().hashCode();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_item_list, parent, false);
        }

        City city = cityList.get(position);

        TextView cityText = (TextView) convertView.findViewById(R.id.cityName);
        cityText.setText(city.getName() + "," + city.getCountry());

        return convertView;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }



    }

