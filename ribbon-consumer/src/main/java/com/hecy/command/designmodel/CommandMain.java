package com.hecy.command.designmodel;

/**
 * @Author: hecy
 * @Date: 2018/10/19 14:46
 * @Version 1.0
 */
public class CommandMain {

    public static void main(String[] args) {
        /**
         * 1、Receiver 接收者， 他知道如何处理具体的业务员逻辑
         * 2、抽象命令， 定义一系列的命令操作， 当命令操作被调用的时候就会去触发接受者去做相对应的具体的业务逻辑
         * 3、ConcreteCommand 命令的具体实现，绑定接受者和命令操作。
         * 4、调用者，他持有一个命令对象， 并且可以在需要的时候通过命令对象完成具体的业务逻辑
         */
        Receiver receiver = new Receiver();

        Command command = new ConcreteCommand(receiver);

        Invoker invoker = new Invoker();

        invoker.setCommand(command);
        invoker.action();


    }


}
