package pogoda.com.pogodoview;

import android.app.Fragment;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of App Widget functionality.
 */
public class AppWidget extends AppWidgetProvider  {


    //doesn't work sorry ;(


    private static TextView temperatura2;
    private static List<Fragment> activeFragment = new ArrayList<Fragment>();
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    PrognozaBiezaca widget = new PrognozaBiezaca();

    TextView temperatura = widget.getTemp();
    TextView miejsowocs = widget.getCityText();
    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


     public void refresh(){
        PrognozaBiezaca ferfe = PrognozaBiezaca.newInstance();
        ferfe.refreshData();

    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        PrognozaBiezaca widget = new PrognozaBiezaca();

        TextView temperatura = widget.getTemp();
        if(temperatura!=null){
            temperatura2 = temperatura;
        }
        TextView miejsowocs = widget.getCityText();
        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.temeratura, "temperatura2"+ widget.getCityText());

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }



}

