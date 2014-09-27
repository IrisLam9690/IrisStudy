package com.vstartek.pca.kmeans;

import java.util.ArrayList;
import java.util.List;

public class kMeansPoint {
	/*
	 * Represents an abstraction for a data point in two dimensional space
	 *
	 * Manas Somaiya
	 * Computer and Information Science and Engineering
	 * University of Florida
	 *
	 * Created: October 29, 2003
	 * Last updated: October 30, 2003
	 *
	 */
	 
	 
	 
	/**
	 * Represents an abstraction for a data point in two dimensional space
	 * @author	Manas Somaiya	mhs@cise.ufl.edu
	 */ 
	
		/** Value in dimension x */
		private double x;
		

		/** Value in dimension y */
		private double y;
		
		/** Value in dimension z */
		private double z;
		
		/** Assigned cluster */
		private int clusterNumber;
		
		//数据点集
		private List<Double> listData;

		/**
		 * Creates a new instance of data point
		 *
		 * @param	_x	value in dimension x
		 * @param	_y	value in dimension y
		 */
		public kMeansPoint(double _x, double _y, double _z) {
		
			this.x = _x;
			this.y = _y;
			this.z = _z;
			this.clusterNumber=0;
		} // end of kMeansPoint()
		
		public kMeansPoint(List<Double> list){
			this.listData = list;
			this.clusterNumber = 0;
		}
		
		public kMeansPoint(Double[] douS){
			List<Double> list = new ArrayList<Double>();
			for(Double d:douS){
				Double dd = d;
				list.add(dd);	
			}
			this.listData = list;
			this.clusterNumber = 0;
		}
		/**
		 * Assigns the data point to a cluster
		 *
		 * @param	_clusterNumber	the cluster to which this data point is to be assigned
		 */
		public void assignToCluster(int _clusterNumber) {
		
			this.clusterNumber = _clusterNumber;
		
		} // end of assignToCluster()
		
		
		/**
		 * Returns the cluster to which the data point belongs
		 *
		 * @return	the cluster number to which the data point belongs
		 */
		public int getClusterNumber() {
		
			return this.clusterNumber;
		
		} // end of getClusterNumber()
		
		
		/**
		 * Returns the value of data point in x dimension
		 *
		 * @return	the value in x dimension
		 */
		public double getX() {
		
			return this.x;
		
		} // end of getX()
		
		
		/**
		 * Returns the value of data point in y dimension
		 *
		 * @return	the value in y dimension
		 */
		public double getY() {
		
			return this.y;
		
		} // end of getY()
		
		public double getZ() {
			
			return this.z;
		
		} // end of getY()
		
		public List<Double> getListData(){
			return this.listData;
		}
		
		public void setListData(List<Double> list){
			this.listData = list;
		}
		/**
		 * Returns the distance between two data points
		 *
		 * @param	dp1 	the first data point
		 * @param	dp2 	the second data point
		 * @return	the distance between the two data points
		 */
		public static double distance(kMeansPoint dp1, kMeansPoint dp2) {
		
			double result = 0;
			double resultX = dp1.getX() - dp2.getX();
			double resultY = dp1.getY() - dp2.getY();
			double resultZ = dp1.getZ() - dp2.getZ();
			result = Math.sqrt(resultX*resultX + resultY*resultY + resultZ*resultZ);
			return result;
		
		} // end of distance()
		
		public static double comPointsDis(kMeansPoint dp1, kMeansPoint dp2){
			double result = 0.0;
			double sum = 0.0;
			List<Double> listMinus = new ArrayList<Double>();
			for(int i = 0; i < dp1.getListData().size(); i++){
				Double min = 0.0;
				min = dp1.getListData().get(i) - dp2.getListData().get(i);
				listMinus.add(min);
			}
			for(int i = 0; i < listMinus.size(); i++){
				sum = sum + listMinus.get(i)*listMinus.get(i);
			}
			result = Math.sqrt(sum);
			return result;
		}
		
		/**
		 * Returns a string representation of this kMeansPoint
		 *
		 * @return	a string representation of this data point
		 */
		public String toString(){
		
			return "(" + this.x + "," + this.y + "," + this.z + ")[" + this.clusterNumber + "]";
		
		} // end of toString()
		
		
		/**
		 * Main method -- to test the kMeansPoint class
		 *
		 * @param	args	command line arguments
		 */
		public static void main(String[] args) {
		
//			kMeansPoint dp1 = new kMeansPoint(-1.0,-1.0,1.0);
//			kMeansPoint dp2 = new kMeansPoint(0.0,0.0,0);
//			System.out.println(kMeansPoint.distance(dp1, dp2));
//			System.out.println(dp1.getX());
//			System.out.println(dp2.getY());
//			dp1.assignToCluster(7);
//			System.out.println(dp1.getClusterNumber());
//			dp1.assignToCluster(17);
//			System.out.println(dp1.getClusterNumber());
//			System.out.println(dp2.getClusterNumber());
//			System.out.println(dp1);
		
			double[] ds1 = {-1.0,-1.0,1.0};
			List<Double> list1 = new ArrayList<Double>();
			for(double d:ds1){
				list1.add(d);
			}
			double[] ds2 = {0,0,0};
			List<Double> list2 = new ArrayList<Double>();
			for(double d:ds2){
				list2.add(d);
			}
			kMeansPoint dp1 = new kMeansPoint(list1);
			kMeansPoint dp2 = new kMeansPoint(list2);
			System.out.println(kMeansPoint.comPointsDis(dp1, dp2));
		} // end of main()


	} // end of class

