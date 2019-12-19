package com.dragon.scw;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScwUserApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(getClass()); //引入日志文件

	@Autowired
	DataSource dataSource;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;//k,v都是字符串
	
	@Test
	public void test01() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println(connection); //代理对象
		connection.close();
	}
	
	@Test
	public void test02() {
		stringRedisTemplate.opsForValue().set("msg", "哈哈");
		logger.debug("操作成功.....");
	}
	
	

}
