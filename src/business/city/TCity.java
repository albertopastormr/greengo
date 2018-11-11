package business.city;

public class TCity {

	private Integer id;
	private String name;
	private Boolean active;

	public TCity(){}

	public TCity(Integer id, String name, Boolean active) {
		this.id = id;
		this.name = name;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Boolean isActive() {
		return active;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString(){
		String ret = "";
        Integer addressLength = name.length();
        if(addressLength <= 8) addressLength = 9;
        String regex = "%-" + addressLength + "s %" + addressLength + "s %n";
		ret += String.format(regex, "Id: ", id);
		ret += String.format(regex, "Name: ", name);
		ret += String.format(regex, "Active: ", active);

		return ret;
	}
}
