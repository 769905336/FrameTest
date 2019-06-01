package mybatis.mapper;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import mybatis.pojo.User;
import mybatis.pojo.UserCustomer;
import mybatis.pojo.UserQueryVo;

public class UserMapperTest {
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void init() throws Exception {
		//配置文件
		String resource = "SqlMapConfig.xml";
		
		//加载配置文件到输入流
		InputStream is = Resources.getResourceAsStream(resource);
		
		//创建会话工厂
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void testFindUserById() throws Exception {
		//创建SqlSession
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//创建代理对象
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		User user = userMapper.findUserById(24);
		System.out.println(user);
	}

	@Test
	public void testFindUserByName() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		List<User> list = userMapper.findUserByName("%小明%");
		System.out.println(list);
	}
	
	@Test
	public void testFindUserList() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//构造查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustomer userCustomer = new UserCustomer();
		userCustomer.setUsername("小明");
		userCustomer.setSex("1");
		userQueryVo.setUserCustomer(userCustomer);
		
		//id集合
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(16);
		ids.add(22);
		userQueryVo.setIds(ids);
		List<User> list = userMapper.findUserList(userQueryVo);
		sqlSession.close();
		System.out.println(list);
	}
	
	@Test
	public void testFindUserListResultMap() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//构造查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustomer userCustomer = new UserCustomer();
		userCustomer.setUsername("小明");
		userQueryVo.setUserCustomer(userCustomer);
		
		List<User> list = userMapper.findUserListResultMap(userQueryVo);
		sqlSession.close();
		System.out.println(list);
	}
	
	@Test
	public void testFindUserCount() throws Exception {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		
		//构造查询条件
		UserQueryVo userQueryVo = new UserQueryVo();
		UserCustomer userCustomer = new UserCustomer();
		userCustomer.setUsername("小明");
		userQueryVo.setUserCustomer(userCustomer);
		
		int count = userMapper.findUserCount(userQueryVo);
		sqlSession.close();
		System.out.println(count);
	}

	@Test
	public void testInsertUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("乔丹");
		user.setBirthday(new Date());
		user.setSex("1");
		user.setAddress("美国");
		
		userMapper.insertUser(user);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testDeleteUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		userMapper.deleteUser(27);
		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testUpdateUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User user = new User();
		user.setId(28);
		user.setUsername("UU妹");
		user.setBirthday(new Date());
		user.setSex("2");
		user.setAddress("中国");
		
		userMapper.updateUser(user);
		sqlSession.commit();
		sqlSession.close();
	}

}
