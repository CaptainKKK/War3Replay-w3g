import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {

		Replay replay = new Replay(new File("C:\\Users\\admin\\Desktop\\War3Replay\\War3W3g\\src\\war3.w3g"));  
        Header header = replay.getHeader();  
        System.out.println();
        System.out.println();
        System.out.println();
		System.out.println("WAR3¼�������ϢΪ��");  
        System.out.println("�汾��1." + header.getVersionNumber() + "." + header.getBuildNumber()); 
       
        long flag= header.getFlag();
        if(flag== 32768) {
        	System.out.println("��Ϸģʽ��������Ϸ");
        }else {
        	System.out.println("��Ϸģʽ��������Ϸ");
        }
        long time= header.getDuration();
        long min= (time/1000)/60;
        long second= (time/1000)%60;
        System.out.println("��Ϸʱ��:"+min+"��"+second+"��");
        
		
		
	}

}
