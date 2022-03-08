package com.cart.model;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisCartListService {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static void addCartList(String memberId, JSONObject product) throws JSONException {
		String cartKey = new StringBuilder("member").append(":").append(memberId).append(":").append("cart").toString();
		Jedis jedis = pool.getResource();
		
		jedis.rpush(cartKey, product.toString());
		
		jedis.close();		
	}
	
	public static boolean updateCartList(String memberId, List<String> cartlist, String merId, String qty, boolean match) throws JSONException {
		String cartKey = new StringBuilder("member").append(":").append(memberId).append(":").append("cart").toString();
		Jedis jedis = pool.getResource();
		
		for (int i = 0; i < cartlist.size(); i++) {
			JSONObject product = new JSONObject(cartlist.get(i));
			if (product.getString("merId").equals(merId)) {
				Integer oldQty = Integer.valueOf(product.getString("qty"));
				Integer newQty = oldQty + Integer.valueOf(qty);
				product.put("qty", newQty.toString());
				jedis.lset(cartKey, i, product.toString());
				match = true;
			}	
		}
		jedis.close();
		return match;
	}
	
	public static List<String> getCartList(String memberId){
		String key = new StringBuilder("member").append(":").append(memberId).append(":").append("cart").toString();
		Jedis jedis = pool.getResource();
		
		List<String> savelist = jedis.lrange(key, 0, -1);
		jedis.close();
		
		return savelist;
	}
	
	public static void deleteCartListbyMerId (String memberId, List<String> cartlist, String merId) throws JSONException {
		String cartKey = new StringBuilder("member").append(":").append(memberId).append(":").append("cart").toString();
		Jedis jedis = pool.getResource();

		for (int i = 0; i < cartlist.size(); i++) {
			JSONObject product = new JSONObject(cartlist.get(i));
			if (product.getString("merId").equals(merId)) {
				jedis.lrem(cartKey, 0, product.toString());
			}
		}
		jedis.close();
	}
	
	public static void deleteCartListbyBusId (String memberId, List<String> cartlist, String busId) throws JSONException {
		String cartKey = new StringBuilder("member").append(":").append(memberId).append(":").append("cart").toString();
		Jedis jedis = pool.getResource();

		for (int i = 0; i < cartlist.size(); i++) {
			JSONObject product = new JSONObject(cartlist.get(i));
			if (product.getString("busId").equals(busId)) {
				jedis.lrem(cartKey, 0, product.toString());
			}
		}
		jedis.close();
	}

}
