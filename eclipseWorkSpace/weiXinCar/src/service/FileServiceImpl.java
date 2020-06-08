package com.neuedu.onlearn.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;

import com.neuedu.onlearn.exception.InvalidParamException;
import com.neuedu.onlearn.mapper.MediaMapper;
import com.neuedu.onlearn.po.Media;
import com.neuedu.onlearn.util.D;
import com.neuedu.onlearn.util.E;
import com.neuedu.onlearn.util.Global;
import com.neuedu.onlearn.util.MediaVliad;
import com.neuedu.onlearn.web.Page;

public class FileServiceImpl implements FileService{
	private static final String IMAGE_PATH ="/upload_files/images/";
	private static final String AUDIO_PATH ="/upload_files/audios/";
	private static final String VIDEO_PATH ="/upload_files/videos/";
	
	private MediaMapper mediaMapper;
	public FileServiceImpl() {
		SqlSession session = D.getConn();
		mediaMapper = session.getMapper(MediaMapper.class);
	}
	
	public String uploadFile(String path, Part part, Media media) throws IOException {
		//验证图片格式和大小
		MediaVliad mediaVliad = new MediaVliad();
		int type = mediaVliad.valid(part);
		media.setType(type);
		
		String uploadDir = path;
		String fileName = Long.toHexString(System.currentTimeMillis());
		String returnPath = null;
		
		if(media.getType() == Global.MEDIA_IMAGE) {
			uploadDir += IMAGE_PATH;
			returnPath = IMAGE_PATH;
		}else if(media.getType() == Global.MEDIA_AUDIO){
			uploadDir += AUDIO_PATH;
			returnPath = AUDIO_PATH;
		}else if(media.getType() == Global.MEDIA_VIDEO){
			uploadDir += VIDEO_PATH;
			returnPath = VIDEO_PATH;
		}else {
			throw new InvalidParamException(E.SELF_DEFINE_ERROR_CODE,"不支持的文件类型");
		}
		//如果目录不存在，创建目录
		File file =new File(uploadDir);
		if(!file.exists()) {
			file.mkdirs();
		}
		
	
		String orgName = part.getSubmittedFileName();
		//获取文件后缀名称
		String sufix= orgName.substring(orgName.lastIndexOf("."), orgName.length());
		
		uploadDir += (fileName +sufix);
		returnPath += (fileName +sufix);
		part.write(uploadDir);
		
		media.setUrl(returnPath);
		mediaMapper.insert(media);
		return returnPath;
	}

	public Page<Media> list(int pageNum, int pageSize, int type, String keyword,int teacherId) {
		int begin = (pageNum - 1) * pageSize;
		int total = mediaMapper.getMediaByKeywordCount(type,keyword,teacherId);
		List<Media> datas = mediaMapper.getMediaByKeyword(begin,pageSize,type,keyword,teacherId);
		
		Page<Media> pageData = new Page<Media>(datas,total,pageSize,pageNum);
		return pageData;
	}

}
