package com.wudoumi.battertest.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;



public class SuperListBean<T> extends Base {


	private List<T> value = new ArrayList<>();

	public List<T> getValue() {
		return value;
	}

	public void setValue(List<T> value) {
		this.value = value;
	}

	public static SuperListBean<News> getNewsBean(String json) {
		SuperListBean<News> temp = null;
		try {

			Gson gson = new Gson();

			Type type = new TypeToken<SuperListBean<News>>() {
			}.getType();

			temp = gson.fromJson(json, type);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}


	@Override
	public String toString() {
		return "SuperListBean{" +
				"value=" + value +
				'}';
	}
}
