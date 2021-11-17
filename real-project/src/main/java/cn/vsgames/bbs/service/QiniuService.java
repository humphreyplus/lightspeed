package cn.vsgames.bbs.service;

import java.io.IOException;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class QiniuService {
	
	private String accessKey;
	private String secretKey;
	private String bucket;
	private String httpBase;

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}
	public void setHttpBase(String httpBase) {
		this.httpBase = httpBase;
	}
	
	public String uploadFile(String file, String key) throws JSONException, AuthException, IOException {
		Config.ACCESS_KEY = accessKey;
        Config.SECRET_KEY = secretKey;
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        PutPolicy putPolicy = new PutPolicy(bucket);
        String uptoken = putPolicy.token(mac);
        
        PutExtra extra = new PutExtra();
        PutRet ret = IoApi.putFile(uptoken, key, file, extra);
        if(ret.getStatusCode() != 200) {
        	throw new IOException("图片上传失败");
        }
        return httpBase + '/' + key;
	}
}