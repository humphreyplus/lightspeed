package cn.vsgames.bbs.init;

public class ApplicationProperties {
	
	private String websiteName;
	private String websiteBase;
	
	private String staticFileUrl;
	
	private int pageSize;
	
	private String avatarUploadTempFolder;
	private String avatarUploadTempHttpUrl;
	private String avatarFolder;
	private int avatarStorage;
	private String avatarHttpUrl;
	private String defaultAvatarHttpUrl;
	private String avatarQiniuPath;
	
	private String imguploadFolder;
	private String imguploadHttpUrl;
	private String imguploadQiniuPath;
	private int imguploadStorage;

	public String getStaticFileUrl() {
		return staticFileUrl;
	}
	public void setStaticFileUrl(String staticFileUrl) {
		this.staticFileUrl = staticFileUrl;
	}
	public String getImguploadFolder() {
		return imguploadFolder;
	}
	public void setImguploadFolder(String imguploadFolder) {
		this.imguploadFolder = imguploadFolder;
	}
	public String getImguploadHttpUrl() {
		return imguploadHttpUrl;
	}
	public void setImguploadHttpUrl(String imguploadHttpUrl) {
		this.imguploadHttpUrl = imguploadHttpUrl;
	}
	public String getDefaultAvatarHttpUrl() {
		return defaultAvatarHttpUrl;
	}
	public String getWebsiteBase() {
		return websiteBase;
	}
	public String getAvatarQiniuPath() {
		return avatarQiniuPath;
	}
	public String getImguploadQiniuPath() {
		return imguploadQiniuPath;
	}
	public void setImguploadQiniuPath(String imguploadQiniuPath) {
		this.imguploadQiniuPath = imguploadQiniuPath;
	}
	public int getImguploadStorage() {
		return imguploadStorage;
	}
	public void setImguploadStorage(int imguploadStorage) {
		this.imguploadStorage = imguploadStorage;
	}
	public void setAvatarQiniuPath(String avatarQiniuPath) {
		this.avatarQiniuPath = avatarQiniuPath;
	}
	public void setWebsiteBase(String websiteBase) {
		this.websiteBase = websiteBase;
	}
	public int getAvatarStorage() {
		return avatarStorage;
	}
	public void setAvatarStorage(int avatarStorage) {
		this.avatarStorage = avatarStorage;
	}
	public void setDefaultAvatarHttpUrl(String defaultAvatarHttpUrl) {
		this.defaultAvatarHttpUrl = defaultAvatarHttpUrl;
	}

	public String getAvatarUploadTempFolder() {
		return avatarUploadTempFolder;
	}

	public void setAvatarUploadTempFolder(String avatarUploadTempFolder) {
		this.avatarUploadTempFolder = avatarUploadTempFolder;
	}

	public String getAvatarUploadTempHttpUrl() {
		return avatarUploadTempHttpUrl;
	}

	public void setAvatarUploadTempHttpUrl(String avatarUploadTempHttpUrl) {
		this.avatarUploadTempHttpUrl = avatarUploadTempHttpUrl;
	}
	public String getAvatarFolder() {
		return avatarFolder;
	}

	public void setAvatarFolder(String avatarFolder) {
		this.avatarFolder = avatarFolder;
	}

	public String getAvatarHttpUrl() {
		return avatarHttpUrl;
	}

	public void setAvatarHttpUrl(String avatarHttpUrl) {
		this.avatarHttpUrl = avatarHttpUrl;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
