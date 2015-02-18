package mx.itesm.acoustics.parte1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarClimateFragment extends Fragment {


    public BarClimateFragment() {
        // Required empty public constructor
    }

    public static BarChart mChart;
    public static FrameLayout parent;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mChart = new BarChart(getActivity());
        mChart.setDescription("");
        mChart.setHighlightIndicatorEnabled(false);
        mChart.setDrawBorder(false);
        mChart.setDrawGridBackground(false);
        mChart.setDrawVerticalGrid(false);
        mChart.setDrawXLabels(true);
        mChart.setDrawYValues(false);
        mChart.setUnit(" °C");
        mChart.setDrawBarShadow(false);
        mChart.getXLabels().setCenterXLabelText(true);
        parent = (FrameLayout) getActivity().findViewById(R.id.contenido);
        //parent.addView(mChart);

        GraphBarAsycnTask  asycnTask = new GraphBarAsycnTask (getActivity());
        asycnTask.execute("https://api.forecast.io/forecast/8effdd9439fad0832a940cb01e367a60/19.432608,-99.133208?units=ca");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bar_climate, container, false);
        /*
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"arial.ttf");
        Legend l = mChart.getLegend();
        l.setTypeface(tf);

        YLabels labels = mChart.getYLabels();
        labels.setTypeface(tf);*/
        return v;
    }


    protected BarData generateBarData(int dataSets, float range, int count) {

        ArrayList<BarDataSet> sets = new ArrayList<BarDataSet>();

        for(int i = 0; i < dataSets; i++) {

            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();


            for(int j = 0; j < count; j++) {
                entries.add(new BarEntry((float) (Math.random() * range) + range / 4, j));
            }

            BarDataSet ds = new BarDataSet(entries, getLabel(i));
            ds.setColors(ColorTemplate.VORDIPLOM_COLORS);
            sets.add(ds);
        }

        BarData d = new BarData(ChartData.generateXVals(0, count), sets);
        return d;
    }

    private String[] mLabels = new String[] { "Company A", "Company B", "Company C", "Company D", "Company E", "Company F" };
    private String getLabel(int i) {
        return mLabels[i];
    }
}
