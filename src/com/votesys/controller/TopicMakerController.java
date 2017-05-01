package com.votesys.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.votesys.bean.TopicInfoBean;
import com.votesys.bean.VoteInfoBean;
import com.votesys.common.VoteSysConstant;
import com.votesys.service.operate.inter.IOperRelationSV;
import com.votesys.service.operate.inter.IOperTopicInfoSV;
import com.votesys.service.operate.inter.IOperVoteInfoSV;
import com.votesys.service.query.inter.IQueryTopicInfoSV;
import com.votesys.service.query.inter.IQueryVoteInfoSV;
import com.votesys.tools.ResponseUtil;
import com.votesys.tools.StringUtils;

import net.sf.json.JSONObject;

/**
 * @ClassName com.votesys.controller.TopicMakerController.java
 * @Description 主题创建控制层
 */
@Controller
@RequestMapping("/topicMaker")
public class TopicMakerController {

	@Autowired
	private IOperTopicInfoSV operTopicInfoSV;
	@Autowired
	private IOperRelationSV operRelationSV;
	@Autowired
	private IOperVoteInfoSV operVoteInfoSV;
	@Autowired
	private IQueryTopicInfoSV queryTopicInfoSV;
	@Autowired
	private IQueryVoteInfoSV queryVoteInfo;
	
	/**
	 * @throws Exception 
	 * @Function com.votesys.controller.TopicMakerController::stepOneSaveTopicInfo
	 * @Description 主题创建步骤一：保存主题基础信息
	 */
	@RequestMapping("/stepOneSaveTopicInfo")
	public void stepOneSaveTopicInfo(@RequestParam("topicTitle") String topicTitle,
			@RequestParam("topicContent") String topicContent, 
			@RequestParam("topicStatus") String topicStatus, @RequestParam("opType") String opType, 
			@RequestParam("effTime") String effTime, HttpServletResponse response) throws Exception {
		TopicInfoBean topicInfo = new TopicInfoBean();
		JSONObject ajaxResult = new JSONObject();
		boolean retCode = false;
		String createTime = null;
		topicInfo.setTopicTitle(topicTitle);
		topicInfo.setTopicContent(topicContent);
		topicInfo.setTopicStatus(topicStatus);
		topicInfo.setEffTime(effTime);
		
		if ("insert".equals(opType)) {
			createTime = operTopicInfoSV.insertTopicInfo(topicInfo);
		} else if("update".equals(opType)) {
			retCode = operTopicInfoSV.updateTopicInfo(topicInfo);
		}
		
		topicInfo = new TopicInfoBean();
		
		if (StringUtils.isNotBlank(createTime)) {
			topicInfo = queryTopicInfoSV.queryTopicInfoByCreateTime(createTime);
			retCode = true;
		}
		
		if (topicInfo != null) {
			retCode = true;
			ajaxResult.put("topicID", topicInfo.getTopicID());
			ajaxResult.put("topicTitle", topicInfo.getTopicTitle());
			/*ajaxResult.put("topicContent", topicInfo.getTopicContent());
			ajaxResult.put("topicStatus", topicInfo.getTopicStatus());
			ajaxResult.put("effTime", topicInfo.getEffTime());*/
		}
		
		if (retCode) {
			ajaxResult.put(VoteSysConstant.Code, "00");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "主题基本信息创建失败...");
		}
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.TopicMakerController::saveImg
	 * @Description 步骤二保存图片
	 * @param files
	 * @param topic
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/stepTwoSaveImg")
	public void stepTwoSaveImg(@RequestParam("upFile") MultipartFile[] files, @RequestParam("topicID") String topicID, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		String path = request.getServletContext().getRealPath("/");
		
		List<String> fileList = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			StringBuffer filePath = new StringBuffer(path);
			filePath.append("image/topic/").append(files[i].getOriginalFilename());
			files[i].transferTo(new File(filePath.toString()));
			fileList.add(files[i].getOriginalFilename());
		}
		
