package com.wzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class MyBaseAdapter extends BaseAdapter
{
	private LayoutInflater mInflater;  
	private List<Map<String, Object>> mData;   
	private ListView mListView;
	int totalPrice = 0;

	public MyBaseAdapter(Context context) {  
		this.mInflater = LayoutInflater.from(context);
		init();
	}  
	
	public int getPrice(){
		return totalPrice;
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

	public View getView(final int position, View convertView, ViewGroup parent) {  
		// TODO Auto-generated method stub  
		ViewHolder holder = new ViewHolder();  
		if (convertView == null) {  
			convertView = mInflater.inflate(R.layout.itemlayout, null);  
			holder.button1 = (Button) convertView  
					.findViewById(R.id.button1);  
			holder.button2 = (Button) convertView  
					.findViewById(R.id.button2); 
			holder.textViewNum = (TextView) convertView  
					.findViewById(R.id.textViewNum);
			holder.textViewDes = (TextView) convertView  
					.findViewById(R.id.textViewDes);
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageViewGood);
			convertView.setTag(holder);  
		} else {  
			holder = (ViewHolder) convertView.getTag();  
		}  

		holder.imageView.setImageResource((Integer) mData.get(position).get("goodPic"));
		holder.textViewDes.setText((String)mData.get(position).get("goodName"));
		holder.textViewNum.setText((String)mData.get(position).get("goodNumber").toString());


		holder.button1.setOnClickListener(new View.OnClickListener() {// ��Ӱ�ť  

			public void onClick(View v) { 
				Integer temp = (Integer) mData.get(position).get("goodNumber");
				temp --;
				if(temp < 0) 
					temp = 0;
				mData.get(position).put("goodNumber", temp);
				totalPrice = 0;
				for(int i=0;i<mData.size();i++)
					totalPrice += ((Integer)mData.get(i).get("goodPrice") * (Integer)mData.get(i).get("goodNumber"));
				View tempView = (View) v.getParent().getParent().getParent();
				TextView tempTextView = (TextView) tempView.findViewById(R.id.textTip);
				tempTextView.setText("�����ܼۣ�\t\t"+ String.valueOf(totalPrice));
				notifyDataSetChanged();  
			}  
		}); 
		holder.button2.setOnClickListener(new View.OnClickListener() {// ��Ӱ�ť  

			public void onClick(View v) { 
				Integer temp = (Integer) mData.get(position).get("goodNumber");
				temp ++;
				mData.get(position).put("goodNumber", temp);
				totalPrice = 0;
				for(int i=0;i<mData.size();i++)
					totalPrice += ((Integer)mData.get(i).get("goodPrice") * (Integer)mData.get(i).get("goodNumber"));
				View tempView = (View) v.getParent().getParent().getParent();
				TextView tempTextView = (TextView) tempView.findViewById(R.id.textTip);
				tempTextView.setText("�����ܼۣ�\t\t"+ String.valueOf(totalPrice));
				notifyDataSetChanged();  
			}  
		});
		return convertView;  
	}  

	public final class ViewHolder {  
		public Button button1,button2;  
		public TextView textViewDes,textViewNum;  
		public ImageView imageView;

	}  
	
	public String getOrderStr(){
		String str = new String();
		for(int i=0;i<mData.size();i++)
			if((Integer)mData.get(i).get("goodNumber") != 0)
			{
				str += (String)mData.get(i).get("goodName") + "  x" + mData.get(i).get("goodNumber").toString() + "\n";
				Integer temp = 0;
				mData.get(i).put("goodNumber", temp);
			}
		str += "     �ϼƣ�  ��" + String.valueOf(totalPrice) +"\n";
		notifyDataSetChanged();
		return str;
	}

	private void init() {              
		mData=new ArrayList<Map<String, Object>>();  
		Map<String, Object> map = new HashMap<String, Object>();         
		map.put("goodName", "�°¶������鿾������\n��47");   
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(47));
		map.put("goodPic", R.drawable.p011);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "��½˫����\n��55"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(55));
		map.put("goodPic", R.drawable.p012);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "�������㿾��������\n��61");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(61));
		map.put("goodPic", R.drawable.p013);
		mData.add(map);  
		map = new HashMap<String, Object>();         
		map.put("goodName", "��༦������\n��39"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(39));
		map.put("goodPic", R.drawable.p014);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "�����������\n��39");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(39));
		map.put("goodPic", R.drawable.p015);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "�������𣨴���\n��55"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(55));
		map.put("goodPic", R.drawable.p016);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "��轾�������\n��9");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(9));
		map.put("goodPic", R.drawable.p021);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "�°¶������鿾����\n��12"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(12));
		map.put("goodPic", R.drawable.p022);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "֥ʿ�ƽ���\n��9");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(9));
		map.put("goodPic", R.drawable.p023);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "���ּ��׻�\n��9"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(9));
		map.put("goodPic", R.drawable.p024);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "��������\n��11");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(11));
		map.put("goodPic", R.drawable.p025);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "��ݷ�βϺ\n��11"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(11));
		map.put("goodPic", R.drawable.p026);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "��Ũ������\n��5");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(5));
		map.put("goodPic", R.drawable.p031);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "��������\n��4"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(4));
		map.put("goodPic", R.drawable.p032);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "���¿���\n��10");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(10));
		map.put("goodPic", R.drawable.p033);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "â�����֭\n��8"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(8));
		map.put("goodPic", R.drawable.p034);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "Ҭ��â��������ɳ\n��14");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(14));
		map.put("goodPic", R.drawable.p035);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "Ħ�����ȱ�ɳ\n��14"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(14));
		map.put("goodPic", R.drawable.p036);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "��������\n��7");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(7));
		map.put("goodPic", R.drawable.p041);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "��������\n��6"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(6));
		map.put("goodPic", R.drawable.p042);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "�������\n��5");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(5));
		map.put("goodPic", R.drawable.p043);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "���ȱ�ɳ\n��8"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(8));
		map.put("goodPic", R.drawable.p044);
		mData.add(map);           
		map = new HashMap<String, Object>();         
		map.put("goodName", "â�����\n��4");
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(4));
		map.put("goodPic", R.drawable.p045);
		mData.add(map);
		map = new HashMap<String, Object>();         
		map.put("goodName", "���̲�\n��5"); 
		map.put("goodNumber", new Integer(0));
		map.put("goodPrice", new Integer(5));
		map.put("goodPic", R.drawable.p046);
		mData.add(map);      
		
	}
}
