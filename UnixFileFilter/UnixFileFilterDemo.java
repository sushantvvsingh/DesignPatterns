package UnixFileFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface IFile{

}

enum FileType{
    XML, JSON, PDF;
}

class File implements IFile{
    private String filename;
    private int size;
    private FileType fileType;

    
    public File(String filename, int size, FileType fileType) {
        this.filename = filename;
        this.size = size;
        this.fileType = fileType;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public int getSize() {
        return size;
    }
    public FileType getFileType() {
        return fileType;
    }
}
class Directory implements IFile{
    private String dirName;
    private List<IFile> files = new ArrayList<>();

    public Directory(String dirName) {
        this.dirName = dirName;
    }
    
    public List<IFile> getFiles() {
        return files;
    }

    public void setFiles(List<IFile> files) {
        this.files = files;
    }
    
    public void addFile(IFile file){
        this.files.add(file);
    }
    public void removeFile(IFile file){
        this.files.remove(file);
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
}

class FileSystemManager{
    private Directory parentDirectory = new Directory("//");
    public void addFile(IFile file){
        this.parentDirectory.addFile(file);
    }
    public void removeFile(IFile file){
        this.parentDirectory.removeFile(file);
    }
    public List<IFile> filterFiles(IFile parent, IFilter filter, List<FilterInputs> inputs){
        return filter.getMatchingFiles(parent, inputs);
    }
    public List<IFile> filterFiles(IFile parent, IFilter filter, FilterInputs inputs){
        return filter.getMatchingFiles(parent, new ArrayList<FilterInputs>(Arrays.asList(inputs)));
    }
}

class FilterInputs{
    private String name;
    private int size;
    private FileType type;
    public FilterInputs(String name, int size, FileType type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public int getSize() {
        return size;
    }
    public FileType getType() {
        return type;
    }
    
}

abstract class IFilter{
    abstract List<IFile> getMatchingFiles(IFile parent, List<FilterInputs> inputs);
    Boolean isValid(IFile parent, List<FilterInputs> inputs){
        return (parent != null && !inputs.isEmpty());
    }
}

class NameFilter extends IFilter{
    @Override
    public List<IFile> getMatchingFiles(IFile parent, List<FilterInputs> inputs){
        List<IFile> machedFiles = new ArrayList<>();
        if(!isValid(parent, inputs))
            return machedFiles;
        if(parent instanceof File){
            if(((File)parent).getFilename().equals(inputs.get(0).getName())){
                machedFiles.add(parent);
            }
        }
        else{
            for(IFile iFile : ((Directory)parent).getFiles()){
                machedFiles.addAll(getMatchingFiles(iFile, inputs));
            }
        }
        return machedFiles;
    }
}

class TypeFilter extends IFilter{
    @Override
    public List<IFile> getMatchingFiles(IFile parent, List<FilterInputs> inputs){
        List<IFile> machedFiles = new ArrayList<>();
        if(!isValid(parent, inputs))
            return machedFiles;
        if(parent instanceof File){
            if(((File)parent).getFileType().equals(inputs.get(0).getType())){
                machedFiles.add(parent);
            }
        }
        else{
            for(IFile iFile : ((Directory)parent).getFiles()){
                machedFiles.addAll(getMatchingFiles(iFile, inputs));
            }
        }
        return machedFiles;
    }
}

class ANDFilter extends IFilter{
    @Override
    public List<IFile> getMatchingFiles(IFile parent, List<FilterInputs> inputs){
        List<IFile> machedFiles = new ArrayList<>();
        if(!isValid(parent, inputs))
            return machedFiles;
        if(parent instanceof File){
            Boolean isMatch = true;
            for(FilterInputs input: inputs){
                if(input.getName() != null || input.getName().trim() != ""){
                    if(!((File)parent).getFilename().equals(input.getName())){
                        isMatch = false;
                        break;
                    }
                }
                if(input.getType() != null){
                    if(!((File)parent).getFileType().equals(input.getType())){
                        isMatch = false;
                        break;
                    }
                }
            }
            if(isMatch){
                machedFiles.add(parent);
            }
        }
        else{
            for(IFile iFile : ((Directory)parent).getFiles()){
                machedFiles.addAll(getMatchingFiles(iFile, inputs));
            }
        }
        return machedFiles;
    }
}

class ORFilter extends IFilter{
    @Override
    public List<IFile> getMatchingFiles(IFile parent, List<FilterInputs> inputs){
        List<IFile> machedFiles = new ArrayList<>();
        if(!isValid(parent, inputs))
            return machedFiles;
        if(parent instanceof File){
            Boolean isMatch = false;
            for(FilterInputs input: inputs){
                if(input.getName() != null || input.getName().trim() != ""){
                    if(((File)parent).getFilename().equals(input.getName())){
                        isMatch = true;
                        break;
                    }
                }
                if(input.getType() != null){
                    if(((File)parent).getFileType().equals(input.getType())){
                        isMatch = true;
                        break;
                    }
                }
            }
            if(isMatch){
                machedFiles.add(parent);
            }
        }
        else{
            for(IFile iFile : ((Directory)parent).getFiles()){
                machedFiles.addAll(getMatchingFiles(iFile, inputs));
            }
        }
        return machedFiles;
    }
}

public class UnixFileFilterDemo {
    public static void main(String[] args){
        IFile file1 = new File("testfile1", 5, FileType.PDF);
        IFile file2 = new File("testfile2", 5, FileType.XML);
        IFile file3 = new File("testfile3", 5, FileType.JSON);
        IFile file5 = new File("testfile4", 5, FileType.PDF);
        IFile file4 = new File("testfile5", 5, FileType.XML);
        Directory directory =  new Directory("level1");
        Directory directory2 = new Directory("level2");
        FileSystemManager fileSystemManager = new FileSystemManager();
        directory.addFile(file1);
        directory2.addFile(file2);
        directory2.addFile(file3);
        directory2.addFile(file5);
        directory.addFile(directory2);
        directory.addFile(file4);
        fileSystemManager.addFile(directory);
        FilterInputs inputs1 = new FilterInputs("testfile1", 0, null);
        FilterInputs inputs2 = new FilterInputs("testfile2", 0, FileType.PDF);
        List<FilterInputs> inputs = new ArrayList<>();
        inputs.add(inputs1);
        inputs.add(inputs2);
        List<IFile> matchedFiles = fileSystemManager.filterFiles(directory, new NameFilter(), inputs2);
        for (IFile iFile : matchedFiles) {
            System.out.println(((File)iFile).getFilename());
        }
        System.out.println("------------------");
        matchedFiles = fileSystemManager.filterFiles(directory, new ANDFilter(), inputs);
        for (IFile iFile : matchedFiles) {
            System.out.println(((File)iFile).getFilename());
        }
        System.out.println("------------------");
        matchedFiles = fileSystemManager.filterFiles(directory, new ORFilter(), inputs);
        for (IFile iFile : matchedFiles) {
            System.out.println(((File)iFile).getFilename());
        }
    }
    
}
