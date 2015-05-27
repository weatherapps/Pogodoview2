package pogoda.com.pogodoview;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import pogoda.com.pogodoview.adapter.AdapterMiasta;
import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.exception.LocationProviderNotFoundException;
import com.survivingwithandroid.weather.lib.exception.WeatherLibException;
import com.survivingwithandroid.weather.lib.model.City;
import com.survivingwithandroid.weather.lib.util.UnitUtility;

import java.util.ArrayList;
import java.util.List;

import pogoda.com.pogodoview.adapter.AdapterMiasta;


public class Lokalizacja extends Activity {

    private ListView listaMiast;
    private ProgressBar bar;
    private AdapterMiasta adapterMiasta;
    private WeatherClient client;
    public String miejscowosc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokalizacja);

        client = PogodaSerwUstawienia.getInstance().getClient(this);
        Log.d("Appka", "Klient [" + client + "]");
        listaMiast = (ListView) findViewById(R.id.cityList);
        bar = (ProgressBar) findViewById(R.id.progressBar2);

        adapterMiasta = new AdapterMiasta(Lokalizacja.this, new ArrayList<City>());
        listaMiast.setAdapter(adapterMiasta);

        ImageView imageView = (ImageView) findViewById(R.id.akceptacja);
        final EditText editText = (EditText) findViewById(R.id.lokalizacja);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    szukajGPS(v.getText().toString());
                    return true;
                }

                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                szukajGPS(editText.getEditableText().toString());
            }
        });

        listaMiast.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos,
                                    long id) {
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Lokalizacja.this);
                SharedPreferences.Editor editor = sharedPref.edit();
                City city = (City) parent.getItemAtPosition(pos);
                editor.putString("cityid", city.getId());
                editor.putString("cityName", city.getName());
                editor.putString("country", city.getCountry());
                editor.commit();

                NavUtils.navigateUpFromSameTask(Lokalizacja.this);
            }
        });

        ImageView locImg = (ImageView) findViewById(R.id.locGPS);
        locImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                try {
                    client.searchCityByLocation(WeatherClient.createDefaultCriteria(), new WeatherClient.CityEventListener() {

                        @Override
                        public void onCityListRetrieved(List<City> cityList) {
                            bar.setVisibility(View.GONE);
                            adapterMiasta.setCityList(cityList);
                            adapterMiasta.notifyDataSetChanged();
                        }

                        @Override
                        public void onWeatherError(WeatherLibException wle) {
                            bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onConnectionError(Throwable t) {
                            bar.setVisibility(View.GONE);
                        }
                    });
                }
                catch(LocationProviderNotFoundException lpnfe) {

                }
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lokalizacja, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void szukajGPS(String pattern) {
        bar.setVisibility(View.VISIBLE);

        client.searchCity(pattern, new WeatherClient.CityEventListener() {
            @Override
            public void onCityListRetrieved(List<City> cityList) {
                bar.setVisibility(View.GONE);

                adapterMiasta.setCityList(cityList);
                adapterMiasta.notifyDataSetChanged();
            }

            @Override
            public void onWeatherError(WeatherLibException t) {
                bar.setVisibility(View.GONE);
            }

            @Override
            public void onConnectionError(Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });
    }

}
