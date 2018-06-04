# ShortTextClassification
* A classification for short text using NLPIR.

For the first test version, you need NLPIR license to do train and classify.

# For Your Model

To be a better model you need make that better "NewTrain": Chinese is using uni-gram and 50-100 dim, and English is Bi-gram and 10 dim.

* Train model

```java
public class Train {

public List<String> files=new ArrayList<String>();
	
	public void getAllfiles(File filePath){
		File[] fsFiles=filePath.listFiles();
		for(File f:fsFiles){
			if(f.isFile()&&!f.getName().equals(".DS_Store")) files.add(f.getPath());
			if(f.isDirectory()) this.getAllfiles(f);
		}
	}
	
	public String getContent(File file)throws Exception{
		RandomAccessFile f=new RandomAccessFile(file, "r");
		byte[] b=new byte[(int) file.length()];
		f.read(b);
		f.close();
		String c=new String(b,"UTF-8").replaceAll("\\s", "");
		return c;
	}
	
	public static void main(String[] args) throws Exception{
		CLibraryTextClassifier.Instance.TextClassifierInit("");
		CLibraryTextClassifier.Instance.NewTrain(false,1,50);
		String fPath="train";
		Train test=new Train();
		test.getAllfiles(new File(fPath));
		for(String f:test.files){
			//System.out.println(f);
			String con=test.getContent(new File(f));
			String[] cls=new File(f).getParent().split("/");
			String cl=cls[cls.length-1];
			System.out.print(cl+"\t");
			CLibraryTextClassifier.Instance.AddString(cl, con);
		}
		CLibraryTextClassifier.Instance.Completed();
		int t=CLibraryTextClassifier.Instance.Train();
		System.out.println(t);
	}
}
```

* Classify

```java
public class Test {

	public static void main(String[] args) throws Exception{
		CLibraryTextClassifier.Instance.TextClassifierInit("");
		CLibraryTextClassifier.Instance.LoadModel();
		String fPath="train";
		Train test=new Train();
		test.getAllfiles(new File(fPath));
		int total=0;
		int pre=0;
		for(String f:test.files){
			//System.out.println(f);
			String con=test.getContent(new File(f));
			String[] cls=new File(f).getParent().split("/");
			String cl=cls[cls.length-1];
			String result=CLibraryTextClassifier.Instance.GetClassification(con , 1,false);//获取最优分类结果
			System.out.println(cl+"\t"+result);
			if(result.contains(cl)) ++pre;
			++total;
		}
		System.out.println((float)pre/(float)total);
	}
}
```