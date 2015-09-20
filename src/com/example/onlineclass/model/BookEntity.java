package com.example.onlineclass.model;

import java.io.Serializable;

/**
 * @author anumbrella
 * 
 * @date 2015-9-20 下午3:22:17
 * 
 *       书籍存储model,可序列化保存对象
 */
public class BookEntity implements Serializable {

	/**
	 * 版本编号
	 */
	private static final long serialVersionUID = 1L;

	public int id;
	public String name;
	public String title;
	public String time;
	public String imgUrl;
	public int type;
	public int progress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

}
