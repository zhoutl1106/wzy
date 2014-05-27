package com.wzy;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import com.wzy.MyBaseAdapter.ViewHolder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderBaseAdapter extends BaseAdapter
{

	private LayoutInflater mInflater;  
	public List<Map<String, Object>> mData;   
	private ListView mListView;

	public OrderBaseAdapter(Context context) {  
		this.mInflater = LayoutInflater.from(context);
		mData=new ArrayList<Map<String, Object>>();  
	}  

	public int getCount() {  
		// TODO Auto-generated method stub  
		return mData.size();
	}  

	public Object getItem(int position) {  
		// TODO Auto-generated method stub  
		return null;
	}  

	public long getItemId(int position) {  
		// TODO Auto-generated method stub  
		return 0;  
	}  
	
	public void putOrder(ArrayList<Map<String, Object>> mm){
		mData = (ArrayList<Map<String, Object>>) mm;
		notifyDataSetChanged();  
	}

	public View getView(final int position, View convertView, ViewGroup parent) {  
		// TODO Auto-generated method stub  
		ViewHolder holder = new ViewHolder();  
		if (convertView == null) {  
			convertView = mInflater.inflate(R.layout.orderitemlayout, null);  
			holder.buttonCancel = (Button) convertView  
					.findViewById(R.id.buttonOrderCancel);  
			holder.buttonConfirm = (Button) convertView  
					.findViewById(R.id.buttonOrderConfirm); 
			holder.textViewOrder = (TextView) convertView  
					.findViewById(R.id.textViewOrder);
			convertView.setTag(holder);  
		} else {  
			holder = (ViewHolder) convertView.getTag();  
		}  

		if(mData.get(position).get("order").toString() != null && mData.get(position).get("orderTel").toString() != null)
			holder.textViewOrder.setText((String)mData.get(position).get("order").toString() + "     电话号码：" 
					+ (String)mData.get(position).get("orderTel").toString() + "\n     配送地址："
					+ (String)mData.get(position).get("orderAddr").toString() + "\n\n");


		holder.buttonCancel.setOnClickListener(new View.OnClickListener() {// 添加按钮  

			public void onClick(View v) { 
				if(position != 0)
					MainActivity.mData.remove(position);
				if(mData.isEmpty())
				{
					Map<String, Object> map = new HashMap<String, Object>();         
					map.put("order", new String("Empty"));
					map.put("orderPrice", new Integer(0));
					map.put("orderTel", new String("0"));
					mData.add(map);
				}
				else
					MainActivity.orderCanceled += 1;
				notifyDataSetChanged();  
			}  
		}); 
		holder.buttonConfirm.setOnClickListener(new View.OnClickListener() {// 添加按钮  

			public void onClick(View v) { 
				if(position != 0)
				{
					MainActivity.orderConfirmed += 1;
					MainActivity.incoming += (Integer)mData.get(position).get("orderPrice");
					/*
					try {		              	
						// outgoing stream redirect to socket
						OutputStream out = MainActivity.s.getOutputStream();
						// 注意第二个参数据为true将会自动flush，否则需要需要手动操作out.flush()
						PrintWriter output = new PrintWriter(out, true);
						output.println("Output Command");
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}*/
					
					MainActivity.mData.remove(position);			
					if(mData.isEmpty())
					{
						Map<String, Object> map = new HashMap<String, Object>();         
						map.put("order", new String("Empty"));
						map.put("orderPrice", new Integer(0));
						map.put("orderTel", new String("0"));
						mData.add(map);
					}
					notifyDataSetChanged(); 
				} 
			}  
		});

		return convertView;  
	}  

	public final class ViewHolder {  
		public Button buttonCancel,buttonConfirm;  
		public TextView textViewOrder;  

	}  

}
