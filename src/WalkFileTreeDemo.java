

/*
 * Copyright [2022] [Luis Enrique Ramos García].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
 
//Java sample program to iterate through directory tree
// Uses the walkFileTree method
public class WalkFileTreeDemo {
    public static void main(String[] args) throws Exception{
        // in windows use the form c:\\folder
        String rootFolder = "C:\\Users\\User\\Documents\\luis_obsidian";
        //System.out.println(rootFolder);
        String searchFor = "md";
        walkFileTreeAndSearch(rootFolder,searchFor);
    }
 
    private static void walkFileTreeAndSearch(String rootFolder,String search) throws Exception {
        Files.walkFileTree(Paths.get(rootFolder), new MyFileVisitor(search));
    }
}
 
// There are multiple methods to override in SimpleFileVisitor
// We override only the visitFile method.
class MyFileVisitor extends SimpleFileVisitor<Path> {
    private String search;
    public MyFileVisitor(String s) {
        search = s;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if(file.toString().contains(search)) {
            System.out.println(file.toString());
        }
        return FileVisitResult.CONTINUE;
    }
}
