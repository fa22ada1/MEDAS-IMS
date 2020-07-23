package entities;

import java.sql.ResultSet;
import java.util.ArrayList;

import utils.Conn;

public class Produit {
	private int ID;
	private String type;
	private String marque;
	private int PN;
	private int stock;
	private String note;
	
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
			rs.close();
			conn.close();
		} catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public int getID() {
		return ID;
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
	public String getNote() {
		return note;
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
			rs.close();
			conn.close();
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
	
	public static String[] getSNs(int ID) {
		ArrayList<String> SNs = new ArrayList<>();
		try {
			Conn conn = new Conn();
			ResultSet rs;
			String q = "Select SN from Produit where Prodid = " + ID + " and idsortie is null;";
			rs = conn.s.executeQuery(q);
			while(rs.next()) {
				SNs.add(Integer.toString(rs.getInt("SN")));
			}
			rs.close();
			conn.close();
		} catch(Exception e1){
			e1.printStackTrace();
		}
		String[] res = new String[SNs.size()];
		for (int j = 0; j < SNs.size(); j++) { 
	            res[j] = SNs.get(j); 
	    }
		return res;
	}
}
