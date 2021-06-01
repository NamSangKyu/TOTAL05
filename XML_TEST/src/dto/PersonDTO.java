package dto;

public class PersonDTO {
	private String name;
	private int age;
	//setter/getter
	//기본생성자
	public PersonDTO() {
		super();
		System.out.println("PersonDTO Constructor");
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
	
	
}
