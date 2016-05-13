package com.app.alldemo.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

import com.app.alldemo.model.ImageBucket;
import com.app.alldemo.model.ImageItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class MediaImagesTool {
	private static final String TAG="MediaImagesTool";
	// 缩略图列表
	HashMap<String, String> thumbnailList = new HashMap<String, String>();
	HashMap<String, ImageBucket> bucketList = new HashMap<String, ImageBucket>();
	ContentResolver cr;
	private static MediaImagesTool tool;
	private MediaImagesTool(){}
	public static MediaImagesTool getInstance(){
		if (tool == null) {
			tool = new MediaImagesTool();
		}
		return tool;
	}
	/**
	 * 获取所有图片的照片
	 * @param context
	 * @return
	 */
	public ArrayList<ImageItem> getAllImages(Context context){
		ArrayList<ImageItem> dataList = null;
		Cursor cursor = null;
		cursor = context.getContentResolver().
				query(Media.EXTERNAL_CONTENT_URI,
						new String[]{
						Media._ID,      //id
						Media.DATA,     //路径
						Media.DISPLAY_NAME,//文件名
				},
				null,null,null);
		if (cursor == null) {
			return null;
		}
		if (cursor.getCount() == 0) {
			cursor.close();
			return null;
		}
		dataList = new ArrayList<ImageItem>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ImageItem imageItem=new ImageItem();
			String path = cursor.getString(cursor.getColumnIndex(Media.DATA));
			imageItem.imagePath=path;
			dataList.add(imageItem);
			cursor.moveToNext();
		}
		return dataList;
	}
	/**
	 * 得到图片集
	 * 
	 * @return
	 */
	public List<ImageBucket> getImagesBucketList(Context context) {
		cr =context.getContentResolver();
		buildImagesBucketList();
		List<ImageBucket> tmpList = new ArrayList<ImageBucket>();
		Iterator<Entry<String, ImageBucket>> itr = bucketList.entrySet()
				.iterator();
		while (itr.hasNext()) {
			Entry<String, ImageBucket> entry = (Entry<String, ImageBucket>) itr
					.next();
			tmpList.add(entry.getValue());
		}
		return tmpList;
	}
	/**
	 * 得到缩略图
	 */
	private void getThumbnail() {
		String[] projection = { Thumbnails._ID, Thumbnails.IMAGE_ID,
				Thumbnails.DATA };
		Cursor cursor = cr.query(Thumbnails.EXTERNAL_CONTENT_URI,
				projection, null, null, null);
		if (cursor != null) {
			getThumbnailColumnData(cursor);
		}
	}
	/**
	 * 从数据库中得到图片缩略图
	 * 
	 * @param cur
	 */
	private void getThumbnailColumnData(Cursor cur) {
		while (cur.moveToNext()) {
			int image_idColumn = cur.getColumnIndex(Thumbnails.IMAGE_ID);
			int dataColumn = cur.getColumnIndex(Thumbnails.DATA);
			int image_id = cur.getInt(image_idColumn);
			String image_path = cur.getString(dataColumn);
			thumbnailList.put("" + image_id, image_path);
		}
		cur.close();
	}
	/**
	 * 得到图片集
	 */
	void buildImagesBucketList() {
		// 构造缩略图索引
		getThumbnail();
		// 构造相册索引
		String columns[] = new String[] { Media._ID, Media.BUCKET_ID,
				Media.PICASA_ID, Media.DATA, Media.DISPLAY_NAME, Media.TITLE,
				Media.SIZE, Media.BUCKET_DISPLAY_NAME };
		// 得到一个游标
		Cursor cur = cr.query(Media.EXTERNAL_CONTENT_URI, columns, null,
				null, null);
		if (cur.moveToFirst()) {
			// 获取指定列的索引
			int photoIDIndex = cur.getColumnIndexOrThrow(Media._ID);
			int photoPathIndex = cur.getColumnIndexOrThrow(Media.DATA);
			int bucketDisplayNameIndex = cur.getColumnIndexOrThrow(Media.BUCKET_DISPLAY_NAME);
			int bucketIdIndex = cur.getColumnIndexOrThrow(Media.BUCKET_ID);
			do {
				String _id = cur.getString(photoIDIndex);
				String path = cur.getString(photoPathIndex);
				String bucketName = cur.getString(bucketDisplayNameIndex);
				String bucketId = cur.getString(bucketIdIndex);
				ImageBucket bucket = bucketList.get(bucketId);
				if (bucket == null) {
					bucket = new ImageBucket();
					bucket.imageList = new ArrayList<ImageItem>();
					bucket.bucketName = bucketName;
					bucketList.put(bucketId, bucket);
				}
				bucket.count++;
				ImageItem imageItem = new ImageItem();
				imageItem.imageId = _id;
				imageItem.imagePath = path;
				imageItem.thumbnailPath = thumbnailList.get(_id);
				bucket.imageList.add(imageItem);
			} while (cur.moveToNext());
			cur.close();
		}
	}
}
