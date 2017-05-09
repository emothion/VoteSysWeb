package com.votesys.service.operate.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.votesys.dao.operate.inter.IOperCommentDAO;
import com.votesys.service.operate.inter.IOperCommentSV;

/**
 * @ClassName com.votesys.service.operate.impl.OperCommentSVImpl.java
 * @Description 评论增删改服务层实现类
 */
@Service("operCommentService")
public class OperCommentSVImpl implements IOperCommentSV {

	@Resource
	private IOperCommentDAO operCommentDAO;
	
	@Override
	public String insertComment(String comContent) {
		return operCommentDAO.insertComment(comContent);
	}

	@Override
	public boolean upDateSubOrUpNum(String comID, String status) {
		return operCommentDAO.upDateSubOrUpNum(comID, status);
	}

	@Override
	public int updateCommentContent(String comID) {
		return operCommentDAO.updateCommentContent(comID);
	}

}
