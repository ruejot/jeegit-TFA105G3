package com.bus.model;

import java.util.List;

public interface BusBeanDAO_interface {
	public abstract void insert(BusVO busBean);
    public abstract void update(BusVO busBean);
	public abstract void delete(Integer busid);
//	select single data By PrimaryKey
	public abstract BusVO select(Integer busid);
//	select all table data
	public abstract List<BusVO> selectAll();
}
