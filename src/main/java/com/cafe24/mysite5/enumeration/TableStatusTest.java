package com.cafe24.mysite5.enumeration;

public enum TableStatusTest{
	Y( "1", true ), N( "0", false );

	/* Y 또는 N의 첫번째 인자 */
	private String table1Value;
	/* Y 또는 N의 두번째 인자 */
	private Boolean table2Value;

	/* Y 또는 N의 생성자 */
	TableStatusTest( String table1Value, boolean table2Value ){
		this.table1Value = table1Value;
		this.table2Value = table2Value;
	}

	public String getTable1Value(){
		return table1Value;
	}

	public Boolean getTable2Value(){
		return table2Value;
	}

}

//class testTableStatusTest{
//	public static void main( String[] args ){
//		for ( TableStatusTest tableStatusTest : TableStatusTest.values() ) {
//			System.out.println( tableStatusTest.getTable1Value() );
//			System.out.println( tableStatusTest.getTable2Value() );
//
//		}
//		System.out.println( "----------------------" );
//		System.out.println( TableStatusTest.N );  // N
//		System.out.println( TableStatusTest.N.getTable1Value() );  // 0
//		System.out.println( TableStatusTest.N.getTable2Value() );  // false
//		System.out.println(TableStatusTest.Y);  // Y
//		System.out.println(TableStatusTest.Y.getTable1Value());  // 1
//		System.out.println(TableStatusTest.Y.getTable2Value());  // true
//
//
//	}
//
//}
