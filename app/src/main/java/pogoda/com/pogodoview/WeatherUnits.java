/*
 * Copyright (C) 2014 Francesco Azzola
 *  Surviving with Android (http://www.survivingwithandroid.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pogoda.com.pogodoview;

import com.survivingwithandroid.weather.lib.WeatherConfig;
import com.survivingwithandroid.weather.lib.util.UnitUtility;
import com.survivingwithandroid.weather.lib.util.WeatherUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Francesco on 20/03/14.
 */
public class WeatherUnits {



    public static String getLanguage(String val) {
        if (val == null)
            return "en";

        if (val.equalsIgnoreCase("system"))
            return Locale.getDefault().getLanguage();

        return null;
    }


    public static String convertDate(long unixTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(unixTime * 1000);
        sdf.setTimeZone(cal.getTimeZone());
        return sdf.format(cal.getTime());
    }
}
