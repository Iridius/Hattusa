package model;

public interface ITreeElement {
    String getKey();
    String getName();
    ITreeElement getParent();
}
