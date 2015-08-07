package com.wudoumi.battertest.bean;





public class News {

	private int _id;
	
	private String id;
	
	private String figure;
	
	private String title;
	
	private String description;
	
	private String threads;
	
	private String comment_nums;
	
	private int type;
	
	
	
	
	
	private String url;
	
	private String var1;
	
	private String var2;
	
	private String var3;
	
	private String var4;
	
	private String var5;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFigure() {
		return figure;
	}
	public void setFigure(String figure) {
		this.figure = figure;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThreads() {
		return threads;
	}
	public void setThreads(String threads) {
		this.threads = threads;
	}
	public String getComment_nums() {
		return comment_nums;
	}
	public void setComment_nums(String comment_nums) {
		this.comment_nums = comment_nums;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "News{" +
				"_id=" + _id +
				", id='" + id + '\'' +
				", figure='" + figure + '\'' +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", threads='" + threads + '\'' +
				", comment_nums='" + comment_nums + '\'' +
				", type=" + type +
				", url='" + url + '\'' +
				", var1='" + var1 + '\'' +
				", var2='" + var2 + '\'' +
				", var3='" + var3 + '\'' +
				", var4='" + var4 + '\'' +
				", var5='" + var5 + '\'' +
				'}';
	}
}
