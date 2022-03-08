package com.bus.model;

import java.util.List;

public interface BusDAO_interface {
	public abstract void insert(BusVO busBean);
    public abstract void update(BusVO busBean);
	public abstract void delete(Integer busid);
	public abstract void updateBusPW(BusVO busBean);
	
//	select single data By PrimaryKey
	public abstract BusVO select(Integer busid);
	public abstract BusVO select(String email);
	public abstract BusVO selectByEmailAndPassword(String email, String password);
//	select 2 parameters of single data By PrimaryKey	
	public abstract BusVO selectBusIDEmail(String email);
//	select all table data
	public abstract List<BusVO> selectAll();
	
}
