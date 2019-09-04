package com.vran.oa;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ClassName BastTest
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/30 16:13
 * @Version 1.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
/*告诉文件位置*/
@ContextConfiguration({"classpath:spring-dao.xml","classpath:spring-biz.xml"})
public class BastTest {

}
