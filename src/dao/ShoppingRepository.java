package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import model.RcpDataBean;
import mybatis.AbstractRepository;


public class ShoppingRepository extends AbstractRepository{
	private final String namespace = "mybatis.dao.ShoppingMapper";
	private static ShoppingRepository instance = new ShoppingRepository();
	
	public static ShoppingRepository getInstance(){
		return instance;
	}
	
	public RcpDataBean getIngredient(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getIngredient";
			return (RcpDataBean)sqlSession.selectOne(statement);
		}finally{
			sqlSession.close();
		}
	}
	
	
}
