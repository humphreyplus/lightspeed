package cn.vsgames.bbs.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.vsgames.bbs.domain.AjaxResult;
import cn.vsgames.bbs.init.ApplicationProperties;

@Controller
public class FileUploadController {
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@ResponseBody
	@RequestMapping(value = "/my/uploadAvatar", method = RequestMethod.POST)
	public Object uploadAvatar(MultipartFile file) throws IOException {
		AjaxResult ajaxResult = new AjaxResult();
		if (!file.isEmpty()) {
            String contentType = file.getContentType();
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
            	ajaxResult.setStatus(false);
                ajaxResult.setMessage("文件格式不正确！");
                return ajaxResult;
            }
            String uuidFileName = UUID.randomUUID().toString();
            String fullPath = applicationProperties.getAvatarUploadTempFolder() 
            		+ File.separatorChar + uuidFileName + "." + expandedName;
            file.transferTo(new File(fullPath));
            ajaxResult.setStatus(true);
            ajaxResult.setMessage(applicationProperties.getAvatarUploadTempHttpUrl() + "/" 
            		+ uuidFileName + "." + expandedName);
        } else {
        	ajaxResult.setStatus(false);
        	ajaxResult.setMessage("图片上传失败！");
        }
		
		return ajaxResult;
	}

}
