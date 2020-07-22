package entities;

import java.sql.ResultSet;

import utils.Conn;

public class Utilisateur {
	int ID;
	String name;
	String title;
	int rang;
	
	public Utilisateur(int ID) {
		this.ID = ID;
		try {
			Conn conn = new Conn();
			ResultSet rs;
			String q = "Select * from Utilisateurs where ID = " + ID + ";";
			rs = conn.s.executeQuery(q);
			rs.next();
			
			name = rs.getString("nom");
			title = rs.getString("titre");
			rang = rs.getInt("rang");
			
			rs.close();
			conn.close();
		} catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public String Tag() {
		return name + " - " + title;
	}
	
	public static String TagfromID(int ID) {
		Utilisateur agent = new Utilisateur(ID);
		return agent.Tag();
	}
	
	public static int getID(String name, String PW) {
		try {
			Conn conn = new Conn();
			ResultSet rs;
			String q = "Select ID from Utilisateurs where nom ='" + name + "' and MP = UNHEX(SHA1('" + PW + "'));";
			rs = conn.s.executeQuery(q);
			if(rs.next()) {
				return rs.getInt("ID");
			}
			rs.close();
			conn.close();
		} catch(Exception e1){
			e1.printStackTrace();
		}
		return -1;
	}
	
	public int getID() {
		return ID;
	}
}
