
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class FileMain 
{
	File fun = new File("fun.dat");
	byte[] time;
	byte[] age;
	byte[] name;
	Scanner reader = new Scanner(System.in);  // Reading from System.in
	public FileMain()
	{
	}
	
	public void addItem() throws IOException
	{
		FileWriter adder = new FileWriter(fun);
		System.out.println("Enter an item: ");
		String input = reader.nextLine(); 
		reader.close();
		
		adder.append(input);
		adder.close();
	}
	
	public void RAFaddItem() throws IOException
	{
		RandomAccessFile adder = new RandomAccessFile(fun, "rw");
		//adder.seek(adder.length());
		System.out.println("Enter an item: ");
		String input = reader.nextLine(); 
		reader.close();
		adder.writeChars(input);
		System.out.println(adder.getFilePointer());
		adder.seek(0);
		System.out.println(adder.getFilePointer());
		adder.seek(adder.length()-2);
		System.out.println(adder.getFilePointer());
		adder.close();
	}
	
}
