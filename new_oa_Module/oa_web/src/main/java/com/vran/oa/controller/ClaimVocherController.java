package com.vran.oa.controller;

import com.vran.oa.bean.DealRecord;
import com.vran.oa.bean.Employee;
import com.vran.oa.biz.ClaimVoucherBiz;
import com.vran.oa.dto.ClaimVoucherInfo;
import com.vran.oa.util.Contant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName ClaimVocherController
 * @Description TODO
 * @Author vrank
 * @Date 2019/9/1 20:52
 * @Version 1.0
 **/

@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVocherController {

    @Autowired
    private ClaimVoucherBiz claimVoucherBiz;

    /*去添加*/
    @RequestMapping("to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("items", Contant.getItems());
        map.put("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    /*添加*/
    /*详情界面*/
    /*查看个人报销单*/
    /*查看个人待处理报销单*/
    /*去修改*/
    /*修改*/
    /*删除*/
    /*提交*/
    /*去处理*/
    /*处理*/


    /*添加*/
    @RequestMapping("/add")
    public String add(HttpSession session, ClaimVoucherInfo info) {
        /*从session中获取employee的编号，在登陆的时候设置过了employee*/
        Employee employee = (Employee) session.getAttribute("employee");
        /*创建者编号即 当前登录的人的编号*/
        info.getClaimVoucher().setCreateSn(employee.getSn());

        claimVoucherBiz.save(info.getClaimVoucher(),info.getItems());
        /*id为报销单的id*/
        return "redirect:deal";
    }

    /*详情界面*/
    @RequestMapping("/detail")
    public String detail(int id,Map<String, Object> map) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));
        return "claim_voucher_detail";
    }

    /*查看个人报销单*/
    @RequestMapping("/self")
    public String self(HttpSession session,Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        /*获取当前登陆用户*/
        map.put("list", claimVoucherBiz.getForSelf(employee.getSn()));
        return "claim_voucher_self";

    }

    /*查看个人待处理报销单*/
    @RequestMapping("/deal")
    public String deal(HttpSession session,Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        /*获取当前登陆用户*/
        map.put("list", claimVoucherBiz.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    /*去修改*/
    @RequestMapping("to_update")
    public String toUpdate(int id, Map<String, Object> map) {
        map.put("items", Contant.getItems());
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherBiz.get(id));
        info.setItems(claimVoucherBiz.getItems(id));


        map.put("info", info);
        return "claim_voucher_update";
    }

    /*修改*/
    @RequestMapping("/update")
    public String update(HttpSession session, ClaimVoucherInfo info) {
        /*从session中获取employee的编号，在登陆的时候设置过了employee*/
        Employee employee = (Employee) session.getAttribute("employee");
        /*创建者编号即 当前登录的人的编号*/
        info.getClaimVoucher().setCreateSn(employee.getSn());

        claimVoucherBiz.update(info.getClaimVoucher(),info.getItems());
        /*id为报销单的id*/
        return "redirect:deal";
    }

    /*删除*/
    @RequestMapping("/delete")
    public String delete(int id) {
        claimVoucherBiz.delete(id);
        return "claim_voucher_self";
    }

    /*提交*/
    @RequestMapping("submit")
    public String submit(int id) {
        claimVoucherBiz.submit(id);
        return "redirect:deal";
    }


    /*去处理*/
    @RequestMapping("to_check")
    public String toCheck(int id, Map<String, Object> map) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecords(id));

        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(id);

        map.put("record", dealRecord);
        return "claim_voucher_check";
    }

    /*处理*/
    @RequestMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord) {
        /*从session中获取employee的编号，在登陆的时候设置过了employee*/
        Employee employee = (Employee) session.getAttribute("employee");

        dealRecord.setDealSn(employee.getSn());
        claimVoucherBiz.deal(dealRecord);

        /*id为报销单的id*/
        return "redirect:deal";
    }

}
