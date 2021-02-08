package javajuc.day2.priorityqueue;

/**
 * @Author:Hardy
 * @QQ:2937270766
 * @官网：http://www.yuandengta.com
 * 已经有20个客户排队，银行谁钱多谁先来办理业务
 * 优先级标准：钱多
 */
public class Human {
    // 金额
    private int money;
    // 客户姓名
    private String name;

    public Human(int money,String name){
        this.money = money;
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Human{" +
                "money=" + money +
                ", name='" + name + '\'' +
                '}';
    }
}
