package mixer_shops.mixer.response;

import lombok.Data;

@Data
public class ApiResponse {
	private String msg;
	private Object data;
	public ApiResponse(String msg, Object data) {
		super();
		this.msg = msg;
		this.data = data;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
