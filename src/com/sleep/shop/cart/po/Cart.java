package com.sleep.shop.cart.po;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	private Map<Integer,CartItem> map = new LinkedHashMap<>();
	public Collection<CartItem> getCartitem(){
		return map.values();
	}
	private double total;
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	//����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		Integer pid = cartItem.getProduct().getPid();
		// �жϹ��ﳵ���Ƿ��Ѿ����ڸù�����:
		if(map.containsKey(pid)){
			// ����
			CartItem _cartItem = map.get(pid);// ��ù��ﳵ��ԭ���Ĺ�����
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}else{
			// ������
			map.put(pid, cartItem);
		}
		// �����ܼƵ�ֵ
		total += cartItem.getSubtotal();	
	}
	// �ӹ��ﳵ�Ƴ�������
		public void removeCart(Integer pid) {
			// ���������Ƴ����ﳵ:
			CartItem cartItem = map.remove(pid);
			// �ܼ� = �ܼ� -�Ƴ��Ĺ�����С��:
			total -= cartItem.getSubtotal();
		}

		// ��չ��ﳵ
		public void clearCart() {
			// �����й��������
			map.clear();
			// ���ܼ�����Ϊ0
			total = 0;
		}
}
