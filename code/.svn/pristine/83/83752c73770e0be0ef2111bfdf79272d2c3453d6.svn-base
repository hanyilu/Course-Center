package vo;

import po.ControlPo;

public class ControlVo {
	private int flag;	//代表当前学期，如：20131表示2012~2013学年第一学期，20132则表示2012~2013学年第二学期
	private int state;	//当前状态
		
	public ControlVo(int f,int s){
		flag = f;
		state = s;
	}
	
	public ControlVo(ControlPo c){
		flag = c.getFlag();
		state = c.getState();
	}
	
	public int getFlag(){
		return this.flag;
	}
	
	public void setFlag(int f){
		this.flag = f;
	}
	
	public int  getState(){
		return this.state;
	}
	
	public void setState(int s){
		this.state = s;
	}
}
