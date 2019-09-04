package com.vran.oa.dao;

import com.vran.oa.bean.ClaimVoucherItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报销单条目实体
 */
@Repository
public interface ClaimVoucherItemDao {
    void insert(ClaimVoucherItem claimVoucherItem);

    void update(ClaimVoucherItem claimVoucherItem);

    void delete(int id);

    List<ClaimVoucherItem> selectByClaimVoucher(int cvid);


}
