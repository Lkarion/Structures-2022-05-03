package tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

class SimpleTree implements Iterable<SimpleTree.Node>{

    private int size = 0;
    @Override
    public Iterator<Node> iterator() {
        return new SimpleTreeIterator();
    }

    class SimpleTreeIterator implements Iterator<Node>{
        private Node next;

        public SimpleTreeIterator() {
            next = root;
            if(next == null)
                return;

            while (next.leftNode != null)
                next = next.leftNode;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Node next() {
         //   if(!hasNext());
            Node tmp = next;

            if(next.rightNode != null) {
                next = next.rightNode;
                while (next.leftNode != null)
                    next = next.leftNode;
                return tmp;
            }

            while(true) {
                if(next.parent == null) {
                    next = null;
                    return tmp;
                }
                if(next.parent.leftNode == next) {
                    next = next.parent;
                    return tmp;
                }
                next = next.parent;
            }
        }
    }

    class Node{
        String info;
        int key;
        Node parent;
        Node leftNode;
        Node rightNode;
        Node(){
            info = "";
            key = Integer.MIN_VALUE;
            leftNode = null;
            rightNode = null;
            parent = null;
            size++;
        }
        Node(String info, int key){
            this.info = info;
            this.key = key;
            leftNode = null;
            rightNode = null;
            size++;
        }
        Node(String info, int key, Node parent){
            this.info = info;
            this.key = key;
            leftNode = null;
            rightNode = null;
            this.parent = parent;
            size++;
        }
        int compareKeyTo(Node n){
            return (this.key - n.key); // >0 - текущий больше
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            if (info.hashCode() != node.info.hashCode()) return false;
            return info.equals(node.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(info);
        }
    }
    Node root;

    SimpleTree(){
        root = null;
    }

    Node getRoot(){
        return root;
    }

    void printKey(Node node){
        System.out.printf("%5d", node.key);
    }
    void insertLeafInTree(String inf, int k){
        Node currentNode = root;
        Node parentNode = null;
        while (currentNode != null) {
            parentNode = currentNode;
            if (k < currentNode.key) {
                currentNode = currentNode.leftNode;
            }
            else if (k > currentNode.key) {
                currentNode = currentNode.rightNode;
            }
            else {
                // if k == currentNode.key
                currentNode.info = inf;
                return;
            }
        } // while

        Node newNode = new Node(inf, k, parentNode);
        if (parentNode == null ){ // создаем корень
            root = newNode;
        }
        else {
            if (k < parentNode.key) {
                parentNode.leftNode = newNode;
            }
            else {
                parentNode.rightNode = newNode;
            }
        }
        size++;
    } // insert

    String search(Node node, int k){
        Node currentNode = root;
        while ((currentNode != null) && (currentNode.key != k)){
            if (k < currentNode.key){
                currentNode = currentNode.leftNode;
            }
            else { // k > result.key
                currentNode = currentNode.rightNode;
            }
        }
        if (currentNode == null){
            return "No such element";
        }
        else {
            return currentNode.info;
        }
    }


    int preOrderinf(Node node, String info, int k ) {

        if (node != null) {
            if (node.info == info) {
                k= k+1;
                preOrderinf(node.leftNode, info, k) ;
                preOrderinf(node.rightNode, info, k);
            }

        }
        return k;
    }


    void preOrder(Node node){
        if (node != null){
            printKey(node);
            preOrder(node.leftNode);
            preOrder(node.rightNode);
        }
    }


   /* int searchkol(Node node, String inf){
        Node currentNode = root;
        int kol = 0;
        while ((currentNode != null) ){
            if (inf == currentNode.info){
                kol += 1;
                currentNode = currentNode.leftNode;
                currentNode = currentNode.rightNode;
            }
        }
        return kol;

    }*/



    void postOrder(Node node){
        if (node != null){
            postOrder(node.leftNode);
            postOrder(node.rightNode);
            printKey(node);
        }
    }

    void inOrder(Node node){
        if (node != null){
            inOrder(node.leftNode);
            printKey(node);
            inOrder(node.rightNode);
        }
    }


