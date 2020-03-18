package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.RcpDataBean;
import mybatis.AbstractRepository;

public class MybatisRcpDaoMysql extends AbstractRepository {
	private final String namespace = "mybatis.Rcp";
	private static MybatisRcpDaoMysql instance = new MybatisRcpDaoMysql();

	public static MybatisRcpDaoMysql getInstance() {
		return instance;
	}

	private MybatisRcpDaoMysql() {
	}

	/*public int getArticleCount() {
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			String statement = namespace + ".getArticleCount";
			return sqlSession.selectOne(statement);
		} finally {
			sqlSession.close();
		}
	}*/

	/*
	 * public int getMyArticleCount(int memNum) { SqlSession sqlSession =
	 * getSqlSessionFactory().openSession(); try {
	 * 
	 * String statement = namespace + ".getMyArticleCount"; return
	 * sqlSession.selectOne(statement, memNum); } finally { sqlSession.close(); } }
	 */

	/*public List<RcpDataBean> getArticles(int startRow, int endRow) {
		// List<RcpDataBean> articleList = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();

		startRow = startRow - 1;
		endRow = endRow - startRow;

		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);

		try {
			String statement = namespace + ".getArticles";
			return sqlSession.selectList(statement, map); // 리턴타입 리스트
		} finally {
			sqlSession.close();*/

	
	
			/*
			 * } try {
			 * 
			 * String statement = namespace + ".getArticles";
			 * sqlSession.selectList(statement, map);
			 * 
			 * } finally { sqlSession.close(); } return articleList;
			 */
	
	
	
		/*}
	}*/

	/*public RcpDataBean getArticle(int RcpNum) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();
		RcpDataBean article = null;

		try {
			
			 * String statement = namespace + ".readcount"; sqlSession.update(statement,
			 * RcpNum); sqlSession.commit();
			 

			article = sqlSession.selectOne(namespace + ".getArticle", RcpNum);

		} finally {
			sqlSession.close();
		}
		return article;
	}*/

	public void insertArticle(RcpDataBean article) {

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		//int rcpNum = article.getRcpNum(); //값을 받아올 필요가 없으니 사용하지 않아야한다. (nullPointerException)
		int rcpNum;

		try {
			String statement = namespace + ".insert_max";
			rcpNum = sqlSession.selectOne(statement);

			article.setRcpNum(rcpNum); // 필요한가요? -> 필요합니다(rcpNum max값을 여기에 저장)
			String statement2 = namespace + ".insert";
			
			sqlSession.insert(statement2, article);
			System.out.println("111111111");
			sqlSession.commit();

		} finally {
			sqlSession.close();
		}
	}

	/*public RcpDataBean getUpdateArticle(int rcpNum) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		RcpDataBean article = null;

		try {
			article = sqlSession.selectOne(namespace + ".getArticle", rcpNum);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
		return article;
	}*/

	/*public int updateBoard(RcpDataBean article) {
		
		 * SqlSession sqlSession = getSqlSessionFactory().openSession(); String dbpasswd
		 * = ""; int x = -1;
		 * 
		 * try { dbpasswd = sqlSession.selectOne(namespace + "getPasswd",
		 * article.getNum()); if (dbpasswd.equals(article.getPasswd())) {
		 * sqlSession.update(namespace + ".updateArticle", article);
		 * sqlSession.commit(); x = 1; } else { x = 0; } } catch (Exception ex) {
		 * ex.printStackTrace(); } finally { sqlSession.close(); } return x;
		 
		return 0;
	}*/

	/*public int deleteArticle(int rcpNum, String passwd) {

		
		 * SqlSession sqlSession = getSqlSessionFactory().openSession(); String dbpasswd
		 * = null; int x = -1;
		 * 
		 * try {
		 * 
		 * dbpasswd = sqlSession.selectOne(namespace + ".getPasswd", num);
		 * 
		 * if (dbpasswd.equals(passwd)) { sqlSession.delete(namespace + "delete", num);
		 * sqlSession.commit(); x = 1; } else { x = 0; } } catch (Exception ex) {
		 * ex.printStackTrace(); } finally { } return x;
		 
		return rcpNum;
	}*/
}
