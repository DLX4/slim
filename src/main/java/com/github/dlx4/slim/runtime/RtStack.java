package com.github.dlx4.slim.runtime;

import com.github.dlx4.slim.symbol.Variable;

import java.util.Stack;

/**
 * @program: slim
 * @description: 运行时的栈
 * @author: dlx
 * @created: 2020/07/03 00:13
 */
public class RtStack {

    private Stack<StackFrame> stack = new Stack<>();

    /**
     * @param variable
     * @Description: 变量运行时的左值
     * @return: com.github.dlx4.slim.runtime.LeftValue
     * @Creator: dlx
     */
    public LeftValue getLeftValue(Variable variable) {
        StackFrame frame = stack.peek();

        RtStore store = null;
        while (frame != null) {
            // 匹配作用域中的symbol
            if (frame.getScope().containsSymbol(variable)) {
                store = frame.getRtStore();
                break;
            }
            frame = frame.getParentFrame();
        }

        // 普通的函数作用域找不到，就从闭包里找
        if (store == null) {
            frame = stack.peek();
            while (frame != null) {
                if (frame.contains(variable)) {
                    store = frame.getRtStore();
                    break;
                }
                frame = frame.getParentFrame();
            }
        }

        LeftValue leftValue = LeftValue.builder()
                .rtStore(store)
                .variable(variable)
                .build();

        return leftValue;
    }

    /**
     * @param
     * @Description: 弹出栈
     * @return: void
     * @Creator: dlx
     */
    public void pop() {
        stack.pop();
    }

    /**
     * @param frame
     * @Description: 入栈
     * @return: void
     * @Creator: dlx
     */
    public void push(StackFrame frame) {
        if (stack.size() > 0) {
            // 从栈顶到栈底依次查找
            for (int i = stack.size() - 1; i > 0; i--) {
                StackFrame frameInStack = stack.get(i);

                // 如果新加入的栈桢，跟某个已有的栈桢的enclosingScope是一样的，那么这俩的parentFrame也一样。
                // 因为它们原本就是同一级的嘛。
                // 比如：
                // void foo(){};
                // void bar(foo());
                // 或者：
                // void foo();
                // if (...){
                //     foo();
                // }

                if (frameInStack.getScope().getEnclosingScope() == frame.getScope().getEnclosingScope()) {
                    frame.setParentFrame(frameInStack.getParentFrame());
                    break;
                }

                //  如果新加入的栈桢，是某个已有的栈桢的下一级，那么就把把这个父子关系建立起来。比如：
                // void foo(){
                //     if (...){  //把这个块往栈桢里加的时候，就符合这个条件。
                //     }
                // }
                // 再比如,下面的例子:
                // class MyClass{
                //     void foo();
                // }
                // MyClass c = MyClass();  //先加Class的栈桢，里面有类的属性，包括父类的
                // c.foo();                //再加foo()的栈桢
                else if (frameInStack.getScope() == frame.getScope().getEnclosingScope()) {
                    frame.setParentFrame(frameInStack);
                    break;
                }

            }

            if (frame.getParentFrame() == null) {
                frame.setParentFrame(stack.peek());
            }
        }

        stack.push(frame);

        dumpStackFrame();
    }

    /**
     * @param
     * @Description: 打印栈帧
     * @return: void
     * @Creator: dlx
     */
    private void dumpStackFrame() {
        System.out.println("\nStack Frames -->");
        for (StackFrame frame : stack) {
            System.out.println(frame);
        }
        System.out.println("<-- Stack Frames\n");
    }
}