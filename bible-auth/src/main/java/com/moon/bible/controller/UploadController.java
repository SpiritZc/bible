package com.moon.bible.controller;

import com.moon.bible.api.common.IUploadService;
import com.moon.bible.base.BaseController;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.UploadResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.controller
 * @ClassName: UploadController
 * @Author: Administrator
 * @Description: 上传controller
 * @Date: 2020-7-28 16:19
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController extends BaseController {

    @Autowired
    private IUploadService iUploadService;

    /**
     * @Method uploadFile
     * @Author zhangcheng
     * @Version  1.0
     * @Description
     * @Return com.moon.bible.base.BaseEntity
     * @Exception
     * @Date 2020-7-29 11:15
     */
    @RequestMapping("/uploadFile")
    public UploadResDto uploadFile(MultipartFile file){
        return iUploadService.uploadFile(file);
    }

}
