package com.mybatis.generator.model;

public class attrSpe {
    private String attrSpecCode;

    private String attrSpecName;

    private String attrSpecDesc;

    private String attrSpecType;

    public String getAttrSpecCode() {
        return attrSpecCode;
    }

    public void setAttrSpecCode(String attrSpecCode) {
        this.attrSpecCode = attrSpecCode == null ? null : attrSpecCode.trim();
    }

    public String getAttrSpecName() {
        return attrSpecName;
    }

    public void setAttrSpecName(String attrSpecName) {
        this.attrSpecName = attrSpecName == null ? null : attrSpecName.trim();
    }

    public String getAttrSpecDesc() {
        return attrSpecDesc;
    }

    public void setAttrSpecDesc(String attrSpecDesc) {
        this.attrSpecDesc = attrSpecDesc == null ? null : attrSpecDesc.trim();
    }

    public String getAttrSpecType() {
        return attrSpecType;
    }

    public void setAttrSpecType(String attrSpecType) {
        this.attrSpecType = attrSpecType == null ? null : attrSpecType.trim();
    }
}