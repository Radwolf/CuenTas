package org.soft.rul.cuentas;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.soft.rul.cuentas.ui.adapter.ResumenPagerAdapter;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    // Definicion de variables utilizadas para las Tabs y el SwipePager

    /**
     * The {@link android.support.v4.view.ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    ResumenPagerAdapter mResumenPagerAdapter;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mDrawerItmes;

    private ActionBar actionBar;
    private static final String STATE_KEY_TAB_SELECTED = "TAB_SELECTED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializaTabsMasSwipePage();
        mTitle = mDrawerTitle = getTitle();

        mDrawerItmes = getResources().getStringArray(R.array.drawer_titles);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,  GravityCompat.START);

        // Add items to the ListView
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mDrawerItmes));
        // Set the OnItemClickListener so something happens when a
        // user clicks on an item.
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // Enable ActionBar app icon to behave as action to toggle the NavigationDrawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // Set the default content area to item 0
        // when the app opens for the first time
        if(savedInstanceState != null) {
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(STATE_KEY_TAB_SELECTED));
        }
    }

    private void inicializaTabsMasSwipePage() {
        //Creamos el adaptador que gestionara la carga de los fragments de cada pagina
        mResumenPagerAdapter = new ResumenPagerAdapter(getSupportFragmentManager());

        //Cargamos el actionBar
        actionBar = getActionBar();
        //Especificamos al actionBar que desactive el boton UP porque esta activity no tiene un padre
        //al que subir ya que ella será el Home de la navegación
        actionBar.setHomeButtonEnabled(false);
        //Especificamos a la activity que utilizaremos una navegación de tipo ACTIONBAR TAB,
        // !!Ojo!! cuando cargemos otros fragments que no sean los propios de las tabs, hay que
        // especificar de nuevo expresamente el tipo de navegación sino queremos que se mantengan las tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Configuramos el viewPager, le asignamos el adaptador que creamos al principio para gestionar
        //la carga de los fragments de las tabs y le asignamos un listener para gestionar los cambios del
        //viewPager al hacer swipe
        mViewPager= (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mResumenPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Cuando vayamos pasando de un viewPage a otro iremos informando.
                // Se podría usar también ActionBar.Tab#select() pero para ello necesitaríamos una
                // referencia al objeto Tab que quisiermos seleccionar.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each de cada una de las secciones del swipePager, para ir añadiendo una tab al actionBar
        // por cada sección del swipePager
        for (int i = 0; i < mResumenPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mResumenPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }

    /*
     * When using the ActionBarDrawerToggle, you must call it during onPostCreate()
     * and onConfigurationChanged()
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }else{
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            navigateTo(position);
        }
    }

    private void navigateTo(int position) {

        switch(position) {
            case 0:
                Toast.makeText(this, "Opcion1", Toast.LENGTH_SHORT).show();
                break;
            case 1:
//                getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.pager,
//                                WebViewFragment.newInstance(),
//                                WebViewFragment.TAG).commit();
                Intent intent = new Intent(this, MovimientoActivity.class);
                startActivity(intent);
                break;
        }
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_KEY_TAB_SELECTED, actionBar.getSelectedNavigationIndex());
    }

    //<editor-fold desc="METODOS TAB LISTENER">
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    //</editor-fold>
}
