/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.bean;

import java.io.Serializable;

/**
 *
 * @author JerryKwok
 */
public class SearchBean implements Serializable{
    private String id;
    private String keyword;
    private int searchPage;
    private String staffId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSearchPage() {
        return searchPage;
    }

    public void setSearchPage(int searchPage) {
        this.searchPage = searchPage;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
    
    
}
