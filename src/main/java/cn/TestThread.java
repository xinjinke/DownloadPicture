package cn;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangxinhe
 * @date 2018/3/23 14:29
 */
public class TestThread {

    //配置log4j.properties的位置
//    static {
//        PropertyConfigurator.configure(System.getProperty("user.dir") + File.separator + "conf" + File.separator
//                + "log4j.properties");
//    }

    public static void main(String[] args){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String,DwtThread> threadMap = new HashMap<String, DwtThread>();
        System.out.println("|------------------------------------------------------------------------------------------------------------|");
        System.out.println("|**********************************************爬图程序*******************************************************|");
        System.out.println("|****************************createDate 2018-03-23  @author zxh**********************************************|");
        System.out.println("|*****************************当前时间："+sdf.format(new Date())+"****************************************************|");
        System.out.println("|------------------------------------------------------------------------------------------------------------|");
        int i = 1;
        while(true){
            System.out.println("\n**菜单：0.查看任务列表 1.新建任务 2.开启任务 3.查看任务 4.停止任务 5.任务全部开启 6.退出系统**");
            System.out.println(">>请输入你要进行的操作：");
            Scanner scanner = new Scanner(System.in);
            int command = scanner.nextInt();
            switch(command){
                case 0:
                    System.out.print("<<任务列表：[");
                    for (Map.Entry<String, DwtThread> entry : threadMap.entrySet()) {
                        System.out.print(entry.getKey()+" ");
                    }
                    System.out.println("]");
                    break;
                case 1:
                    Scanner scan = new Scanner(System.in);
//                    System.out.println(">>请输入图片保存根目录:(如：D:\\\\dwp)");
//                    String basePath = scan.nextLine();
                    String basePath = "/Users/xinhezhang/Pictures";
                    System.out.println(">>请输入爬图地址url:(如：http://www.topit.me/user/6356449/albums)");
                    String url = scan.nextLine();

                    System.out.println(">>请输入起始页:");
                    int page = scan.nextInt();

                    System.out.println(">>请输入任务的线程池大小:");
                    int poolSize = scan.nextInt();

                    DwtThread thread = new DwtThread(url,page,poolSize,basePath);
                    String taskName = "task_"+i++;
                    thread.setName(taskName);
                    threadMap.put(taskName,thread);
                    System.out.println("<<创建任务"+ taskName+"成功");
                    break;
                case 2:
                    System.out.println("<<请输入要开启的任务名称：");
                    Scanner scanner2 = new Scanner(System.in);
                    String name = scanner2.nextLine();
                    final DwtThread th = threadMap.get(name);
                    if(th == null){
                        System.out.println(">>任务名称不正确！请重新输入");
                    }else {
                        th.start();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("  当前"+th.getName()+"任务信息：");
                                th.printState();
                            }
                        },10,10000);
                    }
                    break;
                case 3:
                    System.out.println("<<请输入要查看的任务名称：");
                    Scanner scanner3 = new Scanner(System.in);
                    String name3 = scanner3.nextLine();
                    final DwtThread th3 = threadMap.get(name3);
                    if(null == th3){
                        System.out.println(">>任务名称不正确！请重新输入");
                    }else {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                System.out.println("  当前"+th3.getName()+"任务信息：");
                                th3.printState();
                            }
                        },10,10000);
                    }
                    break;
                case 4:
                    System.out.println("<<请输入要停止的任务名称：");
                    Scanner scanner4 = new Scanner(System.in);
                    String name4 = scanner4.nextLine();
                    DwtThread th4 = threadMap.get(name4);
                    if(null == th4){
                        System.out.println(">>任务名称不正确！请重新输入");
                    }else {
                        th4.printState();
                        th4.getThreadPool().shutdown();
                    }
                    break;
                case 5:
                    for (Map.Entry<String, DwtThread> entry : threadMap.entrySet()) {
                            entry.getValue().start();
                    }
                    break;
                case 6:
                    System.out.println("****************退出系统中*******************");
                    System.exit(0);
                    break;
            }
        }
    }
}
