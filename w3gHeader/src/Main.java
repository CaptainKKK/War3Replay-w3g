import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {

		Replay replay = new Replay(new File("C:\\Users\\admin\\Desktop\\War3Replay\\War3W3g\\src\\war3.w3g"));  
        Header header = replay.getHeader();  
        System.out.println();
        System.out.println();
        System.out.println();
		System.out.println("WAR3录像基本信息为：");  
        System.out.println("版本：1." + header.getVersionNumber() + "." + header.getBuildNumber()); 
       
        long flag= header.getFlag();
        if(flag== 32768) {
        	System.out.println("游戏模式：多人游戏");
        }else {
        	System.out.println("游戏模式：单人游戏");
        }
        long time= header.getDuration();
        long min= (time/1000)/60;
        long second= (time/1000)%60;
        System.out.println("游戏时间:"+min+"分"+second+"秒");
        
		
		
	}

}
