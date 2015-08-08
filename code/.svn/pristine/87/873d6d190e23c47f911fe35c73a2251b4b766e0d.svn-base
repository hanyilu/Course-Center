package po;

import java.io.Serializable;
import vo.ControlVo;

public class ControlPo implements Serializable {
	
	private static final long serialVersionUID = 000;
	
	private int flag;	//代表当前学期，如：20131表示2012~2013学年第一学期，20132则表示2012~2013学年第二学期
	private int state;	//当前状态
		
	public ControlPo(int f,int s){
		flag = f;
		state = s;
	}
	
	public ControlPo(ControlVo c){
		flag = c.getFlag();
		state = c.getState();
	}
	
	public int getFlag(){
		return this.flag;
	}
		
	public int  getState(){
		return this.state;
	}
}
