package pogoda.com.pogodoview;

import android.app.Fragment;
import android.os.Bundle;

import com.survivingwithandroid.weather.lib.WeatherClient;
import pogoda.com.pogodoview.PogodaSerwUstawienia;


/**
 * Created by damian on 26.05.15.
 */
public abstract class FragmentPogodowy extends android.support.v4.app.Fragment {
    protected WeatherClient weatherClient;

    public FragmentPogodowy() {


    }

    protected WeatherEventListener getListener() {
        return ( (WeatherEventListener) getActivity());
    }

    public static interface WeatherEventListener {
        public void requestCompleted();
    }

    public abstract void refreshData();


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        weatherClient = PogodaSerwUstawienia.getInstance().getClient(getActivity());
    }
}
