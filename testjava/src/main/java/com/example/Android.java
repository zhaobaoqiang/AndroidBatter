package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Android {

	public static File rootFile;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		creat(720, 720);
	}

	private static void creat(int stardShortWidth,int graduationNum) {
		createDp(new ConvertMsw480(), stardShortWidth, graduationNum);

		createDp(new ConvertH480(), stardShortWidth, graduationNum);

		createDp(new ConvertH540(), stardShortWidth, graduationNum);

		createDp(new ConvertMsw600(), stardShortWidth, graduationNum);

		createDp(new ConvertSw800(), stardShortWidth, graduationNum);

		createDp(new ConvertX1280x768(), stardShortWidth, graduationNum);

		createDp(new ConvertXsw360(), stardShortWidth, graduationNum);

		System.out.println("over");
	}

	static {
		rootFile = new File("D:/Android_dimes");
		if (!rootFile.exists()) {
			rootFile.mkdir();
		}
	}

	public static void createDp(Convert convert, int shortWidth,
			int graduationNum) {
		StringBuffer sb = new StringBuffer();
		sb.append("<resources>");
		sb.append("\r\n");
		for (int i = 1; i <= graduationNum; i++) {
			sb.append("\t");
			sb.append("<dimen name=\"q");
			sb.append(i);
			sb.append("\">");
			sb.append(convert.convert(i, shortWidth));
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
		float convert(int px, int shortWidth);

		String getSaveFilePath();
	}

	private static float getDp(int px, float density, float score) {
		float dp = (float) Math.round(((float) px * score / density) * 100f) / 100f;
		return dp;
	}

	private static String getFilePath(String fileParent) {
		File file = new File(rootFile, fileParent);
		if (!file.exists()) {
			file.mkdirs();
		}

		File dimesFile = new File(file, "dimens.xml");

		return dimesFile.getAbsolutePath();
	}

	static class ConvertMsw480 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 1.0f, (float) (480f / shortWidth));
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub

			return getFilePath("values-sw480dp");
		}
	}

	static class ConvertMsw600 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 1.0f, (float) (600f / shortWidth));
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub

			return getFilePath("values-sw600dp");
		}
	}

	static class ConvertSw800 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 1.0f, 800f / shortWidth);
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub

			return getFilePath("values-sw800dp");
		}

	}

	static class ConvertH480 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 1.5f, 480f / shortWidth);
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub
			return getFilePath("values-hdpi");
		}

	}

	static class ConvertH540 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 1.5f, 540f / shortWidth);
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub
			return getFilePath("values-hdpi-960x540");
		}

	}

	static class ConvertXsw360 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 2.0f, 720f / shortWidth);
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub
			return getFilePath("values-xhdpi");
		}

	}

	static class ConvertX1280x768 implements Convert {

		@Override
		public float convert(int px, int shortWidth) {
			// TODO Auto-generated method stub
			return getDp(px, 2.0f, 768f / shortWidth);
		}

		@Override
		public String getSaveFilePath() {
			// TODO Auto-generated method stub
			return getFilePath("values-xhdpi-1280x768");
		}

	}

}
