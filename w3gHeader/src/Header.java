import java.util.zip.CRC32;

public class Header {

	public static final String BEGIN_TITLE = "Warcraft III recorded game\u001A\0";  
	private long headSize;
    private long compressedDataSize;  
    private long headerVersion;  
    private long uncompressedDataSize;  
    private long compressedDataBlockCount;  
    private String versionIdentifier;  
    private long versionNumber;  
    public long getHeadSize() {
		return headSize;
	}

	public void setHeadSize(long headSize) {
		this.headSize = headSize;
	}

	public long getUncompressedDataSize() {
		return uncompressedDataSize;
	}

	public void setUncompressedDataSize(long uncompressedDataSize) {
		this.uncompressedDataSize = uncompressedDataSize;
	}

	public long getCompressedDataBlockCount() {
		return compressedDataBlockCount;
	}

	public void setCompressedDataBlockCount(long compressedDataBlockCount) {
		this.compressedDataBlockCount = compressedDataBlockCount;
	}

	public String getVersionIdentifier() {
		return versionIdentifier;
	}

	public void setVersionIdentifier(String versionIdentifier) {
		this.versionIdentifier = versionIdentifier;
	}

	public long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public int getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(int buildNumber) {
		this.buildNumber = buildNumber;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public void setCompressedDataSize(long compressedDataSize) {
		this.compressedDataSize = compressedDataSize;
	}

	public void setHeaderVersion(long headerVersion) {
		this.headerVersion = headerVersion;
	}


	private int buildNumber;  
    private int flag;  
    private long duration;
    
	public Header(byte[] fileBytes) throws Exception {
		
		//1��28�ֽ�
		//Warcraft III recorded game\u001A\0
		String beginTitle= new String(fileBytes,0,28);
		System.out.println("1~28�ֽ�"+ beginTitle);
		if(!beginTitle.equals(BEGIN_TITLE)){
			throw new Exception ("¼���ʽ����ȷ,��ǰ��ʽ");
		}
		
		//29��32�ֽ�
		//�汾��
		headSize = LittleEndianTool.getUnsignedInt32(fileBytes, 28);  
	    System.out.println("29-32�ֽڣ�" + headSize);  
	    if (headSize != 0x44) {  
	    	throw new Exception("��֧��V1.06�����°汾��¼��");  
	    }
	    
        // ��ѹ�����ݴ�С  
        uncompressedDataSize = LittleEndianTool.getUnsignedInt32(fileBytes, 40);  
        System.out.println("41-44�ֽڣ�" + uncompressedDataSize);  
  
        // ѹ�����ݿ�����  
        compressedDataBlockCount = LittleEndianTool.getUnsignedInt32(fileBytes, 44);  
        System.out.println("45-48�ֽ�-ѹ�����ݿ飺" + compressedDataBlockCount);  
  
        // WAR3���Ǳ�������¼��W3XP��������¼��  
        versionIdentifier = LittleEndianTool.getString(fileBytes, 48, 4);  
        System.out.println("49-52�ֽڣ�" + versionIdentifier);  
  
        // �汾�ţ�����1.24�汾��Ӧ��ֵ��24��  
        versionNumber = LittleEndianTool.getUnsignedInt32(fileBytes, 52);  
        System.out.println("53-56�ֽ�-�汾�ţ�" + versionNumber);  
  
        // Build��  
        buildNumber = LittleEndianTool.getUnsignedInt16(fileBytes, 56);  
        System.out.println("57-58�ֽڣ�" + buildNumber);  
  
        // ������Ϸ��0x0000�� ������Ϸ��0x8000����Ӧʮ����32768��  
        flag = LittleEndianTool.getUnsignedInt16(fileBytes, 58);  
        System.out.println("59-60�ֽڣ�" + flag);  
  
        // ¼��ʱ�������룩  
        duration = LittleEndianTool.getUnsignedInt32(fileBytes, 60);  
        System.out.println("61-64�ֽڣ�" + duration);  
  
        // CRC32У����  
        long crc32 = LittleEndianTool.getUnsignedInt32(fileBytes, 64);  
        System.out.println("65-68�ֽڣ�" + crc32);
        CRC32 crc32Tool = new CRC32();  
        crc32Tool.update(fileBytes, 0, 64);  
        crc32Tool.update(0);  
        crc32Tool.update(0);  
        crc32Tool.update(0);  
        crc32Tool.update(0);  
        System.out.println("CRC32��" + crc32Tool.getValue());  
  
        // �ж�Header�к���λ��ȡ��CRC32��ֵ�ͼ���õ���ֵ�Ƚϣ����Ƿ�һ��  
        if (crc32 != crc32Tool.getValue()) {  
            throw new Exception("Header����CRC32У�鲻ͨ����");  
        } 
	}

	public long getHeaderVersion() {
		return headerVersion;
	}


	public long getCompressedDataSize() {
		return compressedDataSize;
	}


}
