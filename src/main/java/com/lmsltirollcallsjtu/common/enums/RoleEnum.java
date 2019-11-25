package com.lmsltirollcallsjtu.common.enums;

import lombok.Getter;

public enum RoleEnum {

    TEACHER("TeacherEnrollment","Instructor","教师"),
    TEACHINGASSISTANT("TaEnrollment","urn:lti:role:ims/lis/TeachingAssistant","助教"),
    STUDENT("StudentEnrollment","","学生");

    @Getter
    private String roleCode;
    @Getter
    private String keywordOfLTI;
    @Getter
    private String description;

    RoleEnum(String roleCode , String keywordOfLTI, String description){

        this.roleCode = roleCode;
        this.keywordOfLTI = keywordOfLTI;
        this.description = description;
    }

    public static RoleEnum getRoleEnumByKeywordOfLTI(String keywordOfLTI){
        for(RoleEnum roleEnum : values()){
            if (roleEnum.getKeywordOfLTI().equals(keywordOfLTI)) {
                return roleEnum;
            }
        }
        return null;
    }

}
