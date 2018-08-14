package web;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/28.
 */
public class PageContentReader {
    public static final long MAXFILEGET = 10485760L;
    public static final int STEPLEN = 102400;
    public static final String WEBDOCDIR = System.getProperty("user.dir") + "/web/";

    public static ByteBuf getContent(String url) throws IOException {
        InputStream in = null;
        try {
            File file = new File(WEBDOCDIR + url);
            long fileLen = file.length();
            if(fileLen<MAXFILEGET){
                in = new FileInputStream(file);
                if (in != null) {
                    byte[] data = new byte[(int) fileLen];
                    byte[] step = new byte[STEPLEN];
                    int readBytes;
                    int writePos = 0;
                    while ((readBytes = in.read(step)) > 0) {
                        System.arraycopy(step, 0, data, writePos, readBytes);
                        writePos += readBytes;
                    }
                    return Unpooled.copiedBuffer(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        } finally {
            if(in != null)
            in.close();
        }
        return null;
    }
}
