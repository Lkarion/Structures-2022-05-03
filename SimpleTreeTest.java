package tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleTreeTest {
    SimpleTree tree;

    @BeforeEach
    void init() {
        tree = new SimpleTree();
    }

    @Test
    void hasNext_True_1Element(){
        tree.insertLeafInTree("qwer", 1);
        assertTrue(tree.iterator().hasNext());
    }

    @Test
    void hasNext_True_ManyElements(){
        tree.insertLeafInTree("qwer", 1);
        tree.insertLeafInTree("asdf", 2);
        tree.insertLeafInTree("yxcv", 3);
        assertTrue(tree.iterator().hasNext());
    }

    @Test
    void hasNext_False(){
        assertFalse(tree.iterator().hasNext());
    }

    @Test
    void next_CheckKey(){
        tree.insertLeafInTree("qwer", 1);
        assertEquals(1, tree.iterator().next().key);
    }

    @Test
    void next_ForEachLoop(){
        String s = "";
        tree.insertLeafInTree("qwer", 1);
        tree.insertLeafInTree("asdf", 2);
        tree.insertLeafInTree("yxcv", 3);
        for (SimpleTree.Node node: tree) {
            s += node.key;
        }
        assertEquals("123", s);
    }


/*
    @Test
    void getRoot() {
    }

    @Test
    void getRoot() {
    }

    @Test
    void printKey() {
    }

    @Test
    void insertLeafInTree() {
    }

    @Test
    void deleteFromTree() {
    }

    @Test
    void same_Yes() {
        tree.insertLeafInTree("qwer", 1);
        tree.insertLeafInTree("asdf", 2);
        tree.insertLeafInTree("asdf", 6);
        tree.insertLeafInTree("yxcv", 3);
        assertTrue(SimpleTree.same(tree));
    }

    @Test
    void same_No_allLeavesAreRight() {
        tree.insertLeafInTree("qwer", 1);
        tree.insertLeafInTree("asdf", 2);
        tree.insertLeafInTree("yxcv", 3);
        tree.insertLeafInTree("ert", 5);
        tree.insertLeafInTree("yxcsegfysv", 8);
        assertFalse(SimpleTree.same(tree));
    }

    @Test
    void same_Yes_allLeavesAreRight() {
        tree.insertLeafInTree("qwer", 1);
        tree.insertLeafInTree("asdf", 2);
        tree.insertLeafInTree("yxcv", 3);
        tree.insertLeafInTree("ert", 5);
        tree.insertLeafInTree("qwer", 7);
        tree.insertLeafInTree("yxcsegfysv", 8);
        assertTrue(SimpleTree.same(tree));
    }
    @Test
    void same_No_allLeavesAreLeft() {
        tree.insertLeafInTree("qwer", 9);
        tree.insertLeafInTree("asdf", 7);
        tree.insertLeafInTree("yxcv", 5);
        tree.insertLeafInTree("ert", 3);
        tree.insertLeafInTree("yxcsegfysv", 2);
        assertFalse(SimpleTree.same(tree));
    }

    @Test
    void same_1Element() {
        tree.insertLeafInTree("qwer", 1);
        assertFalse(SimpleTree.same(tree));
    }*/
}