package com.lingjoin.classifier.train;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.lingjoin.classifier.CLibraryTextClassifier;

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
