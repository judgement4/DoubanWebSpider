package spider;

public class InfoModel {
	
	private String sns;         //�㲥����������
	private String updateTime;  //�㲥�ĸ���ʱ��
	private String source;      //�㲥��ԴWEB����APP
	private String ifshared;    //�㲥Ϊת������ԭ��
	private int id;
	
	
	
	public String getIfshared() {
		return ifshared;
	}
	public void setIfshared(String ifshared) {
		this.ifshared = ifshared;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSns() {
		return sns;
	}
	public void setSns(String sns) {
		this.sns = sns;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public String toString(){
		return sns+"\n"+updateTime+"\n"+source+"\n";
	}

}
