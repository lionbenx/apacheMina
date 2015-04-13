package com.lionben.utils;

/**
 * 对数据进行字节和Short,Int,Long的转换
 *
 * @author chenlinben
 */
public class ByteUtil {
    /**
     * 将两个字节合并为一个short
     *
     * @param b1
     * @param b2
     * @return
     */
    public static short byteToShort(byte b1, byte b2) {
        short s = 0;
        short byte1 = (short) (b1 & 0xff);
        short byte2 = (short) (b2 & 0xff);
        byte1 <<= 8;
        s = (short) (byte1 | byte2);
        return s;
    }


    /**
     * 将四个字节合并为一个integer
     *
     * @param bl    要合并的字节数组
     * @param start 在数组中的起始位置
     * @return
     */
    public static int byteToInteger(byte[] bl, int start) {
        int b1 = bl[start + 3] & 0xff;
        int b2 = bl[start + 2] & 0xff;
        int b3 = bl[start + 1] & 0xff;
        int b4 = bl[start] & 0xff;
        b2 <<= 8;
        b3 <<= 16;
        b4 <<= 24;
        int i = b1 | b2 | b3 | b4;
        return i;
    }

    /**
     * 将8个字节合并为一个long
     *
     * @param b     要合并的数组
     * @param start 在数组中的起始位置
     * @return
     */
    public static long byteToLong(byte[] b, int start) {
        long s = 0;
        long s0 = b[start + 7] & 0xff;
        long s1 = b[start + 6] & 0xff;
        long s2 = b[start + 5] & 0xff;
        long s3 = b[start + 4] & 0xff;
        long s4 = b[start + 3] & 0xff;
        long s5 = b[start + 2] & 0xff;
        long s6 = b[start + 1] & 0xff;
        long s7 = b[start] & 0xff;
        // s0不变 
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }


    //将字符串转换为byte数组
//	public static byte[] stringTobyte(String s,byte encry){
//		char[] array = s.toCharArray() ;
//		short len = 0;
//		if(s != null && !"".equals(s.trim())){
//			len = (short)array.length ;
//		}
//		byte[] b = new byte[3+len] ;
//		byte[] h = shortTobyte(len) ;
//		b[0] = h[0] ;
//		b[1] = h[1] ;
//		b[2] = encry ;
//		for(int i=0;i<len;i++)
//			b[i+3] = (byte)array[i] ;
//		return b ;
//	}

    /**
     * 将整形转换为byte数组
     *
     * @param intValue
     * @return
     */
    public static byte[] intTobyte(final int intValue) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (intValue >> 8 * (3 - i) & 0xFF);
        }
        return b;
    }

    /**
     * 将整形转换为byte数组
     *
     * @param longValue
     * @return
     */
    public static byte[] longTobyte(final long longValue) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) (longValue >> 8 * (7 - i) & 0xFF);
        }
        return b;
    }

    /**
     * 将短整形转换为byte数组
     *
     * @param shortValue
     * @return
     */
    public static byte[] shortTobyte(final short shortValue) {
        byte[] b = new byte[2];
        for (int i = 0; i < 2; i++) {
            b[i] = (byte) (shortValue >> 8 * (1 - i) & 0xFF);
        }
        return b;
    }
}
