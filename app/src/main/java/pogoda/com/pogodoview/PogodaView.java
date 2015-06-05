package pogoda.com.pogodoview;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damian on 26.05.15.
 */
public class PogodaView extends Activity implements FragmentPogodowy.WeatherEventListener{


    private boolean isThereForecast = false;

    private WeatherConfig config;
    private WeatherClient client;
    private List<Fragment> activeFragment = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
       // requestWindowFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.main_activity);

        FragmentTransaction ft = getFragmentManager().beginTransaction();

        PrognozaBiezaca cf = PrognozaBiezaca.newInstance();
        ft.add(R.id.currentWeatherFrag, cf, "currentWeather") ;
        ft.commit();






    }
    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panel_glowny, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i = new Intent();
            i.setClass(this, Lokalizacja.class);
            startActivity(i);
        }

           return super.onOptionsItemSelected(item);
    }


    @Override
    public void requestCompleted() {

    }




    public void aaaa(View view) {
        Intent intent = new Intent(this, Lokalizacja.class);
        startActivity(intent);
    }

    public void Tygodniowa(View view) {
        Intent intent = new Intent(this, PogodaViewPrzy.class);
        startActivity(intent);
    }
}