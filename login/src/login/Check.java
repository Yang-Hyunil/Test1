package login;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Check {
	private Connection conn() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Class.forName("oracle.jdbc.driver.OracleDriver"); //1. 데이터베이스 드라이버를 로딩한다.
		return DriverManager.getConnection(url,"scott","tiger"); //2.연결하여 커넥션 객체를 생성한다.
	}
	
	private void connClose(ResultSet rs,PreparedStatement stmt,Connection con) {
		try { if(rs!=null)	rs.close();  	} catch (Exception e) { }
		try { if(stmt!=null) stmt.close();	} catch (Exception e) { }
		try { if(con!=null) con.close();	} catch (Exception e) { }
	}
	
	CheckType login(String id,String pw){
		//DB에접속후 id,pw 가지고 확인해서
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Connection con=null;
		try {
			con=conn();
			//System.out.println("DB접속 성공");
			stmt= con.prepareStatement("select * from tbl_user where id=?"); //3.생성된 커넥션 개체를 가지고 Statement 객체를 생성한다.
			//4.Statement 객체를 가지고 sql 작업을 진행한다.
			
			//삽입,삭제,갱신시에는 .executeUpate() 리턴이 정수(정수가 의미하는 몇개가 처리되었는지)
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			//rs=stmt.executeQuery("select * from tbl_user where id='" + id + "'");  //sql문(select)실행하기;
			if(rs.next()) { //한행이 있다면(즉, 아이디가 있다면)
				String dbPw=rs.getString("pw"); //db의 결과값중 컬럼이 pw인 값을 읽는다.
				if(pw.equals(dbPw))		
					return CheckType.SUCCESS;			
				else	
					return CheckType.PW_ERROR;
			}else
				return CheckType.ID_ERROR;

		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		} finally {
			connClose(rs,stmt,con);
	
		}
		
		//1~3까지 값을 return주면 된다.
		return CheckType.DB_ERROR;
	}
	
	void test() {
		PreparedStatement stmt=null;
		Connection con=null;
		
		try {
			con=conn();
			stmt= con.prepareStatement("insert into table1 values(?)");
			//Date date = new Date(2022, 8, 21) ;
			stmt.setString(1, "2002-02-10");
			stmt.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace(); //에러 내용 찍기
			System.out.println("설마 에러");
		}finally {
			connClose(null,stmt,con);
		}
		
	}
	
	List<User> list(){
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Connection con=null;
		List<User> list = new ArrayList<>();
		
		try {
			con=conn();
			stmt= con.prepareStatement("select * from tbl_user"); 
			rs=stmt.executeQuery();
			while(rs.next()) {
				User u = new User();
				u.id=rs.getString(1);
				u.pw=rs.getString(2);
				list.add(u);
			}
		}catch(Exception ex) {
			ex.printStackTrace(); //에러 내용 찍기
			System.out.println("설마 에러");
		}finally {
			connClose(rs,stmt,con);
	
		}
		return list;
	}

	void signUp(String id, String pw) {
		

		PreparedStatement stmt=null;
		Connection con=null;
		try {
			con=conn();
			//System.out.println("DB접속 성공");
			stmt= con.prepareStatement("INSERT INTO TBL_USER (ID, PW) VALUES (?, ?)"); //3.생성된 커넥션 개체를 가지고 Statement 객체를 생성한다.
			//4.Statement 객체를 가지고 sql 작업을 진행한다.
			
			//삽입,삭제,갱신시에는 .executeUpate() 리턴이 정수(정수가 의미하는 몇개가 처리되었는지)
			stmt.setString(1,id);
			stmt.setString(2,pw);
			stmt.executeUpdate();  //sql문(select)실행하기;
		
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		} finally {
			connClose(null,stmt,con);
	
		}
		
	}
}













