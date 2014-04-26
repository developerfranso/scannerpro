
package hu.u_szeged.scannerpro.ui.fragments.views;


import hu.u_szeged.scannerpro.R;
import hu.u_szeged.scannerpro.model.DAO;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


/**
 * The fragment of the main view
 */
public class MainFragment extends Fragment
{
	
	public MainFragment()
	{
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.viewfragment_main, container, false);
		
		rootView.findViewById(R.id.buttonNewScan).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				if (DAO.IsInitialized())
				{
					final FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, new ScanFragment());
					ft.addToBackStack(null);
					ft.commit();
				}
				else
				{
					Exception e = DAO.GetInitializationError();
					
					if(e == null)
					{
						new AlertDialog.Builder(getActivity())
								.setTitle("Inicializ�ci�...")
								.setMessage("Az adatb�zis inicializ�l�sa m�g nem fejez�d�tt be, k�rj�k v�rjon.")
								.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										// do nothing
									}
								})
								.setIcon(android.R.drawable.ic_dialog_info)
								.show();
					}
					else
					{
						new AlertDialog.Builder(getActivity())
							.setTitle("Inicializ�ci�s hiba")
							.setMessage("Az adatb�zis inicializ�l�sa k�zben hiba t�rt�nt: " + e.toString() + "\n\nPr�b�lja meg �jraind�tani az alkalmaz�st.")
							.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									// do nothing
								}
							})
							.setIcon(android.R.drawable.ic_dialog_alert)
							.show();
						
						e.printStackTrace();
					}
				}
			}
		});
		
		rootView.findViewById(R.id.buttonOldScans).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				if (DAO.IsInitialized())
				{
					final FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, new OldScansFragment());
					ft.addToBackStack(null);
					ft.commit();
				}
				else
				{
					Exception e = DAO.GetInitializationError();
					
					if(e == null)
					{
						new AlertDialog.Builder(getActivity())
								.setTitle("Inicializ�ci�...")
								.setMessage("Az adatb�zis inicializ�l�sa m�g nem fejez�d�tt be, k�rj�k v�rjon.")
								.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										// do nothing
									}
								})
								.setIcon(android.R.drawable.ic_dialog_info)
								.show();
					}
					else
					{
						new AlertDialog.Builder(getActivity())
							.setTitle("Inicializ�ci�s hiba")
							.setMessage("Az adatb�zis inicializ�l�sa k�zben hiba t�rt�nt: " + e.toString() + "\n\nPr�b�lja meg �jraind�tani az alkalmaz�st.")
							.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									// do nothing
								}
							})
							.setIcon(android.R.drawable.ic_dialog_alert)
							.show();
						
						e.printStackTrace();
					}
				}
			}
		});
		
		rootView.findViewById(R.id.buttonCustomers).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				if (DAO.IsInitialized())
				{
					final FragmentTransaction ft = getFragmentManager().beginTransaction();
					ft.replace(R.id.container, new CustomersFragment());
					ft.addToBackStack(null);
					ft.commit();
				}
				else
				{
					Exception e = DAO.GetInitializationError();
					
					if(e == null)
					{
						new AlertDialog.Builder(getActivity())
								.setTitle("Inicializ�ci�...")
								.setMessage("Az adatb�zis inicializ�l�sa m�g nem fejez�d�tt be, k�rj�k v�rjon.")
								.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which)
									{
										// do nothing
									}
								})
								.setIcon(android.R.drawable.ic_dialog_info)
								.show();
					}
					else
					{
						new AlertDialog.Builder(getActivity())
							.setTitle("Inicializ�ci�s hiba")
							.setMessage("Az adatb�zis inicializ�l�sa k�zben hiba t�rt�nt: " + e.toString() + "\n\nPr�b�lja meg �jraind�tani az alkalmaz�st.")
							.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface dialog, int which)
								{
									// do nothing
								}
							})
							.setIcon(android.R.drawable.ic_dialog_alert)
							.show();
						
						e.printStackTrace();
					}
				}
			}
		});
		
		return rootView;
	}
}
