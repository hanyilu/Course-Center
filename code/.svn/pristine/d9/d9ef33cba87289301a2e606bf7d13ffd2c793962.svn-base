package businesslogic.coursebl;

public class CourseTime {
	
	private String time;
	private int day;
	private int start;
	private int num;
	
	public CourseTime(){
		
	}
	
	public CourseTime(String time){
		this.time = time;
		toDate();
	}
	
	public CourseTime(int d,int s,int n){
		day =d;
		start = s;
		num = n;
		setTime();
	}
	
	public String toString(){
		return day+"."+start+"."+num;
	}
	
	public boolean isColli(CourseTime c){
		if(c.getDay()==day){
			int i= c.getStart()-start;
			if(((i > 0) && (i < num))||((i < 0) && (-i < c.getNum()) || i == 0))
				return true;
		}
		return false;
	}
	
	public String getTime(){
		return time;
	}
	
	public int getDay(){
		return day;
	}
	
	public int getStart(){
		return start;
	}
	
	public int getNum(){
		return num;
	}
	
	public static int dayToInt(char charAt){
		switch(charAt){
		case '一':return 1;
		case '二':return 2;
		case '三':return 3;
		case '四':return 4;
		case '五':return 5;
		case '六':return 6;
		default:return 7;
		}
	}
	
	private void setTime() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(Day());
		sb.append("第");
		sb.append(start);
		if(num>1){
			sb.append("~");
			sb.append(start+num-1);
		}
		sb.append("节");
		time = sb.toString();	
	}
	
	private String Day() {
		// TODO Auto-generated method stub
		switch(day){
		case 1:return "周一";
		case 2:return "周二";
		case 3:return "周三";
		case 4:return "周四";
		case 5:return "周五";
		case 6:return "周六";
		default:return "周日";
		}
	}
	
	private void toDate() {
		// TODO Auto-generated method stub
		setDay(dayToInt(time.charAt(1)));
		setStart(Integer.valueOf(time.substring(3,4)));
		
		if(time.contains("~")){
			String s = time.substring(time.indexOf("~")+1,time.indexOf("节"));
			setNum(Integer.valueOf(s)-start+1);
		}else{
			setNum(1);
		}
	}
	
	private void setNum(int i) {
		// TODO Auto-generated method stub
		num = i;
	}
	
	private void setStart(int i) {
		// TODO Auto-generated method stub
		start = i;
	}
	
	private void setDay(int i) {
		// TODO Auto-generated method stub
		day = i;
	}
}
