package com.jybl.admin.entity;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServiceEntity implements Serializable {
	private Long id;
	private String name;//名称
	private String price; //价格
	private int count = -1;//次数
	private String duration;//期限
	private String content;//内容
	private String risk_level_id;
	private String kind;//适用人群
	private String time;
	private String status;
	private Long rid;

	public ServiceEntity() {

	}
	public ServiceEntity(Long id, String name, String price, int count, String duration, String content, String kind, String time, String risk_level_id, String status, Long rid) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.count = count;
		this.duration = duration;
		this.content = content;
		this.kind = kind;
		this.time = time;
		this.risk_level_id = risk_level_id;
		this.status = status;
		this.rid = rid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTime() {
		return time;
	}

	public void setTime(Date time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.time = sdf.format(time);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRisk_level_id() {
		return risk_level_id;
	}

	public void setRisk_level_id(String risk_level_id) {
		this.risk_level_id = risk_level_id;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}
}
