package com.gz.web.action.tool;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.gz.service.tool.ToolService;
import com.gz.web.action.BaseAction;



@Controller
@RequestMapping("/gz/tool/toolAction")
public class ToolAction extends BaseAction{
	
	@Autowired
	private ToolService toolService;
	
	@RequestMapping(value={"/uploadImgFile"})
	@ResponseBody
	public JSONObject query_right(@RequestParam("uploadImgFile") MultipartFile uploadImgFile) {
		try{
			return map2JSON(toolService.saveImgFile(uploadImgFile));
		}catch(Exception e){
			e.printStackTrace();
			log.error("上传-文件-失败");
			return null;
		}
	}
}
