package com.cafe24.mysite5.enumeration;

public enum Enabled{
	/* 열거형 상수 True, False를 true, false와 연결. enum 생성자 호출 */
	True(true), Fasle(false);

	/* --------------------------------------------- */
	private Boolean enabled;

	/* True 또는 False의 생성자 */
	Enabled( boolean b ){
		this.enabled = b;
	}

	/* True(true)에서 연결된 값 true를 반환 */
	public Boolean getEnabled(){
		return enabled;
	}

}

//class testEnabled{
//	public static void main( String[] args ){
//		for ( Enabled enabled : Enabled.values() ) {
//			System.out.println( enabled.getEnabled() );
//		}
//
//		Enabled test = Enabled.True;
//		System.out.println(test.getEnabled());
//	}
//
//}

