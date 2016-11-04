package bwf.androiddemos.utils;

import java.util.ArrayList;
import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.Size;

@SuppressWarnings("deprecation")
public class CameraUtil {

    // 分别为 默认摄像头（后置）、默认调用摄像头的分辨率、被选择的摄像头（前置或者后置）
    private int defaultScreenResolution = -1;

    // 预览的宽高和屏幕宽高
    private double screenWidth = 480;

    private double screenHeight = 800;

    private int savedWidth;

    private int savedHeight;

    // 用来保存分辨率的宽高比和屏幕宽高比一致的相机分辨率数组
    private ArrayList<Size> previewSizeSuiteScreenArrayList;

    public CameraUtil(double width, double height) {
        screenWidth = width;
        screenHeight = height;
        previewSizeSuiteScreenArrayList = new ArrayList<Size>();
    }

    /**
     * 计算出不会发生拉伸的供mCamera使用的预览宽高尺寸参数
     */
    public Camera.Parameters calculateSuitePreviewSize(Camera.Parameters parameters) {
        // 获取摄像头的所有支持的分辨率
        List<Size> resolutionList = parameters.getSupportedPreviewSizes();
        if (resolutionList != null && resolutionList.size() > 0) {
            Size previewSize = null;
            // defaultScreenResolution默认摄像头的分辨率
            if (defaultScreenResolution == -1) {
                boolean hasSize = false;
                // 因为有的机子的相机分辨率列表是从大往小排列的，有的机子的是从小往大排列的，需要先进行判断
                if (resolutionList.get(0).width > resolutionList.get(resolutionList.size() - 1).width) {
                    // 获取与屏幕宽高比相近的预览尺寸
                    for (int i = resolutionList.size() - 1; i > 0; i--) {
                        previewSize = resolutionList.get(i);
                        // 每个机子的相机分辨率列表中所有预览宽高比和机子屏幕尺寸的宽高比一致的，先把这些宽高比一致的保存到数组中
                        double a = ((double) (previewSize.width)) / ((double) (previewSize.height));
                        double b = screenHeight / screenWidth;
                        if (a == b) {
                            previewSizeSuiteScreenArrayList.add(previewSize);
                            break;
                        }
                    }

                } else {
                    // 获取与屏幕宽高比相近的预览尺寸
                    for (int i = 0; i < resolutionList.size(); i++) {
                        previewSize = resolutionList.get(i);
                        double a = ((double) (previewSize.width)) / ((double) (previewSize.height));
                        double b = screenHeight / screenWidth;
                        if (a == b) {
                            previewSizeSuiteScreenArrayList.add(previewSize);
                            break;
                        }
                    }
                }
                // 如果分辨率宽高比和屏幕宽高比一致的有多个则选择中间的那个（防止如果选择了较高分辨率得到的图片比较大，在展示界面预览时会崩溃）
                if (previewSizeSuiteScreenArrayList.size() > 1) {
                    hasSize = true;
                    savedWidth = previewSizeSuiteScreenArrayList
                            .get(Math.abs(previewSizeSuiteScreenArrayList.size() / 2)).width;
                    savedHeight = previewSizeSuiteScreenArrayList
                            .get(Math.abs(previewSizeSuiteScreenArrayList.size() / 2)).height;
                }
                // 如果分辨率宽高比和屏幕宽高比一致的只有一个,就选择那一个
                else if (previewSizeSuiteScreenArrayList.size() == 1) {
                    hasSize = true;
                    savedWidth = previewSizeSuiteScreenArrayList.get(0).width;
                    savedHeight = previewSizeSuiteScreenArrayList.get(0).height;
                } else {
                    hasSize = false;
                }
                // 如果不支持设为最大的那个
                if (!hasSize) {
                    if (resolutionList.get(0).width > resolutionList.get(resolutionList.size() - 1).width)
                        previewSize = resolutionList.get(0);
                    else
                        previewSize = resolutionList.get(resolutionList.size() - 1);

                    savedWidth = previewSize.width;
                    savedHeight = previewSize.height;
                }

            } else {
                if (defaultScreenResolution >= resolutionList.size())
                    defaultScreenResolution = resolutionList.size() - 1;
                previewSize = resolutionList.get(defaultScreenResolution);
                savedWidth = previewSize.width;
                savedHeight = previewSize.height;
            }
            // 获取计算过的摄像头分辨率
            if (previewSize != null) {
                parameters.setPreviewSize(previewSize.width, previewSize.height);
            }
        }
        return parameters;

    }

