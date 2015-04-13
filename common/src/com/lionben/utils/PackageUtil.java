package com.lionben.utils;

import com.lionben.pojo.Package;

/**
 * Created by lionben.
 */
public class PackageUtil {

    public static byte[] packageToBytes(Package packageInfo){
        if(packageInfo == null)
            throw new NullPointerException() ;
        byte[] bytes = new byte[5+packageInfo.size] ;
        bytes[0] = (byte)(packageInfo.flag >> 8) ;
        bytes[1] = (byte)packageInfo.flag ;
        bytes[2] = packageInfo.options ;
        bytes[3] = (byte)(packageInfo.size >> 8) ;
        bytes[4] = (byte)packageInfo.size ;
        for(int i=0;i<packageInfo.size;i++){
            bytes[5+i] = packageInfo.data[i] ;
        }
        return bytes ;
    }
}
