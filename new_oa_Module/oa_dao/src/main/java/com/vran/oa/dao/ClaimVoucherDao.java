package com.vran.oa.dao;

import com.vran.oa.bean.ClaimVoucher;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * 报销单实体
 */
@Repository
public interface ClaimVoucherDao {

    void insert(ClaimVoucher claimVoucher);

    void update(ClaimVoucher claimVoucher);

    void delete(int id);

    ClaimVoucher select(int id);

    /*查看创建者所创建的所有报销单*/
    List<ClaimVoucher> selectByCreateSn(String csn);

    /*查看处理者能够处理的所有报销单*/
    List<ClaimVoucher> selectByDealSn(String ndsn);

}
