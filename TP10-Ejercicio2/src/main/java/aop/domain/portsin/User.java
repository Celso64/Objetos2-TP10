package aop.domain.portsin;

public record User(Integer id, String userName) {

	public boolean sameId(int id) {
		return this.id.equals(id);
	}
	
	public boolean sameUsername(String name) {
		return this.userName.equals(name);
	}
}
