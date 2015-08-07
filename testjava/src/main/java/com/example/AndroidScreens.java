package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class AndroidScreens {
	
	private static int width = 720;
	private static int height = 1280;
	public static File rootFile;

	static {
		rootFile = new File("D:/AndroidScreen");
		if (!rootFile.exists()) {
			rootFile.mkdir();
		}
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createDp(new SimpleConvert(360), 700);
		createDp(new SimpleConvert(480), 700);
		createDp(new SimpleConvert(600), 700);
		createDp(new SimpleConvert(384), 700);
		createDp(new SimpleConvert(320), 700);
		createDp(new SimpleConvert(800), 700);

		//createDp(new SimpleConvert(1024),300);
		//createDp(new SimpleConvert(412),300);


	}
	
	
	
	public static void createDp(Convert convert,int graduationNum) {
		StringBuffer sb = new StringBuffer();
		sb.append("<resources>");
		sb.append("\r\n");		
		sb.append(convert.getTips());
		sb.append("\r\n");
		for (int i = 1; i <= graduationNum; i++) {
			sb.append("\t");
			sb.append("<dimen name=\"q");
			sb.append(i);
			sb.append("\">");
			sb.append(convert.convert(i));
			sb.append("dp</dimen>");
			sb.append("\r\n");
		}
		sb.append("</resources>");
		saveStringToFile(sb.toString(), convert.getSaveFilePath());
	}
	
	
	public static void saveStringToFile(String str, String filePath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);

			fos.write(str.getBytes());

			fos.flush();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public interface Convert {
		float convert(int px);

		String getSaveFilePath();
		
		String getTips();
	}
	
	
	
	public static class SimpleConvert implements Convert{
		private int shortWidthDP;
		
		private float scale;
		
		public SimpleConvert(int shortWidthDP) {
			super();
			this.shortWidthDP = shortWidthDP;
			
			this.scale = ((float)shortWidthDP)/((float)width);
		}

		@Override
		public float convert(int px) {
			// TODO Auto-generated method stub
			
			return (float)Math.round(((float)px)*scale*100f)/100f;
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub
			return getFilePath("values-sw"+shortWidthDP+"dp");
		}

		@Override
		public String getTips() {
			// TODO Auto-generated method stub
			int minHight = height*shortWidthDP/width;
			
			
			
			return "<!--"+"minHight:"+minHight+"dp -->";
		}
		
	}
	
	
	
	private static String getFilePath(String fileParent) {
		File file = new File(rootFile, fileParent);
		if (!file.exists()) {
			file.mkdirs();
		}

		File dimesFile = new File(file, "dimens.xml");

		return dimesFile.getAbsolutePath();
	}
	

}
