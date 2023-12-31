package com.zhu.casemanage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.dao.TDSchemeDao;
import com.zhu.casemanage.dao.TrackDao;
import com.zhu.casemanage.dao.UserDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.TDSchemePojo;
import com.zhu.casemanage.pojo.TrackPojo;
import com.zhu.casemanage.pojo.UserPojo;
import com.zhu.casemanage.service.CaseServiceImpl;
import com.zhu.casemanage.service.SchemeServiceImpl;
import com.zhu.casemanage.service.TrackServiceImpl;
import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.JwtUtil;
import com.zhu.casemanage.utils.RedisUtil;
import com.zhu.casemanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/platform/caseStatus")
public class CaseStatusController {

    @Autowired
    private TrackServiceImpl trackService;
    @Autowired
    private CaseServiceImpl caseService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SchemeServiceImpl schemeService;
    @Autowired
    private CaseDao caseDao;
    @Autowired
    private TDSchemeDao tdSchemeDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TrackDao trackDao;

    /*
     * 根据病例号获取该患者的所有病例状态（变化记录，数组）
     * */
    @RequestMapping(value = "/tracking/{caseNumber}",method = RequestMethod.GET)
    public Result getTrackingByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        List<TrackPojo> caseTrackList = trackService.getCaseTrackList(caseNumber);
        return Result.success(caseTrackList);
    }

    /*
     * 根据病例号提交该病例，随后进入（待接收病例）状态
     * */
    @RequestMapping(value = "/temporary/{caseNumber}",method = RequestMethod.PUT)
    public Result name(@PathVariable("caseNumber") Long caseNumber) {
        caseService.updateCaseState(caseNumber,3);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(104);
        newTrack.setStatusName(UserConstant.TRACK.STATUS104);
        trackService.addTrack(newTrack);
        caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,caseNumber)
                .set(CasePojo::getCommitTime,newTrack.getCreateTime())
                .set(CasePojo::getStateInfo,null));
        return Result.success();
    }

    /*
     * 根据病例号获取该患者的受限情况（是否可以修改病例）
     * */
    @RequestMapping(value = "/limit/{caseNumber}",method = RequestMethod.GET)
    public Result getLimitByCaseNumber(@PathVariable("caseNumber") Long caseNumber) {
        Boolean limitByNumber = caseService.getLimitByNumber(caseNumber);
        return Result.success(limitByNumber);
    }

    /*
     * 接收或者驳回病例
     * */
    @RequestMapping(value = "/reception",method = RequestMethod.PUT)
    public Result caseReception(@RequestBody Map<String,Object> map) {
        Boolean isPass = (Boolean) map.get("isPass");
        Long caseNumber = Long.valueOf((String) map.get("caseNumber"));
        String reason = (String) map.get("reason");
        if (reason == null){
            reason = "";
        }
        if (isPass){
            caseService.updateCaseState(caseNumber,4);
            TrackPojo newTrack = new TrackPojo();
            newTrack.setCaseNumber(caseNumber);
            newTrack.setStatus(106);
            newTrack.setStatusName(UserConstant.TRACK.STATUS106);
            trackService.addTrack(newTrack);
        } else {
            caseService.updateCaseState(caseNumber,2);
            TrackPojo newTrack = new TrackPojo();
            newTrack.setCaseNumber(caseNumber);
            newTrack.setStatus(105);
            newTrack.setStatusName(UserConstant.TRACK.STATUS105);
            newTrack.setRemark("理由："+reason);
            trackService.addTrack(newTrack);
            caseDao.update(null, new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,caseNumber)
                    .set(CasePojo::getStateInfo,"病例已驳回(" + reason + ")"));
        }
        return Result.success();
    }


    /*
     * 由当前主管自己领取相应病例号的病例（原系统用的align，改为allocate）
     * */
    @RequestMapping(value = "/allocate/{caseNumber}",method = RequestMethod.PUT)
    public Result allocateCase(@PathVariable("caseNumber") Long caseNumber) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的token
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));

        caseService.updateMechanic(caseNumber,userPojo.getUserId());
        caseService.updateCaseState(caseNumber,5);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(107);
        newTrack.setStatusName(UserConstant.TRACK.STATUS107);
        newTrack.setRemark("分配技工主管："+userPojo.getUserName());
        trackService.addTrack(newTrack);
        return Result.success();
    }

    /*
     * 把相应病例号的病例分配病例给相应的技工
     * */
    @RequestMapping(value = "/allocate/{caseNumber}/{mechanicId}",method = RequestMethod.PUT)
    public Result allocateCaseToMechanic(@PathVariable("caseNumber") Long caseNumber,@PathVariable("mechanicId") int mechanicId) {
        caseService.updateMechanic(caseNumber,mechanicId);
        caseService.updateCaseState(caseNumber,5);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(107);
        newTrack.setStatusName(UserConstant.TRACK.STATUS107);
        newTrack.setRemark("分配技工："+userService.getUserInfoById(mechanicId).getUserName());
        trackService.addTrack(newTrack);
        return Result.success();
    }

    /*
     * 排牙完成，由技工将状态改为方案设计中
     * */
    @RequestMapping(value = "/toothSet/{caseNumber}",method = RequestMethod.PUT)
    public Result toothSetBySupervisor(@PathVariable("caseNumber") Long caseNumber) {
        caseService.updateCaseState(caseNumber,6);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(108);
        newTrack.setStatusName(UserConstant.TRACK.STATUS108);
        trackService.addTrack(newTrack);
        return Result.success();
    }
