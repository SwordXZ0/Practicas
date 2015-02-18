package mx.itesm.acoustics.parte1;

import android.accounts.NetworkErrorException;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by genome on 2/6/15.
 */
public class GraphBarAsycnTask extends AsyncTask<String, Void, ArrayList<String>> {

    private ArrayList<BarEntry> data;
    private Context context;

    private ProgressDialog processDialog;

    public GraphBarAsycnTask(Context c){
        context = c;
        data = new ArrayList<BarEntry>();
        processDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        processDialog.setTitle("Procesando...");
        processDialog.setCancelable(false);
        processDialog.show();
    }


    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> arreglo = new  ArrayList<String>();
        arreglo= procesaJSON(leerArchivo(params[0]));
        return arreglo;
    }



    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        super.onPostExecute(strings);
        data.clear();
        for(String s: strings){
            BarEntry entry = new BarEntry( Float.parseFloat(s), strings.indexOf(s));
            data.add(entry);
        }
        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();
        BarDataSet ds1 = new BarDataSet(data, "Temperatura Minima (Celsius Degrees)");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        sets.add(ds1);
        ArrayList<String> xlabels= new ArrayList<String>();
        xlabels.add("Día 1");
        xlabels.add("Día 2");
        xlabels.add("Día 3");
        xlabels.add("Día 4");
        xlabels.add("Día 5");
        xlabels.add("Día 6");
        xlabels.add("Día 7");
        xlabels.add("Día 8");
        BarClimateFragment.mChart.setData(new BarData(xlabels, sets));
        BarClimateFragment.parent.addView(BarClimateFragment.mChart);
        processDialog.dismiss();
    }



    public String leerArchivo(String url)
    {
        StringBuilder responseString = new StringBuilder();
        HttpURLConnection connection = null;
        URL serviceURL = null;
        try {
            serviceURL = new URL(url);
            connection = (HttpURLConnection) serviceURL.openConnection();
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.connect();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                throw new NetworkErrorException();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            String line = "";
            while ((line = reader.readLine()) != null)
            {
                responseString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NetworkErrorException e){
            e.printStackTrace();
        }
        finally
        {
            connection.disconnect();
        }

        return responseString.toString();
    }

    public ArrayList<String> procesaJSON(String datos){
        ArrayList<String> objetos = new ArrayList<String>();
        try {
            JSONObject ob = new JSONObject(datos);
            JSONObject ob2 = ob.getJSONObject("daily");
            JSONArray arreglo = ob2.getJSONArray("data");
            //JSONArray arreglo = ob.getJSONArray("results");
            for(int i = 0; i < arreglo.length(); i++){
                JSONObject s = arreglo.getJSONObject(i);
                objetos.add(s.getString("temperatureMin"));
            }
        }catch (Exception ex ){
            ex.printStackTrace();
        }

        return objetos;
    }
}