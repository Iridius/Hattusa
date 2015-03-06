package model;

public interface ITreeElement {
    String getName();
    String getFullName();
    ITreeElement getParent();
    boolean isRoot();
    boolean isFolder();
}