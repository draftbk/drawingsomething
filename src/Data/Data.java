package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Data{
	Connection con;
	public 	Data(){
		System.out.println("01");
		 String url = "jdbc:mysql://localhost:3306/drawingsomething?"
	                + "user=root&password=butanfengyue123&useUnicode=true&characterEncoding=UTF8";
		try{
			System.out.println("02");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("03");
			System.out.println("0");
			con =DriverManager.getConnection(url);
			System.out.println("1");
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace(System.out);
		}
	}
	public ResultSet getData(){
		Statement st;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			String sql = "SELECT * FROM info";
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	public void addData(String word,String character){
		try{
		PreparedStatement pstm = con.prepareStatement("INSERT INTO info(name,tip) VALUES(?,?)");
		pstm.setString(1,word);
		pstm.setString(2,character);
		pstm.executeUpdate();
	    System.out.println("��ӳɹ�");
	    }
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deletData(String str){
		try{
			System.out.println(str);
		PreparedStatement pstm = con.prepareStatement("DELETE FROM info WHERE name='"+str+"'");
	    pstm.executeUpdate();
	    System.out.println("ɾ��"+str+"���");
		pstm.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void DataClose(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





