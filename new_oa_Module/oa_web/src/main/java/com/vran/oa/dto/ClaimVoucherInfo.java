package com.vran.oa.dto;

import com.vran.oa.bean.ClaimVoucher;
import com.vran.oa.bean.ClaimVoucherItem;

import java.util.List;

/**数据代理类
 *
 * @ClassName ClaimVoucher
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/17 15:56
 * @Version 1.0
 **/
public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;

    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }
}
