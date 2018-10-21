package com.hecy.command.designmodel;

/**
 * @Author: hecy
 * @Date: 2018/10/19 14:41
 * @Version 1.0
 */
public class Invoker {
    private Command command;

    public void action(){
        this.command.execute();
    }


    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }



}
