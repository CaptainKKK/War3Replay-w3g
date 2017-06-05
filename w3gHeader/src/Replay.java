import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Replay {
    public Header header;  
    
    public Replay(File w3gFile) throws Exception{  
          
        byte[] fileBytes = fileToByteArray(w3gFile);  
        header = new Header(fileBytes);  
  
    } 
	
/** 
* ���ļ�ת�����ֽ����� 
* @param w3gFile �ļ� 
* @return �ֽ����� 
* @throws IOException 
*/  
	public byte[] fileToByteArray(File w3gFile) throws IOException {
		
		FileInputStream fileInputStream= new FileInputStream(w3gFile);
		ByteArrayOutputStream  byteArrayOutputStream =new ByteArrayOutputStream ();
		
		byte[] buffer= new byte[1024];
		int n;
		try{
			while((n= fileInputStream.read(buffer)) != -1) {
				byteArrayOutputStream.write(buffer, 0, n);  
			}
		}finally{
			fileInputStream.close();
		}

		return byteArrayOutputStream.toByteArray();	
	}
    public Header getHeader() {  
        return header;  
    }  
}
