package businesslogicservice.userblservice;

public interface UserBLService {
	
	/**
	 * 登陆
	 * @param id 账号
	 * @param password 密码
	 * @return 登陆验证结果
	 */
	public String login(String id, String password);
	
	/**
	 * 修改密码
	 * @param old 旧密码
	 * @param new1 新密码
	 * @param new2 新密码
	 * @return 验证结果
	 */
	public String changePassword(String old, String new1, String new2);
}
