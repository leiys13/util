package com.sadasen.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr home
 * @desc
 */
public class FileUtil {
	
	private FileUtil() {
	}
	
	public static void download(String fileUrl, String filePath, int buffSize) throws Exception {
		URL url = new URL(fileUrl);
		final HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		ReadableByteChannel inChannel = null;
		WritableByteChannel outChannel = null;
		
		InputStream in = httpConn.getInputStream();
		OutputStream out = null;
		
		int code = httpConn.getResponseCode();
		if(2 != code/100) {
			throw new Exception("Error: http response code : " + code);
		}
		
		try {
			out = new FileOutputStream(filePath);
			inChannel = Channels.newChannel(new BufferedInputStream(in));
			outChannel = Channels.newChannel(new BufferedOutputStream(out));
			
			ByteBuffer buf = ByteBuffer.allocate(buffSize);
			while(-1 != inChannel.read(buf)) {
				buf.flip();
				outChannel.write(buf);
				buf.clear();
			}
		} finally {
			if(null!=inChannel) {
				inChannel.close();
			}
			if(null!=outChannel) {
				outChannel.close();
			}
			httpConn.disconnect();
		}
		System.out.println(in);
		System.out.println(out);
		System.out.println(inChannel);
		System.out.println(outChannel);
	}
	
	public static void main(String[] args) throws Exception {
		download("", "", 2048);
	}

}
