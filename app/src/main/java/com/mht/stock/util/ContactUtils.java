package com.mht.stock.util;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;

public class ContactUtils {

	/**
	 * 联系人读取权限
	 * @return
     */
	public static boolean isContactsPermission(ContentResolver contentResolver) {
		Cursor setCursor = contentResolver.query(
				ContactsContract.RawContactsEntity.CONTENT_URI, null, null, null, null);
		int count = 0;
		if(setCursor != null) {
			count = setCursor.getCount();
			setCursor.close();
		}
		return count > 0;
	}

	/**
	 * 读取联系人
	 * @param mNumber
	 * @return
	 */
	public static String getContacts(ContentResolver contentResolver, String mNumber) {
		String name = null;
		String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME};
		Cursor cursor = contentResolver.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
				ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + mNumber + "'", null, null);
		if( cursor != null ) {
			for( int i = 0; i < cursor.getCount(); i++ ) {
				cursor.moveToPosition(i);
				int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
				cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME);
				name = cursor.getString(nameFieldColumnIndex);
				break;
			}
			cursor.close();
		}
		return name;
	}


	/**
	 * 写人联系人
	 * @param name
	 * @param phone
     * @return
     */
	public static Uri insertContact(ContentResolver contentResolver, String name, String phone) {
		ContentValues values = new ContentValues();
		values.put(Contacts.People.NAME, name);
		Uri uri = contentResolver.insert(Contacts.People.CONTENT_URI, values);
		Uri numberUri = Uri.withAppendedPath(uri, Contacts.People.Phones.CONTENT_DIRECTORY);
		values.clear();

		values.put(Contacts.Phones.TYPE, Contacts.People.Phones.TYPE_MOBILE);
		values.put(Contacts.People.NUMBER, phone);
		contentResolver.insert(numberUri, values);
		return uri;
	}
}