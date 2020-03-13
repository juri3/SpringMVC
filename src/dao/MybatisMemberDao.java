package dao;
import java.util.List;



import org.apache.ibatis.session.SqlSession;

import model.Member;
import mybatis.AbstractRepository;

public class MybatisMemberDao extends AbstractRepository
{
    private final String namespace = "mybatis.Member";

    private static MybatisMemberDao instance = new MybatisMemberDao();
    
    public static MybatisMemberDao getInstance()
    {
        return instance;
    }
    private MybatisMemberDao(){}

    public Member selectById(String email) 
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        try
        {
            String statement = namespace + ".selectById";
            return sqlSession.selectOne(statement, email);
        }
        finally
        {
            sqlSession.close();
        }
    }
    
    public void insert(Member mem) 
    {
        SqlSession sqlSession = getSqlSessionFactory().openSession();
        int number;
        
        try
        {
        	number=sqlSession.selectOne(namespace+".insert_max");	
        	mem.setMemNum(number);
        	
            String statement = namespace + ".insert";
            sqlSession.insert(statement, mem);
            sqlSession.commit();
        }
        finally
        {
            sqlSession.close();
        }
    }
    
    public int getProfileCount(){
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		int count;
		
		try{
			String statement=namespace+".getProfileCount"; 
			count=sqlSession.selectOne(statement);
		}finally{
			sqlSession.close();
		}

		return count;
	}
	
	public List<Member> getProfile(){
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		List<Member> profileList=null;
		
		try{
			String statement=namespace+".getProfile";         
			profileList=sqlSession.selectList(statement);
		}finally{
			sqlSession.close();
		}

		return profileList;
	}
	
	public Member getMember(String memNum){
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		Member member=null;
		
		try{
			String statement=namespace+".getMember";         
			member=sqlSession.selectOne(statement, memNum);
		}finally{
			sqlSession.close();
		}

		return member;
	}
	
	public int updateMember(Member member) throws Exception{
		SqlSession sqlSession=getSqlSessionFactory().openSession();
		String dbpasswd="";
		int x=-1;
		
		try{
			dbpasswd=sqlSession.selectOne(namespace+".getPasswd", member.getMemNum());
			
			if(dbpasswd.equals(member.getPasswd())){
				sqlSession.update(namespace+".updateMember", member);
				sqlSession.commit();
				x=1;
			}else{
				x=0;
			}		
		}finally{
			sqlSession.close();
		}
		return x;
	}
}
