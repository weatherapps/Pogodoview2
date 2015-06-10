package pogoda.com.pogodoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

public class Powitanie extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_powitanie);
    }

    public void onNextClick(View view) {
        Intent intent = new Intent(this, Lokalizacja.class);
        startActivity(intent);
    }


}
