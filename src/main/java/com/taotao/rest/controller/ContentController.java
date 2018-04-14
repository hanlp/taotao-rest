package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.rest.service.ContentService;
import com.taotao.util.TaotaoResult;

@Controller
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/content/category/{categoryId}")
	@ResponseBody
	public TaotaoResult getContentList(@PathVariable long categoryId) {
		return contentService.getContentListTaoTao(categoryId);
	}
}
