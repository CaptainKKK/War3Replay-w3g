
public class LittleEndianTool {
    public static int getUnsignedInt16(byte[] bytes, int offset) {  
    	
        int b0 = bytes[offset] & 0xFF;  
        int b1 = bytes[offset + 1] & 0xFF;  
        
        return b0 + (b1 << 8);  
        
    }  
	public static long getUnsignedInt32(byte[] bytes, int begin) {
		
        long b0 = bytes[begin] & 0xFFl;  
        long b1 = bytes[begin + 1] & 0xFFl;  
        long b2 = bytes[begin + 2] & 0xFFl;  
        long b3 = bytes[begin + 3] & 0xFFl; 
		
        return b0 + (b1 << 8) + (b2 << 16) + (b3 << 24);  
	}
    public static String getString(byte[] bytes, int offset, int length) {  
        byte[] temp = new byte[length];  
        for(int i = 0; i < length; i++) {  
            temp[i] = bytes[offset + length - i - 1];  
        }
        
        return new String(temp);
    }  
}
