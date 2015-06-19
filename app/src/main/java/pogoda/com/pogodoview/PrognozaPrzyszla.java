package pogoda.com.pogodoview;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.WeatherForecast;

import pogoda.com.pogodoview.adapter.AdapterMiasta;
import pogoda.com.pogodoview.adapter.AdapterPogody;

/**
 * Created by damian on 29.05.15.
 */
public class PrognozaPrzyszla extends FragmentPogodowy {

   // protected WeatherClient weatherClient;

    private SharedPreferences preferencje;
    private ListView forecastList;
    private AdapterPogody adapterPogody;

    public static PrognozaPrzyszla newInstance() {
        PrognozaPrzyszla fragment = new PrognozaPrzyszla();
        return fragment;
    }

    public PrognozaPrzyszla() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.forecast_fragment, container, false);
        forecastList = (ListView) v.findViewById(R.id.forecastDays);
        return v;

    }

    public void refreshData() {
        refresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    private void refresh() {
        // Update forecast

        preferencje = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String cityId = preferencje.getString("cityid", null);

        weatherClient.getForecastWeather(cityId, new WeatherClient.ForecastWeatherEventListener() {
            @Override
            public void onWeatherRetrieved(WeatherForecast forecast) {
                AdapterPogody adp = new AdapterPogody(forecast, getActivity());
                forecastList.setAdapter(adp);
            }

            @Override
            public void onWeatherError(WeatherLibException t) {

            }

            @Override
            public void onConnectionError(Throwable t) {
            }
        });
    }
}
