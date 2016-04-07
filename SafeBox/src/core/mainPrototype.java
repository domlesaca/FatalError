package core;
import java.util.Scanner;

public class mainPrototype {

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		EncryptedFileSystemManager efsm = new EncryptedFileSystemManager();
		FileSystemHandler fileSys = efsm.fileSystem;
		Tree tree = efsm.fileSystem.contents;
		Node current = tree.getRoot();
		Node root = tree.getRoot();
		String choice = "";
		boolean exit = false;
		while (!exit)
		{
			System.out.println(current.toString());
			System.out.println("Select Folder/Record Number or \n[F]older\n[R]ecord\n[B]ack\n[E]xit");
			choice = scan.nextLine();
			if(choice.equals("F") || choice.equals("f")){
				System.out.println("What would you like to call your Folder?");
				String fName = scan.nextLine();
				current.addChild(fileSys.createFolder(current, fName));
			}
			else if(choice.equals("R") || choice.equals("r")){
				System.out.println("What would you like to call your Record?");
				String rName = scan.nextLine();
				current.addChild(fileSys.createRecord(current, rName));
			}
			else if(choice.equals("B") || choice.equals("b")){
				if(current.getParent() != null){
					current = current.getParent();
				}
				else{
					System.out.println("You are at the home directory!");
				}
			}
			else if(choice.equals("e") || choice.equals("E")){
				exit = true;
			}
			else if(choice.charAt(0) >48 && choice.charAt(0)<= 57){
				int index = Integer.parseInt(choice);
				if(index <= current.getChildren().size() && index > 0){
					current = current.getChild(index-1);
				}
			}
			
		}
		System.out.println("Bye!");
	}

}
