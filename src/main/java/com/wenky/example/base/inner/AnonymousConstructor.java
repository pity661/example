package com.wenky.example.base.inner;

/**
 * @program: example
 * @description:
 * @author: wenky
 * @email: huwenqi@panda-fintech.com
 * @create: 2020-10-21 10:57
 */
public class AnonymousConstructor {
    {
        System.out.println("AnonymousConstructor instance");
    }

    AnonymousConstructor() {
        System.out.println("AnonymousConstructor constructor");
    }

    abstract class Base {
        {
            System.out.println("Base instance");
        }

        Base(int i) {
            System.out.println("Base constructor, i = " + i);
        }

        public abstract void f();
    }

    public Base getBase(int i) {
        return new Base(i) {
            {
                System.out.println("getBase instance");
            }

            @Override
            public void f() {
                System.out.println("In anonymous f()");
            }
        };
    }

    /**
     * AnonymousConstructor instance AnonymousConstructor constructor Base instance Base
     * constructor, i = 2 getBase instance In anonymous f()
     *
     * @param args
     */
    public static void main(String[] args) {
        Base base = new AnonymousConstructor().getBase(2);
        base.f();
    }
}
