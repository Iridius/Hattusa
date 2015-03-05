package model;

public interface ITreeElement {
    String getName();
    ITreeElement getParent();
    boolean isRoot();
}