package scenario;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import components.Point;

public class Tree {
    private Node<Point> root;

    public Tree(Node<Point> rootData) {
        this.root = rootData;
    }
    
    public Tree(Point rootData, Point leafData, ScenarioManager sm){
    	this.root = new Node<Point>();
    	this.root.setData(rootData);
    	this.root.setParent(new Node<Point>());
    	List<Node<Point>> rootNeigh = transformToNode(sm.getAllNeighbors(rootData, null, false));
    	List<Node<Point>> list = new LinkedList<Tree.Node<Point>>();
    	list.add(new Node<Point>(leafData));
    	Node<Point> result = contains(rootNeigh, list) ;
    	if(result != null){
    		root.setChildren(list);
    	} else {
	    	List<Node<Point>> neighborsLeaf = transformToNode(sm.getAllNeighbors(leafData, null, true));
	    	constructorTree(new Node<Point>(), this.root, null, leafData, neighborsLeaf, sm);
    	}
    }
    
    private void constructorTree(Node<Point> resultTree, Node<Point> parent, 
    		List<Point> neighborsActual, Point destination,
		List<Node<Point>> neighborsLeaf, ScenarioManager sm) {
    	if(parent != null && !parent.isNILL()){
	    	List<Point> neighbors = sm.getAllNeighbors(parent.getData(), neighborsActual, true);
	    	List<Node<Point>> neighborsTransformed = transformToNode(neighbors);
	    	Node<Point> result = contains(neighborsTransformed, neighborsLeaf);
	    	if(result == null){
		    	parent.setChildren(neighborsTransformed);
		    	for(Node<Point> n : parent.getChildren()){
		    		List<Node<Point>> resultTreeChildrens = new LinkedList<Node<Point>>();
		    		resultTreeChildrens.add(new Node<Point>());
		    		resultTree.setChildren(resultTreeChildrens);
		    		constructorTree(resultTree.getChildren().get(0), 
		    				n, neighbors, destination, neighborsLeaf, sm);
		    		resultTree.setData(n.getData());
		    	}
	    	} else {
	    		List<Point> neighborsToResult = sm.getAllNeighbors(result.getData());
	    		for(Point ng : neighborsToResult ){
	    			if(ng.equals(destination)){
	    				resultTree.setData(ng);
	    			}
	    		}
	    	}
    	}
	}
    
    public Node<Point> contains(List<Node<Point>> children, List<Node<Point>> neighborsLeaf) {
    	if(children.isEmpty() || neighborsLeaf.isEmpty()){
    		return null;
    	}
		for(Node<Point> nChild : children){
			for(Node<Point> nLeaf : neighborsLeaf){
				if(nChild.equals(nLeaf)){
					return nChild;
				}
			}
		}
		return null;
	}

	private List<Node<Point>> transformToNode(List<Point> allNeighbors) {
		List<Node<Point>> nodes = new ArrayList<Node<Point>>();
		for(Point p : allNeighbors){
			nodes.add(new Node<Point>(p));
		}
		return nodes;
	}

	public void addChildrens(Node<Point> parent, List<Node<Point>> children){
    	addChildrens(root, parent, children);
    }
    
    private void addChildrens(Node<Point> actualNode, 
    		Node<Point> parent, List<Node<Point>> children){
    	if(actualNode.equals(parent)){
			actualNode.setChildren(children);
		} else {
	    	if(!actualNode.getChildren().isEmpty()){
		    	for(Node<Point> n : actualNode.getChildren()){
		    		if(actualNode.isNILL()){
		    			return;
		    		} else {
		    			addChildrens(n, parent, children);
		    		}
		    	}
	    	}
		}
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
        
        public Node(T data){
        	this.data = data;
        }
        
		public Node(){}
        
		public T getData() {
			return data;
		}
		public boolean isNILL() {
			if(children == null && data == null){
				return true;
			}
			return false;
		}
		public void setData(T data) {
			this.data = data;
		}
		public Node<T> getParent() {
			return parent;
		}
		public void setParent(Node<T> parent) {
			this.parent = parent;
		}
		public List<Node<T>> getChildren() {
			return children;
		}
		public void setChildren(List<Node<T>> children) {
			for(Node<T> n : children){
				n.setParent(this);
			}
			this.children = children;
		}
		
		@Override
		public boolean equals(Object obj){
			if(!(obj instanceof Node)){
				return false;
			}
			
			Node<T> n = (Node<T>) obj;
			return this.data.equals(n.getData());
		}
    }
}