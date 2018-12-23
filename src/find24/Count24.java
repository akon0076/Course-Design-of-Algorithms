/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package find24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class Count24 {

	private List<String> answerList = new ArrayList<String>();

	public List<String> getAnswerList() {
		return answerList;
	}

	//内部类Data
	public static class Data {

		public float[] arr;//四个操作数的存放容器
		public String expStr = "";//最终的表达式
		public String[] strs;//组成的表达式

		public Data() {
		}

		public Data(int a, int b, int c, int d) {
			arr = new float[]{a, b, c, d};
			strs = new String[]{a + "", b + "", c + "", d + ""};
			expStr = a + "";
		}

		public Data(int arr[]) {
			this.arr = new float[]{arr[0], arr[1], arr[2], arr[3]};
			this.strs = new String[]{arr[0] + "", arr[1] + "", arr[2] + "", arr[3] + ""};
		}
	}

	// 计算的主要函数,递归
	public void count(Data data) {
		float[] arr = data.arr;
		//如果式子的长度只有1，判断是不是就是24，是的话就直接加入解的表里
		if (arr.length <= 1) {
			if (arr.length == 1 && arr[0] == 24) {
				answerList.add(data.expStr.substring(1, data.expStr.length() - 1));
			}
			return;
		}
		//否则就遍历
		for (int i = 0, len = arr.length; i < len - 1; i++) {
			for (int j = i + 1; j < len; j++) {
				float x = arr[i];
				float y = arr[j];
				String xs = data.strs[i];
				String ys = data.strs[j];
				for (int k = 0; k < 6; k++) {
					Data newData = getNewArr(data, i);
					switch (k) {
					case 0:
						newData.arr[j - 1] = x + y;
						newData.expStr = xs + "+" + ys;
						break;
					case 1:
						newData.arr[j - 1] = x - y;
						newData.expStr = xs + "-" + ys;
						break;
					case 2:
						newData.arr[j - 1] = y - x;
						newData.expStr = ys + "-" + xs;
						break;
					case 3:
						newData.arr[j - 1] = x * y;
						newData.expStr = xs + "*" + ys;
						break;
					case 4:
						if (y != 0) {
							newData.arr[j - 1] = x / y;
							newData.expStr = xs + "/" + ys;
						} else {
							continue;
						}
						break;
					case 5:
						if (x != 0) {
							newData.arr[j - 1] = y / x;
							newData.expStr = ys + "/" + xs;
						} else {
							continue;
						}
						break;
					default: break;
					}
					newData.expStr = "(" + newData.expStr + ")";
					newData.strs[j - 1] = newData.expStr;
					count(newData);
				}
			}
		}

	}

	private static Data getNewArr(Data data, int i) {
		Data newData = new Data();
		newData.expStr = data.expStr;
		newData.arr = new float[data.arr.length - 1];
		newData.strs = new String[data.arr.length - 1];
		for (int m = 0, len = data.arr.length, n = 0; m < len; m++) {
			if (m != i) {
				newData.arr[n] = data.arr[m];
				newData.strs[n] = data.strs[m];
				n++;
			}
		}
		return newData;
	}

	public List<String> easyCount(int[] curRandNums) {
		Count24 count24 = new Count24();
		count24.count(new Data(curRandNums));
		Set<String> set = new HashSet<String>(count24.getAnswerList());// 去重
		return new ArrayList<String>(set);
	}
}