    String deleteFromTree(int k){
        String result;
        Node currentNode = root;
        Node parentNode = null;

        while ((currentNode != null) && (currentNode.key != k)){
            parentNode = currentNode;

            if (k < currentNode.key) {
                currentNode = currentNode.leftNode;
            }
            else if (k > currentNode.key){
                currentNode = currentNode.rightNode;
            }
        } // while
        if (currentNode == null){
            return result = "No such node";
        }
        // k = currentNode.key
        result = currentNode.info;
        if (currentNode.rightNode == null){ // нет правого поддерева
            if (parentNode == null){ // это корень
                root = currentNode.leftNode;
                if (currentNode.leftNode != null){
                    currentNode.leftNode.parent = null;
                }
            }
            else if(parentNode.leftNode == currentNode){
                parentNode.leftNode = currentNode.leftNode;
                if (currentNode.leftNode != null) {
                    currentNode.leftNode.parent = parentNode;
                }
            }
            else { // это был правый узел
                parentNode.rightNode = currentNode.leftNode;
                currentNode.leftNode.parent = parentNode;
            }
        } // rightNode == null
        else if (currentNode.leftNode == null){ // нет левого поддерева
            if (parentNode == null) {
                root = currentNode.rightNode;
                if (currentNode.rightNode != null){
                    currentNode.rightNode.parent = null;
                }
            }
            else if (parentNode.leftNode == currentNode){
                parentNode.leftNode = currentNode.rightNode;
                if (currentNode.rightNode != null) {
                    currentNode.rightNode.parent = parentNode;
                }
            }
            else {
                parentNode.rightNode = currentNode.rightNode;
                if (currentNode.rightNode != null) {
                    currentNode.rightNode.parent = parentNode;
                }
            }
        }
        else { // есть оба поддерева - тогда ищем самый правый узел в левом поддереве
            Node rightestNode = currentNode.leftNode;
            parentNode = currentNode;

            while(rightestNode.rightNode != null){
                parentNode = rightestNode;
                rightestNode = rightestNode.rightNode;
            }
            // копируем
            currentNode.info = rightestNode.info;
            currentNode.key = rightestNode.key;
            // удаляем ставший лишним узел
            if (parentNode == currentNode) { // никуда не уходили
                parentNode.leftNode = rightestNode.leftNode;
                if (rightestNode.leftNode != null) {
                    rightestNode.leftNode.parent = parentNode;
                }
            }
            else {
                parentNode.rightNode = rightestNode.leftNode;
                if (rightestNode.leftNode != null) {
                    rightestNode.leftNode.parent = parentNode.rightNode;
                }
            }
        }
        size--;
        return result;
    } // delete


    // Опишите логическую функцию same(T), определяющую, есть ли в дереве T хотя бы два одинаковых элемента
    public static boolean same(SimpleTree tree) {
        List<String> set = new ArrayList<>();
        Node currentNode = tree.root;
        while (currentNode != null) {
            if (set.contains(currentNode.info)) return true;
            else {
                set.add(currentNode.info);
                if (currentNode.leftNode != null) currentNode = currentNode.leftNode;
                else if (currentNode.rightNode != null) currentNode = currentNode.rightNode;
                else if (currentNode.parent != null && currentNode.parent.rightNode != currentNode)
                    currentNode = currentNode.parent.rightNode;
                else if (currentNode.parent.parent != null && currentNode.parent.parent.rightNode != currentNode.parent)
                    currentNode = currentNode.parent.parent.rightNode;
                else break;
            }
        }
        return false;
    }
}
class Tree {
    public static void main(String[] args){
        /*SimpleTree st = new SimpleTree();
        String inf;
        int k;

        inf = "Ale";
        k = 5;
        st.insertLeafInTree(inf, k);


        inf = "Mas";
        k = 1;
        st.insertLeafInTree(inf, k);

        inf = "Ale";
        k = 7;
        st.insertLeafInTree(inf, k);

        inf = "KLe";
        k = 6;
        st.insertLeafInTree(inf, k);
        st.deleteFromTree(1);*/
        SimpleTree tree = new SimpleTree();
        tree.insertLeafInTree("qwer", 1);
        tree.insertLeafInTree("asdf", 2);
        tree.insertLeafInTree("yxcv", 3);


       // System.out.println(st.searchkol(st.getRoot(), "Ale"));

        //   System.out.println(st.search(st.getRoot(), 6));

        //   st.preOrder(st.getRoot());
        //  st.postOrder(st.getRoot());
        // st.inOrder(st.getRoot());




    }
}
