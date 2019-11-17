package co.analysis.input.processor;

import co.analysis.exceptions.ExtensionDoesNotExist;
import co.analysis.exceptions.FileNotAllowedException;

import java.util.List;

public interface FileProcessor {
    List<String>  process(String fileName) throws ExtensionDoesNotExist, FileNotAllowedException;
}
