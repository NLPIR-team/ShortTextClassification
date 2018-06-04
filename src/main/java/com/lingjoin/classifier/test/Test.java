package com.lingjoin.classifier.test;

import java.io.File;

import com.lingjoin.classifier.CLibraryTextClassifier;
import com.lingjoin.classifier.train.Train;


public class Test {

	public static void main(String[] args) throws Exception{
		CLibraryTextClassifier.Instance.DANClassifierInit("");
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
			String result=CLibraryTextClassifier.Instance.GetClassification(con , 1,false);
			System.out.println(cl+"\t"+result);
			if(result.contains(cl)) ++pre;
			++total;
		}
		System.out.println((float)pre/(float)total);
	}
}
