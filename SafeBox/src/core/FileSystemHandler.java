package core;

import java.io.Serializable;
import java.util.ArrayList;


public class FileSystemHandler implements Serializable{
	private static final long serialVersionUID = -5417133060210085474L;
	private Node currentNode;
	public Tree contents;
	
	@Override
	public String toString() {
		return "FileSystemHandler [currentNode=" + currentNode + ", contents=\n" + contents + "]";
	}


	public FileSystemHandler(){
		contents = new Tree();
		currentNode = contents.getRoot();
	}

	
	///////////
	//GETTERS//
	///////////
	public Node getRoot(){
		return contents.getRoot();
	}
	
	public Node getCurrent(){
		return currentNode;
	}
	
	public Tree getContents(){
		return contents;
	}
	////////////////
	//MANIPULATION//
	////////////////
	/**
	 * @param parent the parent of the new node
	 * @param name the new folder's name
	 * @return the node just created
	 */
	public Node createFolder(Node parent, String name){
		Folder folderToAdd = new Folder();
		folderToAdd.setName(name);
		
		Node nodeToAdd = new Node(folderToAdd);
		contents.addNode(parent, nodeToAdd);
		return nodeToAdd;
	}
	
	
	/**
	 * @param parent the parent of the new node
	 * @param name the new record's name
	 * @return the node just created
	 */
	public Node createRecord(Node parent, String name){
		Record recordToAdd = new Record();
		recordToAdd.setName(name);
		Node nodeToAdd = new Node(recordToAdd);
		contents.addNode(parent, nodeToAdd);
		return nodeToAdd;
	}
	
	/**
	 * @param parent the node that is the parent of the node being deleted
	 * @param index the index in the tree of the folder being deleted
	 * @return
	 */
	public boolean deleteFolder(Node parent, int index){
		//TODO: Start from last index, remove inward
		//TODO: Set nodeList pointer to null
		if (parent == null){
			return false;
		}
		else{
			Node temp = parent.getChild(index);
			parent.removeChild(index);
			int size = temp.getChildren().size();
			for(int i = size; i >= 0; i--){
				if(temp.getChild(i).hasChildren()){
					deleteFolder(temp, i);
				}
				contents.getNodeList().set(temp.getChild(i).getGlobalIndex(), null);
				temp.getChildren().remove(i);
			}
			return true;
		}
	}
	
	/**
	 * @param query the name being searched for in the tree
	 * @param startNode the node of the folder where the search starts in
	 * @return
	 */
	public ArrayList<Node> search(String query, Node startNode){
		ArrayList<Node> toReturn = new ArrayList<Node>();
		int size = contents.getMaxGlobalIndex();
		String lowercaseQuery = query.toLowerCase();
		Node node;
		
		for (int i = 0; i <=size; i++){
			node = contents.getNodeByGlobalIndex(i);
			
			if (node == null){
				continue;
			}
			
			if(node.getData().getName().toLowerCase().contains(lowercaseQuery)){
				toReturn.add(contents.getNodeByGlobalIndex(i));
			}
		}
		return toReturn;
	}
	
	public static void main(String[] args){
		FileSystemHandler fsh = new FileSystemHandler();
		fsh.createFolder(fsh.getCurrent(), "One Folder");
		fsh.createRecord(fsh.getCurrent(), "Two Folder");
		fsh.createRecord(fsh.getCurrent(), "Not searched");
		fsh.createRecord(fsh.getCurrent().getChild(0), "Three Folder");
		ArrayList<Node> temp = fsh.search("folder", fsh.getContents().getRoot());
		for(int i = 0; i < temp.size(); i++){
			System.out.println(temp.get(i));
		}
		fsh.deleteFolder(temp.get(0), 0);
		for(int i = 0; i < temp.size(); i++){
			System.out.println(temp.get(i));
		}
	}
}
