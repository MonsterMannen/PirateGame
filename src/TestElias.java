
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Viktor
 */
public class TestElias {
	
	public static void main(String[] args){
		try {
			TestElias.test();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(TestElias.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	public static void test() throws FileNotFoundException {
		Scanner s = new Scanner(new File("xd.txt"));
		System.out.println(s.next());
		System.out.println(s.next());
		System.out.println(s.nextInt());
		System.out.println(s.next());
		s.close(); 
	}
	
}
