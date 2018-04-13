package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.utils.CatResult;

@Controller
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	/*
	 * @RequestMapping(value = "/itemcat/all", produces =
	 * MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	 * 
	 * @ResponseBody public String getCatList(String callback) { CatResult
	 * result = itemCatService.getItemCatList(); String jsonResult =
	 * JsonUtils.objectToJson(result); String jsonstr = callback + "(" +
	 * jsonResult + ");"; return jsonstr; }
	 */
	@RequestMapping(value = "/itemcat/all", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public Object getItemCatList(String callback) {
		CatResult catResult = itemCatService.getItemCatList();
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}
}
