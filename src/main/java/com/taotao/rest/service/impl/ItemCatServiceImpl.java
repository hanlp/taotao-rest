package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.utils.CatNode;
import com.taotao.rest.utils.CatResult;

@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	private TbItemCatMapper itemCatMapper;

	@Override
	public CatResult getItemCatList() {
		CatResult result = new CatResult();
		result.setData(getCatList(0));
		return result;
	}

	private List<?> getCatList(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List lisi = new ArrayList();
		int count = 0;
		for (TbItemCat tbItemCat : list) {
			// 判断是否为父节点
			if (tbItemCat.getIsParent()) {
				CatNode catNode = new CatNode();
				if (parentId == 0) {
					catNode.setName(
							"<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
				} else {
					catNode.setName(tbItemCat.getName());
				}
				catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
				catNode.setItem(getCatList(tbItemCat.getId()));
				lisi.add(catNode);
				count++;

			} else {
				lisi.add("/products/" + tbItemCat.getId() + ".html|" + tbItemCat.getName() + "");
			}
			// 展示14条
			if (parentId == 0 && count == 14) {
				break;
			}
		}

		return lisi;
	}

}
