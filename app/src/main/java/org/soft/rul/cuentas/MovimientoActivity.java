package org.soft.rul.cuentas;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import org.soft.rul.cuentas.ui.fragment.MovimientoListFragment;


public class MovimientoActivity extends Activity {

    public final static String TAG = MovimientoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        if (savedInstanceState == null) {
//            String id = "IdQueNecesitaMyFragment";
            Bundle arguments = new Bundle();
//            arguments.putString("id", id);
            MovimientoListFragment fragment = MovimientoListFragment.newInstance(arguments);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(android.R.id.content, fragment, MovimientoListFragment.TAG);
            ft.commit();
        }
    }

}
