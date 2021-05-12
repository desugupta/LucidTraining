package com.leave.employee.domain;

public class RequestObject<T> {

	private T data;
	private String name;
	private String id;
	private Class<?> className;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getClassName() {
		return className;
	}

	public void setClassName(Class<?> className) {
		this.className = className;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "RequestObject [data=" + data + ", name=" + name + ", id=" + id + ", className=" + className + "]";
	}

}