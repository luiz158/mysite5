package com.cafe24.mysite5.dto;

//프로토콜만 정의해서 해당 형식으로만 반환하도록 한다.
public class JSONResult{
	private String result;	//"success" or "fail"
	private String message; //result == "fail"이면 원인 메시지 전달
	private Object data; 	//result == "success"이면 전달할 데이터 
							//어떤 데이터가 전달될지 모르므로 Object 타입으로 전달.
	private JSONResult( String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	//외부에서 사용할 수 있도록
	//성공했을 때는 전달할 데이터만 넘긴다
	public static JSONResult success( Object data ) {
		return new JSONResult("success", null, data);
	}
	//실패했을 때는 원인 메시지만 넘긴다.
	public static JSONResult fail( String message ) {
		return new JSONResult("fail", message, null);
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
}
