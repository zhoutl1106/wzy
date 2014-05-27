package com.wzy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	public static  List<Map<String, Object>> mData;
	public static int orderCanceled = 0;
	public static int orderConfirmed = 0;
	public static int incoming = 0;
	public static Socket s;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mData=new ArrayList<Map<String, Object>>();  
		Map<String, Object> map = new HashMap<String, Object>();         
		map.put("order", new String("Empty"));
		map.put("orderPrice", new Integer(0));
		map.put("orderTel", new String("0"));
		map.put("orderAddr", new String("0"));
		mData.add(map);
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()        
		.detectDiskReads()        
		.detectDiskWrites()        
		.detectNetwork()   // or .detectAll() for all detectable problems       
		.penaltyLog()        
		.build());        
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()        
		.detectLeakedSqlLiteObjects()     
		.penaltyLog()        
		.penaltyDeath()        
		.build());
		try
		{
			s = new Socket("192.168.115.1", 8000);
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter
	{

		public SectionsPagerAdapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount()
		{
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			Locale l = Locale.getDefault();
			switch (position)
			{
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment
	{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		private MyBaseAdapter myBaseAdapter;
		private static  OrderBaseAdapter orderBaseAdapter;
		EditText telInput;
		Spinner spinner;
		int currentAddr = 999;
		public DummySectionFragment()
		{
		}

		@Override
		public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = null; 
			
			orderBaseAdapter = new OrderBaseAdapter(this.getActivity());
			switch(getArguments().getInt(ARG_SECTION_NUMBER))
			{
			case 1:		
				rootView = inflater.inflate(R.layout.fragment_main_dummy,container, false);
				ListView dummyListView = (ListView) rootView.findViewById(R.id.section_list);
				final TextView dummyTextView = (TextView) rootView.findViewById(R.id.textTip);
				telInput = (EditText) rootView.findViewById(R.id.editText1);
				spinner = (Spinner) rootView.findViewById(R.id.spinner1);
				ArrayAdapter adapter = ArrayAdapter.createFromResource(
		                getActivity(), R.array.addr, android.R.layout.simple_spinner_item);
		        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		        spinner.setAdapter(adapter);
		        spinner.setSelection(16);
				spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3)
					{
						// TODO Auto-generated method stub
						currentAddr = arg2;
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0)
					{
						// TODO Auto-generated method stub
						currentAddr = 999;
					}
					
				});
				
				myBaseAdapter=new MyBaseAdapter(this.getActivity()); 
				dummyListView.setAdapter(myBaseAdapter); 
				Button btn = (Button) rootView.findViewById(R.id.btnComfirm);
				btn.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v)
					{
						// TODO Auto-generated method stub
						if(currentAddr == 999)
							Toast.makeText(getActivity(), "产生订单失败：\n请输入正确的地址", Toast.LENGTH_SHORT).show();
						else if(myBaseAdapter.getPrice() == 0)
							Toast.makeText(getActivity(), "产生订单失败：\n请至少选择一种菜品", Toast.LENGTH_SHORT).show();
						else
						{
							Map<String, Object> map = new HashMap<String, Object>();         
							map.put("order", myBaseAdapter.getOrderStr());
							map.put("orderPrice", new Integer(myBaseAdapter.getPrice()));
							map.put("orderTel", telInput.getText().toString());
							map.put("orderAddr", spinner.getItemAtPosition(currentAddr).toString());
							mData.add(map);
							View temprootView = inflater.inflate(R.layout.orderlayout,container, false);
							ListView orderListView = (ListView) temprootView.findViewById(R.id.order_list);
							orderBaseAdapter.putOrder((ArrayList<Map<String, Object>>) mData);
							orderListView.setAdapter(orderBaseAdapter);
							orderBaseAdapter.notifyDataSetInvalidated();
							Toast.makeText(getActivity(), "产生订单成功：\n请在流程管理中查看", Toast.LENGTH_SHORT).show();
							telInput.setText("");
						}
					}					
				});
				
				Button btn2 = (Button) rootView.findViewById(R.id.btnRequest);
				btn2.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v)
					{
						// TODO Auto-generated method stub
						
						try {		              	
							// outgoing stream redirect to socket
							OutputStream out = MainActivity.s.getOutputStream();
							// 注意第二个参数据为true将会自动flush，否则需要需要手动操作out.flush()
							PrintWriter output = new PrintWriter(out, true);
							output.println("1330333133313031343133373936303932343238");
						} catch (UnknownHostException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}					
				});
				


			break;
			case 2:
				rootView = inflater.inflate(R.layout.orderlayout,container, false);
				ListView orderListView = (ListView) rootView.findViewById(R.id.order_list);
				if(!mData.isEmpty())
					orderBaseAdapter.putOrder((ArrayList<Map<String, Object>>) mData);
				orderListView.setAdapter(orderBaseAdapter); 
			break;
			case 3:
				rootView = inflater.inflate(R.layout.statuslayout,container, false);
				final TextView statusTextView = (TextView) rootView.findViewById(R.id.textViewStatus); 
				
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");       
				Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
				String str = formatter.format(curDate);
				str += "\n\n当前完成的订单数:\t\t" + orderConfirmed +"\n当前取消的订单数:\t\t" + orderCanceled + "\n\n今日收入：\t\t\t" + incoming;
				statusTextView.setText(str);
				Button btnClean = (Button) rootView.findViewById(R.id.buttonClean);
				btnClean.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v)
					{
						// TODO Auto-generated method stub
						orderCanceled = 0;
						orderConfirmed = 0;
						incoming = 0;
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");       
						Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
						String str = formatter.format(curDate);
						str += "\n\n当前完成的订单数:\t\t" + orderConfirmed +"\n当前取消的订单数:\t\t" + orderCanceled + "\n\n今日收入：\t\t\t" + incoming;
						statusTextView.setText(str);
					}
					
				});
				Button btnRefresh = (Button) rootView.findViewById(R.id.buttonRefresh);
				btnRefresh.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v)
					{
						// TODO Auto-generated method stub
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");       
						Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
						String str = formatter.format(curDate);
						str += "\n\n当前完成的订单数:\t\t" + orderConfirmed +"\n当前取消的订单数:\t\t" + orderCanceled + "\n\n今日收入：\t\t\t" + incoming;
						statusTextView.setText(str);
					}
					
				});
			break;
			}
			
			return rootView;
		}
		
	}

}
