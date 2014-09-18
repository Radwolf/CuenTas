package org.soft.rul.cuentas;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TabbedFragment extends FragmentActivity implements ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	ResumenPagerAdapter mResumenPagerAdapter;

	public static final String TAG = TabbedFragment.class.getSimpleName();
	
	/**
	 * The {@link android.support.v4.view.ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	public static TabbedFragment newInstance() {
		return new TabbedFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        //Creamos el adaptador que gestionara la carga de los fragments de cada pagina
        mResumenPagerAdapter = new ResumenPagerAdapter(getSupportFragmentManager());

        //Cargamos el actionBar
        final ActionBar actionBar = getActionBar();
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

	/**
	 * A {@link android.support.v4.app.FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class ResumenPagerAdapter extends FragmentPagerAdapter {

		public ResumenPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
            Fragment fragment = new ResumenSectionFragment();
            Bundle args = new Bundle();
            args.putInt(ResumenSectionFragment.ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Section " + (position + 1);
        }
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class ResumenSectionFragment extends Fragment {
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

}
