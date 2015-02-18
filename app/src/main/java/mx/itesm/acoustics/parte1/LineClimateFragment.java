package mx.itesm.acoustics.parte1;


import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LineClimateFragment extends Fragment {


    public LineClimateFragment() {
        // Required empty public constructor
    }

    public  static LineChart mChart;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mChart = (LineChart) getActivity().findViewById(R.id.lineChartt1);

        mChart.setDescription("");
        mChart.setDrawYValues(false);

        mChart.setHighlightIndicatorEnabled(false);
        mChart.setDrawBorder(false);
        mChart.setDrawGridBackground(false);
        mChart.setDrawVerticalGrid(true);
        mChart.setDrawYValues(false);
        mChart.setStartAtZero(false);


        GraphLineAsycnTask  asycnTask = new GraphLineAsycnTask (getActivity());
        asycnTask.execute("https://api.forecast.io/forecast/8effdd9439fad0832a940cb01e367a60/19.432608,-99.133208?units=ca");

        mChart.animateX(3000);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"arial.ttf");
        mChart.setUnit(" °C");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_line_climate, container, false);
        return v;

    }

    protected LineData getComplexity() {

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();

        LineDataSet ds1 = new LineDataSet(generateData(), "Estructuras");
        LineDataSet ds2 = new LineDataSet(generateData(), "Bases");
        LineDataSet ds3 = new LineDataSet(generateData(), "Android");
        LineDataSet ds4 = new LineDataSet(generateData(), "Big Data");

        ds1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        ds2.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        ds3.setColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        ds4.setColor(ColorTemplate.VORDIPLOM_COLORS[3]);

        ds1.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        ds2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        ds3.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        ds4.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[3]);

        ds1.setLineWidth(2.5f);
        ds1.setCircleSize(3f);
        ds2.setLineWidth(2.5f);
        ds2.setCircleSize(3f);
        ds3.setLineWidth(2.5f);
        ds3.setCircleSize(3f);
        ds4.setLineWidth(2.5f);
        ds4.setCircleSize(3f);

        sets.add(ds1);
        sets.add(ds2);
        sets.add(ds3);
        sets.add(ds4);

        LineData d = new LineData(ChartData.generateXVals(0, ds1.getEntryCount()), sets);
        return d;
    }


    public ArrayList<Entry> generateData(){
        ArrayList<Entry> data = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            Entry entry = new Entry( (float)(Math.random() * 10), i);
            data.add(entry);
        }
        return  data;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
