package entities;

import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Conn;

public class Produit {
	int ID;
	String type;
	String marque;
	int PN;
	int stock;
	String note;
	
	public Produit(int ID){
		this.ID = ID;
		try {
			Conn conn = new Conn();
			ResultSet rs;
			String q = "Select * from Prod where ID =" + ID;
			rs = conn.s.executeQuery(q);
			rs.next();
			type = rs.getString("prodnom");
			marque = rs.getString("marque");
			PN = rs.getInt("pn");
			stock = rs.getInt("stock");
			note = rs.getString("note");
		} catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public String getType() {
		return type;
	}
	public String getMarque() {
		return marque;
	}
	public int getPN() {
		return PN;
	}
	public int getStock() {
		return stock;
	}

	public static int[] allIDs() {
		ArrayList<Integer> IDs1 = new ArrayList<>();
		try {
			Conn conn = new Conn();
			ResultSet rs;
			String q = "Select ID from Prod";
			rs = conn.s.executeQuery(q);
			while(rs.next()) {
				IDs1.add(rs.getInt("ID"));
			}
		} catch(Exception e1){
			e1.printStackTrace();
		}
		int[] IDs = new int[IDs1.size()];
	    for (int i=0; i < IDs.length; i++)
	    {
	        IDs[i] = IDs1.get(i).intValue();
	    }
		return IDs;
	}
	
	public static int PNfromID(int ID) {
		Produit P = new Produit(ID);
		return P.getPN();
	}
}
