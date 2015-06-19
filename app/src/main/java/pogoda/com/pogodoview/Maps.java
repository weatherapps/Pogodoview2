package pogoda.com.pogodoview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A fragment that launches other parts of the demo application.
 */
public class Maps extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate and return the layout
        View v = inflater.inflate(R.layout.activity_maps, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        googleMap.setMyLocationEnabled(true);

        googleMap.getUiSettings().setZoomGesturesEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false); //Przycisk lokalizacji

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(52, 19.1), 5.6f)); //center na Polskę

        googleMap.addMarker(new MarkerOptions().position(new LatLng(52.25, 21))
                .title("Warszawa")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(54.1, 22.9))
                .title("Suwalki")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(54.3, 18.7))
                .title("Gdansk")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(53.4, 14.5))
                .title("Szczecin")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(50.1, 22))
                .title("Rzeszow")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        googleMap.addMarker(new MarkerOptions().position(new LatLng(52.4, 16.9))
                .title("Poznan")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));


        googleMap.addMarker(new MarkerOptions().position(new LatLng(50.3, 19))
                .title("Katowice")
                .snippet("Aktualna pogoda: brak danych")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));




        // Perform any camera updates here
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}