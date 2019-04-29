package com.thomsonreuters.restassured.rest.model;

import lombok.Data;

import java.util.List;

@Data
public class Folder {

    public String categoryId;
    private boolean isLeaf;
    private boolean isRoot;
    private boolean isShared;
    private String label;
    private String labelStyle;
    private FolderPermissions permissions;
    private String type;

    private List<Folder> children;
}
