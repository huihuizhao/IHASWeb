package com.dongzi.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import sun.misc.BASE64Decoder;

import com.dongzi.daoimp.UserDaoImp;

public class UploadFilesAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void UploadFiles() {
		try {

			this.response.setContentType("text/html;charset=utf-8");
			this.response.setCharacterEncoding("UTF-8");

			Map<String, String> json = new HashMap<String, String>();

			String imageName = this.request.getParameter("imageName");
			String imageBase64 = this.request.getParameter("imageStreamBase64");
			String voiceName = this.request.getParameter("voiceName");		
			String voiceBase64 = this.request.getParameter("voiceStreamBase64");
			String uploadPath = request.getRealPath("\\uploadfile\\");
//			String uploadPath = this.getServletContext().getRealPath("/")+"\\uploadfile\\";
			// 将路径和上传文件名组合成完整的服务端路径
			imageName = uploadPath + imageName;
			GenerateImage(  imageBase64,   imageName);

			boolean b = true;
			if (b) {
				json.put("message", "上传成功");
			} else {
				json.put("message", "上传失败");
			}

			byte[] jsonBytes = json.toString().getBytes("utf-8");
			response.setContentLength(jsonBytes.length);
			response.getOutputStream().write(jsonBytes);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	 public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		    if (imgStr == null) // 图像数据为空
		      return false;
		    BASE64Decoder decoder = new BASE64Decoder();
		    try {
		      // Base64解码
		      byte[] bytes = decoder.decodeBuffer(imgStr);
		      for (int i = 0; i < bytes.length; ++i) {
		        if (bytes[i] < 0) {// 调整异常数据
		          bytes[i] += 256;
		        }
		      }
		      // 生成jpeg图片
		      OutputStream out = new FileOutputStream(imgFilePath);
		      out.write(bytes);
		      out.flush();
		      out.close();
		      return true;
		    } catch (Exception e) {
		      return false;
		    }
		  }
	
}
