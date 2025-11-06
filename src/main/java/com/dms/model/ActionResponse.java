package com.dms.model;

import java.util.List;
import java.util.Map;

public class ActionResponse<T> {

	public ActionResponse(){		
		this.response = "FALSE";
	}
	
	private String response;
	
	private Map<String, Object> dataMapList;
	
	private Map<String, List> dataMapLists;
	
	private Map<Object, List> dataObjectLists;
	
	public Object getAdvData() {
		return advData;
	}

	public void setAdvData(Object advData) {
		this.advData = advData;
	}

	private List<Object> dataList;
	
	private Object data;
	
	private Object advData;
	
	private T modelData;
	
	private List<T> modelList;
	
	private Map<Object, List<T>> modelLists;
	
	public Map<Object, List<T>> getModelLists() {
		return modelLists;
	}

	public void setModelLists(Map<Object, List<T>> modelLists) {
		this.modelLists = modelLists;
	}

	public T getModelData() {
		return modelData;
	}


	public void setModelData(T modelData) {
		this.modelData = modelData;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Map<String, Object> getDataMapList() {
		return dataMapList;
	}

	public void setDataMapList(Map<String, Object> dataMapList) {
		this.dataMapList = dataMapList;
	}

	public Map<String, List> getDataMapLists() {
		return dataMapLists;
	}

	public void setDataMapLists(Map<String, List> dataMapLists) {
		this.dataMapLists = dataMapLists;
	}

	public List<Object> getDataList() {
		return dataList;
	}

	public void setDataList(List<Object> dataList) {
		this.dataList = dataList;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<Object, List> getDataObjectLists() {
		return dataObjectLists;
	}

	public void setDataObjectLists(Map<Object, List> dataObjectLists) {
		this.dataObjectLists = dataObjectLists;
	}

	public List<T> getModelList() {
		return modelList;
	}

	public void setModelList(List<T> modelList) {
		this.modelList = modelList;
	}

	
	
}
