package com.dragon.scw.project.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.dragon.scw.utils.AppDateUtils;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * OSS云存储模板类
 * @author Administrator
 */
@Slf4j
@ToString
@Data
public class OssTemplate {
	
	private String endpoint; 
	private String accessKeyId;
	private String accessKeySecret;
	private String bucket;

	/**
	 * 返回上传后的文件的访问路径
	 * 
	 * @param inputStream
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String upload(String fileName, InputStream inputStream) throws IOException {
		try {
			// 加工文件夹和文件名
			String folderName = AppDateUtils.getFormatTime("yyyy-MM-dd");
			fileName = UUID.randomUUID().toString().replace("-", "") + "_" + fileName;
			
			//创建OSSClient实例
			OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
			// 上传文件流。
			// 指定bucket的名
			ossClient.putObject(bucket, "pic/" + folderName + "/" + fileName, inputStream);
			inputStream.close();
			ossClient.shutdown();
			
			String filePath = "https://" + bucket + "." + endpoint + "/pic/" + folderName + "/" + fileName;
			
			log.debug("文件上传成功-{}", filePath);
			
			return filePath;
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("文件上传失败-{}", e.getMessage());
			return null;
		}
	}
}
