package com.app.alldemo.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 获取文件夹大小，MB
 * 获取文件夹大小,单位是B
 * 格式化单位为MB
 * 给目标文件或者目录重新命名
 * 删除指定文件或者目录
 * 复制指定文件到目标目录
 * 移动指定文件到目标目录
 * 复制指定文件或目录到目标目录
 * 移动指定文件或目录到目标目录
 * 将数据保存到文件中String data
 * 创建目录或文件
 * 将数据保存到文件中  byte[] data
 * 从文件中获取字符串数据
 * 图片字节压缩到文件中
 * ZIP压缩
 * ZIP解压缩
 * GZIP压缩
 * GZIP解压缩
 * Deflater压缩
 * Deflater解压
 * 编码解码
 */
public class FileUtils {
	private static FileUtils instance;
	public static FileUtils getInstance() {
		if (instance == null) {
			instance = new FileUtils();
		}
		return instance;
	}
	/**
	 * 获取文件夹大小，MB
	 * 
	 * @param file
	 * @return
	 */
	public String getFileValue(File file) {
		return formatFileSizeM(getFolderSize(file));
	}
	/**
	 * 获取文件夹大小,单位是B
	 * 
	 * @param file
	 * @return
	 */
	private long getFolderSize(File file) {
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);

				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}
	/**
	 * 格式化单位为MB
	 * 
	 * @param fileS 单位B
	 * @return
	 */
	private String formatFileSizeM(long fileS) {
		String fileSizeString = "";
		double value = fileS / (1000 * 1024);
		if (value < 1.0) {
			DecimalFormat df = new DecimalFormat("0.00");
			fileSizeString = df.format((double) fileS / (1000 * 1024));
		} else {
			DecimalFormat df = new DecimalFormat("#.00");
			fileSizeString = df.format((double) fileS / (1000 * 1024));
		}
		return fileSizeString;
	}
	/**
	 * 给目标文件或者目录重新命名
	 * @param path 目标文件的绝对路径
	 * @param newName 新名称
	 * */
	public boolean renameDirOrFile(String path, String newName)
			throws Exception {
		boolean done = false;
		String newPath = null;
		String type = null;
		File file = null;
		try {
			file = new File(path);
			newPath = path.substring(0, path.lastIndexOf("/") + 1) + newName;
			if (path.contains(".")) {
				type = path.substring(path.lastIndexOf("."), path.length());
				if (!newPath.contains(".")) { // 表示还没有输入扩展名，必须将扩展名加上
					newPath = newPath + type;
				}
			}
			done = file.renameTo(new File(newPath));
			return done;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			newPath = null;
			type = null;
			file = null;
		}
	}
	/**
	 * 删除指定文件或者目录
	 * */
	public boolean deleteDirOrFile(String path) throws Exception {
		boolean done = false;
		File file = null;
		File[] files = null;
		try {
			file = new File(path);
			if (file.isDirectory()) {
				files = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					done = deleteDirOrFile(files[i].getAbsolutePath());
				}
			}
			done = file.delete();
			return done;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			file = null;
			files = null;
		}
	}
	/**
	 * 移动指定文件到目标目录
	 * 
	 * @param fromPath
	 *            指定文件的绝对路径，只针对文件
	 * 
	 * @param toPath
	 *            目标目录
	 * 
	 * */
	public boolean moveFile(String fromPath, String toPath) throws Exception {
		boolean done = false;
		File fromFile = null;
		File toFile = null;
		byte[] bytes = null;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			fromFile = new File(fromPath);
			toFile = new File(toPath + "/" + fromFile.getName());
			if (toFile.exists()) {
				// 文件已经存在时的处理
			}
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			bytes = new byte[1024];
			int count;
			while ((count = in.read(bytes)) != -1) {
				out.write(bytes, 0, count);
			}
			done = new File(fromPath).delete();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			fromFile = null;
			toFile = null;
			bytes = null;
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
		return done;
	}
	/**
	 * 复制指定文件到目标目录
	 * @param fromPath 指定文件的绝对路径，只针对文件
	 * @param toPath 目标目录
	 * @return
	 * @throws Exception
	 */
	public boolean copyFile(String fromPath, String toPath) throws Exception {
		boolean done = false;
		File fromFile = null;
		File toFile = null;
		byte[] bytes = null;
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			fromFile = new File(fromPath);
			toFile = new File(toPath + "/" + fromFile.getName());
			if (toFile.exists()) {
				// 文件已经存在时的处理
			}
			in = new FileInputStream(fromFile);
			out = new FileOutputStream(toFile);
			bytes = new byte[1024];
			int count;
			while ((count = in.read(bytes)) != -1) {
				out.write(bytes, 0, count);
			}
			if (count == -1) {
				done = true;
			}
			return done;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			fromFile = null;
			toFile = null;
			bytes = null;
			if (in != null) {
				in.close();
				in = null;
			}
			if (out != null) {
				out.close();
				out = null;
			}
		}
	}
	/**
	 * 复制指定文件或目录到目标目录
	 * 
	 * @param fromPath
	 *            指定文件或目录的绝对路径
	 * 
	 * @param toPath
	 *            目标目录
	 * 
	 * */
	public boolean copyDirOrFile(String fromPath, String toPath)
			throws Exception {
		boolean done = false;
		boolean exist = false;
		File fromFile = null; // 指定文件或目录对应的文件对象
		File newDir = null; // 复制目录时，用于在目标目录下创建新目录的文件对象
		File[] files = null; // 复制目录时，目录下对应的所有文件和目录
		File itemFile = null; // 复制目录时，用于保存目录下每一个文件的文件对象
		try {
			fromFile = new File(fromPath);
			if (fromFile.isFile()) { // 是文件，直接复制
				done = copyFile(fromPath, toPath);
			} else {// 是目录
				newDir = new File(toPath + "/" + fromFile.getName());
				exist = newDir.exists();
				if (exist) {
					// 目录已经存在时的处理
				}
				newDir.mkdir();
				files = fromFile.listFiles();
				if (files.length == 0) {
					return true;
				}
				for (int i = 0; i < files.length; i++) {
					done = false;
					itemFile = files[i];
					// 递归调用
					done = copyDirOrFile(itemFile.getAbsolutePath(),
							newDir.getAbsolutePath());
					if (!done) {
						continue;
					}
				}
			}
			return done;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			fromFile = null;
			newDir = null;
			files = null;
			itemFile = null;
		}
	}
	/**
	 * 移动指定文件或目录到目标目录
	 * 
	 * @param fromPath
	 *            指定文件或目录的绝对路径
	 * 
	 * @param toPath
	 *            目标目录
	 * 
	 * */
	public boolean moveDirOrFile(String fromPath, String toPath)
			throws Exception {
		boolean done = false;
		boolean exist = false;
		File fromFile = null; // 指定文件或目录对应的文件对象
		File newDir = null; // 复制目录时，用于在目标目录下创建新目录的文件对象
		File[] files = null; // 复制目录时，目录下对应的所有文件和目录
		File itemFile = null; // 复制目录时，用于保存目录下每一个文件的文件对象
		try {
			fromFile = new File(fromPath);
			if (fromFile.isFile()) { // 是文件，直接复制
				done = copyFile(fromPath, toPath);
			} else {// 是目录
				newDir = new File(toPath + "/" + fromFile.getName());
				exist = newDir.exists();
				if (exist) {
					// 目录已经存在时的处理
				}
				newDir.mkdir();
				files = fromFile.listFiles();
				if (files.length == 0) {
					return true;
				}
				for (int i = 0; i < files.length; i++) {
					done = false;
					itemFile = files[i];
					// 递归调用
					done = copyDirOrFile(itemFile.getAbsolutePath(),
							newDir.getAbsolutePath());
					if (!done) {
						continue;
					}
				}
			}
			if (done) {
				done = deleteDirOrFile(fromPath);
			}
			return done;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * 将数据保存到文件中
	 * 
	 * @param path
	 *            文件保存路径
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 * @param data
	 *            待保存的数据
	 * 
	 * */
	public boolean saveDataToFile(String path, String fileName, String data) {
		boolean flag=false;
		try {
			OutputStream out = null;
			File file = null;
			byte b[] = data.getBytes();
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			if (!TextUtils.isEmpty(fileName)) {
				file = new File(path + "/" + fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
			}
			if(file!=null){
				out = new FileOutputStream(file);
				out.write(b);
				if (out != null) {
					out.close();
					out = null;
				}
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return flag;
	}
	/**
	 * 创建目录或文件
	 * 
	 * @param path
	 *            目录名
	 * @param fileName
	 *            文件名，只创建目录的话，传""即可
	 * @return
	 */
	public File createFile(String path, String fileName) {
		File file = null;
		try {
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			if (!TextUtils.isEmpty(fileName)) {
				file = new File(path + "/" + fileName);
				if (!file.exists()) {
					file.createNewFile();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	/**
	 * 将数据保存到文件中
	 * 
	 * @param path
	 *            文件保存路径(该目录必须存在，否则会抛出异常)
	 * 
	 * @param fileName
	 *            文件名
	 * 
	 * @param data
	 *            待保存的数据
	 * 
	 * */
	public boolean saveDataToFile(String path, String fileName, byte[] data) {
		try {
			boolean done = false;
			File file = null;
			OutputStream out = null;
			file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(path + "/" + fileName);
			if (!file.exists()) {
				done = file.createNewFile();
			}
			byte b[] = data;
			out = new FileOutputStream(file);
			out.write(b);
			if (out != null) {
				out.close();
				out = null;
			}
			done = true;
			return done;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 从文件中获取字符串数据
	 * @param filePath 文件路径
	 * */
	@SuppressWarnings("resource")
	public String getDataFromData(String filePath) {
		String data = null;
		File file = null;
		file = new File(filePath);
		InputStream in = null;
		if (!file.exists()) {
			return null;
		}
		try {
			in = new FileInputStream(file);
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			data = new String(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return data;
	}
	/**
	 * 图片字节压缩到文件中
	 * @param arg2
	 * @param saveFilePath
	 * @return
	 */
	public String saveFile(byte[] arg2, String saveFilePath) {
		Bitmap bmp = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
		String path = "";
		File file = new File(saveFilePath);
		// 压缩格式
		CompressFormat format = CompressFormat.JPEG;
		// 压缩比例
		int quality = 100;
		try {
			if (file.exists()) {
				file.delete();
			}
			// 创建文件
			file.createNewFile();
			OutputStream stream = new FileOutputStream(file);
			bmp.compress(format, quality, stream);
			stream.close();
			path = saveFilePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	// ZIP压缩
	public byte[] compressZip(String str) {
		if (str == null && "".equals(str)) {
			return null;
		}
		byte[] compress = null;
		ByteArrayOutputStream out;
		try {
			out = new ByteArrayOutputStream();
			ZipOutputStream gzip = new ZipOutputStream(out);
			gzip.putNextEntry(new ZipEntry("Zip"));
			gzip.write(str.getBytes());
			gzip.close();
			compress = out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return compress;
	}
	// ZIP解压缩
	public String uncompressZip(byte[] data) {
		String compress = "";
		ByteArrayOutputStream out;
		try {
			out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			ZipInputStream gunzip = new ZipInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			compress = new String(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compress;
	}
	// GZIP压缩
	public byte[] compressGzip(String str) {
		if (str == null && "".equals(str)) {
			return null;
		}
		byte[] compress = null;
		ByteArrayOutputStream out;
		try {
			out = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes());
			gzip.close();
			compress = out.toByteArray();
		} catch (IOException e) {
			MyLog.e("", "compress:" + e.getMessage());
			e.printStackTrace();
		}
		return compress;
	}
	// GZIP解压缩
	public String uncompressGzip(byte[] data) {
		String compress = "";
		ByteArrayOutputStream out;
		try {
			out = new ByteArrayOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			compress = new String(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return compress;
	}
	/**
	 * Deflater压缩
	 * @param bytes
	 * @return
	 */
	public byte[] encodeData(byte[] bytes) {
		ByteArrayOutputStream out;
		try {
			out = new ByteArrayOutputStream();
			DeflaterOutputStream deflater = new DeflaterOutputStream(out,
					new Deflater(1, true));
			deflater.write(bytes);
			deflater.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Deflater解压
	 * @return
	 */
	public byte[] decodeData(byte[] data) {
		ByteArrayOutputStream out;
		try {
			ByteArrayInputStream bins = new ByteArrayInputStream(data);
			InflaterInputStream inflater = new InflaterInputStream(bins,
					new Inflater(true));
			byte[] buf = new byte[1024];
			int len = 0;
			out = new ByteArrayOutputStream();
			while ((len = inflater.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			inflater.close();
			bins.close();
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void code(){
		try {
			//使用指定的编码机制对字符串解码
			URLDecoder.decode("%E6%88%91%E4%BB%AC%E5%91%A2", "UTF-8");
			//使用指定的编码机制将字符串转换为指定格式。
			URLEncoder.encode("我们呢", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 编码解码
	 */
	private void endeString(){
		try {
			//编码
			String encodeString= URLEncoder.encode("我是计算机技术", "UTF-8");
			//解码
			String decodeString=URLDecoder.decode(encodeString, "UTF-8");
			System.out.println(decodeString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
		int options = 100;
		while ( baos.toByteArray().length / 1024>100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
			baos.reset();//重置baos即清空baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
			options -= 10;//每次都减少10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
		return bitmap;
	}
}
