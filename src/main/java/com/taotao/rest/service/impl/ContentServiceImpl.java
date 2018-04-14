package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.service.ContentService;
import com.taotao.util.ExceptionUtil;
import com.taotao.util.JsonUtils;
import com.taotao.util.TaotaoResult;

@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public String getContentList(long categoryId) {
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);
		List resultlist = new ArrayList<>();
		for (TbContent tbContent : list) {
			Map map = new HashMap();
			map.put("srcB", tbContent.getPic());
			map.put("height", 240);
			map.put("alt", tbContent.getContent());
			map.put("width", 670);
			map.put("src", tbContent.getPic2());
			map.put("heightB", 550);
			map.put("widthB", 240);
			map.put("href", tbContent.getUrl());
			resultlist.add(map);
		}
		return JsonUtils.objectToJson(resultlist);
	}

	@Override
	public TaotaoResult getContentListTaoTao(long categoryId) {
		try {
			TbContentExample example = new TbContentExample();
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(categoryId);
			List<TbContent> list = contentMapper.selectByExample(example);
			// int a = 1 / 0;
			return TaotaoResult.ok(list);// 如果没有异常,返回taotaoresult list
		} catch (Exception e) {
			e.printStackTrace();
			// 异常信息是给同事看的.
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

}
