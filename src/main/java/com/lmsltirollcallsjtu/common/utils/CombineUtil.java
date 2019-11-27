package com.lmsltirollcallsjtu.common.utils;

import com.lmsltirollcallsjtu.common.bean.bo.UsersCombine;
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
            if (usersCombineTemp.get(0).getState().equals("NORMAL") && usersCombineTemp.get(1).getState().equals("NORMAL")) {
                usersCombine.setState("NORMAL");
            }else if (usersCombineTemp.get(0).getState().equals("NORMAL") && usersCombineTemp.get(1).getState().equals("UNNORMAL")) {
                usersCombine.setState("LEAVE_EARLY");
            }else if (usersCombineTemp.get(0).getState().equals("UNNORMAL") && usersCombineTemp.get(1).getState().equals("NORMAL")){
                usersCombine.setState("LATE");
            }else if (usersCombineTemp.get(0).getState().equals("UNNORMAL") && usersCombineTemp.get(1).getState().equals("UNNORMAL")){
                usersCombine.setState("TRUANT");
            }else{
                usersCombine.setState("ETC");
            }
        }
        if (usersCombineTemp.size() >= 3){
            if (usersCombineTemp.get(0).getState().equals("NORMAL") && usersCombineTemp.get(2).getState().equals("NORMAL")) {
                usersCombine.setState("NORMAL");
            }else if (usersCombineTemp.get(0).getState().equals("NORMAL") && usersCombineTemp.get(2).getState().equals("UNNORMAL")) {
                usersCombine.setState("LEAVE_EARLY");
            }else if (usersCombineTemp.get(0).getState().equals("UNNORMAL") && usersCombineTemp.get(2).getState().equals("NORMAL")){
                usersCombine.setState("LATE");
            }else if (usersCombineTemp.get(0).getState().equals("UNNORMAL") && usersCombineTemp.get(2).getState().equals("UNNORMAL")){
                usersCombine.setState("TRUANT");
            }else{
                usersCombine.setState("ETC");
            }
        }

        return usersCombine;
    }
}
