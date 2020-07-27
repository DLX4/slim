极客时间编译原理之美课后实现的脚本语言，支持函数式编程。

```
//支持闭包

int a = 0;

function int() fun1(){
    int b = 0;

    int inner(){
        a = a+1;
        b = b+1;
        return a+b;
    }

    return inner;
}

function int() fun2 = fun1();
println (fun2());//2
println (fun2());//4
fun2();//6

function int() fun3 = fun1();
println (fun3());//5
println (fun3());//7
fun3();//9
```