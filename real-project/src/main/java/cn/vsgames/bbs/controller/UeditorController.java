package cn.vsgames.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiniu.api.auth.AuthException;

import cn.vsgames.bbs.init.ApplicationProperties;
import cn.vsgames.bbs.service.QiniuService;

@Controller
public class UeditorController {
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private QiniuService qiniuService;
	
	@RequestMapping(value = "ueditorService", params = "action=config")
	@ResponseBody
	public String getConfig() throws IOException {
		ClassPathResource classPathResource = new ClassPathResource("ueditor/config.json");
		String config = IOUtils.toString(classPathResource.getInputStream());
		return config;
	}
	
	@RequestMapping(value = "ueditorService", params = "action=uploadimage", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String uploadImage(MultipartFile upfile) throws IOException, JSONException, AuthException {
		
		if (!upfile.isEmpty()) {
            String contentType = upfile.getContentType();
            String expandedName = "";
            if (contentType.equals("image/pjpeg") || contentType.equals("image/jpeg")) {  
                expandedName = "jpg";  
            } else if (contentType.equals("image/png") || contentType.equals("image/x-png")) {  
                expandedName = "png";  
            } else if (contentType.equals("image/gif")) {  
                expandedName = "gif";  
            } else if (contentType.equals("image/bmp")) {  
                expandedName = "bmp";  
            } else {
            	JSONObject jsonObject = new JSONObject();
            	jsonObject.put("state", "图片格式错误！");
            	return jsonObject.toString();
            }
            String uuidFileName = UUID.randomUUID().toString();
            String fullPath = applicationProperties.getImguploadFolder()
            		+ File.separatorChar + uuidFileName + "." + expandedName;
            upfile.transferTo(new File(fullPath));
            
            String httpUrl = null;
            if(applicationProperties.getImguploadStorage() == 1) {
            	httpUrl = qiniuService.uploadFile(fullPath, applicationProperties.getImguploadQiniuPath()
					+ "/" + uuidFileName + '.' + expandedName);
            } else {
            	httpUrl = applicationProperties.getImguploadHttpUrl() + '/' + uuidFileName + '.' + expandedName;
            }
            
            JSONObject jsonObject = new JSONObject();
        	jsonObject.put("state", "SUCCESS");
        	jsonObject.put("url", httpUrl);
        	return jsonObject.toString();
        } else {
        	JSONObject jsonObject = new JSONObject();
        	jsonObject.put("state", "图片上传失败！");
        	return jsonObject.toString();
        }
		
	}
	
	@RequestMapping(value = "ueditorService", params = "action=uploadscrawl")
	@ResponseBody
	public String uploadScrawl(String upfilebase64) throws IOException, JSONException, AuthException {
		
		String uuidFileName = UUID.randomUUID().toString();
        String fullPath = applicationProperties.getImguploadFolder()
        		+ File.separatorChar + uuidFileName + ".png";
		
		byte[] bytes = Base64Utils.decodeFromString(upfilebase64);
		FileUtils.writeByteArrayToFile(new File(fullPath), bytes);
		
		String httpUrl = null;
        if(applicationProperties.getImguploadStorage() == 1) {
        	httpUrl = qiniuService.uploadFile(fullPath, applicationProperties.getImguploadQiniuPath()
				+ "/" + uuidFileName + ".png");
        } else {
        	httpUrl = applicationProperties.getImguploadHttpUrl() + '/' + uuidFileName + ".png";
        }
		
		JSONObject jsonObject = new JSONObject();
    	jsonObject.put("state", "SUCCESS");
    	jsonObject.put("url", httpUrl);
    	return jsonObject.toString();
	}
}