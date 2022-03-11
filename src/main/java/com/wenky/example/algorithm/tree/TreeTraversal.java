package com.wenky.example.algorithm.tree;

import java.util.LinkedList;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-25 14:29
 */
public class TreeTraversal {

    // https://www.cnblogs.com/yongh/p/9629940.html
    public static void main(String[] args) {
        TreeNode<Integer> treeNode = createTree();
        prePrint(treeNode); // 1245367
        System.out.println();
        midPrint(treeNode); // 4251637
        System.out.println();
        postPrint(treeNode); // 4526731
        System.out.println();
        levelPrint(treeNode); //
    }

    public static TreeNode<Integer> createTree() {
        TreeNode<Integer> treeNode1 = new TreeNode<>(1);
        TreeNode<Integer> treeNode2 = new TreeNode<>(2);
        TreeNode<Integer> treeNode3 = new TreeNode<>(3);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        TreeNode<Integer> treeNode4 = new TreeNode<>(4);
        TreeNode<Integer> treeNode5 = new TreeNode<>(5);
        treeNode2.setLeft(treeNode4);
        treeNode2.setRight(treeNode5);
        TreeNode<Integer> treeNode6 = new TreeNode<>(6);
        TreeNode<Integer> treeNode7 = new TreeNode<>(7);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);
        return treeNode1;
    }

    // 二叉树先序遍历
    public static void prePrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.print(treeNode.getElement());
        prePrint(treeNode.getLeft());
        prePrint(treeNode.getRight());
    }

    // 二叉树中序遍历
    public static void midPrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        midPrint(treeNode.getLeft());
        System.out.print(treeNode.getElement());
        midPrint(treeNode.getRight());
    }

    // 二叉树后序遍历
    public static void postPrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        postPrint(treeNode.getLeft());
        postPrint(treeNode.getRight());
        System.out.print(treeNode.getElement());
    }

    // 层序遍历
    public static void levelPrint(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(treeNode);
        while (!list.isEmpty()) {
            TreeNode current = list.poll();
            System.out.print(current.getElement());
            if (current.getLeft() != null) {
                list.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                list.offer(current.getRight());
            }
        }
    }
}
