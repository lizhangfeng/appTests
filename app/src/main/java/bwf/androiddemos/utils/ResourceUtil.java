package bwf.androiddemos.utils;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.Field;

/**
 * 
 * Copyright 2014-2015 Secken, Inc. All Rights Reserved.
 * 
 * @author lizhangfeng
 * @version V1.0.0
 * @Description: 获取资源的方式（正常获取方式打包成sdk放入第三方project会出现resource notFound）
 * @date 2015-9-1
 */
public class ResourceUtil
{
	public static int getLayoutId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "layout", paramContext.getPackageName());
	}

	public static int getStringId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "string", paramContext.getPackageName());
	}

	public static int getDrawableId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "drawable", paramContext.getPackageName());
	}

	public static int getStyleId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "style", paramContext.getPackageName());
	}

	public static int getId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "id", paramContext.getPackageName());
	}

	public static int getColorId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "color", paramContext.getPackageName());
	}

	public static int getArrayId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "array", paramContext.getPackageName());
	}
	public static int getRawId(Context paramContext, String paramString)
	{
		return paramContext.getResources().getIdentifier(paramString, "raw", paramContext.getPackageName());
	}

	public static int getItemId(Context paramContext, String className, String stirngName)
	{
		try
		{
			Class<?> localClass = Class.forName(paramContext.getPackageName() + ".R$" + className);
			Field localField = localClass.getField(stirngName);
			int i = Integer.parseInt(localField.get(localField.getName()).toString());
			return i;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public static int[] getItemIdArray(Context paramContext, String className, String stringName)
	{
		try
		{
			Class<?> localClass = Class.forName(paramContext.getPackageName() + ".R$" + className);
			Field localField = localClass.getField(stringName);
			int[] i = (int[]) localField.get(localField.getName().toString());
			return i;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getString(Context context,String paramString){
		int id = getStringId(context, paramString);
		if (!TextUtils.isEmpty(context.getResources().getString(id)))
		{
			return context.getResources().getString(id);
		}
		return "";
	}

}
