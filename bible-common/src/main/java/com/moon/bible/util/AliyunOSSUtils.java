package com.moon.bible.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.google.common.io.CharStreams;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.OSSDto;
import com.moon.bible.base.UploadResDto;
import com.moon.bible.constants.Constants;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.aspectj.org.eclipse.jdt.core.dom.ASTParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @ClassName : AliyunOSSUtils  //类名
 * @Description : oss 文件上传工具类 //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-26 17:26  //时间
 * 如果Object是公共读/公共读写权限，那么文件URL的格式为：BucketName.Endpoint/ObjectName。
 * 例如华东1（杭州）地域下名为aliyun-abc的存储空间（Bucket）下有名为abc的文件夹，文件夹内有个名为myphoto.jpg的文件。则该文件URL为：
 * 外网访问URL：aliyun-abc.oss-cn-hangzhou.aliyuncs.com/abc/myphoto.jpg
 * 内网访问URL（供同地域ECS实例访问）：aliyun-abc.oss-cn-hangzhou-internal.aliyuncs.com/abc/myphoto.jpg
 */
@Component
@Slf4j
public class AliyunOSSUtils {
    /**
     * 阿里云的配置参数
     */
    @Value("${aliyun.AccessKeyID}")
    private String accessKeyId;
    @Value("${aliyun.AccessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.EndPoint}")
    private String endpoint;
    @Value("${aliyun.Buckets}")
    private String bucketName;
    /**
     * 存储在OSS中的前缀名
     */
    @Value("${aliyun.prefix}")
    private String filePrefix;

    /**
     * 获取图片的URL头信息
     *
     * @return 返回url头信息
     */
    private String getURLHead() {
        //从哪个位置截取
        int cutPoint = endpoint.lastIndexOf('/') + 1;
        //http头
        String head = endpoint.substring(0, cutPoint);
        //服务器地址信息
        String tail = endpoint.substring(cutPoint);
        //返回结果
        return head + bucketName + "." + tail + "/";
    }

    /**
     * 获取存储在服务器上的地址
     *
     * @param oranName 文件名
     * @return 文件URL
     */
    private String getRealURL(String oranName) {
        return getURLHead() + oranName;
    }

    /**
     * 获取一个随机的文件名
     *
     * @param oranName 初始的文件名
     * @return 返回加uuid后的文件名
     */
    public String getRandomImageName(String oranName) {
        //获取一个uuid 去掉-
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //查一下是否带路径
        int cutPoint = oranName.lastIndexOf("/") + 1;
        //如果存在路径
        if (cutPoint != 0) {
            //掐头 如果开头是/ 则去掉
            String head = oranName.indexOf("/") == 0 ? oranName.substring(1, cutPoint) : oranName.substring(0, cutPoint);
            //去尾
            String tail = oranName.substring(cutPoint);
            //返回正确的带路径的图片名称
            return filePrefix + getFolderName(oranName.substring(oranName.lastIndexOf("."))) + head + uuid + tail;
        }
        //不存在 直接返回
        return filePrefix + getFolderName(oranName.substring(oranName.lastIndexOf("."))) + uuid + oranName;
    }

    /**
     * @Method getFileName
     * @Author zhangcheng
     * @Version  1.0
     * @Description 根据路径获取文件夹携带文件名
     * @Return
     * @Exception
     * @Date 2020-7-31 9:30
     */
    public String getFileName(String url){
       return url.replace(getURLHead(),"");
    }


