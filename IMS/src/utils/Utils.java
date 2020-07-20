package utils;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class Utils {
	public static Color PC1 = SWTResourceManager.getColor(60, 110, 113);
	public static Color PC2 = SWTResourceManager.getColor(40, 75, 99);
	
	public static void PNCombo(Combo combo) {
		ArrayList<Integer> SNs = new ArrayList<>();
		try {
			Conn conn = new Conn();
			ResultSet rs;
			String q = "Select PN from Prod";
			rs = conn.s.executeQuery(q);
			while(rs.next()) {
				SNs.add(rs.getInt("PN"));
			}
		} catch(Exception E) {
    		E.printStackTrace();
    	}
		String[] SNs1 = new String[SNs.size()];
	    for (int i=0; i < SNs1.length; i++)
	    {
	        SNs1[i] = Integer.toString(SNs.get(i).intValue());
	    }
		combo.setItems(SNs1);
	}
}
