package pptest;

public class DBHelperUnitTest {
	public static void main(String args[]) {
		DatabaseHelper x = new DatabaseHelper();
		x.createConnection();
		if(x.insertSession("July 30th, 2021", "20:02:01", 20)==0) {
			System.out.println("Succesfully inserted data!!");
		}
	}
}
