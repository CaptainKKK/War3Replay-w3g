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
		
		//1～28字节
		//Warcraft III recorded game\u001A\0
		String beginTitle= new String(fileBytes,0,28);
		System.out.println("1~28字节"+ beginTitle);
		if(!beginTitle.equals(BEGIN_TITLE)){
			throw new Exception ("录像格式不正确,当前格式");
		}
		
		//29～32字节
		//版本号
		headSize = LittleEndianTool.getUnsignedInt32(fileBytes, 28);  
	    System.out.println("29-32字节：" + headSize);  
	    if (headSize != 0x44) {  
	    	throw new Exception("不支持V1.06及以下版本的录像。");  
	    }
	    
        // 解压缩数据大小  
        uncompressedDataSize = LittleEndianTool.getUnsignedInt32(fileBytes, 40);  
        System.out.println("41-44字节：" + uncompressedDataSize);  
  
        // 压缩数据块数量  
        compressedDataBlockCount = LittleEndianTool.getUnsignedInt32(fileBytes, 44);  
        System.out.println("45-48字节-压缩数据块：" + compressedDataBlockCount);  
  
        // WAR3：非冰封王座录像，W3XP冰封王座录像  
        versionIdentifier = LittleEndianTool.getString(fileBytes, 48, 4);  
        System.out.println("49-52字节：" + versionIdentifier);  
  
        // 版本号（例如1.24版本对应的值是24）  
        versionNumber = LittleEndianTool.getUnsignedInt32(fileBytes, 52);  
        System.out.println("53-56字节-版本号：" + versionNumber);  
  
        // Build号  
        buildNumber = LittleEndianTool.getUnsignedInt16(fileBytes, 56);  
        System.out.println("57-58字节：" + buildNumber);  
  
        // 单人游戏（0x0000） 多人游戏（0x8000，对应十进制32768）  
        flag = LittleEndianTool.getUnsignedInt16(fileBytes, 58);  
        System.out.println("59-60字节：" + flag);  
  
        // 录像时长（毫秒）  
        duration = LittleEndianTool.getUnsignedInt32(fileBytes, 60);  
        System.out.println("61-64字节：" + duration);  
  
        // CRC32校验码  
        long crc32 = LittleEndianTool.getUnsignedInt32(fileBytes, 64);  
        System.out.println("65-68字节：" + crc32);
        CRC32 crc32Tool = new CRC32();  
        crc32Tool.update(fileBytes, 0, 64);  
        crc32Tool.update(0);  
        crc32Tool.update(0);  
        crc32Tool.update(0);  
        crc32Tool.update(0);  
        System.out.println("CRC32：" + crc32Tool.getValue());  
  
        // 判断Header中后四位读取的CRC32的值和计算得到的值比较，看是否一致  
        if (crc32 != crc32Tool.getValue()) {  
            throw new Exception("Header部分CRC32校验不通过。");  
        } 
	}

	public long getHeaderVersion() {
		return headerVersion;
	}


	public long getCompressedDataSize() {
		return compressedDataSize;
	}


}