    public Size calculateSuitePreviewSize2(Camera.Parameters parameters) {
        Size previewSize = null;
        // 获取摄像头的所有支持的分辨率
        List<Size> resolutionList = parameters.getSupportedPreviewSizes();
        if (resolutionList != null && resolutionList.size() > 0) {
            // defaultScreenResolution默认摄像头的分辨率
            if (defaultScreenResolution == -1) {
                boolean hasSize = false;
                // 因为有的机子的相机分辨率列表是从大往小排列的，有的机子的是从小往大排列的，需要先进行判断
                if (resolutionList.get(0).width > resolutionList.get(resolutionList.size() - 1).width) {
                    // 获取与屏幕宽高比相近的预览尺寸
                    for (int i = resolutionList.size() - 1; i > 0; i--) {
                        previewSize = resolutionList.get(i);
                        if (previewSize.height >= 480) // 最小预览尺寸不能小于480
                        {
                            // 每个机子的相机分辨率列表中所有预览宽高比和机子屏幕尺寸的宽高比一致的，先把这些宽高比一致的保存到数组中
                            double a = ((double) (previewSize.width)) / ((double) (previewSize.height));
                            double b = screenHeight / screenWidth;
                            if (a == b) {
                                previewSizeSuiteScreenArrayList.add(previewSize);
                                break;
                            }
                        }
                    }

                } else {
                    // 获取与屏幕宽高比相近的预览尺寸
                    for (int i = 0; i < resolutionList.size(); i++) {
                        previewSize = resolutionList.get(i);
                        if (previewSize.height >= 480) // 最小预览尺寸不能小于480
                        {
                            double a = ((double) (previewSize.width)) / ((double) (previewSize.height));
                            double b = screenHeight / screenWidth;
                            if (a == b) {
                                previewSizeSuiteScreenArrayList.add(previewSize);
                                break;
                            }
                        }
                    }
                }
                // 如果分辨率宽高比和屏幕宽高比一致的有多个则选择中间的那个（防止如果选择了较高分辨率得到的图片比较大，在展示界面预览时会崩溃）
                if (previewSizeSuiteScreenArrayList.size() > 1) {
                    hasSize = true;
                    savedWidth = previewSizeSuiteScreenArrayList
                            .get(Math.abs(previewSizeSuiteScreenArrayList.size() / 2)).width;
                    savedHeight = previewSizeSuiteScreenArrayList
                            .get(Math.abs(previewSizeSuiteScreenArrayList.size() / 2)).height;
                }
                // 如果分辨率宽高比和屏幕宽高比一致的只有一个,就选择那一个
                else if (previewSizeSuiteScreenArrayList.size() == 1) {
                    hasSize = true;
                    savedWidth = previewSizeSuiteScreenArrayList.get(0).width;
                    savedHeight = previewSizeSuiteScreenArrayList.get(0).height;
                } else {
                    hasSize = false;
                }
                // 如果不支持设为最大的那个
                if (!hasSize) {
                    if (resolutionList.get(0).width > resolutionList.get(resolutionList.size() - 1).width)
                        previewSize = resolutionList.get(0);
                    else
                        previewSize = resolutionList.get(resolutionList.size() - 1);

                    savedWidth = previewSize.width;
                    savedHeight = previewSize.height;
                }

            } else {
                if (defaultScreenResolution >= resolutionList.size())
                    defaultScreenResolution = resolutionList.size() - 1;
                previewSize = resolutionList.get(defaultScreenResolution);
                savedWidth = previewSize.width;
                savedHeight = previewSize.height;
            }
        }
        return previewSize;

    }

    public int getSavedWidth() {
        return savedWidth;
    }

    public int getSavedHeight() {
        return savedHeight;
    }

}
