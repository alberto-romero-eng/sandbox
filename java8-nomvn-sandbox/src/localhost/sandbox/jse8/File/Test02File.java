package localhost.sandbox.jse8.File;

import java.io.File;

/**
 * Tests regarding {@link File}.
 * 
 * @author Alberto Romero
 * @since 2026-05-16
 * 
 */
public class Test02File {

	public static void test00FileCurrentDir() {
		// String relPath = "tmp/numbers.txt";

		// file 1
		System.out.println("--- file1 ---");
		String relPath1 = "";
		File file1 = new File(relPath1);
		boolean file1_IsAbs = file1.isAbsolute();
		boolean file1_Exists = file1.exists();
		boolean file1_IsDir = file1.isDirectory();
		boolean file1_IsFile = file1.isFile();
		String file1_Name = file1.getName();
		String file1_AbsPath = file1.getAbsolutePath();
		String file1_Path = file1.getPath();
		System.out.println("file1_Src:     '" + relPath1 + "'");
		System.out.println("file1_IsAbs:   '" + file1_IsAbs + "'");
		System.out.println("file1_Exists:  '" + file1_Exists + "'");
		System.out.println("file1_IsDir:   '" + file1_IsDir + "'");
		System.out.println("file1_IsFile:  '" + file1_IsFile + "'");
		System.out.println("file1_Name:    '" + file1_Name + "'");
		System.out.println("file1_Path:    '" + file1_Path + "'");
		System.out.println("file1_AbsPath: '" + file1_AbsPath + "'");

		// file 2
		System.out.println("--- file2 ---");
		String absPath2 = file1_AbsPath;
		File file2 = new File(absPath2);
		boolean file2_IsAbs = file2.isAbsolute();
		boolean file2_Exists = file2.exists();
		boolean file2_IsDir = file2.isDirectory();
		boolean file2_IsFile = file2.isFile();
		String file2_Name = file2.getName();
		String file2_AbsPath = file2.getAbsolutePath();
		String file2_Path = file2.getPath();
		System.out.println("file2_Src:     '" + file1_AbsPath + "'");
		System.out.println("file2_IsAbs:   '" + file2_IsAbs + "'");
		System.out.println("file2_Exists:  '" + file2_Exists + "'");
		System.out.println("file2_IsDir:   '" + file2_IsDir + "'");
		System.out.println("file2_IsFile:  '" + file2_IsFile + "'");
		System.out.println("file2_Name:    '" + file2_Name + "'");
		System.out.println("file2_Path:    '" + file2_Path + "'");
		System.out.println("file2_AbsPath: '" + file2_AbsPath + "'");

		// file 3
		System.out.println("--- file3 ---");
		String relPath3 = ".";
		File file3 = new File(relPath3);
		boolean file3_IsAbs = file3.isAbsolute();
		boolean file3_Exists = file3.exists();
		boolean file3_IsDir = file3.isDirectory();
		boolean file3_IsFile = file3.isFile();
		String file3_Name = file3.getName();
		String file3_AbsPath = file3.getAbsolutePath();
		String file3_Path = file3.getPath();
		System.out.println("file3_Src:     '" + relPath3 + "'");
		System.out.println("file3_IsAbs:   '" + file3_IsAbs + "'");
		System.out.println("file3_Exists:  '" + file3_Exists + "'");
		System.out.println("file3_IsDir:   '" + file3_IsDir + "'");
		System.out.println("file3_IsFile:  '" + file3_IsFile + "'");
		System.out.println("file3_Name:    '" + file3_Name + "'");
		System.out.println("file3_Path:    '" + file3_Path + "'");
		System.out.println("file3_AbsPath: '" + file3_AbsPath + "'");
	}

}
