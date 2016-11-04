package com.mht.stock.util;

import android.graphics.Bitmap;

import com.mht.stock.constant.Constants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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

    public static void write(String content, File file) throws IOException {
        Writer writer = IO.createWriter(file);
        try {
            writer.write(content);
        } finally {
            writer.close();
        }
    }

    public static void write(String content, OutputStream outputStream) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        try {
            writer.write(content);
        } finally {
            writer.close();
        }
    }
    
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


    public static void copy(Bitmap bitmap, File outFile) throws IOException {
    	BufferedOutputStream out = IO.createOutputStream(outFile);
    	bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
    	out.flush();
    	out.close();
    }
    
    public static void copy(File inFile, File outFile) throws IOException {
        copy(IO.createInputStream(inFile), IO.createOutputStream(outFile));
    }
    
    public static void copy(File inFile, OutputStream out) throws IOException {
        copy(IO.createInputStream(inFile), out);
    }
    
    public static void copy(InputStream in, File outFile) throws IOException {
        copy(in, IO.createOutputStream(outFile));
    }
    
    public static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] bytes = new byte[BUFFER];
        int len;
        try {
            while((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } finally {
            in.close();
            out.close();
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
            in.close();
            out.close();
        }
    }

    
    public static Reader createReader(File file) throws FileNotFoundException {
        return new BufferedReader(new FileReader(file));
    }

    public static Writer createWriter(File file) throws IOException {
        return new BufferedWriter(new FileWriter(file));
    }

    public static BufferedOutputStream createOutputStream(File file) throws FileNotFoundException {
        return new BufferedOutputStream(new FileOutputStream(file));
    }

    public static BufferedInputStream createInputStream(File file) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    
    public static interface Progress {
    	public boolean isCancel();
    	public void onProgress(long progress);
    }
    
    private IO(){}
}
