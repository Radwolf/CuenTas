package org.soft.rul.cuentas.ui.fragment;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.soft.rul.cuentas.R;

/**
 * Created by rgonzalez on 19/09/2014.
 */
public class MovimientoListFragment extends Fragment {

    public final static String TAG = MovimientoListFragment.class.getSimpleName();
//    private FragmentIterationListener mCallback = null;

    private ActionBar actionBar;

    public MovimientoListFragment() {
    }

    public static MovimientoListFragment newInstance(Bundle arguments){
        MovimientoListFragment f = new MovimientoListFragment();
        if(arguments != null){
            f.setArguments(arguments);
        }
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movimiento_list, container, false);
        actionBar = getActivity().getActionBar();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        inflater.inflate(R.menu.movimiento_list, menu);
    }

//    @Override
//    public void onPrepareOptionsMenu(Menu menu) {
//        for(int i = 0; i < menu.size(); i++){
//            menu.getItem(i).setVisible(visible);
//        }
//        super.onPrepareOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.action_movimiento_add:
//              String id = "IdQueNecesitaMyFragment";
                Bundle arguments = new Bundle();
//              arguments.putString("id", id);
                MovimientoAddFragment fragment = MovimientoAddFragment.newInstance(arguments);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, fragment, MovimientoAddFragment.TAG);
                ft.commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //El fragment se ha adjuntado al Activity
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
//            mCallback = (FragmentIterationListener) activity;
//        }catch(CastClassException ex){
        }catch(Exception ex){
            Log.e(MovimientoListFragment.TAG, "El Activity debe implementar la interfaz FragmentIterationListener");
        }
    }

    //El Activity que contiene el Fragment ha terminado su creación
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true); //Indicamos que este Fragment tiene su propio menu de opciones
    }
}
