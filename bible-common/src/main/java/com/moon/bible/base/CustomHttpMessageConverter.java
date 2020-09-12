package com.moon.bible.base;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moon.bible.util.StringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;

public class CustomHttpMessageConverter extends FastJsonHttpMessageConverter{

	private ObjectMapper mapper = new ObjectMapper();
	@Override
	 public void write(Object obj, Type type, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		if (type.toString().contains("springfox"))
		{
			HttpHeaders headers = outputMessage.getHeaders();
			ByteArrayOutputStream outnew = new ByteArrayOutputStream();
			mapper.writeValue(outnew, obj);
			outnew.flush();
			headers.setContentLength(outnew.size());
			OutputStream out = outputMessage.getBody();
			outnew.writeTo(out);
			outnew.close();
		}else {
			ResponseMessage responseMessage = new ResponseMessage();
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			if(null != requestAttributes) {
	            HttpServletResponse response = requestAttributes.getResponse();
	            String token = response.getHeader("Authorization");
	            if (StringUtil.isNotEmpty(token)) {
					responseMessage.setNewToken(token);
				}
	        }
	        if (obj instanceof BaseEntity) {
	        	BaseEntity dto = (BaseEntity) obj;
	            responseMessage.setCode(dto.getStatusCode());
	            responseMessage.setMessage(dto.getStatusMsg());
	            responseMessage.setMsgLevel(dto.getMsgLevel());
	            responseMessage.setResponseData(obj);
	        }else {
	        	responseMessage.setResponseData(obj);
	        }
	        super.writeInternal(responseMessage, outputMessage);
		}
		
	}
}
