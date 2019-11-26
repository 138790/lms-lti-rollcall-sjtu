package com.lmsltirollcallsjtu.common.utils;

import com.lmsltirollcallsjtu.common.bean.bo.UsersCombine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CombineUtil {

    private static String combineStates(List<UsersCombine> usersCombine){
//        List<String> states=new ArrayList<String>();
//        states.add("NORMAL");
//        states.add("AMEND");
//        states.add("TRUANT");
//        states.add("LATE");
//        states.add("SICK_LEAVE");
//        states.add("PERSONAL_LEAVE");
//        states.add("ETC");
//        states.add("LEAVE_EARLY");
        List<String> stateList = usersCombine.stream().map(item -> item.getState()).collect(Collectors.toList());
//        Map<String, List<String>> map = stateList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.toList()));

        return null;
    }
}
