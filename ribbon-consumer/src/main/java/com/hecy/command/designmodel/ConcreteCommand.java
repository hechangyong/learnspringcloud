package com.hecy.command.designmodel;

/**
 * @Author: hecy
 * @Date: 2018/10/19 14:39
 * @Version 1.0
 * 命令的具体实现
 */
public class ConcreteCommand  implements Command{
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        this.receiver.action();
    }
}
