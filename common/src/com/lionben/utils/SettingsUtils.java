package com.lionben.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author lionben
 */
public class SettingsUtils {

    /**
     * �õ��ļ���������Ϣ
     *
     * @param fileName �����ļ�������
     * @param encoding �����ļ��ı���
     * @return
     * @throws Exception
     */
    public static Properties initSettings(String fileName, String encoding) throws Exception {
        encoding = encoding == null ? "utf-8" : encoding;
        InputStream src = SettingsUtils.class.getClassLoader().getResourceAsStream(fileName);
        InputStreamReader reader = new InputStreamReader(src, encoding);
        Properties settings = new Properties();
        settings.load(reader);
        reader.close();
        src.close();
        return settings;
    }

    /**
     * �õ����Ե�ֵ
     *
     * @param settings     �����б�
     * @param name         Ҫ��ȡ����������
     * @param defaultValue ��ȡʧ�ܵ�Ĭ��ֵ
     * @return
     */
    public static String getSettings(Properties settings, String name, String defaultValue) {
        if (settings == null)
            return defaultValue;
        String result = settings.getProperty(name);
        if (result == null)
            return defaultValue;
        else
            return result;
    }

    /**
     * �õ����Ե�ֵ
     *
     * @param settings �����б�
     * @param name     Ҫ��ȡ����������
     * @return
     */
    public static String getSettings(Properties settings, String name) {
        String result = settings.getProperty(name);
        if (result == null)
            throw new RuntimeException(name + " not found!");
        return result;
    }

}
