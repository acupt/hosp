package com.acupt.hosp.controller;

import com.acupt.domain.Result;
import com.acupt.domain.enums.CodeEnum;
import com.acupt.entity.enums.SickCaseTypeEnum;
import com.acupt.entity.es.SickCase;
import com.acupt.service.SickCaseService;
import com.acupt.util.StringUtil;
import jxl.Sheet;
import jxl.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liujie on 2017/8/12.
 */
@RequestMapping("/api/admin")
@Controller
public class AdminController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SickCaseService sickCaseService;

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    @ResponseBody
    public Result index(HttpServletRequest request, @RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return new Result(CodeEnum.PARAM_MISS, "请选择文件");
        }
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!"xls".equals(type)) {
            return new Result(CodeEnum.PARAM_ERR, "请使用xls文件");
        }
        Workbook rwb = null;
        int err = 0;
        int suc = 0;
        try {
            rwb = Workbook.getWorkbook(file.getInputStream());
            Sheet sheet = rwb.getSheet(0);
            int readRows = sheet.getRows();
            int readColumns = sheet.getColumns();
            for (int i = 1; i < readRows; i++) {
                String gender = sheet.getCell(0, i).getContents();
                String age = sheet.getCell(1, i).getContents();
                String drRes = sheet.getCell(2, i).getContents();
                String zhusu = sheet.getCell(3, i).getContents();
                String chati = sheet.getCell(4, i).getContents();
                String caseHistory = sheet.getCell(5, i).getContents();
                String drAdvise = sheet.getCell(6, i).getContents();

                SickCase sickCase = new SickCase();
                sickCase.setTitle(drRes);
                StringBuilder sb = new StringBuilder();
                if (StringUtil.isNotBlank(drRes)) {
                    sb.append("医生诊断：").append(drRes);
                }
                if (StringUtil.isNotBlank(drRes)) {
                    sb.append(sb.length() > 0 ? "\n医生建议：" : "医生建议：").append(drAdvise);
                }
                sickCase.setDrRemarks(sb.toString());
                sb = new StringBuilder().append('[').append(drRes).append("]\n");
                if (StringUtil.isNotBlank(gender)) {
                    sb.append(gender);
                }
                if (StringUtil.isNotBlank(age)) {
                    sb.append("，").append(age).append("。");
                }
                if (StringUtil.isNotBlank(zhusu)) {
                    sb.append("主诉：").append(zhusu).append("。");
                }
                if (StringUtil.isNotBlank(chati)) {
                    sb.append("查体：").append(chati).append("。");
                }
                if (StringUtil.isNotBlank(caseHistory)) {
                    sb.append("病史：").append(caseHistory).append("。");
                }
                sickCase.setContent(sb.toString());
                sickCase.setDrRemarks(sb.toString());
                sickCase.setProcessed(true);
                sickCase.setType(SickCaseTypeEnum.DB_IMPORT.getValue());
                Result<SickCase> result = sickCaseService.insert(sickCase);
                if (!result.isSuccess()) {
                    err++;
                    logger.error("import-insert-failed,{},{}", sickCase, result.getMsg());
                    continue;
                }
                suc++;
            }
        } catch (Throwable e) {
            logger.error("import-failed," + e.getMessage(), e);
        } finally {
            if (rwb != null) {
                rwb.close();
            }
        }
        logger.info("import-finish,suc={},err={}", suc, err);
        return new Result().setMsg(String.format("成功导入%d条记录，失败%d条。", suc, err));
    }

}
