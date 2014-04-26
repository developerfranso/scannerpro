
package hu.u_szeged.scannerpro.ui.fragments.views;


import java.util.List;

import hu.u_szeged.scannerpro.R;
import hu.u_szeged.scannerpro.model.DAO;
import hu.u_szeged.scannerpro.model.beans.Customer;
import hu.u_szeged.scannerpro.model.beans.Reading;
import hu.u_szeged.scannerpro.ui.fragments.components.CustomerThumbnailFragment;
import hu.u_szeged.scannerpro.ui.fragments.components.ReadingThumbnailFragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;


/**
 * The fragment of the scan result view
 */
public class ScanResultFragment extends Fragment
{
	
	private Reading result;
	private Customer customer;
	
	public ScanResultFragment()
	{
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.viewfragment_scanresult, container, false);
		
		rootView.findViewById(R.id.buttonSave).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				try
				{
					int newResult = Integer.parseInt(((EditText) getView().findViewById(R.id.editTextResult)).getText().toString());
					
					if(newResult != result.getReading())
					{
						result = new Reading(newResult);
					}
					
					customer.addReading(result);
					DAO.Commit();
					getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); // go back home
				}
				catch (NumberFormatException e)
				{
					new AlertDialog.Builder(getActivity())
						.setTitle("Hib�s leolvasott adat")
						.setMessage("Az �ra�ll�snak eg�sz sz�mnak kell lennie")
						.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						
							@Override
							public void onClick(DialogInterface dialog, int which)
							{
								// do nothing
							}
						})
						.setIcon(android.R.drawable.ic_dialog_alert)
						.show();
				}
			}
		});
		
		rootView.findViewById(R.id.buttonRescan).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v)
			{
				getFragmentManager().popBackStack();
			}
		});
		
		return rootView;
	}
	
	@Override
	public void onStart()
	{
		super.onStart();
		
		layoutCustomer();
		layoutResult();
	}
	
	
	/**
	 * @return the customer whom this result belongs to
	 */
	public Customer getCustomer()
	{
		return customer;
	}
	
	
	/**
	 * @param customer the customer whom this result belongs to
	 */
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
		
		layoutCustomer();
	}
	
	
	/**
	 * @return the result of the scan
	 */
	public Reading getResult()
	{
		return result;
	}
	
	
	/**
	 * @param result the result of the scan
	 */
	public void setResult(Reading result)
	{
		this.result = result;
		
		layoutResult();
	}
	
	private void layoutCustomer()
	{
		if (getView() == null)
		{
			return;
		}
		
		LinearLayout panelCustomerInfo = (LinearLayout) getView().findViewById(R.id.linearLayoutCustomerInfo);
		
		panelCustomerInfo.removeAllViews();
		
		final FragmentTransaction ft = getFragmentManager().beginTransaction();
		
		CustomerThumbnailFragment customerThumbnail = new CustomerThumbnailFragment();
		customerThumbnail.setCustomer(customer);
		ft.add(R.id.linearLayoutCustomerInfo, customerThumbnail);
		
		List<Reading> readings = customer.getReadings();
		
		if (readings.size() > 0)
		{
			ReadingThumbnailFragment readingThumbnail = new ReadingThumbnailFragment();
			readingThumbnail.setReading(readings.get(readings.size() - 1));
			readingThumbnail.setHideCustomer(true);
			ft.add(R.id.linearLayoutCustomerInfo, readingThumbnail);
		}
		
		ft.commit();
	}
	
	private void layoutResult()
	{
		if (getView() == null)
		{
			return;
		}
		
		((EditText) getView().findViewById(R.id.editTextResult)).setText("" + result.getReading());
	}
}
