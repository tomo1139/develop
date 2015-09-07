package jp.com.tt.model.beans;

import java.util.List;

public class PageInfo {
	private int nowPage;
	private int maxPage;
	private int dataNum;
	private int printStartDataIdx;
	private int printEndDataIdx;
	private List<Integer> printPageList;

	public List<Integer> getPrintPageList() {
		return printPageList;
	}
	public void setPrintPageList(List<Integer> printPageList) {
		this.printPageList = printPageList;
	}
	public int getPrintStartDataIdx() {
		return printStartDataIdx;
	}
	public void setPrintStartDataIdx(int printStartDataIdx) {
		this.printStartDataIdx = printStartDataIdx;
	}
	public int getPrintEndDataIdx() {
		return printEndDataIdx;
	}
	public void setPrintEndDataIdx(int printEndDataIdx) {
		this.printEndDataIdx = printEndDataIdx;
	}
	public int getDataNum() {
		return dataNum;
	}
	public void setDataNum(int dataNum) {
		this.dataNum = dataNum;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
}
