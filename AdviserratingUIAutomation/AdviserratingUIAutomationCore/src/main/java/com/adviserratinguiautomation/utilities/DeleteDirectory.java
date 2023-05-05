package com.adviserratinguiautomation.utilities;

import java.io.File;

public class DeleteDirectory {
		 
	    // function to delete subdirectories and files
	    public void deleteDirectory(File sourceFolder)
	    {
	        // store all the paths of files and folders present
	        // inside directory
	        for (File subfile : sourceFolder.listFiles()) {
	 
	            // if it is a subfolder,e.g Rohan and Ritik,
	            //  recursively call function to empty subfolder
	            if (subfile.isDirectory()) {
	                deleteDirectory(subfile);
	            }
	 
	            // delete files and empty subfolders
	            subfile.delete();
	        }
	    }
}
