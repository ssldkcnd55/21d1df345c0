package com.hg.second.test.model.vo;

//ajax 통신은 네트워크 입출력이다.
//네트워크 입출력은 바이트 스트림만 제공됨
//객체 입출력(Object IO)도 바이트 스트림만 제공됨
//객체를 네트워크 입출력에 사용하려면 반드시 직렬화해야 함
public class Sample {
	//역직렬화를 위한 아이디 설정 반드시 해야 함
	private static final long serialVersionUID = 3333L;
	
	private String name;
	private int age;
	
	public Sample() {}

	public Sample(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return this.name + ", " + this.age;
	}
}
