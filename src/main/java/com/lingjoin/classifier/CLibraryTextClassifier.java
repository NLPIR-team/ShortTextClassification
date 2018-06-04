package com.lingjoin.classifier;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface CLibraryTextClassifier extends Library{

	CLibraryTextClassifier Instance = (CLibraryTextClassifier) Native.loadLibrary("TextClassification", CLibraryTextClassifier.class);
	
    public int DANClassifierInit(String datafile);
    void NewTrain(boolean increament,int ngram,int dim);
    public void AddString(String classname,String content);
    void Completed();
    public int Train();
    public void LoadModel();
    public String GetClassification(String text,int k,boolean p);
    void Exit();
}