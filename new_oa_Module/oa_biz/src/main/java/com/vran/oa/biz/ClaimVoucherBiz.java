package com.vran.oa.biz;

import com.vran.oa.bean.ClaimVoucher;
import com.vran.oa.bean.ClaimVoucherItem;
import com.vran.oa.bean.DealRecord;

import java.util.List;


public interface ClaimVoucherBiz {

    /*保存报销单*/
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /*查看报销单*/
    ClaimVoucher get(int id);

    /*查看报销单所有条目*/
    List<ClaimVoucherItem> getItems(int cvid);

    /*查看处理*/
    List<DealRecord> getRecords(int cvid);

    /*查看个人报销单*/
    List<ClaimVoucher> getForSelf(String sn);

    /*获取个人待处理报销单*/
    List<ClaimVoucher> getForDeal(String sn);

    /*修改条目*/
    void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    /*提供一个报销单Id就会执行这个提交方法 */
    void submit(int id);

    /*删除个人报销单*/
    void delete(int id);

    /*  审核/打款  */
    void deal(DealRecord dealRecord);
}
