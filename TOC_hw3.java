/*
 * THEORY OF COMPUTATION - HW3
 * 資工系104乙班
 * 鄭文
 * F74004012
*/

import java.net.*;
import java.io.*;
import org.json.*;


public class Hw3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 4)
		{
			System.out.println("格式錯誤");
			return;
		}
		String myUrl=args[0];
		String Area=args[1];
		String Road=args[2];
		int Date=Integer.parseInt(args[3]);
		
		//System.out.println(Url);
		//System.out.println(Area);
		//System.out.println(Road);
		//System.out.println(Date);
		int TotalData=0;
		long TotalValue=0;
		try{
		URL MyURLData = new URL(myUrl);
		Reader MyURLReader = new BufferedReader(new InputStreamReader(MyURLData.openStream(),"UTF-8"));	
		//File MyFile = new File("C:/Users/Aquile/Desktop/jsonfile.json");
		//Reader MyReader = new BufferedReader(new InputStreamReader(new FileInputStream(MyFile),"UTF-8"));
		JSONTokener MyJson = new JSONTokener(MyURLReader);
		JSONArray MyJsonArray = new JSONArray(MyJson);
		JSONObject MyJsonObject;
		for (int i = 0; i < MyJsonArray.length(); i++)
		{
			//System.out.println("Doing for loop...");
			MyJsonObject = MyJsonArray.getJSONObject(i);
			if (!MyJsonObject.getString("鄉鎮市區").equals(Area)) continue;
			if(Date<1000){Date=Date*100;}
			if (MyJsonObject.getString("土地區段位置或建物區門牌").contains(Road) && MyJsonObject.getInt("交易年月") > (Date))
			{
				//System.out.println(MyJsonObject.getString("鄉鎮市區") + "\t" + MyJsonObject.getString("土地區段位置或建物區門牌") + "\t" + MyJsonObject.getInt("交易年月") + "\t" + MyJsonObject.getInt("總價元"));
				TotalData++;
				TotalValue += MyJsonObject.getInt("總價元");
			}
		}
		System.out.println(TotalValue/TotalData);
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
