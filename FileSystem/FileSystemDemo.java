package FileSystem;

import java.util.ArrayList;
import java.util.List;

interface IFileSystem{
    void ls();
    void ls(String filename);
}
class File implements IFileSystem{
    private String fileName;
    File(String fileName){
        this.fileName = fileName;
    }
    @Override
    public void ls(){
        System.out.println(this.fileName);
    }
    @Override
    public void ls(String dirPath){
        System.out.println(dirPath + "\\" + this.fileName);
    }
}

class Directory implements IFileSystem{
    private String directoryName;
    private List<IFileSystem> fileSystems = new ArrayList<>();
    Directory(String directoryName){
        this.directoryName = directoryName;
    }
    @Override
    public void ls(){
        this.ls("");
    }
    @Override
    public void ls(String dirPath){
        for (IFileSystem fileSystem : this.fileSystems) {
            fileSystem.ls(dirPath + "\\" + directoryName);
        }
    }
    public void addFileOrDir(IFileSystem file){
        this.fileSystems.add(file);
    }
}

public class FileSystemDemo {
    public static void main(String[] args){
        IFileSystem file1 = new File("test1.txt");
        IFileSystem file2 = new File("test2.txt");
        Directory directory = new Directory("dir1");
        directory.addFileOrDir(file1);
        directory.addFileOrDir(file2);
        directory.ls();
        file1.ls();
    }
}
