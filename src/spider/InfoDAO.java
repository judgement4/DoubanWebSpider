package spider;

import com.mysql.jdbc.*;
import java.sql.DriverManager;

import java.sql.SQLException;


public class InfoDAO implements Dao{
	private static Connection get(){
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/info";
		String user="admin";
		String psw="admin";
		Connection conn=null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn=(Connection)DriverManager.getConnection(url, user, psw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public void save(InfoModel model){

		int i=0;
		
		Connection conn=get();
		String sql="insert into info3 (sns, updateTime, source, ifshared) values(?,?,?,?)";
		PreparedStatement ps;
		try{
			ps=(PreparedStatement)conn.prepareStatement(sql);
			System.out.println("see");
			ps.setString(1, model.getSns());
			ps.setString(2, model.getUpdateTime());
			ps.setString(3, model.getSource());
			ps.setString(4, model.getIfshared());
			i=ps.executeUpdate();
			ps.close();
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		

		
/*		Session s = HibernateSessionFactory.getSession();
		Transaction ts =(Transaction) s.beginTransaction();
		s.save(model);
		try {
			ts.commit();
		} catch (Exception e) {
		}
		s.close();*/
		
	}

}
