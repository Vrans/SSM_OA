package com.vran.oa.biz.impl;

import com.vran.oa.bean.ClaimVoucher;
import com.vran.oa.bean.ClaimVoucherItem;
import com.vran.oa.bean.DealRecord;
import com.vran.oa.bean.Employee;
import com.vran.oa.biz.ClaimVoucherBiz;
import com.vran.oa.dao.ClaimVoucherDao;
import com.vran.oa.dao.ClaimVoucherItemDao;
import com.vran.oa.dao.DealRecordDao;
import com.vran.oa.dao.EmployeeDao;
import com.vran.oa.util.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ClaimVoucherBizImpl
 * @Description TODO
 * @Author vrank
 * @Date 2019/8/31 1:26
 * @Version 1.0
 **/

@Service("clamVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
    @Autowired
    private ClaimVoucherDao claimVoucherDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Autowired
    private DealRecordDao dealRecordDao;

    @Override
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        //先加入创建时间
        claimVoucher.setCreateTime(new Date());
        //设置报销单处理人为自己
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        //设置状态
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_CREATED);
        /*插入报销单*/
        claimVoucherDao.insert(claimVoucher);

        //插入报销单详情
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(claimVoucher.getId());
            //插入详情
            claimVoucherItemDao.insert(item);
        }
    }


    /*查看报销单*/
    @Override
    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    @Override
    public List<ClaimVoucherItem> getItems(int cvid) {
        return claimVoucherItemDao.selectByClaimVoucher(cvid);
    }

    /*查看处理流程*/
    @Override
    public List<DealRecord> getRecords(int cvid) {
        return dealRecordDao.selectByClaimVoucherId(cvid);
    }

    //查看个人报销单----根据报销单表的create_sn查看
    @Override
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherDao.selectByCreateSn(sn);
    }

    //查看待处理报销单
    @Override
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherDao.selectByDealSn(sn);
    }

    //更新报销单
    @Override
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        /*设置待处理人为本人*/
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        /*设置状态*/
        claimVoucher.setStatus(Contant.DEAL_UPDATE);
        /*更新状态*/
        claimVoucherDao.update(claimVoucher);
        /*获取原先有的 报销单item*/
        List<ClaimVoucherItem> olds = claimVoucherItemDao.selectByClaimVoucher(claimVoucher.getId());
        boolean isHave = false;
        //遍历原报销单item
        for (ClaimVoucherItem old : olds) {
            //拿到现有报销单item
            for (ClaimVoucherItem item : items) {
                //如果现在的报销单的id等于原报销单  那就代表有
                if (item.getId() == old.getId()) {
                    isHave = true;
                }
            }
            //如果没有,将原来的删除
            if (!isHave) {
                claimVoucherItemDao.delete(claimVoucher.getId());
            }
        }
        //遍历现有报销单  更新
        for (ClaimVoucherItem item : items) {
            //设置报销单id 防止空指针
            item.setClaimVoucherId(claimVoucher.getId());
            //原先是否存在
            if (item.getId() != null && item.getId() > 0) {
                claimVoucherItemDao.update(item);
            } else {
                claimVoucherItemDao.insert(item);
            }
        }
    }

    @Override
    public void submit(int id) {
        //提交需要获取到提交的哪一条clamVoucher
        ClaimVoucher claimVoucher = claimVoucherDao.select(id);
        //拿到创建人
        Employee employee = claimVoucher.getCreater();

        //修改状态
        claimVoucher.setStatus(Contant.CLAIMVOUCHER_SUBMIT);

        //拿到部门id
        String dsn = employee.getDepartmentSn();
        //拿到部门经理
        String postFm = Contant.POST_FM;
        //拿到用户对象 属于是同的部门id  并且职位是部门经理的
        List<Employee> list = employeeDao.selectDepartmentAndPost(dsn, postFm);
        //因为只有一条数据
        String sn = list.get(0).getSn();
        //设置下一个处理人
        claimVoucher.setNextDealSn(sn);


        //更新报销单 ---因为需要后台设置的值就这几个：修改状态为提交，修改下一个处理人为部门经理，修改时间为当前时间，修改
        claimVoucherDao.update(claimVoucher);

        //待处理表需要设置 提供一个新的待处理表
        DealRecord dealRecord = new DealRecord();
        //设置提交的人为 当前员工
        dealRecord.setDealSn(employee.getSn());
        //设置处理方式为已提交
        dealRecord.setDealWay(Contant.DEAL_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setDealResult(Contant.CLAIMVOUCHER_SUBMIT);
        //提交的时候备注 不需要什么东西 默认==无
        dealRecord.setComment("无");
        dealRecordDao.insert(dealRecord);

    }
    @Override
    public void delete(int id) {
        claimVoucherItemDao.delete(id);
        //claimVoucherDao.delete(id);
    }

    @Override
    public void deal(DealRecord dealRecord) {
        //拿到报销单
        ClaimVoucher claimVoucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        //当前处理人
        Employee employee = employeeDao.select(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());

        //判断是否需要复审
        switch (dealRecord.getDealWay()) {
            case Contant.DEAL_PAST:

                if (claimVoucher.getTotalAmount() <= Contant.LIMT_CHECK || employee.getPost().equals(Contant.POST_GM)) {
                    /*不需要重复审核*/

                    //报销单状态
                    claimVoucher.setCause(Contant.CLAIMVOUCHER_APPROVED);
                    //设置待处理人为财务
                    claimVoucher.setNextDealSn(employeeDao.selectDepartmentAndPost(null, Contant.POST_CASHIER).get(0).getSn());

                    //报销单处理结果
                    dealRecord.setDealResult(Contant.CLAIMVOUCHER_APPROVED);
                } else {
                    /*复审情况*/

                    //需要复审情况
                    claimVoucher.setCause(Contant.CLAIMVOUCHER_RECHECK);
                    //设置待处理人为总经理
                    claimVoucher.setNextDealSn(employeeDao.selectDepartmentAndPost(null, Contant.POST_GM).get(0).getSn());

                    //处理结果
                    dealRecord.setDealResult(Contant.CLAIMVOUCHER_RECHECK);
                }
                break;
            case Contant.DEAL_BACK:
                /*打回情况*/

                //需要审核情况
                claimVoucher.setCause(Contant.CLAIMVOUCHER_BACK);
                //设置待处理人为本人
                claimVoucher.setNextDealSn(claimVoucher.getCreateSn());

                //处理结果
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_BACK);
                break;
            case Contant.DEAL_REJECT:
                /*拒绝情况*/

                //需要审核情况
                claimVoucher.setCause(Contant.CLAIMVOUCHER_TERMINATED);
                //设置待处理人为本人
                claimVoucher.setNextDealSn(null);

                //处理结果
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_TERMINATED);
                break;
            case Contant.DEAL_PAID:
                /*打款情况*/


                //设置状态
                claimVoucher.setCause(Contant.CLAIMVOUCHER_PAID);
                //设置待处理人为本人
                claimVoucher.setNextDealSn(null);

                //处理结果
                dealRecord.setDealResult(Contant.CLAIMVOUCHER_PAID);
                break;
        }


        //更新报销单
        claimVoucherDao.update(claimVoucher);
        //插入处理报销单
        dealRecordDao.insert(dealRecord);
    }
}
