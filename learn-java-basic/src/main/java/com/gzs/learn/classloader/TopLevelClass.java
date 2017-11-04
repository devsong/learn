package com.gzs.learn.classloader;

import lombok.Data;

@Data
public class TopLevelClass {
    private static int s = 1;
    private int inta = 1;
    MemberClass memberClass = new MemberClass();
    StaticClass staticClass = new StaticClass();

    public TopLevelClass() {
        // inta = 3;
    }

    public void doObjMethod() {
        System.out.println("in obj method");
    }

    public static void doStaticMethod() {
        System.out.println("in static method");
    }

    class MemberClass {
        public MemberClass() {
            inta = 2;
            doObjMethod();
        }
    }

    static class StaticClass {
        public StaticClass() {
            s = 2;
            doStaticMethod();
        }
    }

    public static void main(String[] args) {
        TopLevelClass class1 = new TopLevelClass();
        System.out.println(class1.getInta());
        System.out.println(s);
    }
}


class IndependentClass {
    IndependentClass() {}
}
