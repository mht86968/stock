package com.mht.stock.util;

import android.graphics.Bitmap;

import com.mht.stock.constant.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public final class IO {
    public static final int BUFFER = 8 * 1024;
    public static final int UPDATE_BLOCK = 512 * 1024;


    public static String readAsString(File in) throws IOException {
        return new String(IO.readAsBytes(in), Constants.UTF8);
    }

    public static String readAsString(InputStream in) throws IOException {
        return new String(IO.readAsBytes(in), Constants.UTF8);
    }

    public static byte[] readAsBytes(File in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(IO.createInputStream(in), out);
        return out.toByteArray();
    }

    public static byte[] readAsBytes(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        return out.toByteArray();
    }



    /**
     *
     * @param content
     * @param file
     * @param append    是否追加在文件末
     * @throws IOException
     */
    public static void write(String content, File file, boolean append) throws IOException {
        Writer writer = IO.createWriter(file, append);
        try {
            writer.write(content);
        } finally {
            closeIO(writer);
        }
    }

    public static void write(String content, OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        try {
            writer.write(content);
        } finally {
            closeIO(writer);
        }
    }




    public static void copy(Bitmap in, File out) throws IOException {
    	BufferedOutputStream outputStream = IO.createOutputStream(out, false);
        in.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        closeIO(outputStream);
    }

    /**
     * 复制
     * @param in
     * @param out
     * @param append        true：添加到文件末尾，false：复制替换文件
     * @throws IOException
     */
    public static void copy(File in, File out, boolean append) throws IOException {
        copy(IO.createInputStream(in), IO.createOutputStream(out, append));
    }

    public static void copy(File in, OutputStream out) throws IOException {
        copy(IO.createInputStream(in), out);
    }
    
    public static void copy(InputStream in, File out, boolean append) throws IOException {
        copy(in, IO.createOutputStream(out, append));
    }

    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] bytes = new byte[BUFFER];
        int len;
        try {
            while((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } finally {
            closeIO(in, out);
        }
    }
    
    public static void copy(InputStream in, OutputStream out, Progress progress) throws IOException {
        byte[] bytes = new byte[BUFFER];
        int len;
        long download = 0;
        long preDownload = 0;
        int updateBlock = UPDATE_BLOCK;
        try {
        	progress.onProgress(0);
            while((len=in.read(bytes)) != -1 && !progress.isCancel()) {
            	download += len;
                out.write(bytes, 0, len);
                if(download-preDownload >= updateBlock) {
                	preDownload = download;
                	progress.onProgress(download);
                }
            }
            if(preDownload!=download && !progress.isCancel()) {
            	progress.onProgress(download);
            }
        } finally {
            closeIO(in, out);
        }
    }





    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    public static BufferedReader createReader(File in) throws FileNotFoundException {
        return new BufferedReader(new FileReader(in));
    }

    public static BufferedWriter createWriter(File out, boolean append) throws IOException {
        return new BufferedWriter(new FileWriter(out, append));
    }

    public static BufferedOutputStream createOutputStream(File out, boolean append) throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(out, append));
    }

    public static BufferedInputStream createInputStream(File in) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(in));
    }



    
    public static interface Progress {
    	public boolean isCancel();
    	public void onProgress(long progress);
    }
    
    private IO(){}
}
