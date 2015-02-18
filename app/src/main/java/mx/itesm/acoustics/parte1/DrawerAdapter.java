package mx.itesm.acoustics.parte1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by genome on 2/2/15.
 */

public class DrawerAdapter extends ArrayAdapter<Object> {

    Context mContext;
    int layoutResourceId;
    Object data[] = null;

    /*
     * @mContext - app context
     *
     * @layoutResourceId - the listview_item_row.xml
     *
     * @data - the ListItem data
     */
    public DrawerAdapter(Context mContext, int layoutResourceId, Object[] data) {

        super(mContext, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.data = data;
    }

    /*
     * @We'll overried the getView method which is called for every ListItem we
     * have.
     *
     * @There are lots of different caching techniques for Android ListView to
     * achieve better performace especially if you are going to have a very long
     * ListView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        // inflate the listview_item_row.xml parent
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        // get the elements in the layout

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageView);
        TextView textViewName = (TextView) listItem.findViewById(R.id.textView);


                /*
                 * Set the data for the list item. You can also set tags here if you
                 * want.
                 */
        Object objeto = data[position];

        imageViewIcon.setImageResource(objeto.icono);
        textViewName.setText(objeto.nombre);

        return listItem;
    }

}