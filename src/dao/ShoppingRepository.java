package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import model.Cart;
import model.RcpDataBean;
import model.Sale;
import mybatis.AbstractRepository;


public class ShoppingRepository extends AbstractRepository{
	private final String namespace = "mybatis.dao.ShoppingMapper";
	private static ShoppingRepository instance = new ShoppingRepository();
	
	public static ShoppingRepository getInstance(){
		return instance;
	}
	
	public RcpDataBean getIngredient(int rcpNum){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getIngredient";
			return (RcpDataBean)sqlSession.selectOne(statement, rcpNum);
		}finally{
			sqlSession.close();
		}
	}
	
	public int insertCart(Cart cart){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		int cartnum = 0;
		try{
			cartnum = sqlSession.selectOne(namespace+".insert_maxCart");
			cart.setCartNum(cartnum);
			System.out.println(cart);
			String statement = namespace + ".insertCart";
			int result = sqlSession.insert(statement, cart);
			if(result > 0){
				sqlSession.commit();
				System.out.println("commit");
			}else{
				sqlSession.rollback();
				System.out.println("rollback");
			}
			return result;
		}finally{
			sqlSession.close();
		}
	}

	public List<Cart> getCart(int memNum) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getCart";
			return sqlSession.selectList(statement, memNum);
		}finally{
			sqlSession.close();
		}
	}
	
	public int deleteCartvalue(int cartNum) throws Exception{
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".deleteCartvalue";
			sqlSession.selectOne(statement, cartNum);
			sqlSession.commit();
			return 1;
		}finally{
			sqlSession.close();
		}
		
	}
	public int registjjim(int cartNum){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		List<Cart> cart = null;
		
		try{
			cart = sqlSession.selectList(namespace+".getCartintoJjim");
			//가져온 카트를 찜으로 등록하는 레퍼지토리 작성
			String statement = namespace + ".insertjjim";
			int result = sqlSession.insert(statement, cart);
			if(result > 0){
				sqlSession.commit();
				System.out.println("commit");
			}else{
				sqlSession.rollback();
				System.out.println("rollback");
			}
			return result;
		}finally{
			sqlSession.close();
		}
	}
	
	public Sale getSale(int rcpNum) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		try{
			String statement = namespace + ".getSale";
			return (Sale)sqlSession.selectOne(statement, rcpNum);
		}finally{
			sqlSession.close();
		}
	}
	
}
