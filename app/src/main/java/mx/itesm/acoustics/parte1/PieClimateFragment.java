package mx.itesm.acoustics.parte1;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PieClimateFragment extends Fragment {


    public static PieChart mChart;
    public static FrameLayout parent;


    public PieClimateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mChart = (PieChart) getActivity().findViewById(R.id.PieChartt1);
        mChart=new PieChart(getActivity());
        mChart.setDescription("");

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "arial.ttf");

        mChart.setValueTypeface(tf);
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getActivity().getAssets(), "arial.ttf"));
        mChart.setUsePercentValues(true);
        mChart.setCenterText("Temperatura\nMinima");
        mChart.setCenterTextSize(22f);

        //mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);
        parent = (FrameLayout) getActivity().findViewById(R.id.contenidoPie);


        GraphPieAsycnTask  asycnTask = new GraphPieAsycnTask (getActivity());
        asycnTask.execute("https://api.forecast.io/forecast/8effdd9439fad0832a940cb01e367a60/19.432608,-99.133208?units=ca");


        //mChart.setData(generatePieData());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pie_climate, container, false);
        return  v;
    }

    protected PieData generatePieData() {


        int count = 4;

        ArrayList<Entry> entries1 = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Auto 1");
        xVals.add("Auto 2");
        xVals.add("Auto 3");
        xVals.add("Auto 4");

        for(int i = 0; i < count; i++) {
            xVals.add("entry" + (i+1));

            entries1.add(new Entry((float) (Math.random() * 60) + 40, i));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Autos 2014");
        ds1.setColors(ColorTemplate.VORDIPLOM_COLORS);
        ds1.setSliceSpace(2f);

        PieData d = new PieData(xVals, ds1);
        return d;
    }

}
