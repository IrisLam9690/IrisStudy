package com.vstartek.pca.kmeans;



import java.io.*;
import java.util.*;
 
public class kMeans {


	/** Number of clusters */
	private int k;
	

	/** Array of clusters */
	private cluster[] clusters;
	
	
	/** Number of iterations */
	private int nIterations;
	
	
	/** Vector of data points */
	private Vector kMeansPoints;
	
	
	/** Name of the input file */
	private String inputFileName;
	
	
	/**
	 * Returns a new instance of kMeans algorithm
	 *
	 * @param	k		number of clusters
	 * @param	inputFileName	name of the file containing input data
	 */
         public kMeans(int k, String inputFileName) {
	
		this.k = k;
		this.inputFileName = inputFileName;
		this.clusters = new cluster[this.k];
		this.nIterations = 0;
		this.kMeansPoints = new Vector();
	
	} // end of kMeans()


	/**
	 * Returns a new instance of kMeans algorithm
	 *
	 * @param	k		number of clusters
	 * @param	kMeansPoints	List containing objects of type kMeansPoint
	 */
         public kMeans(int k, List kMeansPoints) {
	
		this.k = k;
		this.inputFileName = inputFileName;
		this.clusters = new cluster[this.k];
		this.nIterations = 0;
		this.kMeansPoints=new Vector(kMeansPoints);
	
	} // end of kMeans()
	
	
	/**
	 * Reads the input data from the file and stores the data points in the vector
	 */
	public void readData() throws IOException{
	
		BufferedReader in = new BufferedReader(new FileReader(this.inputFileName));
		String line = "";
		while ((line = in.readLine()) != null ){
                        
//			StringTokenizer st = new StringTokenizer(line, " \t\n\r\f,");
			String[] st = line.split(":");
			    //3维
				if (st.length == 3) {
					
					kMeansPoint dp = new kMeansPoint(Double.parseDouble(st[0]), Double.parseDouble(st[1]), Double.parseDouble(st[2]));
					dp.assignToCluster(0);
					this.kMeansPoints.add(dp);
                        
                }
				
				if(st.length>=2){
                	List<Double> listST = new ArrayList<Double>();
                	for(String str:st){
                		listST.add(Double.parseDouble(str));
                	}
                	kMeansPoint dp = new kMeansPoint(listST);
                	dp.assignToCluster(0);
                	this.kMeansPoints.add(dp);
                }
		}
		
		in.close();
	
	} // end of readData()
	
	
	/**
	 * Runs the k-means algorithm over the data set
	 */
	public void runKMeans() {
	
		// Select k points as initial means
		for (int i=0; i < k; i++){
		
			this.clusters[i] = new cluster(i);
			this.clusters[i].setMean((kMeansPoint)(this.kMeansPoints.get((int)(Math.random() * this.kMeansPoints.size()))));
		
		}
		
		
		do {
			// Form k clusters
			Iterator i = this.kMeansPoints.iterator();
			while (i.hasNext())
				this.assignToCluster((kMeansPoint)(i.next()));
				
			this.nIterations++;
		
		}
		// Repeat while centroids do not change
		while (this.updateMeans());
	
		
	} // end of runKMeans()
	
