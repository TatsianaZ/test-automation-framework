package com.thomsonreuters.restassured.rest.model;

import lombok.Data;

@Data
public class FolderPermissions {

    private boolean addContainersAllowed;
    private boolean addItemsAllowed;
    private boolean copyAllowed;
    private boolean deleteAllowed;
    private boolean editDescriptionAllowed;
    private boolean moveAllowed;
    private boolean renameAllowed;

}
