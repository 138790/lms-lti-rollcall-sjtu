package com.lmsltirollcallsjtu.common.utils;

import com.lmsltirollcallsjtu.common.bean.bo.UsersCombine;
import com.lmsltirollcallsjtu.common.enums.SignInStateEnum;

import java.util.List;

public class CombineUtil {

    public static UsersCombine combineStates(List<UsersCombine> usersCombineTemp) {

        UsersCombine usersCombine=UsersCombine.builder().courseCode(usersCombineTemp.get(0).getCourseCode())
                .sectionName(usersCombineTemp.get(0).getSectionName())
                .createdBy(usersCombineTemp.get(0).getUserCode().toString())
                .sectionListJsonStr(usersCombineTemp.get(0).getSectionListJsonStr())
                .totalStudents(usersCombineTemp.get(0).getTotalStudents())
                .userCode(usersCombineTemp.get(0).getUserCode())
                .userName(usersCombineTemp.get(0).getUserName())
                .expAttendancesCount(usersCombineTemp.get(0).getExpAttendancesCount())
                .build();
        if (usersCombineTemp.size() == 2){
            if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.NORMAL.getCode()) &&
                    usersCombineTemp.get(1).getState().equals(SignInStateEnum.NORMAL.getCode())) {
                usersCombine.setState(SignInStateEnum.NORMAL.getCode());
            }else if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.NORMAL.getCode()) &&
                    usersCombineTemp.get(1).getState().equals(SignInStateEnum.UNNORMAL.getCode())) {
                usersCombine.setState(SignInStateEnum.UNNORMAL.getCode());
            }else if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.UNNORMAL.getCode()) &&
                    usersCombineTemp.get(1).getState().equals(SignInStateEnum.NORMAL.getCode())){
                usersCombine.setState(SignInStateEnum.UNNORMAL.getCode());
            }else if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.UNNORMAL.getCode()) &&
                    usersCombineTemp.get(1).getState().equals(SignInStateEnum.UNNORMAL.getCode())){
                usersCombine.setState(SignInStateEnum.UNNORMAL.getCode());
            }else{
                usersCombine.setState(SignInStateEnum.ETC.getCode());
            }
        }
        if (usersCombineTemp.size() >= 3){
            if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.NORMAL.getCode()) &&
                    usersCombineTemp.get(2).getState().equals(SignInStateEnum.NORMAL.getCode())) {
                usersCombine.setState(SignInStateEnum.NORMAL.getCode());
            }else if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.NORMAL.getCode()) &&
                    usersCombineTemp.get(2).getState().equals(SignInStateEnum.UNNORMAL.getCode())) {
                usersCombine.setState(SignInStateEnum.UNNORMAL.getCode());
            }else if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.UNNORMAL.getCode()) &&
                    usersCombineTemp.get(2).getState().equals(SignInStateEnum.NORMAL.getCode())){
                usersCombine.setState(SignInStateEnum.UNNORMAL.getCode());
            }else if (usersCombineTemp.get(0).getState().equals(SignInStateEnum.UNNORMAL.getCode()) &&
                    usersCombineTemp.get(2).getState().equals(SignInStateEnum.UNNORMAL.getCode())){
                usersCombine.setState(SignInStateEnum.UNNORMAL.getCode());
            }else{
                usersCombine.setState(SignInStateEnum.ETC.getCode());
            }
        }
        
        return usersCombine;
    }
}
