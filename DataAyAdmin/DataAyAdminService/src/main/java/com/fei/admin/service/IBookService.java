package com.fei.admin.service;

import com.fei.common.Response;

public interface IBookService {

	Response get(int id);

	Response getAll(int cid, int page, int pageNum);

}
