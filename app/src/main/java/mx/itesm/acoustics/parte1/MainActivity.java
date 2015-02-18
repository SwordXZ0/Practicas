package mx.itesm.acoustics.parte1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
    private String[] itemTitulos;
    private DrawerLayout drawerLayout;
    private ListView listViewDrawer;
    ActionBarDrawerToggle toggle;

    private CharSequence tituloDrawer, titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titulo= tituloDrawer=getTitle();
        itemTitulos = getResources().getStringArray(R.array.opciones);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listViewDrawer = (ListView)findViewById(R.id.left_drawer);
        Object[] objetos = new Object[8];
        objetos[0]= new Object(R.drawable.ic_action_augmented, itemTitulos[0]);
        objetos[1]= new Object(R.drawable.ic_action_facebook, itemTitulos[1]);
        objetos[2]= new Object(R.drawable.ic_launcher, itemTitulos[2]);
        objetos[3] = new Object(R.drawable.ic_launcher, itemTitulos[3]);
        objetos[4] = new Object(R.drawable.ic_launcher, itemTitulos[4]);
        objetos[5] = new Object(R.drawable.ic_launcher, itemTitulos[5]);
        objetos[6] = new Object(R.drawable.ic_launcher, itemTitulos[6]);
        objetos[7] = new Object(R.drawable.ic_launcher, itemTitulos[7]);

        DrawerAdapter drawerAdapter= new DrawerAdapter(this, R.layout.renglon,objetos);
        listViewDrawer.setAdapter(drawerAdapter);

        listViewDrawer.setOnItemClickListener(new DrawerListListener());

        if(savedInstanceState==null){
            seleccionaAction(0);
        }
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.abrir, R.string.cerrar){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(tituloDrawer);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(titulo);
            }
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
    }
    private class DrawerListListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            seleccionaAction(position);
        }
    }

    public void seleccionaAction(int position){
        Fragment fragment=null;

        switch(position){
            case 0:
                fragment= new AugmentedRealityFragment();
                break;
            case 1:
                fragment= new FacebookFragment();
                break;
            case 2:
                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(it);
                break;
            case 3:
                fragment = new ITunesFragment();
                break;
            case 4:
                fragment = new LineClimateFragment();
                break;
            case 5:
                fragment = new BarClimateFragment();
                break;
            case 6:
                fragment = new PieClimateFragment();
                break;
            case 7:
                fragment = new MapaFragment();
                break;
        }

        if (fragment !=null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame,fragment).commit();
            listViewDrawer.setItemChecked(position,true);
            listViewDrawer.setSelection(position);
            setTitle(itemTitulos[position]);
            drawerLayout.closeDrawer(listViewDrawer);
        }else{
            listViewDrawer.setItemChecked(position, true);
            listViewDrawer.setSelection(position);
            setTitle(itemTitulos[position]);
            drawerLayout.closeDrawer(listViewDrawer);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml

        //noinspection SimplifiableIfStatement
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
