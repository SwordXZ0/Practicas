package mx.itesm.acoustics.parte1;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class ITunesFragment extends Fragment {


    public ITunesFragment() {
        // Required empty public constructor
    }

    private ListView lista;
    private EditText texto;
    private Button button;
    private ArrayAdapter<String> adapter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lista = (ListView) getView().findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, new ArrayList<String>());
        lista.setAdapter(adapter);

        ItunesAsycnTask asycnTask = new ItunesAsycnTask(getActivity(),adapter);
        asycnTask.execute("https://api.forecast.io/forecast/8effdd9439fad0832a940cb01e367a60/19.432608,-99.133208");

        texto = (EditText)getView().findViewById(R.id.editText);

        button = (Button)getView().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(texto.getText().length() >0){
                    String s = texto.getText().toString();
                    ItunesAsycnTask asycnTask = new ItunesAsycnTask(getActivity(),adapter);
                    asycnTask.execute("https://api.forecast.io/forecast/8effdd9439fad0832a940cb01e367a60/" +  s);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.itunes_list_fragment, container, false);
    }


}