    /**
     * 上传文件流
     * @param file 来自本地的文件或者文件流
     */
    public UploadResDto uploadFileInputSteam(MultipartFile file,String originalFilename) throws Exception {
        //上传返回
        UploadResDto uploadResDto = new UploadResDto();
        //上传文件url
        String url = "";
        String newName = "";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //获取文件名
         originalFilename = originalFilename;
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getInputStream().available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(file.getContentType());
            objectMetadata.setContentDisposition("inline;filename=" + originalFilename);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName, originalFilename, file.getInputStream(), objectMetadata);
            log.info("文件上传到oss成功 eTag={}", putResult.getETag());
            url = getRealURL(originalFilename);
            newName = originalFilename;
            uploadResDto.setUrl(url);
            uploadResDto.setNewName(newName);
        }finally {
            try {
                if (file.getInputStream() != null) {
                    file.getInputStream().close();
                }
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return uploadResDto;
    }

    /**
     * @Method deleteFile
     * @Author zhangcheng
     * @Version  1.0
     * @Description 删除文件
     * @Return
     * @Exception
     * @Date 2020-7-31 10:04
     */
    public void deleteFile(String originalFilename) throws Exception {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            boolean exist = ossClient.doesObjectExist(bucketName, originalFilename);
            if (!exist) {
                log.error("文件不存在,filePath={}", originalFilename);
                throw new BizException(StatusCode.FAILURE,MessageUtil.getValue("error.file.delete.empty"));
            }
            //删除文件
            ossClient.deleteObject(bucketName, originalFilename);
            log.info("{} 文件从oss删除成功",originalFilename);
        }finally {
            try {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public OSSObject getFile(String originalFilename) throws Exception {
        OSSObject ossObject = null;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ossObject = ossClient.getObject(bucketName, originalFilename);
            if (null == ossObject){
                log.info("{} 文件从oss查询失败",originalFilename);
                throw new BizException(StatusCode.FAILURE,MessageUtil.getValue("error.file.search"));
            }
            log.info("{} 文件从oss查询成功",originalFilename);
        }finally {
            try {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ossObject;
    }


    /**
        * 功能描述: getFileContent<br>
        * 〈〉
        * @Param: [originalFilename]
        * @Return: java.lang.String
        * @Author: Administrator
        * @Date: 2020/8/6 20:02
     */
    public OSSDto getFileContent(String originalFilename) throws Exception {
        OSSDto ossDto = new OSSDto();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            OSSObject ossObject =  ossClient.getObject(bucketName, originalFilename);
            if (null == ossObject){
                log.info("{} 文件从oss查询失败",originalFilename);
                throw new BizException(StatusCode.FAILURE,MessageUtil.getValue("error.file.search"));
            }
            String result = CharStreams.toString(new InputStreamReader(ossObject.getObjectContent(), Charsets.UTF_8));
            BeanUtils.copyProperties(ossObject,ossDto);
            ossDto.setContent(result);
            log.info("{} 文件从oss查询成功",originalFilename);
        }finally {
            try {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ossDto;
    }

    /**
     * @param: FilenameExtension
     * @Return: java.lang.String
     * @Decription: 判断OSS服务文件上传时文件的contentType
     * @CreateDate: Created in 2018/12/11 17:19
     * @Modify:
     */
//    private static String getcontentType(String FilenameExtension) {
//        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
//            return "image/bmp";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".gif")) {
//            return "image/gif";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
//                FilenameExtension.equalsIgnoreCase(".jpg") ||
//                FilenameExtension.equalsIgnoreCase(".png")) {
//            return "image/jpeg";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".html")) {
//            return "text/html";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".txt")) {
//            return "text/plain";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
//            return "application/vnd.visio";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
//                FilenameExtension.equalsIgnoreCase(".ppt")) {
//            return "application/vnd.ms-powerpoint";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".docx") ||
//                FilenameExtension.equalsIgnoreCase(".doc")) {
//            return "application/msword";
//        }
//        if (FilenameExtension.equalsIgnoreCase(".xml")) {
//            return "text/xml";
//        }
//        return "image/jpeg";
//    }

    /**
     * @Method
     * @Author zhangcheng
     * @Version  1.0
     * @Description 根据contentType 来分配文件夹
     * @Return
     * @Exception
     * @Date 2020-7-29 10:59
     */
    private static String getFolderName(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/";
        }
        if (FilenameExtension.equalsIgnoreCase(".gif")) {
            return "image/";
        }
        if (FilenameExtension.equalsIgnoreCase(".jpeg") ||
                FilenameExtension.equalsIgnoreCase(".jpg") ||
                FilenameExtension.equalsIgnoreCase(".png")) {
            return "image/";
        }
        if (FilenameExtension.equalsIgnoreCase(".html")) {
            return "text/";
        }
        if (FilenameExtension.equalsIgnoreCase(".txt")) {
            return "text/";
        }
        if (FilenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/";
        }
        if (FilenameExtension.equalsIgnoreCase(".pptx") ||
                FilenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/";
        }
        if (FilenameExtension.equalsIgnoreCase(".docx") ||
                FilenameExtension.equalsIgnoreCase(".doc")) {
            return "application/";
        }
        if (FilenameExtension.equalsIgnoreCase(".xml")) {
            return "text/";
        }
        return "other/";
    }

}
