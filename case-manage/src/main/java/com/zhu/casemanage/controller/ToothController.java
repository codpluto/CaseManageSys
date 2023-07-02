//package com.zhu.casemanage.controller;
//
//import com.zhu.casemanage.utils.Result;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/platform")
//public class ToothController {
//
//    /*
//     * 根据病例号获取患者的排牙设定
//     * */
//    @RequestMapping(value = "/toothSet/caseId/{caseNumber}",method = RequestMethod.GET)
//    public Result getToothSetByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }
//
//    /*
//     * 根据病例号提交患者的排牙设定
//     * */
//    @RequestMapping(value = "/toothSet/caseId/{caseNumber}",method = RequestMethod.POST)
//    public Result commitToothSetByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }
//
//    /*
//     * 根据病例号获取患者的牙位标记
//     * */
//    @RequestMapping(value = "/toothTag/caseId/{caseNumber}",method = RequestMethod.GET)
//    public Result getToothTagByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }
//
//    /*
//     * 根据病例号提交患者的牙位标记
//     * */
//    @RequestMapping(value = "/toothTag/caseId/{caseNumber}",method = RequestMethod.POST)
//    public Result commitToothTagByCaseNumber(@PathVariable("caseNumber") String caseNumber) {
//        return new Result();
//    }
//
//}
