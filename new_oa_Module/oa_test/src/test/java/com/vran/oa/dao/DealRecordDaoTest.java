package com.vran.oa.dao;

import com.vran.oa.BastTest;
import com.vran.oa.bean.DealRecord;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName DealRecordDaoTest
 * @Description TODO
 * @Author vrank
 * @Date 2019/9/1 13:26
 * @Version 1.0
 **/



public class DealRecordDaoTest extends BastTest {

    @Autowired
    private DealRecordDao dealRecordDao;

    @Test
    public void test01() {
        List<DealRecord> list = dealRecordDao.selectByClaimVoucherId(13);
        Assert.assertEquals(6,list.size());
    }

}