	public static void write(String path, String content){
		String s1 = new String();
		try{
			File f = new File(path);
			if(f.exists()){
				System.out.println("文件存在");
			}else{
				System.out.println("文件不存在，正在创建....");
				if(f.createNewFile()){
					System.out.println("文件创建成功！");
				}else{
					System.out.println("文件创建失败！");
				}
			}
			
			s1 = "\n" + content;
			
			OutputStream outPut = new FileOutputStream(path, true);
			byte[] b = s1.getBytes();
			outPut.write(b, 0, b.length);
			outPut.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Assigns a data point to one of the k clusters based on its distance from the means of the clusters
	 *
	 * @param	dp	data point to be assigned
	 */
	public void assignToCluster(kMeansPoint dp) {
	
		int currentCluster = dp.getClusterNumber();
		if(dp.getListData().size() == 3){
			double minDistance = kMeansPoint.distance(dp, this.clusters[currentCluster].getMean());;
			
			for (int i=0; i <this.k; i++)
				if (kMeansPoint.distance(dp, this.clusters[i].getMean()) < minDistance) {
			
					minDistance = kMeansPoint.distance(dp, this.clusters[i].getMean());
					currentCluster = i;
					
				}
		}else{
			double minDistance = kMeansPoint.comPointsDis(dp, this.clusters[currentCluster].getMean());;
			
			for (int i=0; i <this.k; i++)
				if (kMeansPoint.comPointsDis(dp, this.clusters[i].getMean()) < minDistance) {
			
					minDistance = kMeansPoint.comPointsDis(dp, this.clusters[i].getMean());
					currentCluster = i;
					
				}
		}
		
		
		dp.assignToCluster(currentCluster);	
	
	} // end of assignToCluster
	
	
	/**
	 * Updates the means of all k clusters, and returns if they have changed or not
	 *
	 * @return	have the updated means of the clusters changed or not
	 */
	private boolean updateMeans() {
	
		boolean reply = false;
		if(((kMeansPoint)this.kMeansPoints.get(0)).getListData().size() == 3){
			double[] x = new double[this.k];
			double[] y = new double[this.k];
			double[] z = new double[this.k];
			int[] size = new int[this.k];
			kMeansPoint[] pastMeans = new kMeansPoint[this.k];
			
			for (int i=0; i<this.k; i++) {
			
				x[i] = 0.0;
				y[i] = 0.0;
				z[i] = 0.0;
				size[i] = 0;
				pastMeans[i] = this.clusters[i].getMean();
			
			}
			
			Iterator i = this.kMeansPoints.iterator();
			while (i.hasNext()) {
			
			
				kMeansPoint dp = (kMeansPoint)(i.next());
				int currentCluster = dp.getClusterNumber();
				
				x[currentCluster] += dp.getX();
				y[currentCluster] += dp.getY();
				z[currentCluster] += dp.getZ();
				size[currentCluster]++;
			
			}
			
			for (int j=0; j < this.k; j++ ) 
				if(size[j] != 0) {
				
					x[j] /= size[j];
					y[j] /= size[j];
					z[j] /= size[j];
					kMeansPoint temp = new kMeansPoint(x[j], y[j], z[j]);
					temp.assignToCluster(j);
					this.clusters[j].setMean(temp);
					if (kMeansPoint.distance(pastMeans[j], this.clusters[j].getMean()) !=0 )
						reply = true;
						
				}
		}else{
			double[][] douAVE = new double[((kMeansPoint)this.kMeansPoints.get(0)).getListData().size()][this.k];
			int[] size = new int[this.k];
			kMeansPoint[] pastMeans = new kMeansPoint[this.k];
			for (int i=0; i<this.k; i++) {
				
				for(int j = 0; j < ((kMeansPoint)this.kMeansPoints.get(0)).getListData().size(); j++){
					douAVE[j][i] = 0.0;
				}
				size[i] = 0;
				pastMeans[i] = this.clusters[i].getMean();
			}
			
			Iterator i = this.kMeansPoints.iterator();
			while (i.hasNext()) {
			
			
				kMeansPoint dp = (kMeansPoint)(i.next());
				int currentCluster = dp.getClusterNumber();
				
				for(int j = 0; j < ((kMeansPoint)this.kMeansPoints.get(0)).getListData().size(); j++){
					douAVE[j][currentCluster] += dp.getListData().get(j);
				}
				size[currentCluster]++;
			
			}
			
			for (int j=0; j < this.k; j++ ) 
				if(size[j] != 0) {
				
					for(int k = 0; k < ((kMeansPoint)this.kMeansPoints.get(0)).getListData().size(); k++){
						douAVE[k][j] /= size[j];
					}
					List<Double> listKM = new ArrayList<Double>();
					for(int k = 0; k < ((kMeansPoint)this.kMeansPoints.get(0)).getListData().size(); k++){
						listKM.add(douAVE[k][j]);
					}
					kMeansPoint temp = new kMeansPoint(listKM);
					temp.assignToCluster(j);
					this.clusters[j].setMean(temp);
					if (kMeansPoint.comPointsDis(pastMeans[j], this.clusters[j].getMean()) !=0 )
						reply = true;
						
				}
		}
		
		
		return reply;
		
	} // end of updateMeans()


	/**
	 * Returns the value of k
	 *
	 * @return	the value of k
	 */
	public int getK() {

		return this.k;

	} // end of getK()
	
	
	/**
	 * Returns the specified cluster by index
	 *
	 * @param	index	index of the cluster to be returned
	 * @return	return the specified cluster by index
	 */
	public cluster getCluster(int index) {
	
		return this.clusters[index];
	
	} // end of getCluster()
        
        
	/**
	 * Returns the string output of the data points
	 *
	 * @return  the string output of the data points
	 */
	public String toString(){
            
		return this.kMeansPoints.toString();
            
	} // end of toString()
        
        
	/**
	 * Returns the data points
	 *
	 * @return  the data points
	 */
	public Vector getDataPoints() {
            
		return this.kMeansPoints ;
            
	} // end of getDataPoints()
        
        
	/**
	 * Main method -- to test the kMeans class
	 *
	 * @param   args    command line arguments
	 */
	public static void main(String[] args) {
         
//		//对用户矩阵，从3到100个聚类数目，分别计算这个数目下的聚类结果，写入文件
//		for(int z = 3; z <=100; z++){
//			
//			//计算z个聚类的聚类结果
//			kMeans km = new kMeans(z, "D:/pca/pcauserM0.85.txt");
//			
//			try {
//				km.readData();
//			} catch (Exception e) {
//				System.err.println(e);
//				System.exit(-1);
//			}
//	            
//			km.runKMeans();
//			System.out.println(km.getDataPoints().size());
//			System.out.println(km.getDataPoints().get(0));
//			System.out.println(km.getDataPoints().get(km.getDataPoints().size()-1));
//			
//	        //把结果写入文件
////			Iterator i = this.kMeansPoints.iterator();
////			while (i.hasNext()){
////				//设置一个String用来写入到文件
////				String strWr = new String();
////				strWr = "\n" + (kMeansPoint)(i.next());
////			}
//			
//			//把结果写入文件
//			for(int i = 0; i < km.getDataPoints().size(); i++){
//				//设置一个String用来写入到文件
//				String strWr = new String();
//				kMeansPoint kmp = (kMeansPoint) km.getDataPoints().get(i);
//				strWr = i+":"+ kmp.getClusterNumber();
//				
//				//设置一个路径用来保存文件
//				String pathZ = "D:/pca/difClusters/kM_" + z + "kind_0.85_0.3user.txt";
//				write(pathZ, strWr);
//			}
//		}
		
//		//给电影矩阵聚类，从3到100个聚类数目，分别计算这个数目下的聚类结果，写入文件
//		for(int z = 3; z <=100; z++){
//			//计算z个聚类的聚类结果
//			kMeans km = new kMeans(z, "D:/pca/pcauserMovieM0.85.txt");
//			
//			try {
//				km.readData();
//			} catch (Exception e) {
//				System.err.println(e);
//				System.exit(-1);
//			}
//	            
//			km.runKMeans();
//			System.out.println(km.getDataPoints().size());
//			System.out.println(km.getDataPoints().get(0));
//			System.out.println(km.getDataPoints().get(km.getDataPoints().size()-1));
//			
//	        //把结果写入文件
////			Iterator i = this.kMeansPoints.iterator();
////			while (i.hasNext()){
////				//设置一个String用来写入到文件
////				String strWr = new String();
////				strWr = "\n" + (kMeansPoint)(i.next());
////			}
//			
//			//把结果写入文件
//			for(int i = 0; i < km.getDataPoints().size(); i++){
//				//设置一个String用来写入到文件
//				String strWr = new String();
//				kMeansPoint kmp = (kMeansPoint) km.getDataPoints().get(i);
//				strWr = i+":"+ kmp.getClusterNumber();
//				
//				//设置一个路径用来保存文件
//				String pathZ = "D:/pca/difMovieClusters/kM_" + z + "kind_0.85_0.3movie.txt";
//				write(pathZ, strWr);
//			}
//    	}
         
		//给用户-电影类别矩阵聚类，从3到100个聚类数目，分别计算这个数目下的聚类结果，写入文件
		for(int z = 238; z <=300; z++){
			//计算z个聚类的聚类结果
			kMeans km = new kMeans(z, "D:/pca/recommendWork/pcamovieByAdd1Matrix0.85.txt");
			
			try {
				km.readData();
			} catch (Exception e) {
				System.err.println(e);
				System.exit(-1);
			}
	            
			km.runKMeans();
			System.out.println(km.getDataPoints().size());
			System.out.println(km.getDataPoints().get(0));
			System.out.println(km.getDataPoints().get(km.getDataPoints().size()-1));
			
	        //把结果写入文件
//			Iterator i = this.kMeansPoints.iterator();
//			while (i.hasNext()){
//				//设置一个String用来写入到文件
//				String strWr = new String();
//				strWr = "\n" + (kMeansPoint)(i.next());
//			}s
			
			//把结果写入文件
			for(int i = 0; i < km.getDataPoints().size(); i++){
				//设置一个String用来写入到文件
				String strWr = new String();
				kMeansPoint kmp = (kMeansPoint) km.getDataPoints().get(i);
				strWr = i+":"+ kmp.getClusterNumber();
				
				//设置一个路径用来保存文件
				String pathZ = "D:/pca/recommendWork/difMovClusters/kM_" + z + "kind_0.85_clu_movie.txt";
				write(pathZ, strWr);
			}
    	}
		
        } // end of main()

} // end of class




