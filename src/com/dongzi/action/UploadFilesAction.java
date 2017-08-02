package com.dongzi.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
			// String uploadPath =
			// this.getServletContext().getRealPath("/")+"\\uploadfile\\";

			File file = new File(uploadPath);
			if (!file.exists()) {
				file.mkdir();
			}
			// 将路径和上传文件名组合成完整的服务端路径
			imageName = uploadPath + "\\" + imageName;
			// GenerateImage( imageBase64, imageName);

	generateImage(imageBase64, imageName);

			System.out.println(uploadPath);
			System.out.println(imageName);
			// System.out.println(imageBase64);

			// string2Image(imageBase64, imageName);

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

	public static String generateImage(String base64Str, String filePath) {
		if (base64Str.indexOf("data:image/jpg;base64,") != -1) {
			base64Str = base64Str.replace("data:image/jpg;base64,", "");
		}
		System.out.println(base64Str);

		BASE64Decoder decoder = new BASE64Decoder();

		FileOutputStream out = null;
		try {

			byte[] b = decoder.decodeBuffer(base64Str);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			// if (EmptyUtil.isNullStr(filePath)) {
			// String newFileName = userName + CommonUtil.getDateString(new
			// Date(), "yyyyMMhhHHmmss") + ".jpg";
			// filePath = getFilePath() + newFileName;
			// }
			out = new FileOutputStream(filePath);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			return null;
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return filePath;
	}

	/**
	 * 通过BASE64Decoder解码，并生成图片
	 * 
	 * @param imgStr
	 *            解码后的string
	 */
	public boolean string2Image(String imgStr, String imgFilePath) {
		// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null)
			return false;
		try {
			// Base64解码
			byte[] b = new BASE64Decoder().decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成Jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
