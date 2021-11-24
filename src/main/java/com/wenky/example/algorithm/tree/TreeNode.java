package com.wenky.example.algorithm.tree;

/**
 * @program: example-algorithm-io-excel-crawler
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2021-03-25 14:26
 */
public class TreeNode<E> {
  private E element;
  private TreeNode<E> left;
  private TreeNode<E> right;

  public TreeNode(E element) {
    this.element = element;
  }

  public E getElement() {
    return element;
  }

  public void setElement(E element) {
    this.element = element;
  }

  public TreeNode<E> getLeft() {
    return left;
  }

  public void setLeft(TreeNode<E> left) {
    this.left = left;
  }

  public TreeNode<E> getRight() {
    return right;
  }

  public void setRight(TreeNode<E> right) {
    this.right = right;
  }
}
