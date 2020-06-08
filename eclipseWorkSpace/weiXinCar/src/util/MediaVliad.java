package com.neuedu.onlearn.util;

import javax.servlet.http.Part;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.neuedu.onlearn.exception.InvalidParamException;

public class MediaVliad {
	private static Logger log = LogManager.getLogger(MediaVliad.class);
	private static final String[] IMAGE_TYPES = {"jpeg","gif","png"};
	private static final int IMAGE_LIMIT =1024*1024*2;
	private static final String[] AUDIO_TYPES = {"MP3"};
	private static final int AUDIO_LIMIT =1024*1024 *30;
	private static final String[] VIDEO_TYPES = {"MP4","avi","wmv"};
	private static final int VIDEO_LIMIT =1024*1024 *300;
	
	public int valid(Part part) {
		
		
		String fileType = part.getContentType();
		log.info("上传文件类型：" + fileType);
		int type = getMediaType(fileType);
		
		int limitSize = 0;
		if(type == Global.MEDIA_IMAGE) {
			limitSize = IMAGE_LIMIT;
		}else if(type == Global.MEDIA_AUDIO) {
			limitSize = AUDIO_LIMIT;
		}else if(type == Global.MEDIA_VIDEO) {
			limitSize = VIDEO_LIMIT;
		}
		//验证文件大小
		long size = part.getSize();
		if(size>limitSize) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"上传文件超过" + (limitSize / 1024/ 1024) + "M");
		}
		return type;
	}
	private int getMediaType(String fileType) {
		int type = 0;
		for(String format : IMAGE_TYPES) {
			if(fileType.endsWith(format)) {
				type = Global.MEDIA_IMAGE;
				break;
			}
		}
		for(String format : AUDIO_TYPES) {
			if(fileType.endsWith(format)) {
				type = Global.MEDIA_AUDIO;
				break;
			}
		}
		for(String format : VIDEO_TYPES) {
			if(fileType.endsWith(format)) {
				type = Global.MEDIA_VIDEO;
				break;
			}
		}
		if(type == 0) {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"上传文件类型不支持");
		}
		return type;
	}
}