		if (StringUtils.isNotBlank(topicID)) {
			operTopicInfoSV.insertTopicExtInfo(fileList, topicID);
		} else {
			return;
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.TopicMakerController::stepThirdSaveVoteInfo
	 * @Description 步骤三保存投票项目
	 * @param voteOptions
	 * @param topicID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/stepThirdSaveVoteInfo")
	public void stepThirdSaveVoteInfo(@RequestParam("voteOptions") String voteOptions, @RequestParam("topicID") String topicID, 
			HttpServletResponse response) throws Exception {
		List<VoteInfoBean> voList = new ArrayList<VoteInfoBean>();
		JSONObject ajaxResult = new JSONObject();
		String[] voteOption = voteOptions.split("&");
		boolean retCode = false;
		
		if (StringUtils.isNotBlank(topicID)) {
			retCode = operVoteInfoSV.insertVoteInfo(new ArrayList<String>(Arrays.asList(voteOption)), topicID);
		}
		
		if (retCode) {
			voList = queryVoteInfo.queryVoteInfoByRemarks(topicID);
			if (voList.isEmpty()) {
				retCode = false;
			}
		}
		
		List<String> voteIDList = new ArrayList<String>();
		
		if (retCode) {
			for (VoteInfoBean vo : voList) {
				voteIDList.add(vo.getVoteID());
			}
			retCode = operRelationSV.insertTopicVoteRelate(voteIDList, topicID);
		}
		
		if (retCode) {
			String voteIDs = ""; 
			for (String string : voteIDList) {
				voteIDs += string+"&";
			}
			ajaxResult.put("voteIDS", voteIDs);
			ajaxResult.put(VoteSysConstant.Code, "00");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "投票项目保存失败...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @Function com.votesys.controller.TopicMakerController::completeMakeTopic
	 * @Description 完成主题创建
	 * @param voteStop
	 * @param voteStyle
	 * @param request
	 * @param expTime
	 * @param topicID
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/completeMakeTopic")
	public void completeMakeTopic(@RequestParam("voteStop") String voteStop, @RequestParam("voteStyle") String voteStyle, HttpServletRequest request,
			@RequestParam("expTime") String expTime, @RequestParam("topicID") String topicID, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		boolean retCode = false;
		String userID = (String) request.getSession().getAttribute("userID");
		
		if (StringUtils.isNotBlank(topicID)) {
			retCode = operVoteInfoSV.insertVoteInfo(voteStop, voteStyle, expTime, topicID);
		}
		
		if (retCode) {
			retCode = operRelationSV.insertUserTopicRelate(userID, topicID);
		}
		
		if (retCode) {
			ajaxResult.put(VoteSysConstant.Code, "00");
			ajaxResult.put(VoteSysConstant.Message, "/userOperate/toUserInfoPage.do");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "设置投票属性失败...");
		}
		ResponseUtil.write(ajaxResult, response);
	}
	
	/**
	 * @throws Exception 
	 * @Function com.votesys.controller.TopicMakerController::backToStepThird
	 * @Description 返回到第三步
	 */
	@RequestMapping("/backToStepThird")
	public void backToStepThird(@RequestParam("topicID") String topicID, @RequestParam("voteIDs") String voteIDs, HttpServletResponse response) throws Exception {
		JSONObject ajaxResult = new JSONObject();
		
		boolean retCode = operRelationSV.deleteTopicVoteRelate(topicID);
		
		if (retCode) {
			String[] voteID = voteIDs.split("&");
			for (String vID : voteID) {
				retCode = operVoteInfoSV.deleteVoteInfo(vID);
				if (!retCode) {
					break;
				}
			}
		}
		
		if (retCode) {
			ajaxResult.put(VoteSysConstant.Code, "00");
		} else {
			ajaxResult.put(VoteSysConstant.Code, "11");
			ajaxResult.put(VoteSysConstant.Message, "无法返回上一步...");
		}
		
		ResponseUtil.write(ajaxResult, response);
	}
}
