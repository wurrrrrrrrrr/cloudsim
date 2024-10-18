package org.cloudbus.cloudsim.examples;

import java.util.Arrays;
import java.util.Random;

public class multiobjgreywolf {
    static final int MAX_ITERATIONS = 1000;  // 𝜉 - 最大迭代次數
    static final int NUM_TASKS = 10;         // 𝑚 - 任務數
    static final int NUM_VMS = 5;            // 𝑝 - 虛擬機數
    static final int NUM_WOLVES = 20;        // 𝑊 - 狼群數量
    
    static Random rand = new Random();

    // 狼的解表示，每個狼的解是任務如何分配給虛擬機器
    static int[][] wolves = new int[NUM_WOLVES][NUM_TASKS]; // 狼的解
    static double[] fitness = new double[NUM_WOLVES];       // 每個狼的適應度
    static int[][] wolvesort = new int[wolves.length][];
    public static int[] a = new int[NUM_TASKS];
    public static int[] b = new int[NUM_TASKS];
    public static int[] c = new int[NUM_TASKS];

    
    public static void main(String[] args) {
    	//設定初始
        double[][] A = new double[3][NUM_TASKS];
        double[][] D = new double[3][NUM_TASKS];
        double[][] X = new double[3][NUM_TASKS];
        double a1;
        // 1. 初始化狼群
        initializeWolves();

        int iteration = 0;
        for (int i = 0; i < wolves.length; i++) {
            wolvesort[i] = Arrays.copyOf(wolves[i], wolves[i].length); // 複製每個子陣列
        }
        while (iteration < MAX_ITERATIONS) {
        	//1.先設立三匹狼
        	a = Arrays.copyOf(wolves[0], wolves[0].length);
        	b = Arrays.copyOf(wolves[1], wolves[1].length);
        	c = Arrays.copyOf(wolves[2], wolves[2].length);
            
            a1 = 2 - iteration * (2.0 / MAX_ITERATIONS);
        	
            for (int i = 0; i < NUM_WOLVES; i++) {
                for (int z = 0; z < NUM_TASKS; z++) {
                    // 生成隨機數 r0 到 r6
                    double[] r = new double[7];
                    for (int k = 0; k < 7; k++) {
                        r[k] = rand.nextDouble(); // 生成 0 到 1 之間的隨機數
                    }

                    for (int j = 0; j <= 2; j++) {
                        A[j][z] = 2 * a1 * r[j*2] - a1;
                        D[j][z] = Math.abs(2 * r[j*2+1]  + a[z] - wolvesort[i][z]);
                        X[j][z] = Math.abs(A[j][z] * D[j][z]);
                    }

                    // 根據 r6 的值更新 W
                    if (r[6] < 0.25) {
                    	wolvesort[i][z] = (int) Math.ceil((X[0][z] + X[1][z]) / 2) % NUM_VMS;
                    } else if (r[6] < 0.50) {
                    	wolvesort[i][z] = (int) Math.ceil((X[0][z] + X[2][z]) / 2) % NUM_VMS;
                    } else if (r[6] < 0.75) {
                    	wolvesort[i][z] = (int) Math.ceil((X[1][z] + X[2][z]) / 2) % NUM_VMS;
                    } else {
                    	wolvesort[i][z] = (int) Math.ceil((X[0][z] + X[0][z]) / 2) % NUM_VMS;
                    }
                }
            }
         }
     }
        	
        	


        // 5. 輸出最好的解
    

    // 初始化狼群，隨機分配任務給虛擬機
    static void initializeWolves() {
        for (int i = 1; i < NUM_WOLVES; i++) {
            for (int j = 0; j < NUM_TASKS; j++) {
                wolves[i][j] = rand.nextInt(NUM_VMS); // 每個任務隨機分配給一個虛擬機
            }
        }
    }

    static int[][] WeightedSort(int[][] wolves) {
    	for (int i = 1; i < NUM_WOLVES; i++) {
    		for (int j = 0; j < NUM_TASKS; j++) {
    			wolves[i][j] = rand.nextInt(NUM_VMS);
    			return 0;// 每個任務隨機分配給一個虛擬機
    			}
    		}
    }
    
}
   

