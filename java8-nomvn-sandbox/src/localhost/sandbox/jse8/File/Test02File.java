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

	public static void test00FileDirCurrentAndRoot() {
		// String relPath = "tmp/numbers.txt";

		// file 1
		System.out.println("--- file1 ---");
		String srcPath1 = "";
		File file1 = new File(srcPath1);
		System.out.println("srcPath1:      '" + srcPath1 + "'");
		printFileProperties(file1);

		// file 2
		System.out.println("--- file2 ---");
		String srcPath2 = file1.getAbsolutePath();
		File file2 = new File(srcPath2);
		System.out.println("srcPath2:      '" + srcPath2 + "'");
		printFileProperties(file2);

		// file 3
		System.out.println("--- file3 ---");
		String srcPath3 = ".";
		File file3 = new File(srcPath3);
		System.out.println("srcPath3:      '" + srcPath3 + "'");
		printFileProperties(file3);

		// file 4
		System.out.println("--- file4 ---");
		String srcPath4 = "./";
		File file4 = new File(srcPath4);
		System.out.println("srcPath4:      '" + srcPath4 + "'");
		printFileProperties(file4);

		// file 5
		System.out.println("--- file5 ---");
		String srcPath5 = "/";
		File file5 = new File(srcPath5);
		System.out.println("srcPath5:      '" + srcPath5 + "'");
		printFileProperties(file5);

		return;
	}


	private static void printFileProperties (File f) {
		boolean file_IsAbs = f.isAbsolute();
		boolean file_Exists = f.exists();
		boolean file_IsDir = f.isDirectory();
		boolean file_IsFile = f.isFile();
		String file_Name = f.getName();
		String file_AbsPath = f.getAbsolutePath();
		String file_Path = f.getPath();
		System.out.println("file_toString: '" + f.toString() + "'");
		System.out.println("file_IsAbs:    '" + file_IsAbs + "'");
		System.out.println("file_Exists:   '" + file_Exists + "'");
		System.out.println("file_IsDir:    '" + file_IsDir + "'");
		System.out.println("file_IsFile:   '" + file_IsFile + "'");
		System.out.println("file_Name:     '" + file_Name + "'");
		System.out.println("file_Path:     '" + file_Path + "'");
		System.out.println("file_AbsPath:  '" + file_AbsPath + "'");
	}

}
