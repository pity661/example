package com.wenky.example.algorithm.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: huwenqi
 * @email: huwenqi@olading.com
 * @create: 2022-01-10 09:09
 */
public class RightWatch {
    private Integer element;
    private RightWatch left;
    private RightWatch right;
    private Integer level;

    public RightWatch(Integer element) {
        this.element = element;
    }

    public Integer getElement() {
        return element;
    }

    public void setElement(Integer element) {
        this.element = element;
    }

    public RightWatch getLeft() {
        return left;
    }

    public void setLeft(RightWatch left) {
        this.left = left;
    }

    public RightWatch getRight() {
        return right;
    }

    public void setRight(RightWatch right) {
        this.right = right;
    }

    public Integer getLevel() {
        return level;
    }

    public RightWatch setLevel(Integer level) {
        this.level = level;
        return this;
    }

    private static Map<Integer, List<Integer>> cache = new HashMap<>();

    public static void handle(RightWatch treeNode) {
        if (treeNode == null) {
            return;
        }
        LinkedList<RightWatch> list = new LinkedList<>();
        list.add(treeNode);
        while (!list.isEmpty()) {
            RightWatch current = list.poll();
            Integer level = current.getLevel();
            List<Integer> levelList = cache.get(level);
            if (levelList == null) {
                levelList = new ArrayList<>();
                cache.put(level, levelList);
            }
            levelList.add(current.getElement());

            RightWatch left = current.getLeft();
            if (left != null) {
                left.setLevel(level + 1);
                list.offer(left);
            }

            RightWatch right = current.getRight();
            if (right != null) {
                right.setLevel(level + 1);
                list.offer(right);
            }
        }
    }

    public static void main(String[] args) {
        RightWatch treeNode = createTree();
        treeNode.setLevel(1);
        handle(treeNode);
        List<Integer> result =
                cache.values().stream()
                        .map(list -> list.get(list.size() - 1))
                        .collect(Collectors.toList());
        System.out.println(result);
    }

    public static RightWatch createTree() {
        RightWatch treeNode1 = new RightWatch(1);
        RightWatch treeNode2 = new RightWatch(2);
        RightWatch treeNode3 = new RightWatch(3);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        RightWatch treeNode4 = new RightWatch(4);
        RightWatch treeNode5 = new RightWatch(5);
        treeNode2.setRight(treeNode5);
        treeNode3.setRight(treeNode4);
        return treeNode1;
    }
}
