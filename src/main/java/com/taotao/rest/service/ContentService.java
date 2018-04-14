package com.taotao.rest.service;

import com.taotao.util.TaotaoResult;

public interface ContentService {

	public String getContentList(long categoryId);

	public TaotaoResult getContentListTaoTao(long categoryId);

}
