package com.moon.bible.impl.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.moon.bible.api.common.IUploadService;
import com.moon.bible.api.filelog.IFileLogService;
import com.moon.bible.base.BaseEntity;
import com.moon.bible.base.OSSDto;
import com.moon.bible.base.UploadResDto;
import com.moon.bible.constants.StatusCode;
import com.moon.bible.entity.filelog.FileLog;
import com.moon.bible.enums.OperateFileStausEnum;
import com.moon.bible.enums.OperateFileTypeEnum;
import com.moon.bible.enums.OperateStausEnum;
import com.moon.bible.enums.RequestHeaderEnums;
import com.moon.bible.exception.BizException;
import com.moon.bible.util.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName : IUploadServiceImpl  //类名
 * @Description : 上传文件  //描述
 * @Author : HTB  //作者
 * @Date: 2020-07-28 20:41  //时间
 */
@Service
@Slf4j
public class IUploadServiceImpl implements IUploadService {

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private IFileLogService iFileLogService;

    @Value("${reqSource}")//请求来源
    private Integer reqSource;


    /**
     * @Method uploadFile
     * @Author zhangcheng
     * @Version  1.0
     * @Description 文件上传
     * @Return com.moon.bible.base.UploadResDto
     * @Exception
     * @Date 2020-7-29 12:53
     */
    @Override
    public UploadResDto uploadFile(MultipartFile file) {
        //返回结果
        UploadResDto uploadResDto;
        if (null == file) {
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.empty",new String[]{"文件"}));
        }
        String randomImageName = aliyunOSSUtils.getRandomImageName(file.getOriginalFilename());
        log.info("文件上传开始，开始时间{}，文件名：{}，文件大小{}", DateUtil.getNow() ,randomImageName,file.getSize());
        String headerReqSource = httpServletRequest.getHeader(RequestHeaderEnums.ReqSource.getCode());
        //请求来源
        Integer reqSource = null;
        FileLog fileLog = new FileLog();
        fileLog.setFileSize(file.getSize());//文件大小
        String ip = CusAccessObjectUtil.getIpAddress(httpServletRequest);
        fileLog.setOperateIp(ip);//ip
        fileLog.setType(OperateFileTypeEnum.UPLOAD.getCode());//文件操作类型
        if (StringUtil.isNullOrEmpty(headerReqSource)) {
            reqSource = this.reqSource;
        }else {
            if(CheckUtil.isNumber(headerReqSource)) {
                reqSource = Integer.valueOf(headerReqSource);
            }else {
                reqSource = this.reqSource;
            }
        }
        fileLog.setRequestSource(reqSource);
        long startTime=System.currentTimeMillis();   //获取开始时间
        try {
            uploadResDto = aliyunOSSUtils.uploadFileInputSteam(file,randomImageName);
            log.info("文件上传结束，结束时间：{}，上传结果：成功，返回结果：{}，上传路径：{}",DateUtil.getNow(), JSONObject.toJSONString(uploadResDto),uploadResDto.getUrl());
            fileLog.setFileName(uploadResDto.getNewName().substring(uploadResDto.getNewName().lastIndexOf("/")+1));//上传生成的名字
            fileLog.setFileUrl(uploadResDto.getUrl());//生成后返回地路径
            fileLog.setOperateStatus(OperateFileStausEnum.UPSUCCESS.getCode());//上传状态
            fileLog.setResult(JSON.toJSONString(uploadResDto));
        }catch (Exception e) {
            long endTime=System.currentTimeMillis(); //获取结束时间
            e.printStackTrace();
            log.info("文件上传结束，上传结果：失败，失败时间{}，异常信息{}", DateUtil.getNow(), e.getMessage());
            if (e instanceof OSSException) {//服务器异常
                OSSException e1 = (OSSException) e;
                fileLog.setErrorInfo(e1.getErrorMessage());
            } else if (e instanceof ClientException) {//客户端异常
                ClientException e1 = (ClientException) e;
                fileLog.setErrorInfo(e1.getErrorMessage());
            } else {
                fileLog.setErrorInfo(e.getMessage());
            }
            //设置上传状态
            fileLog.setOperateStatus(OperateFileStausEnum.UPFAILURE.getCode());
            fileLog.setExecuteTime(String.valueOf((endTime - startTime)));
            this.iFileLogService.save(fileLog);
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.upload"));
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        fileLog.setExecuteTime(String.valueOf((endTime - startTime)));
        this.iFileLogService.save(fileLog);
        return uploadResDto;
    }

    @Override
    public void deleteFile(String fileName) {
        if (null == fileName) {
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.empty",new String[]{"文件"}));
        }
        //获取服务器上的文件名
        String ossFileName = aliyunOSSUtils.getFileName(fileName);
        String headerReqSource = httpServletRequest.getHeader(RequestHeaderEnums.ReqSource.getCode());
        //请求来源
        Integer reqSource = null;
        FileLog fileLog = new FileLog();
        String ip = CusAccessObjectUtil.getIpAddress(httpServletRequest);//ip
        fileLog.setOperateIp(ip);
        if (StringUtil.isNullOrEmpty(headerReqSource)) {
            reqSource = this.reqSource;
        }else {
            if(CheckUtil.isNumber(headerReqSource)) {
                reqSource = Integer.valueOf(headerReqSource);
            }else {
                reqSource = this.reqSource;
            }
        }
        fileLog.setRequestSource(reqSource);
        fileLog.setType(OperateFileTypeEnum.DELETE.getCode());//文件操作类型
        long startTime=System.currentTimeMillis();   //获取开始时间
        try{
            OSSObject file = aliyunOSSUtils.getFile(ossFileName);
            long size = file.getResponse().getContentLength();
            aliyunOSSUtils.deleteFile(ossFileName);
            fileLog.setFileSize(size);//文件大小
            fileLog.setFileName(aliyunOSSUtils.getFileName(file.getKey()));//文件名
            fileLog.setFileUrl(file.getResponse().getUri());//文件路径
            fileLog.setOperateStatus(OperateFileStausEnum.DELETESUCCESS.getCode());//设置删除状态
//            fileLog.setResult(JSON.toJSONString(file));//存一下查询回来的对象
            log.info("文件删除结束，结束时间：{}，删除结果：成功，上传路径：{}",DateUtil.getNow(),file.getResponse().getUri());
        }catch (Exception e){
            long endTime=System.currentTimeMillis(); //获取结束时间
            e.printStackTrace();
            log.info("文件删除结束，删除结果：失败，失败时间{}，异常信息{}", DateUtil.getNow(), e.getMessage());
            if (e instanceof OSSException) {//服务器异常
                OSSException e1 = (OSSException) e;
                fileLog.setErrorInfo(e1.getErrorMessage());
            } else if (e instanceof ClientException) {//客户端异常
                ClientException e1 = (ClientException) e;
                fileLog.setErrorInfo(e1.getErrorMessage());
            } else {
                fileLog.setErrorInfo(e.getMessage());
            }
            //设置删除状态
            fileLog.setOperateStatus(OperateFileStausEnum.DELETEFAILURE.getCode());
            fileLog.setExecuteTime(String.valueOf((endTime - startTime)));
            this.iFileLogService.save(fileLog);
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.delete",new String[]{"文件"}));
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        fileLog.setExecuteTime(String.valueOf((endTime - startTime)));
        this.iFileLogService.save(fileLog);
    }

    /**
        * 功能描述: 查询文件<br>
        * 〈〉
        * @Param: [fileName]
        * @Return: com.aliyun.oss.model.OSSObject
        * @Author: Administrator
        * @Date: 2020/8/5 21:37
     */
    @Override
    public OSSDto getFile(String fileName) {
        if (null == fileName) {
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.empty",new String[]{"文件"}));
        }
        OSSDto file = null;
        //获取服务器上的文件名
        String ossFileName = aliyunOSSUtils.getFileName(fileName);
        String headerReqSource = httpServletRequest.getHeader(RequestHeaderEnums.ReqSource.getCode());
        //请求来源
        Integer reqSource = null;
        FileLog fileLog = new FileLog();
        String ip = CusAccessObjectUtil.getIpAddress(httpServletRequest);//ip
        fileLog.setOperateIp(ip);
        if (StringUtil.isNullOrEmpty(headerReqSource)) {
            reqSource = this.reqSource;
        }else {
            if(CheckUtil.isNumber(headerReqSource)) {
                reqSource = Integer.valueOf(headerReqSource);
            }else {
                reqSource = this.reqSource;
            }
        }
        fileLog.setRequestSource(reqSource);
        fileLog.setType(OperateFileTypeEnum.DELETE.getCode());//文件操作类型
        long startTime=System.currentTimeMillis();   //获取开始时间
        try{
            file = aliyunOSSUtils.getFileContent(ossFileName);
            long size = file.getResponse().getContentLength();
            fileLog.setFileSize(size);//文件大小
            fileLog.setFileName(aliyunOSSUtils.getFileName(file.getKey()));//文件名
            fileLog.setFileUrl(file.getResponse().getUri());//文件路径
            fileLog.setOperateStatus(OperateFileStausEnum.SEARCHSUCCESS.getCode());//设置查询状态
            log.info("文件查询结束，结束时间：{}，查询结果：成功，文件路径：{}",DateUtil.getNow(),file.getResponse().getUri());
        } catch (Exception e) {
            long endTime=System.currentTimeMillis(); //获取结束时间
            e.printStackTrace();
            log.info("文件查询结束，查询结果：失败，失败时间{}，异常信息{}", DateUtil.getNow(), e.getMessage());
            if (e instanceof OSSException) {//服务器异常
                OSSException e1 = (OSSException) e;
                fileLog.setErrorInfo(e1.getErrorMessage());
            } else if (e instanceof ClientException) {//客户端异常
                ClientException e1 = (ClientException) e;
                fileLog.setErrorInfo(e1.getErrorMessage());
            } else {
                fileLog.setErrorInfo(e.getMessage());
            }
            //设置查询状态
            fileLog.setOperateStatus(OperateFileStausEnum.SEARCHFAILURE.getCode());
            fileLog.setExecuteTime(String.valueOf((endTime - startTime)));
            this.iFileLogService.save(fileLog);
            throw new BizException(StatusCode.FAILURE, MessageUtil.getValue("error.file.search"));
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
        fileLog.setExecuteTime(String.valueOf((endTime - startTime)));
        this.iFileLogService.save(fileLog);

        return file;
    }


    public static void main(String[] args) {
        String a = "bible/image/d792af0f3b97438babdf444da57c6bd7apic26678.jpg";
        System.out.println(a.substring(a.lastIndexOf("/")+1));
    }
}
