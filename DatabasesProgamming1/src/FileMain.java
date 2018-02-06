import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
//Ben Lehman
//Contains all of the methods for the program
public class FileMain 
{
	//The file and scanner used throughout the class
	//itemCOunt counts the amount of items in the file
	File fun = new File("fun.dat");
	static Scanner reader = new Scanner(System.in);  // Reading from System.in
	static int itemCount = 0;
	
	public FileMain()
	{
	}
	
	//Main to run the program
	//Has a series of if statements to decide what action to take.
	public static void main (String [] args) throws IOException
	{
		boolean continueProg = true;
		
		FileMain main = new FileMain();
		main.fileClear();
		do
		{
			System.out.println("Add, Edit, or Stop: ");
			String input = "";
			input = reader.nextLine().toLowerCase();
			if(input.equals("add"))
			{
				main.RAFaddItem();
			}
			else if(input.equals("edit"))
			{
				if(itemCount == 0)
				{
					System.out.println("No Items to Edit. Redirecting to Add");
					
					main.RAFaddItem();
				}
				else
				{
					main.RAFeditItem();
				}
			}
			else if(input.equals("stop"))
			{
				System.out.println("Stopping Program...");
				
				continueProg = false;
			}
			else
			{
				System.out.println("Invalid Entry. Valid Entries: Add, Edit, Stop");
			}
			
		}while(continueProg);
		
		
		
	}
	//This method allows you to add a new item to the end of the file 
	//It uses a Random Access File to write the inputs into the file
	public void RAFaddItem() throws IOException
	{
		RandomAccessFile adder = new RandomAccessFile(fun, "rw");
		String time = "";
		String age = "";
		String name = "";
		String fullItem = "";
		itemCount = 1;
		
		
			adder.seek(adder.length());
			
			time = addTime();
			
			age = addAge();
			
			name = addName();
			
			fullItem = time + "," + age + "," + name + '\n';
	
			adder.writeBytes(fullItem);
			
	
		adder.close();
	}

	
	//This method allows the user to edit a line in the file
	//It asks for the line that they want to edit and then what component they want 
	//Then runs through an if else to choose what to change on the line
	public void RAFeditItem() throws IOException
	{
		RandomAccessFile editor = new RandomAccessFile(fun, "rw");
		long byteNumber=0; 
		boolean entryValid = false;
		long entryNumber;
		
		do
		{
			
			System.out.println("Specify entry to edit (1-" +itemCount+ "): ");
			entryNumber = reader.nextLong();
			
			if((entryNumber > itemCount)||(entryNumber < 1))
			{
				System.out.println("Item doesn't exist");
				entryValid = false;
			}
			else
			{
				entryValid = true;
			}
			
		}while(entryValid == false);
		
		byteNumber = 21 * (entryNumber -1);
		reader.nextLine();
		
		
			System.out.println("Specify component to edit: ");
			String component = reader.nextLine();
		
			component.toLowerCase();
			
			if(component.equals("time"))
			{
				String time = addTime();
				editor.writeBytes(time);
			}
			else if(component.equalsIgnoreCase("age"))
			{
				editor.seek(byteNumber + 6);
				String age = addAge();
				editor.writeBytes(age);
			}
			else if(component.equals("name"))
			{
				editor.seek(byteNumber + 10);
				String name = addName();
				editor.writeBytes(name);
			}
			else
			{
				System.out.println("Invalid entry. Valid Entries: \"time\" , \"age\" , \"name\" ");
			}
			
			
		editor.close();
		
	}
	
	//Use to delete the file to clear it before tests
	public void fileClear()
	{
		fun.delete();
		System.out.println("File cleared");
	}
	
	//Takes input from the user for their name
	private String addName() 
	{
		String name;
		System.out.println("Enter Name: "); 
		name = reader.nextLine();
		while(name.length()<10)
		{
			name = name + ' ';
		}
		name = checkLength(name, 10);
		return name;
	}

	//Takes input from the user for their age
	private String addAge() 
	{
		String age;
		System.out.println("Enter Age: "); 
		age = reader.nextLine(); 
		
		
		age = checkLength(age, 3);
		
		return age;
	}

	//Takes input from the user for the time
	private String addTime() {
		String time;
		System.out.println("Enter Hour(Two Digits): "); 
		String inputHour = reader.nextLine(); 
		
		inputHour = checkLength(inputHour, 2);
		
		System.out.println("Enter Minute(Two Digits): "); 
		String inputMinute = reader.nextLine(); 
		
		
		inputMinute = checkLength(inputMinute, 2);
		
		time = inputHour + ":" + inputMinute;
		return time;
	}
	
	private String checkLength(String target, int length)
	{
		while(target.length() < length)
		{
			target= ' ' + target;
		}
		if(target.length() > length)
		{
			target = target.substring(0, Math.min(target.length(), length));
		}
		
		return target;
	}

	
	
	
	
}
