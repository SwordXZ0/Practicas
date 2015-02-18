package mx.itesm.acoustics.parte1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment {
    public MapView mapView;
    private static GoogleMap googleMap;

    public MapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_mapa, container, false);
        View vista = inflater.inflate(R.layout.fragment_mapa, container, false);
        if (googleMap != null)
            cargaMapa();
        if (googleMap == null)
        {
            googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapa)).getMap();
            if (googleMap != null)
            {
                cargaMapa();
            }
        }
        return vista;
    }

    private static void cargaMapa()
    {
        googleMap.setMyLocationEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(new LatLng(19.432608, -99.133208)).title("Zocalo"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19.432608, -99.133208), 12.0f));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(googleMap!=null){
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.mapa)).commit();
            googleMap = null;
        }
    }
}