//
//    /*
//     * 排牙完成，由技工将状态改为方案设计中
//     * */
//    @RequestMapping(value = "/toothSet/{caseNumber}/{mechanicId}",method = RequestMethod.PUT)
//    public Result toothSetByMechanic(@PathVariable("caseNumber") String caseNumber,@PathVariable("mechanicId") int mechanicId) {
//        return new Result();
//    }

    /*
     * 医生评审治疗方案
     * */
    @RequestMapping(value = "/audit/doctor",method = RequestMethod.PUT)
    public Result auditByDoctor(@RequestBody TDSchemePojo tdSchemePojo) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的token
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        tdSchemePojo.setAuditorId(userPojo.getUserId());
        tdSchemePojo.setAuditorType(userPojo.getUserType());

        TDSchemePojo tdSchemePojo1 = tdSchemeDao.selectById(tdSchemePojo.getTdSchemeId());
        if (tdSchemePojo1 == null){
            throw new BusinessException("该3D方案不存在");
        }
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(tdSchemePojo1.getCaseNumber());
        if (tdSchemePojo.getIsPass() == 1){
            caseService.updateCaseState(tdSchemePojo1.getCaseNumber(), 9);
            newTrack.setStatus(110);
            newTrack.setStatusName(UserConstant.TRACK.STATUS110);
            String stateInfo = "(U:" + tdSchemePojo1.getUpperTotalStep() + "/L:" + tdSchemePojo1.getLowerTotalStep() + ")";
            caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,tdSchemePojo1.getCaseNumber())
                    .set(CasePojo::getLowerTotalStep,tdSchemePojo1.getLowerTotalStep())
                    .set(CasePojo::getUpperTotalStep,tdSchemePojo1.getUpperTotalStep())
                    .set(CasePojo::getStateInfo,stateInfo));

        } else {
            caseService.updateCaseState(tdSchemePojo1.getCaseNumber(), 6);
            newTrack.setStatus(111);
            newTrack.setStatusName(UserConstant.TRACK.STATUS111);
            caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,tdSchemePojo1.getCaseNumber())
                    .set(CasePojo::getStateInfo,"已驳回"));
        }
        trackService.addTrack(newTrack);
        schemeService.update3dScheme(tdSchemePojo);
        return Result.success();
    }

    /*
     * 专家评审治疗方案
     * */
    @RequestMapping(value = "/audit/specialist",method = RequestMethod.PUT)
    public Result auditBySpecialist(@RequestBody TDSchemePojo tdSchemePojo) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(servletRequestAttributes, "header 获取异常");
        // 获取请求头中的token
        String token = servletRequestAttributes.getRequest().getHeader("token");
        Assert.notNull(token, "token获取失败");
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        } else if (!tokenCache.equals(token)){
            throw new BusinessException("token已过期");
        }
        UserPojo userPojo = userDao.selectOne(new LambdaQueryWrapper<UserPojo>().eq(UserPojo::getAccount, account));
        tdSchemePojo.setAuditorId(userPojo.getUserId());
        tdSchemePojo.setAuditorType(userPojo.getUserType());

        TDSchemePojo tdSchemePojo1 = tdSchemeDao.selectById(tdSchemePojo.getTdSchemeId());
        if (tdSchemePojo1 == null){
            throw new BusinessException("该3D方案不存在");
        }
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(tdSchemePojo1.getCaseNumber());
        if (tdSchemePojo.getIsPass() == 1){
            caseService.updateCaseState(tdSchemePojo1.getCaseNumber(), 9);
            newTrack.setStatus(112);
            newTrack.setStatusName(UserConstant.TRACK.STATUS112);
            String stateInfo = "(U:" + tdSchemePojo1.getUpperTotalStep() + "/L:" + tdSchemePojo1.getLowerTotalStep() + ")";
            caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,tdSchemePojo1.getCaseNumber())
                    .set(CasePojo::getLowerTotalStep,tdSchemePojo1.getLowerTotalStep())
                    .set(CasePojo::getUpperTotalStep,tdSchemePojo1.getUpperTotalStep())
                    .set(CasePojo::getStateInfo,stateInfo));

        } else {
            caseService.updateCaseState(tdSchemePojo1.getCaseNumber(), 6);
            newTrack.setStatus(113);
            newTrack.setStatusName(UserConstant.TRACK.STATUS113);
            caseDao.update(null,new LambdaUpdateWrapper<CasePojo>().eq(CasePojo::getCaseNumber,tdSchemePojo1.getCaseNumber())
                    .set(CasePojo::getStateInfo,"已驳回"));
        }
        trackService.addTrack(newTrack);
        schemeService.update3dScheme(tdSchemePojo);
        return Result.success();
    }

    /*
    * 上传3d方案
    * */
    @RequestMapping(value = "/3dScheme",method = RequestMethod.POST)
    public Result add3dScheme(@RequestBody TDSchemePojo tdSchemePojo){
        schemeService.add3dScheme(tdSchemePojo);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(tdSchemePojo.getCaseNumber());
        newTrack.setStatus(109);
        newTrack.setStatusName(UserConstant.TRACK.STATUS109);
        trackService.addTrack(newTrack);
        switch (tdSchemePojo.getAuditorType()){
            case 1 : caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 7);break;
            case 2 : caseService.updateCaseState(tdSchemePojo.getCaseNumber(), 8);break;
        }
        return Result.success();
    }

    @RequestMapping(value = "/complete/{caseNumber}",method = RequestMethod.PUT)
    public Result completeCase(@PathVariable("caseNumber") Long caseNumber){
        caseService.updateCaseState(caseNumber,15);
        TrackPojo newTrack = new TrackPojo();
        newTrack.setCaseNumber(caseNumber);
        newTrack.setStatus(121);
        newTrack.setStatusName(UserConstant.TRACK.STATUS121);
        trackService.addTrack(newTrack);
        return Result.success();
    }

}
