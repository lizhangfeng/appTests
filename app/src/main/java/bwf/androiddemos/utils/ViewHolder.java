package bwf.androiddemos.utils;

import android.util.SparseArray;
import android.view.View;
/**
 * 重用机制的封装
 * */
public class ViewHolder {

	@SuppressWarnings("unchecked")
	public static <T extends View> T getView(View convertView, int sourceID) {
		SparseArray<View> sparseArray = (SparseArray<View>) convertView.getTag();
		if (sparseArray == null) {
			sparseArray = new SparseArray<View>();
			convertView.setTag(sparseArray);
		}

		View view = sparseArray.get(sourceID);
		if (view == null) {
			view = convertView.findViewById(sourceID);
			sparseArray.put(sourceID, view);
		}
		return (T) view;
	}

}
