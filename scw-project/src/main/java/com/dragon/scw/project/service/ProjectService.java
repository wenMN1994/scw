package com.dragon.scw.project.service;

/**
 * 
 * <p>Title: ProjectService</p>  
 * <p>Description: </p>  
 * @author Dragon.Wen
 * @date 2019年12月19日
 */
public interface ProjectService {

	void saveProject(String accessToken, String projectToken, byte draft);

}
