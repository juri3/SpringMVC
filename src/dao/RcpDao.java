package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.RcpDataBean;

public class RcpDao {

	private static RcpDao instance = new RcpDao();

	public static RcpDao getInstance() {
		return instance;
	}
	private RcpDao() {}

	private Connection getConnection() throws Exception {
		Connection conn = null;
		String jdbcUrl = "jdbc:mysql://localhost:3306/spring";
		String dbId = "scott";
		String dbPass = "1111";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		return conn;
	}

	/*
	 * Map map = new HashMap<>();
	 * 
	 * public void insertArticle(RcpDataBean article){ SqlSession
	 * sqlSession=getSqlSessionFactory().openSession();
	 * 
	 * int rcpNum = article.getRcpNum();
	 * 
	 * int number=1;
	 * 
	 * try{ number=sqlSession.selectOne(namespace+".insert_max");
	 * 
	 * if(rcpNum!=0){ String statement=namespace+".insert_update";
	 * sqlSession.update(statement, map); sqlSession.commit(); }
	 * 
	 * article.setNum(number); article.setRef(ref);
	 * article.setRe_level(re_level); article.setRe_step(re_step);
	 * 
	 * String statement=namespace+".insert"; sqlSession.insert(statement,
	 * article); sqlSession.commit(); }finally{ sqlSession.close(); } }
	 */

	public void insertArticle(RcpDataBean article) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rcpNum = 0;
		
		try {
			conn = getConnection();
			String sql = "select ifnull(max(rcpNum),0)+1 from Rcp";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rcpNum = rs.getInt(1);
			}
			System.out.println(rcpNum);
			System.out.println(article.toString());
			
			sql = "insert into Rcp values(?,?,?,?,?,?,?,now(),?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rcpNum);
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getFoodName());
			pstmt.setString(4, article.getSubtitle());
			pstmt.setString(5, article.getIngredient());
			pstmt.setString(6, article.getCookingTime());
			pstmt.setInt(7, article.getMemNum());
			pstmt.setString(8, article.getThumbNail());
			pstmt.setString(9, article.getHashTag());
			/*pstmt.setInt(10, article.getStep());
			pstmt.setInt(11, article.getFileSize());
			pstmt.setString(12, article.getFileName());
			pstmt.setString(13, article.getContent());*/
			int num = pstmt.executeUpdate();
			System.out.println("dao+num: " + num);
			System.out.println(rcpNum);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}
		}
	}

}
