package com.moon.bible.api.common;

import com.aliyun.oss.model.OSSObject;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.OSSDto;
import com.moon.bible.base.UploadResDto;
import com.moon.bible.util.AliyunOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: bible-project
 * @Package: com.moon.bible.api.common
 * @ClassName: IUploadService
 * @Author: Administrator
 * @Description: 文件上传
 * @Date: 2020-7-28 16:26
 * @Version: 1.0
 */
public interface IUploadService {
    /**
     * @Method uploadFile
     * @Author zhangcheng
     * @Version  1.0
     * @Description 文件上传
     * @Return
     * @Exception
     * @Date 2020-7-29 12:51
     */
    UploadResDto uploadFile(MultipartFile file);

    /**
     * @Method deleteFile
     * @Author zhangcheng
     * @Version  1.0
     * @Description 删除文件
     * @Return
     * @Exception
     * @Date 2020-7-31 9:03
     */
    void deleteFile(String fileName);

    /**
        * 功能描述: 根据文件名获取文件 <br>
        * 〈〉
        * @Param: [url]
        * @Return: com.aliyun.oss.model.OSSObject
        * @Author: Administrator
        * @Date: 2020/8/5 21:28
     */
    OSSDto getFile(String url);
}
