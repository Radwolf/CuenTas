package org.soft.rul.cuentas.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.soft.rul.cuentas.R;

/**
 * Created by rgonzalez on 18/09/2014.
 */
public class ResumenSectionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";

    public ResumenSectionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tabbed_resumen,
                container, false);
        TextView dummyTextView = (TextView) rootView
                .findViewById(R.id.section_label);
        dummyTextView.setText(Integer.toString(getArguments().getInt(
                ARG_SECTION_NUMBER)));
        return rootView;
    }

}
