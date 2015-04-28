package qianghe.teasales.model;

public enum UserRole {
	ADMIN("系统管理员"),
	SALES("销售员"),
	ACCOUNTANT("财会人员");
	
	private String roleName;
	
	private UserRole(String name) {
		roleName = name;
	}

	public String getRoleName() {
		return roleName;
	}
}
