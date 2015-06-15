package pogoda.com.pogodoview;

import android.content.Context;

import com.survivingwithandroid.weather.lib.WeatherClient;
import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.client.volley.WeatherClientDefault;
import com.survivingwithandroid.weather.lib.exception.WeatherProviderInstantiationException;
import com.survivingwithandroid.weather.lib.provider.IProviderType;
import com.survivingwithandroid.weather.lib.provider.openweathermap.OpenweathermapProviderType;
import com.survivingwithandroid.weather.lib.provider.wunderground.WeatherUndergroundProviderType;
import com.survivingwithandroid.weather.lib.provider.yahooweather.YahooWeatherProvider;

/**
 * Created by damian on 15.05.15.
 */
public class PogodaSerwUstawienia {
    private static PogodaSerwUstawienia me;
    private WeatherClient client;

    private PogodaSerwUstawienia() {}

    public static PogodaSerwUstawienia getInstance() {
        if (me == null)
            me = new PogodaSerwUstawienia();

        return me;
    }

    public WeatherClient getClient(Context ctx) {
        if (client != null)
            return client;

        try {
            client = new WeatherClient.ClientBuilder()
                    .attach(ctx)
                    .config(new WeatherConfig())
                    .provider( new OpenweathermapProviderType())
                    .httpClient(WeatherClientDefault.class)
                    .build();
        }
        catch (WeatherProviderInstantiationException e) {
            e.printStackTrace();
        }

        return client;
    }
}
