package com.study.encode.CommonEncoding.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtils {
 public FileUtils() {
 }

 public static String getProjectPath(Class cls) {
     String projectPath = cls.getResource("/").getPath();
     String separator = File.separator;
     if(separator.equals("\\")) {
         projectPath = projectPath.substring(1);
     }

     projectPath = projectPath.substring(0, projectPath.indexOf("/target/classes/"));
     return projectPath;
 }

 public static boolean smartCreateDirectory(String path) {
     String last = path.substring(path.lastIndexOf("/") + 1);
     if(last.indexOf(".") != -1) {
         path = path.substring(0, path.lastIndexOf("/"));
     }

     File file = new File(path);
     if(!file.exists()) {
         file.mkdirs();
         return true;
     } else {
         return false;
     }
 }

 public static boolean createFile(String filePath, byte[] fileContent) {
     FileOutputStream fos = null;

     boolean var4;
     try {
         File file = new File(filePath);
         fos = new FileOutputStream(file);
         fos.write(fileContent);
         fos.flush();
         var4 = true;
         return var4;
     } catch (Exception var8) {
         var8.printStackTrace();
         var4 = false;
     } finally {
         close((OutputStream)fos);
     }

     return var4;
 }

 public static void deleteFile(String filePath) {
     File file = new File(filePath);
     if(file.isDirectory()) {
         File[] var2 = file.listFiles();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
             File f = var2[var4];
             deleteFile(f.getPath());
         }
     } else {
         file.delete();
     }

 }

 public static List<String> unzip(String zipFilePath, String outputPath) {
     List<String> fileNameList = new ArrayList();
     ZipInputStream zipInputStream = null;
     BufferedInputStream bufferedInputStream = null;
     FileOutputStream fileOutputStream = null;
     BufferedOutputStream bufferedOutputStream = null;
     ZipEntry zipEntry = null;
     File file = null;

     try {
         zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath));
         bufferedInputStream = new BufferedInputStream(zipInputStream);

         while((zipEntry = zipInputStream.getNextEntry()) != null && !zipEntry.isDirectory()) {
             file = new File(outputPath, zipEntry.getName());
             if(!file.exists()) {
                 (new File(file.getParent())).mkdirs();
             }

             fileOutputStream = new FileOutputStream(file);
             bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

             int b;
             while((b = bufferedInputStream.read()) != -1) {
                 bufferedOutputStream.write(b);
             }

             fileNameList.add(file.getName());
         }

         return fileNameList;
     } catch (Exception var13) {
         var13.printStackTrace();
     } finally {
         close((OutputStream)bufferedOutputStream);
         close((OutputStream)fileOutputStream);
         close((InputStream)bufferedInputStream);
         close((InputStream)zipInputStream);
     }

     return null;
 }

 public static String readFile(String filePath, String charset) {
     File file = new File(filePath);
     StringBuffer content = new StringBuffer();
     FileInputStream fileInputStream = null;
     InputStreamReader reader = null;
     BufferedReader bufferedReader = null;

     try {
         fileInputStream = new FileInputStream(file);
         reader = new InputStreamReader(fileInputStream, charset);
         bufferedReader = new BufferedReader(reader);

         String line;
         while((line = bufferedReader.readLine()) != null) {
             content.append(line + "\n");
         }
     } catch (Exception var11) {
         var11.printStackTrace();
     } finally {
         close((Reader)bufferedReader);
         close((Reader)reader);
         close((InputStream)fileInputStream);
     }

     return content.toString();
 }

 public static void close(InputStream inputStream) {
     if(inputStream != null) {
         try {
             inputStream.close();
         } catch (IOException var2) {
             var2.printStackTrace();
         }
     }

 }

 public static void close(OutputStream outputStream) {
     if(outputStream != null) {
         try {
             outputStream.close();
         } catch (IOException var2) {
             var2.printStackTrace();
         }
     }

 }

 public static void close(Reader reader) {
     if(reader != null) {
         try {
             reader.close();
         } catch (IOException var2) {
             var2.printStackTrace();
         }
     }

 }

 public static void close(Writer writer) {
     if(writer != null) {
         try {
             writer.close();
         } catch (IOException var2) {
             var2.printStackTrace();
         }
     }

 }
}
