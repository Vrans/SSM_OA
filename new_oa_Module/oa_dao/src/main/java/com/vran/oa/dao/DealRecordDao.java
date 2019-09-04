package com.vran.oa.dao;

import com.vran.oa.bean.DealRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/*处理者接口*/
public interface DealRecordDao {
    void insert(DealRecord dealRecord);

    /*根据claimVoucherId查询处理表*/
    List<DealRecord> selectByClaimVoucherId(int cvid);

}
