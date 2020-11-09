package com.jk.utils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;



public class FileUtil {
	//下载图片
    public static ResponseEntity<byte[]> downloadFile(String filename,HttpServletRequest request) throws IOException{
		String path = request.getServletContext().getRealPath("/")+"/upload/"+filename;
		File file = new File(path);
		byte[] byteArr = FileUtils.readFileToByteArray(file);
		HttpHeaders header = new HttpHeaders();
		header.setContentDispositionFormData("attchment", URLEncoder.encode(filename, "utf-8"));
		return new ResponseEntity<byte[]>(byteArr,header,HttpStatus.OK);
	}
	//图片
	public static String uploadFile(MultipartFile imgfile,HttpServletRequest request){
    	//1、上传路径：项目发布tomcat服务器
		//D:\workUtilsInstall\apache-tomcat-8.0.0\webapps\week_employee_hzy\\upload
		String path = request.getServletContext().getRealPath("/")+"/upload";
		
		//2、文件
		File file = new File(path);
		if(!file.exists()){//不存在
			file.mkdirs();
		}
		
		//生成新的文件名称，原因：防止文件名称一样后者上传的文件会覆盖前者上传的文件（前提是文件名称必须一样并且在用一个目录下）
		//生成新的文件名称，保证文件名称唯一有两种方法：
		// 	  1.通过UUID实现文件名称唯一 （UUID会生成32位字母+数字唯一的一个字符串）
		//	  2.通过时间戳现文件名称唯一  （时间戳是毫秒级时间 时间会一直往上加，生成13位数字）注意只有java生成13位 其他则是10位比如oracle、mysql、php
		//  获取时间戳
		//long currentTimeMillis = System.currentTimeMillis();
		//System.out.println(currentTimeMillis);
		String uuid = UUID.randomUUID().toString();
		
		String oldName = imgfile.getOriginalFilename();//1.jpg
		//截取文件后缀:.jpg
		String suffix = oldName.substring(oldName.lastIndexOf("."));
		//新文件名
		String newFile = uuid+suffix;
		
		//3、上传
		////D:\workUtilsInstall\apache-tomcat-8.0.0\webapps\week_employee_hzy\\upload\1.jpg
		File file2 = new File(path+"\\"+newFile);
		try {
			imgfile.transferTo(file2);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;		
	}
	
		
	/*<img id="add_img" width="100px" height="100px"/>
		<input type="file" id="uploadImg"/>
			<input id="hideImg" name="tupian"/><br>
			<div id="fileQueue"></div>	 */
	
	
/*$("#uploadImg").uploadify({
	'swf': '<%=request.getContextPath()%>/js/uploadify/uploadify.swf',
	'uploader': '<%=request.getContextPath()%>/task/tu.do',
  	'queueID': 'fileQueue',
	'fileObjName' : 'imgfile',
	'buttonText': '上传头像',
	'auto': true,
	'multi': true,
	'removeCompleted': true,
    'fileExt': '*.jpg;*.gif;*.png', 
    'cancelImg': '<%=request.getContextPath()%>/js/uploadify/uploadify-cancel.png', 	
	'removeTimeout' : 1,
	'uploadLimit':  -1,
	'fileTypeExts': '*.jpg;*.png',
	'onUploadSuccess':function(file,data,response){
			var imgurl = "http://<%= request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/upload/"+eval(data);
			$("#add_img").attr("src",imgurl);
			$('#hideImg').val(eval(data));
	}
});*/
	
/* $("#add_img").attr("src","<%=path%>/upload/"+data.tupian); */

/* @RequestMapping("tu")
		@ResponseBody
		public String uploadImg(MultipartFile imgfile,HttpServletRequest request) throws IllegalStateException, IOException{
			return FileUtil.uploadFile(imgfile, request);
		} 
		
		
		<img style='width:50px;height:50px' src='<%=path%>/upload/"+value+"'>";
		*/
}
