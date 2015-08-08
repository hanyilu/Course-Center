package businesslogicservice.adminblservice;

import java.util.ArrayList;

import vo.UserVo;

public interface AdminBLService {
	
	/**
	 * 管理员添加用户
	 * @param user 用户信息
	 * @return 操作结果
	 */
	public String addUser(UserVo user);
	
	/**
	 * 管理员删除用户
	 * @param user 用户信息
	 * @return 操作结果
	 */
	public String deleteUser(UserVo user);

	/**
	 * 删除某一年级的所有学生
	 * @param registerYear 要删除的学生的注册年份
	 */
	public void deleteUser(int registerYear);
	
	/**
	 * 管理员移交某老师的课程给另一老师，然后删除该老师
	 * @param oldT 要删除的老师账号
	 * @param newT 移交课程的目的老师的账号
	 * @return 操作结果
	 *//*
	public String teacherGiveDel(String oldT, String newT);*/
	
	/**
	 * 管理员修改用户
	 * @param user 用户信息
	 * @return 操作结果
	 */
	public String modifyUser(UserVo user);
	
	/**
	 * 管理员得到所有的用户信息
	 * @return 所有的用户信息的list
	 */
	public ArrayList<UserVo> getUser();
	
	/**
	 * 管理员得到某一角色的所有用户信息
	 * @param role 用户角色
	 * @return 角色为role的用户信息的list
	 */
	public ArrayList<UserVo> getUser(String role);
	
	/**
	 * 管理员得到某院系的所有某角色的用户信息
	 * @param de 院系
	 * @param role 角色
	 * @return 该院系的所有指定角色的用户信息
	 */
	public ArrayList<UserVo> getDeUser(String de, String role);
	
	/**
	 * 得到某院系的所有用户信息
	 * @param department 院系
	 * @return 该院系的所有用户信息
	 */
	public ArrayList<UserVo> getDeUser(String department);
	
	/**
	 * 通过id得到特定用户信息
	 * @param id
	 * @return 工号为id的用户信息
	 */
	public UserVo getUserById(String id);
	
	/**
	 * 得到当前已有的所有院系
	 * @return 院系信息
	 */
	public ArrayList<String> getDepartment();
	
	/**
	 * 管理员登录
	 * @param password 密码
	 * @return 登录验证结果
	 */
	public boolean login(String password);
	
	/**
	 * 管理员修改密码
	 * @param old 旧密码
	 * @param new1 新密码
	 * @param new2 新密码
	 * @return 修改密码验证结果
	 */
	public String changePassword(String old,String new1,String new2);
}
