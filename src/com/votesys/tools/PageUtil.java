package com.votesys.tools;

/**
 * ��ҳ������
 * 
 * @author Administrator
 *
 */
public class PageUtil {

	/**
	 * ��ȡ��ҳ����
	 * 
	 * @param targetUrl
	 *            Ŀ���ַ
	 * @param totalNum
	 *            �ܼ�¼��
	 * @param currentPage
	 *            ��ǰҳ
	 * @param pageSize
	 *            ÿҳ��С
	 * @return
	 */
	
	public static String getPagation(String targetUrl, int totalNum, int currentPage, int pageSize, int flag) {
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if (totalPage == 0) {
			if (flag == 1) {
				return "<font color=red>�������ۣ�</font>";
			} else {
				return "<font color=red>δ��ѯ�����ݣ�</font>";
			}
		}
		StringBuffer pageCode = new StringBuffer();
		if (flag == 1) {
			pageCode.append("<li><a href='" + targetUrl + "&page=1'>��ҳ</a></li>");	
		}else{
			pageCode.append("<li><a href='" + targetUrl + "?page=1'>��ҳ</a></li>");			
		}
		if (currentPage == 1) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		} else {
			if (flag == 1) {
				pageCode.append("<li><a href='" + targetUrl + "&page=" + (currentPage - 1) + "'>��һҳ</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage - 1) + "'>��һҳ</a></li>");
			}
		}

		for (int i = currentPage - 1; i <= currentPage + 1; i++) {
			if (i < 1 || i > totalPage) {
				continue;
			}
			if (i == currentPage) {
				pageCode.append("<li class='active'><a href='#'>" + i + "</a></li>");
			} else {
				if (flag == 1) {
					pageCode.append("<li><a href='" + targetUrl + "&page=" + i + "'>" + i + "</a></li>");
				} else {
					pageCode.append("<li><a href='" + targetUrl + "?page=" + i + "'>" + i + "</a></li>");
				}
			}

		}

		if (currentPage == totalPage) {
			pageCode.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
		} else {
			if (flag == 1) {
				pageCode.append("<li><a href='" + targetUrl + "&page=" + (currentPage + 1) + "'>��һҳ</a></li>");
			} else {
				pageCode.append("<li><a href='" + targetUrl + "?page=" + (currentPage + 1) + "'>��һҳ</a></li>");
			}
		}
		if (flag == 1) {
			pageCode.append("<li><a href='" + targetUrl + "&page=" + totalPage + "'>ĩҳ</a></li>");
		} else {
			pageCode.append("<li><a href='" + targetUrl + "?page=" + totalPage + "'>ĩҳ</a></li>");
		}
		return pageCode.toString();
	}
}
