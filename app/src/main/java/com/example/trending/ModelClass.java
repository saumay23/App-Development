package com.example.trending;

public class ModelClass
{
    private String Name;
    private String Desc;
    private String display_pic;
    private String content;
    private String starcount;
    private String forkcount;
    private String langcolor;
    private String language;
    private boolean expanded;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getDisplay_pic() {
        return display_pic;
    }

    public void setDisplay_pic(String display_pic) {
        this.display_pic = display_pic;
    }

    ModelClass()
    {
    }

    @Override
    public String toString() {
        return "ModelClass{" +
                "Name='" + Name + '\'' +
                ", Desc='" + Desc + '\'' +
                ", display_pic='" + display_pic + '\'' +
                ", content='" + content + '\'' +
                ", starcount='" + starcount + '\'' +
                ", forkcount='" + forkcount + '\'' +
                ", langcolor='" + langcolor + '\'' +
                ", language='" + language + '\'' +
                ", expanded=" + expanded +
                '}';
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    ModelClass(String Name, String Desc, String display_pic, String content, String starcount, String forkcount, String langcolor, String language)
    {
        this.Name=Name;
        this.Desc=Desc;
        this.display_pic=display_pic;
        this.content=content;
        this.forkcount=forkcount;
        this.starcount=starcount;
        this.langcolor=langcolor;
        this.language=language;
        this.expanded=false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStarcount() {
        return starcount;
    }

    public void setStarcount(String starcount) {
        this.starcount = starcount;
    }

    public String getForkcount() {
        return forkcount;
    }

    public void setForkcount(String forkcount) {
        this.forkcount = forkcount;
    }

    public String getLangcolor() {
        return langcolor;
    }

    public void setLangcolor(String langcolor) {
        this.langcolor = langcolor;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
