package com.zhu.casemanage.service;

import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.Pictures;
import com.zhu.casemanage.dao.CaseDao;
import com.zhu.casemanage.dao.FileDao;
import com.zhu.casemanage.dao.SchemeDao;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.pojo.CasePojo;
import com.zhu.casemanage.pojo.FilePojo;
import com.zhu.casemanage.pojo.SchemePojo;
import com.zhu.casemanage.pojo.SendPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImpl {
    @Autowired
    private FileDao fileDao;
    @Autowired
    private CaseDao caseDao;
    @Autowired
    private SchemeDao schemeDao;
    @Value("${file-save-path}")
    private String fileSavePath;


//    /**
//     * 根据MD5查询文件
//     * @param md5
//     * @return
//     */
//    public FilePojo getFileByMd5(String md5) {
//        LambdaQueryWrapper<FilePojo> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(FilePojo::getMd5, md5);
//        List<FilePojo> list = fileDao.selectList(queryWrapper);
//        return list.size() == 0 ? null : list.get(0);
//    }


    /*
     * 添加文件
     * */
    public Map<String,Object> addFile(FilePojo newFile){
        FilePojo filePojo = fileDao.selectOne(new LambdaQueryWrapper<FilePojo>().eq(FilePojo::getCaseNumber, newFile.getCaseNumber()).eq(FilePojo::getFileType, newFile.getFileType()));
        Map<String ,Object> map = new HashMap<>();
        if (filePojo == null){
            map.put("isNew",1);
            fileDao.insert(newFile);
        } else {
            LambdaUpdateWrapper<FilePojo> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(FilePojo::getFileType, newFile.getFileType()).eq(FilePojo::getCaseNumber, newFile.getCaseNumber())
                    .set(FilePojo::getFileName,newFile.getFileName())
                    .set(FilePojo::getFileUrl,newFile.getFileUrl());
            fileDao.update(null,wrapper);
            map.put("isNew",0);
            map.put("fileUrl",filePojo.getFileUrl());
        }
        return map;
    }

    /*
    * 根据病例号查询图片列表
    * */
    public List<FilePojo> getImageListByNumber(Long caseNumber){
        List<FilePojo> imageList = fileDao.selectList(new QueryWrapper<FilePojo>().eq("case_number", caseNumber)
                .between("file_type",1,13));
//        if (imageList.size() == 0){
//            throw new BusinessException("该病例未上传照片");
//        }
        return imageList;
    }

    /*
    * 根据文件id删除文件
    * */
    public void delFile(Integer fileId){
        if (fileDao.deleteById(fileId) == 0){
            throw new BusinessException("文件不存在");
        }
    }

    /*
    * 根据病例号和文件类型删除文件
    * */
    public void delFileByType(Long caseNumber,int fileType){
        if (fileDao.delete(new QueryWrapper<FilePojo>().eq("case_number",caseNumber)
                .eq("file_type",fileType)) == 0){
            throw new BusinessException("文件不存在");
        }
    }

    /*
    * 根据病例号查询数字模型和压缩包或照片
    * */
    public List<FilePojo> getFileListByCaseNumber(Long caseNumber){
        List<FilePojo> fileList = fileDao.selectList(new QueryWrapper<FilePojo>().eq("case_number", caseNumber));
//        if (fileList.size() == 0){
//            throw new BusinessException("文件不存在");
//        }
        return fileList;
    }


//    /*
//    * 下载文件
//    * */
//    public static String downloadFile(String fileUrl) {
//        long l = 0L;
//        String path = null;
//        String staticAndMksDir = null;
//        if (fileUrl != null) {
//            //下载时文件名称
//            String fileName = fileUrl.substring(fileUrl.lastIndexOf("."));
//            try {
//                String dataStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
//                String uuidName = UUID.randomUUID().toString();
//                path = "resources/images/"+dataStr+"/"+uuidName+fileName;
//                staticAndMksDir = Paths.get(ResourceUtils.getURL("classpath:").getPath(),"resources", "images",dataStr).toString();
//                HttpUtil.downloadFile(fileUrl, staticAndMksDir + File.separator + uuidName + fileName);
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//
//            }
//        }
//        System.out.println(System.currentTimeMillis()-l);
//        return path;
//    }


    /*
    * 根据病例资料动态生成word
    * */
    public Map<String,Object> getCaseInfoWord(Long caseNumber, HttpServletRequest req) throws IOException {
        CasePojo casePojo = caseDao.selectOne(new LambdaQueryWrapper<CasePojo>().eq(CasePojo::getCaseNumber, caseNumber));
        SchemePojo schemePojo = schemeDao.selectOne(new LambdaQueryWrapper<SchemePojo>().eq(SchemePojo::getCaseNumber, caseNumber));

//        XWPFTemplate template = XWPFTemplate.compile("D:\\projectfiles\\IDEA\\CaseManageSys\\case-manage\\src\\main\\resources\\1.docx").render(
        XWPFTemplate template = XWPFTemplate.compile(fileSavePath + "1.docx").render(
                new HashMap<String, Object>(){{
                    put("name", casePojo.getPatientName());
                    put("doctor",casePojo.getDoctorName());
                    if (casePojo.getGender() == 1){
                        put("gender","男");
                    } else {
                        put("gender","女");
                    }
//                    put("face", Pictures.ofLocal("D:\\用户\\桌面\\新建文件夹\\1.jpg").sizeInCm(3.8,5).create());
                    put("birthday",casePojo.getBirthday());
                    put("address",casePojo.getAddress());
                    put("clinic",casePojo.getClinic());
                    put("complaint",casePojo.getPatientComplaint());

//                    put("face","C:\\Users\\ZQH\\Desktop\\新建文件夹 (2)\\logo.png");
                    put("diagnosis_infos",casePojo.getDiagnosisInfos());
                    put("doctor_remark",casePojo.getDoctorRemark());
                    switch (schemePojo.getOrthodonticArch()){
                        case 1: put("orthodontic_arch","全口");break;
                        case 2: put("orthodontic_arch","上颌");break;
                        case 3: put("orthodontic_arch","下颌");break;
                    }
                    switch (schemePojo.getExtTooth()){
                        case 1: put("ext_tooth","拔牙");
                            put("tooth_extraction",schemePojo.getToothExtraction());
                            break;
                        case 2: put("ext_tooth","不拔牙");break;
                    }
                    switch (schemePojo.getAnchorageLeft()){
                        case 1: put("anchorage_left","强");break;
                        case 2: put("anchorage_left","中");break;
                        case 3: put("anchorage_left","弱");break;
                    }
                    switch (schemePojo.getAnchorageRight()){
                        case 1: put("anchorage_left","强");break;
                        case 2: put("anchorage_left","中");break;
                        case 3: put("anchorage_left","弱");break;
                    }
                    switch (schemePojo.getCenterlineChoiceUp()){
                        case 1: put("centerline_choice_up","保持");break;
                        case 2: put("centerline_choice_up","左调整");break;
                        case 3: put("centerline_choice_up","右调整");break;
                        case 4: put("centerline_choice_up","调整");break;
                    }
                    put("centerline_up_val",schemePojo.getCenterlineUpVal());
                    put("centerline_low_val",schemePojo.getCenterlineLowVal());
                    switch (schemePojo.getOverbiteAdjust()){
                        case 1: put("overbite_adjust","保持");break;
                        case 2: put("overbite_adjust","压低上下前牙/正常");break;
                        case 3: put("overbite_adjust","压低上下前牙/Ⅰ度深覆");break;
                        case 4: put("overbite_adjust","压低上下前牙/Ⅱ度深覆");break;
                        case 5: put("overbite_adjust","压低上下前牙/Ⅲ度深覆");break;
                        case 6: put("overbite_adjust","压低上前牙/正常");break;
                        case 7: put("overbite_adjust","压低上前牙/Ⅰ度深覆");break;
                        case 8: put("overbite_adjust","压低上前牙/Ⅱ度深覆");break;
                        case 9: put("overbite_adjust","压低上前牙/Ⅲ度深覆");break;
                        case 10: put("overbite_adjust","压低下前牙/正常");break;
                        case 11: put("overbite_adjust","压低下前牙/Ⅰ度深覆");break;
                        case 12: put("overbite_adjust","压低下前牙/Ⅱ度深覆");break;
                        case 13: put("overbite_adjust","压低下前牙/Ⅲ度深覆");break;
                    }
                    switch (schemePojo.getOverjetAdjust()){
                        case 1: put("overjet_adjust","保持");break;
                        case 2: put("overjet_adjust","正常");break;
                        case 3: put("overjet_adjust","轻度");break;
                        case 4: put("overjet_adjust","中度");break;
                        case 5: put("overjet_adjust","重度");break;
                    }
                    put("overjet_val",schemePojo.getOverjetVal());
                    switch (schemePojo.getMolarRelationshipLeft()){
                        case 1: put("molar_relationship_left","保持");break;
                        case 2: put("molar_relationship_left","中性");break;
                        case 3: put("molar_relationship_left","近中");break;
                        case 4: put("molar_relationship_left","远中");break;
                    }
                    switch (schemePojo.getMolarRelationshipRight()){
                        case 1: put("molar_relationship_right","保持");break;
                        case 2: put("molar_relationship_right","中性");break;
                        case 3: put("molar_relationship_right","近中");break;
                        case 4: put("molar_relationship_right","远中");break;
                    }
                    put("crowding_tooth_up",schemePojo.getCrowdingToothUp());
                    put("crowding_tooth_low",schemePojo.getCrowdingToothLow());
                    switch (schemePojo.getMove()){
                        case 1: put("move","0.15mm");break;
                        case 2: put("move","0.2mm");break;
                        case 3: put("move","0.25mm");break;
                    }
                    switch (schemePojo.getAngle()){
                        case 1: put("angle","1°");break;
                        case 2: put("angle","2°");break;
                        case 3: put("angle","3°");break;
                    }
                    put("unmovable_teeth",schemePojo.getUnmovableTeeth());
                    put("accessory_teeth",schemePojo.getAccessoryTeeth());
                    put("interval_teeth",schemePojo.getIntervalTeeth());
                    put("adjacent_glaze",schemePojo.getAdjacentGlaze());
                    switch (schemePojo.getIsExcessive()){
                        case 0: put("is_excessive","不需要");break;
                        case 1: put("is_excessive","需要");break;
                    }


                }});
        String fileName = caseNumber + casePojo.getPatientName() + ".docx";
        template.writeAndClose(new FileOutputStream(fileName));

        //设置下载的文件路径
        String url = req.getScheme() + "://" + req.getServerName() + ":" +
                req.getServerPort() + fileSavePath + fileName;
        Map<String,Object> map = new HashMap<>();
        map.put("name",fileName);
        map.put("url",url);
        map.put("patientName",casePojo.getPatientName());
        return map;
    }

}
